package com.ddf.training.springrecipeapp.controllers;

import com.ddf.training.springrecipeapp.services.IngredientService;
import com.ddf.training.springrecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/recipe/{recipeId}/ingredients")
public class IngredientController {

    private final RecipeService recipeService;

    private final IngredientService ingredientService;


    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @RequestMapping({"/", ""})
    public String getIngredientList(@PathVariable Long recipeId, Model model) {
        log.debug("Getting ingredient list for recipe id: " + recipeId);
        model.addAttribute("recipe", recipeService.findRecipeCommandById(recipeId));
        return "recipe/ingredients/list";
    }

    @RequestMapping("/details/{ingredientId}")
    public String showIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId, Model model) {
        model.addAttribute("ingredient", ingredientService.findRecipeIdAndIngredientById(recipeId, ingredientId));
        model.addAttribute("recipe", recipeService.findRecipeCommandById(recipeId));
        return "recipe/ingredients/show-ingredient";
    }


}
