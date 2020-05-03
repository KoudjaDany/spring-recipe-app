package com.ddf.training.springrecipeapp.controllers;

import com.ddf.training.springrecipeapp.commands.IngredientCommand;
import com.ddf.training.springrecipeapp.commands.RecipeCommand;
import com.ddf.training.springrecipeapp.domain.Recipe;
import com.ddf.training.springrecipeapp.enums.Difficulty;
import com.ddf.training.springrecipeapp.exceptions.NotFoundException;
import com.ddf.training.springrecipeapp.services.CategoryService;
import com.ddf.training.springrecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;
    private final CategoryService categoryService;

    Set<IngredientCommand> ingredients = new HashSet<>();

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
    public String newRecipe(Model model, @ModelAttribute(name = "recipe") RecipeCommand recipe, @ModelAttribute IngredientCommand ingredientCommand) {

        if (Objects.nonNull(ingredientCommand) && ingredientCommand.isNotEmpty()) {
            ingredients.add(ingredientCommand);
        }
        recipe.setIngredients(ingredients);
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
    public String saveOrUpdateRecipe(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult bindingResult, Model model) {
        recipeCommand.setIngredients(ingredients);
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> log.error(error.toString()));
            model.addAttribute("categories", categoryService.findAll());
            return "recipe/recipe-form";
        }
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
        ingredients = Collections.emptySet();
        return "redirect:/recipe/details/" + savedRecipeCommand.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteById(id);
        return "redirect:/index";
    }

    @RequestMapping(value = {"/{id}"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<RecipeCommand> getDetail(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.findRecipeCommandById(id));
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
        log.error("Handling Not Found Exception.");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }

}
