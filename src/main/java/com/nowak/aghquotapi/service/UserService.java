package com.nowak.aghquotapi.service;

import com.nowak.aghquotapi.entities.User;
import com.nowak.aghquotapi.repo.AuthorityRepo;
import com.nowak.aghquotapi.repo.QuotationRepo;
import com.nowak.aghquotapi.repo.UserRepository;
import com.nowak.aghquotapi.requestBodies.UserDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {

   private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthorityRepo authorityRepo;
    private final QuotationRepo quotationRepo;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthorityRepo authorityRepo, QuotationRepo quotationRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authorityRepo = authorityRepo;

        this.quotationRepo = quotationRepo;
    }
    public User findByUsername(String userName){
        User user = userRepository.findByName(userName);
        if(user==null) {
           return null;
        }
        else{
            return user;
        }
    }

    public User findByToken(String token){
        User user = null;
        try{
            Optional<User> userOptional= Optional.ofNullable(userRepository.findByName(token));
            if(userOptional.isPresent()) {
                user = userOptional.get();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return  user;
    }
    public void registerUser(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setUsersAuthorities(Arrays.asList(authorityRepo.findByAuthority("ROLE_USER")));
        userRepository.save(user);
    }
    public void addToken(String username, String token){
        quotationRepo.updateToken(username, token);
    }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User userToLoad = userRepository.findByName(userName);
        if(userToLoad!=null){
            return  com.nowak.aghquotapi.service.UserDetails.buildUser(userToLoad);
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }

    }
}
