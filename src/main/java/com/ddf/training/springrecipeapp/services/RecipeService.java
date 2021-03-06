package com.ddf.training.springrecipeapp.services;

import com.ddf.training.springrecipeapp.commands.RecipeCommand;
import com.ddf.training.springrecipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> listAll();
    Recipe getRecipe(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
    RecipeCommand findRecipeCommandById(Long id);

    void deleteById(Long id);
}
