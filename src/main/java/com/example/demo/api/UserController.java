package com.example.demo.api;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/user")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@Validated @NotNull @RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping(path="{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id).orElse(null);
    }

    @GetMapping(path="{id}/friends")
    public Optional<List<String>> getUserFriends(@PathVariable("id") Long id) {
        return userService.getUserFriends(id);
    }

    @GetMapping(path="top/{no}")
    public Optional<List<User>> getMostPopular(@PathVariable("no") Long no) {
        return Optional.ofNullable(userService.getMostPopular(no));
    }
    @PostMapping(path="{id}/friend/{id2}")
    public void addUserFriend(@PathVariable("id") Long id,@PathVariable("id2") Long id2) {
        userService.addUserFriend(id,id2);
    }

    @DeleteMapping(path="{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }
    @PutMapping(path="{id}")
    public void updateUser(@PathVariable("id")Long id,@Validated @NotNull @RequestBody User user){
        userService.updateUser(id,user);
    }
}
