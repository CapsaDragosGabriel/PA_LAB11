package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

       return userRepository.findAll().stream().toList();
    }
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
    public Optional<List<String>> getUserFriends(Long id){
      User tempUser=new User();
      tempUser= userRepository.findById(id).orElse(null);
        assert tempUser != null;
        return Optional.ofNullable(tempUser.getFriendsNames());
    }

    public List<User> getMostPopular(Long count) {
        List<User> popularUsers = new ArrayList<>();
        Long usersLeft = count;
        List<User> DB = new ArrayList<>();
            DB=userRepository.findAll();
        while (usersLeft-- > 0 && !DB.isEmpty()) {
            User maxFriends = DB
                    .stream()
                    .max(Comparator.comparing(User::getFriendsCount))
                    .orElseThrow(NoSuchElementException::new);
            DB.remove(maxFriends);
            popularUsers.add(maxFriends);
        }
        return popularUsers;
    }

    public void deleteUser(Long id){
        userRepository.delete(userRepository.findById(id).get());
    }

    public void updateUser(Long id,User user){
        user.setId(id);
        userRepository.save(user);
    }

    public void addUserFriend(Long  id,Long id2) {
        if(!Objects.equals(id, id2)) {
            User tempUser = new User();
            User tempUser2 = new User();
            tempUser = userRepository.findById(id).orElse(null);
            tempUser2 = userRepository.findById(id2).orElse(null);
            assert tempUser != null;
            tempUser.addFriend(tempUser2);
            assert tempUser2 != null;
            tempUser2.addFriend(tempUser);
            userRepository.save(tempUser);
            userRepository.save(tempUser2);
        }
    }
}
