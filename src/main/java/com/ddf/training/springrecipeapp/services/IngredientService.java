package com.ddf.training.springrecipeapp.services;

import com.ddf.training.springrecipeapp.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findRecipeIdAndIngredientById(Long recipeId, Long ingredientId);
}
