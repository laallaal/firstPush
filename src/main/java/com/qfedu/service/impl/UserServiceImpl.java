package com.qfedu.service.impl;


import com.qfedu.entry.User;
import com.qfedu.mapper.UserMapper;
import com.qfedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;

    @Override
    public User selectUserByEmail(String email) {

        User user = mapper.selectUserByEmail(email);

        System.out.println(user);

        return user;

    }

    @Override
    public Boolean insertUser(User user) {

        int i = mapper.insertIntoUser(user);


        return i > 0 ? true : false;
    }

    @Override
    public Boolean selectUserByEmailAndPassword(User user) {

        User user1 = mapper.selectUserByEmailAndPassword(user);

        return user1 != null ? true :false;
    }

    @Override
    public int updateUser(User user) {

        int rows = mapper.updateUser(user);
        return rows;
    }

    @Override
    public void updateUser2(User user) {

        mapper.updateUser2(user);

    }

    @Override
    public void updateUserPassword(User user) {

        mapper.updateUserPassword(user);

    }


}
