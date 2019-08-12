package com.example.demo.Controller;


import com.example.demo.server.UserImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginServerController {
    @Autowired

    UserImpl userDAO;

    @RequestMapping("/checklogin")
    public String login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
        String role = userDAO.findUserByUsername(username).getRoles();
        if ("user".equals(role)) {
            return "欢迎登陆";
        }
        if ("admin".equals(role)) {
            return "欢迎来到管理员页面";
        }
        return "权限错误！";
    }
    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public String notLogin() {
        return "您尚未登陆！";
    }

    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    public String notRole() {
        return"您没有权限！";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return "成功注销！";
    }

}
