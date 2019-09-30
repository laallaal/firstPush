package com.qfedu.controller.before;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController {

    @RequestMapping("/*")
    public String myException() {

        return "404";
    }


}
