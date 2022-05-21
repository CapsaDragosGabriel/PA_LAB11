package com.example.demo.dao;


import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Repository("fakeDao")
public class FakeUserDataAccessService implements UserDAO {
    private static List<User> DB = new ArrayList<>();

    @Override
    public int insertUser(Long id, User user) {
        DB.add(new User(id, user.getName()));
        return 1;
    }

    @Override
    public List<User> selectAllUsers() {
        return DB;
    }

    @Override
    public Optional<User> selectUserById(Long id) {

        return DB.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    @Override
    public int deleteUserById(Long id) {
        Optional<User> userPossible = selectUserById(id);
        if (userPossible.isEmpty()) {
            return 0;
        }
        DB.remove(userPossible.get());
        return 1;
    }

    @Override
    public int updateUserById(Long id, User user) {
        return selectUserById(id).map(u -> {
            int indexOfUserToUpdate = DB.indexOf(u);
            if (indexOfUserToUpdate >= 0) {
                DB.set(indexOfUserToUpdate, new User(id, user.getName()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }

    /**
     * to fix
     */



    @Override
    public int addFriend(Long id, Long id2) {
        User tempUser;
        User tempUser2;
        tempUser = DB.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
        tempUser2 = DB.stream().filter(user -> user.getId().equals(id2)).findFirst().orElse(null);
        if (tempUser != null && tempUser2 != null)
            tempUser.addFriend(tempUser2);
        return 0;
    }
}
