package com.qfedu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//ctrl + o  : 提示

public class AdminLoginInterceptor2 implements HandlerInterceptor {


//handler 处理器  就是Controller中的方法

    /**
     * handler 处理器执行前 如果返回true 继续访问你的handler，否则终止访问
     * 一般用于登录和权限校验
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String userName = (String) request.getSession().getAttribute("USERNAME");

        String uri = request.getRequestURI();

        System.out.println(uri);
        if (uri.contains("/video/")) {

        }


        return false;
    }



    /**
     * handler 处理器执行完 页面返回之前
     * 用户给页面追加一个特殊信息
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    /**
     * handler 处理器执行完 并且页面也返回给用户之后
     * 用于记录用户的日志
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
