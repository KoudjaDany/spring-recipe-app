package com.ddf.training.springrecipeapp.controllers;

import com.ddf.training.springrecipeapp.commands.RecipeCommand;
import com.ddf.training.springrecipeapp.domain.Recipe;
import com.ddf.training.springrecipeapp.services.CategoryService;
import com.ddf.training.springrecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public RecipeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
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
            return "recipe/recipe-details";
        }
        return "index";
    }

    @RequestMapping("/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipe-form";
    }

    @PostMapping
    @RequestMapping("/save")
    public String saveRecipe(@ModelAttribute RecipeCommand recipeCommand, Model model) {
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
        model.addAttribute("categories", categoryService.findAll());
        return "redirect:/recipe/details/" + savedRecipeCommand.getId();
    }
}
