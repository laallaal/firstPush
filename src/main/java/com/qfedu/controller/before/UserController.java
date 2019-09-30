package com.qfedu.controller.before;

import com.qfedu.entry.User;
import com.qfedu.service.UserService;
import com.qfedu.utils.EmailUtil;
import com.qfedu.utils.ImageCut;
import com.qfedu.utils.InfoUtils;
import com.qfedu.utils.UUIDUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Random;

@Controller
@RequestMapping("/user")
@Api(value = "用户操作", tags = "User操作")
public class UserController {


    @Autowired
    UserService userService;


    //判断邮箱是否有效
    @RequestMapping("/validateEmail")
    @ResponseBody
    public String validateEmail(String email, Model model) {
        User user = userService.selectUserByEmail(email);

        if (user != null) {
            return "false";
        }
        return "success";

    }


    @RequestMapping("/insertUser")
    @ResponseBody
    public String insertUser(HttpSession session, User user) {

        Boolean isInsertUser = userService.insertUser(user);


        if (isInsertUser) {
            session.setAttribute("userAccount", user.getEmail());
            return "success";
        }


        return "false";
    }


    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpSession session, User user) {

        Boolean isLogin = userService.selectUserByEmailAndPassword(user);

        System.out.println("user.getEmail():::" + user.getEmail());
        if (isLogin) {
            session.setAttribute("userAccount", user.getEmail());
            return "success";
        }


        return "false";


    }


    @RequestMapping("/showMyProfile")
    public String showMyProfile(HttpSession session, Model model) {

        String email = (String) session.getAttribute("userAccount");

        System.out.println("email:" + email);

        User user = userService.selectUserByEmail(email);

        System.out.println("user:" + user);

        model.addAttribute("user", user);

        return "before/my_profile";
    }


    @RequestMapping("/changeAvatar")
    public String upLoadImage(HttpSession session, Model model) {

        String email = (String) session.getAttribute("userAccount");

        User user = userService.selectUserByEmail(email);

        session.setAttribute("imgUrl", user.getImgUrl());
        model.addAttribute("user", user);


        return "before/change_avatar";
    }

    @RequestMapping("/upLoadImage")
    public String upLoadImage(MultipartFile imageFile, HttpSession session, String x1, String x2, String y1, String y2) {


        String originalFilename = imageFile.getOriginalFilename();

        System.out.println("originalFilename:" + originalFilename);


        String newFileName = UUIDUtils.getUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

        String imgUrl = InfoUtils.getProperties("IMG_URL") + newFileName;

        try {
            imageFile.transferTo(new File(InfoUtils.getProperties("IMG_PATH"), newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        float isX1 = Float.valueOf(x1);
        float isX2 = Float.valueOf(x2);
        float isY1 = Float.valueOf(y1);
        float isY2 = Float.valueOf(y2);

        System.out.println(isX1);
        System.out.println(isX2);
        System.out.println(isY1);
        System.out.println(isY2);

        ImageCut imageCut = new ImageCut();


        imageCut.cutImage(InfoUtils.getProperties("IMG_PATH") + "/" + newFileName, (int) isX1, (int) isY1, (int) (isX2 - isX1), (int) (isY2 - isY1));

        System.out.println(imgUrl);

        String email = (String) session.getAttribute("userAccount");

        User user = new User();
        user.setEmail(email);
        user.setImgUrl(InfoUtils.getProperties("IMG_URL") + newFileName);

        userService.updateUser(user);


        return "redirect:/user/showMyProfile";
    }

    @RequestMapping("/changeProfile")
    public String changeProfile(HttpSession session, Model model) {

        String email = (String) session.getAttribute("userAccount");

        User user = userService.selectUserByEmail(email);

        model.addAttribute("user", user);


        return "before/change_profile";
    }

    @RequestMapping("/updateUser")
    public String updateUser(User user, Model model, HttpSession session) {

        System.out.println(user+"11111111111111111111111111111");

        String email = (String) session.getAttribute("userAccount");

        User userBefore = userService.selectUserByEmail(email);


        userBefore.setNickName(user.getNickName());
        userBefore.setSex(user.getSex());
        userBefore.setBirthday(user.getBirthday());
        userBefore.setAddress(user.getAddress());
        System.out.println(userBefore+"222222222222222222222222");


        userService.updateUser2(userBefore);


        model.addAttribute("user", userBefore);
        return "redirect:/user/showMyProfile";
    }

    @RequestMapping("/passwordSafe")
    public String passwordSafe(HttpSession session, Model model) {
        String email = (String) session.getAttribute("userAccount");

        User user = userService.selectUserByEmail(email);

        model.addAttribute("user", user);

        return "before/password_safe";

    }




    @RequestMapping("/updatePassword")
    public String updatePassword(String newPassword, HttpSession session, Model model) {



            String email = (String) session.getAttribute("userAccount");

            User user = userService.selectUserByEmail(email);

            user.setPassword(newPassword);

            userService.updateUserPassword(user);

            model.addAttribute("user", user);

            session.removeAttribute("userAccount");
            return "redirect:/index.jsp";



    }


    @RequestMapping("/validatePassword")
    @ResponseBody
    public String validatePassword(String password, HttpSession session) {
        System.out.println("进来没得");
        String email = (String) session.getAttribute("userAccount");
        User user = userService.selectUserByEmail(email);

        if (user.getPassword().equals(password)) {
            return "success";

        }

        return "false";

    }


    @RequestMapping("/loginOut")
    @ResponseBody
    public String loginOut(HttpSession session) {
        session.removeAttribute("userAccount");

        return "success";
    }


    @RequestMapping("/loginOut2")
    public String loginOut2(HttpSession session) {

        session.removeAttribute("userAccount");

        return "redirect:/index.jsp";
    }


    @RequestMapping("/forgetPassword")
    public String forgetPassword() {

        return "before/forget_password";
    }



    @RequestMapping("/sendEmail")
    public String sendEmail(String email, HttpSession session) {

        String num1 = String.valueOf(new Random().nextInt(10));
        String num2 = String.valueOf(new Random().nextInt(10));
        String num3 = String.valueOf(new Random().nextInt(10));
        String num4 = String.valueOf(new Random().nextInt(10));
        String num5 = String.valueOf(new Random().nextInt(10));
        String num6 = String.valueOf(new Random().nextInt(10));

        String emailNum = num1 + num2 + num3 + num4 + num5 + num6;
        EmailUtil.send(email, "验证码",emailNum);

        session.setAttribute("EMAILNUM", emailNum);

        return "success";
    }


    @RequestMapping("/validateEmailCode")
    @ResponseBody
    public String validateEmailCode( String code, HttpSession session, String email ) {

        System.out.println("1234651321619856155615651651");
        System.out.println(email);

        String emailnum = (String) session.getAttribute("EMAILNUM");

        if (emailnum.equals(code)) {

            System.out.println("2222222222222222222222222");

            session.setAttribute("EMAIL", email);
            return "success";
        }

        return "fail";


    }

    @RequestMapping("/reset_password")

    public String resetPassword( String password, HttpSession session ) {
        System.out.println(password);

        System.out.println("999999999999999999999");

        String email = (String) session.getAttribute("EMAIL");

        System.out.println(email);

        User user = userService.selectUserByEmail(email);

        user.setPassword(password);

        System.out.println("user:"+user);

        userService.updateUserPassword(user);

        session.removeAttribute("EMAIL");


        return "redirect: ../index.jsp";

    }


    @RequestMapping("/ToResetPassword")
    public String ToResetPassword() {


        return "before/reset_password";

    }


















}
