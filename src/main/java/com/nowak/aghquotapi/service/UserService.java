package com.nowak.aghquotapi.service;

import com.nowak.aghquotapi.entities.User;
import com.nowak.aghquotapi.repo.AuthorityRepo;
import com.nowak.aghquotapi.repo.UserRepository;
import com.nowak.aghquotapi.requestBodies.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthorityRepo authorityRepo;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, AuthorityRepo authorityRepo) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepo = authorityRepo;

    }

    public User findByUsername(String userName){
        User user = null;
        try{
           Optional<User> userOptional= Optional.ofNullable(userRepository.findByName(userName));
           if(userOptional.isPresent()) {
               user = userOptional.get();
           }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return  user;
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

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User userToLoad = userRepository.findByName(userName);
        if(userToLoad!=null){
            System.out.println(userToLoad.getName());
            return new org.springframework.security.core.userdetails
                    .User(userToLoad.getName(), userToLoad.getPassword(),
                    true,true,true,
                    true,userToLoad.getAuthorities());
        }
        else {
            return (UserDetails) new UsernameNotFoundException(HttpStatus.NOT_FOUND.toString());
        }
    }
}
