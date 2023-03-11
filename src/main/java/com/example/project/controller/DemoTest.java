package com.example.project.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/demo1")
public class DemoTest {

    @RequestMapping(value = "/test")
    public String testDemo(Model model){
        model.addAttribute("name","haha");
        return "hello";
    }
}
