package com.ddf.training.springrecipeapp.controllers;

import com.ddf.training.springrecipeapp.commands.IngredientCommand;
import com.ddf.training.springrecipeapp.commands.UnitOfMeasureCommand;
import com.ddf.training.springrecipeapp.services.IngredientService;
import com.ddf.training.springrecipeapp.services.RecipeService;
import com.ddf.training.springrecipeapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/recipe/{recipeId}/ingredients")
public class IngredientController {

    private final RecipeService recipeService;

    private final IngredientService ingredientService;

    private final UnitOfMeasureService unitOfMeasureService;


    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
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


    @PostMapping
    @PutMapping
    @RequestMapping("/save")
    public String saveOrUpdateIngredient(@ModelAttribute IngredientCommand ingredientCommand) {
        ingredientService.saveIngredientCommand(ingredientCommand);
        return "redirect:/recipe/" + ingredientCommand.getRecipeId() + "/ingredients/details/" + ingredientCommand.getId();
    }

    @RequestMapping("/add")
    public String addIngredient(@PathVariable Long recipeId, Model model) {
        Set<UnitOfMeasureCommand> allUoms = unitOfMeasureService.findAllUoms();
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setUom(allUoms.iterator().next());
        model.addAttribute("ingredient", ingredientCommand);
        model.addAttribute("recipe", recipeService.findRecipeCommandById(recipeId));
        model.addAttribute("uomList", allUoms);
        return "recipe/ingredients/ingredient-form";
    }

    @RequestMapping("/update/{ingredientId}")
    public String updateIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId, Model model) {
        model.addAttribute("ingredient", ingredientService.findRecipeIdAndIngredientById(recipeId, ingredientId));
        model.addAttribute("recipe", recipeService.findRecipeCommandById(recipeId));
        model.addAttribute("uomList", unitOfMeasureService.findAllUoms());
        return "recipe/ingredients/ingredient-form";
    }

    @DeleteMapping
    @RequestMapping("/delete/{ingredientId}")
    public String deleteIngredient(@PathVariable Long ingredientId, @PathVariable Long recipeId) {
        ingredientService.deleteIngredient(ingredientId);
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }


}
