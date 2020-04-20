package com.ddf.training.springrecipeapp.controllers;

import com.ddf.training.springrecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index", "/index.html", "/index.php"})
    public String getIndexPage(Model model){
        //log.debug("Some message to say... kljfs");
        model.addAttribute("recipes", recipeService.listAll());
        //log.debug("recipeService = " + recipeService.listAll());
        return "index";
    }
}
