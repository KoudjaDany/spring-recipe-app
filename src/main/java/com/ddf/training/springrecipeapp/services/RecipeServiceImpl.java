package com.ddf.training.springrecipeapp.services;

import com.ddf.training.springrecipeapp.domain.Recipe;
import com.ddf.training.springrecipeapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService{

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Set<Recipe> listAll(){
        Set<Recipe> recipes = new HashSet<>();
        Iterator<Recipe> recipeIterator = recipeRepository.findAll().iterator();
        recipeIterator.forEachRemaining(recipes::add);
        return recipes;
    }

    public Recipe getRecipe(Long id){
        return recipeRepository.findById(id).orElseThrow(()->new EntityNotFoundException("The recipe of id"+id+" doesn't exist."));
    }
}
