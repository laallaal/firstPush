package com.qfedu.utils;


import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;


public class EmailUtil {

    public static boolean send(String to,String subject,String msg){

        Properties props = new Properties();
        //邮件传输的协议
        props.put("mail.transport.protocol", "smtp");
        //连接的邮件服务器
        props.put("mail.host","smtp.qq.com");
        //发送人
        props.put("mail.from","1505363731@qq.com");

        //第一步：创建session
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        try {
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.ssl.socketFactory", "true");
            //第二步：获取邮件传输对象
            Transport ts= session.getTransport();
            //连接邮件服务器
            ts.connect("1505363731@qq.com", "pjsgtbdkttujbahb");
            //第三步：创建邮件消息体
            MimeMessage message = new MimeMessage(session);
            //设置邮件的内容
            message.setSubject(subject);
            //设置邮件的内容
            message.setContent(msg,"text/html;charset=utf-8");
            //第四步：设置发送昵称
            String nick="";
            nick = javax.mail.internet.MimeUtility.encodeText("Miss.ss");
            message.setFrom(new InternetAddress(nick+"'<1505363731@qq.com>'"));
            //第五步：设置接收人信息
            ts.sendMessage(message, InternetAddress.parse(to));

        } catch (Exception ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }

        return false;

    }

    public static void main(String[] args) {
        EmailUtil.send("602203772@qq.com", "验证码","775800");
    }

}
