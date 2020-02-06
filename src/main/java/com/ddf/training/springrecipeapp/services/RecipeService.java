package com.ddf.training.springrecipeapp.services;

import com.ddf.training.springrecipeapp.domain.Recipe;
import com.ddf.training.springrecipeapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Set<Recipe> listAll(){
        Set<Recipe> recipes = new HashSet<>();
        if (recipeRepository.findAll().iterator().hasNext()) {
            recipes.add(recipeRepository.findAll().iterator().next());
        }
        return recipes;
    }
}
