package com.nowak.aghquotapi.service;

import com.nowak.aghquotapi.JWTGenerator;
import com.nowak.aghquotapi.entities.User;
import com.nowak.aghquotapi.repo.AuthorityRepo;
import com.nowak.aghquotapi.repo.UserRepository;
import com.nowak.aghquotapi.requestBodies.UserData;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthorityRepo authorityRepo;
    private final JWTGenerator jwtGenerator;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, AuthorityRepo authorityRepo, JWTGenerator jwtGenerator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepo = authorityRepo;
        this.jwtGenerator = jwtGenerator;
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
    public void registerUser(UserData userData){
        User user = new User();
        user.setName(userData.getName());
        user.setPassword(passwordEncoder.encode(userData.getPassword()));
        user.setName(userData.getEmail());
        user.setUsersAuthorities(Arrays.asList(authorityRepo.findByAuthority("ROLE_USER")));
        user.setToken(jwtGenerator.generateJWTtoken(userData));
        userRepository.save(user);
    }
}
