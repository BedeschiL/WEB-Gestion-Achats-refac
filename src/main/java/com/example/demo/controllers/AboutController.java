package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AboutController {
    @GetMapping("/about")
    public String about(HttpServletRequest request)
    {


        request.setAttribute("test",1515);
        return "about";
    }
}
