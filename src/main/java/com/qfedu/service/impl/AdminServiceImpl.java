package com.qfedu.service.impl;

import com.qfedu.entry.Admin;
import com.qfedu.mapper.AdminMapper;
import com.qfedu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper mapper;

    @Override
    public Boolean isLogin(String userName, String password) {


        Admin admin = new Admin();
        admin.setUserName(userName);
        admin.setPassword(password);

        int i = mapper.selectAdminByUserNameAndPassword(admin);

        System.out.println("i:"+i);

        return i > 0 ? true : false;
    }
}
