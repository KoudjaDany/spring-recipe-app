package com.ddf.training.springrecipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index", "/index.html", "/index.php"})
    public String getIndexPage(){
        System.out.println("Some message to say... kljfs");
        return "index";
    }
}
