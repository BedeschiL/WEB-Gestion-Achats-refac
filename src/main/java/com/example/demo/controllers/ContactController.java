package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ContactController {
    @GetMapping("/contact")
    public String home(HttpServletRequest request, ModelMap modelMap)
    {

        return "contact";


    }
}
