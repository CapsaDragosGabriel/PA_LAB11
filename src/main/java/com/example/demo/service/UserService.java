package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService  {
     private  final UserRepository userRepository;
    @Autowired
    public UserService(@Qualifier("postgres") UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    public void addUser(User user){
        userRepository.save(user);
    }
    public List<User> getAllUsers(){
       return userRepository.findAll();
    }
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
    public void deleteUser(Long id){
        userRepository.delete(userRepository.findById(id).get());
    }

    public void updateUser(Long id,User user){
        user.setId(id);
        userRepository.save(user);
    }
}
