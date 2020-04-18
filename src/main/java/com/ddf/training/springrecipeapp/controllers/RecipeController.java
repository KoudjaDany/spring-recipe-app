package com.ddf.training.springrecipeapp.controllers;

import com.ddf.training.springrecipeapp.commands.RecipeCommand;
import com.ddf.training.springrecipeapp.domain.Recipe;
import com.ddf.training.springrecipeapp.enums.Difficulty;
import com.ddf.training.springrecipeapp.services.CategoryService;
import com.ddf.training.springrecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("difficulties", Difficulty.values());
        return "recipe/recipe-form";
    }

    @RequestMapping("/update/{id}")
    public String updateRecipe(Model model, @PathVariable Long id) {
        model.addAttribute("recipe", recipeService.findRecipeCommandById(id));
        model.addAttribute("difficulties", Difficulty.values());
        return "recipe/recipe-form";
    }

    @PutMapping
    @PostMapping
    @RequestMapping("/save")
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand, Model model) {
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
        model.addAttribute("categories", categoryService.findAll());
        return "redirect:/recipe/details/" + savedRecipeCommand.getId();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteById(id);
        return "redirect:/index";
    }


}
