package com.example.demo.dao;

import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public interface UserDAO {
    int insertUser (Long id, User user);
    default  int insertUser(User user){
        Long id=new Random().nextLong(0,1000);
        return insertUser(id,user);
    }
    List<User> selectAllUsers();
    Optional<User> selectUserById(Long id);
    int deleteUserById(Long id);
    int updateUserById(Long id,User user);

}
