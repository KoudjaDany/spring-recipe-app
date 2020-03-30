package com.ddf.training.springrecipeapp.controllers;

import com.ddf.training.springrecipeapp.domain.Recipe;
import com.ddf.training.springrecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/details/{id}"})
    public String getDetailPage(Model model, @PathVariable Long id){
        System.out.println("Some message to say... kljfs");
        if (id != null){
            Recipe recipe = recipeService.getRecipe(id);
            if (recipe == null){
                return "error-page";
            }
            model.addAttribute("recipe", recipe);
            return "recipe/recipe-detail";
        }
        return "index";
    }
}
