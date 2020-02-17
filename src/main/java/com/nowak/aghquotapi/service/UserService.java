package com.nowak.aghquotapi.service;

import com.nowak.aghquotapi.entities.User;
import com.nowak.aghquotapi.repo.UserRepository;
import com.nowak.aghquotapi.requestBodies.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByUsername(String userName){
        User user = null;
        try{
           Optional<User> userOptional= Optional.ofNullable(userRepository.findByName(userName));
           if(userOptional.isPresent()) {
               user = userOptional.get();
           }
        else{
            throw new UsernameNotFoundException("User not found");
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
            else{
                throw new UsernameNotFoundException("User not found by token");
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
        userRepository.save(user);
    }


}
