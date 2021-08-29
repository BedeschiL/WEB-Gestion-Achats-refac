package com.example.demo.controllers;

import com.example.demo.Models.Item;
import com.example.demo.Repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Set;


@Controller
public class IndexController {
    @Autowired
    ItemRepository ItemRepository;

    @GetMapping("/")
    public String home(HttpServletRequest request, ModelMap modelMap)
    {

        modelMap.addAttribute("items", ItemRepository.findAll());

        return "index";
    }
}
