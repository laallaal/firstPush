package com.qfedu.service;

import com.qfedu.entry.User;

public interface UserService {
    User selectUserByEmail(String email);

    Boolean insertUser(User user);

    Boolean selectUserByEmailAndPassword(User user);

    int updateUser(User user);

    void updateUser2(User user);


    void updateUserPassword(User user);
}
