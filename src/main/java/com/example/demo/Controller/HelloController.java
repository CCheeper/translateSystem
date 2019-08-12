package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @RequestMapping("/view")
    public String show(){
        return "index";
    }
    @RequestMapping("/login")
    public String loginpage(){
        return "login";
    }
    @RequestMapping("/testuser")
    @ResponseBody
    public String testuser(){
        return "testuser";
    }
    @RequestMapping("/testadmin")
    @ResponseBody
    public String testadmin(){
        return "testadmin";
    }
}