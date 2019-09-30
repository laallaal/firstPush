package com.qfedu.controller.after;

import com.qfedu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    //从spring容器中获取一个AdminService的实现类赋值给该变量

    @Autowired
    AdminService adminService;

    @RequestMapping("/showLogin")
    public String showLogin(HttpSession session) {

        //int a = 10 / 0;

        Object adminname = session.getAttribute("ADMINNAME");
        System.out.println(adminname);

        session.setAttribute("ADMINNAME","");

        return "/behind/login";

    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(String userName, String password, HttpSession session) {

        System.out.println(userName+password);

        if (adminService.isLogin(userName,password)) {

            session.setAttribute("ADMINUSER", userName);
            return "success";
        } else {
            System.out.println("jinlaicuowu");
            return "false";
        }


    }



}
