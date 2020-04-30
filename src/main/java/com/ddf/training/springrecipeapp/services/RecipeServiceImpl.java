package com.ddf.training.springrecipeapp.services;

import com.ddf.training.springrecipeapp.commands.RecipeCommand;
import com.ddf.training.springrecipeapp.converters.RecipeCommandToRecipe;
import com.ddf.training.springrecipeapp.converters.RecipeToRecipeCommand;
import com.ddf.training.springrecipeapp.domain.Recipe;
import com.ddf.training.springrecipeapp.exceptions.NotFoundException;
import com.ddf.training.springrecipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final RecipeCommandToRecipe recipeCommandToRecipe;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand, RecipeCommandToRecipe recipeCommandToRecipe) {
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
    }

    public Set<Recipe> listAll(){
        //log.debug("I'm in the service to return a list of all recipes.");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll();
        Iterator<Recipe> recipeIterator = recipeRepository.findAll().iterator();
        recipeIterator.forEachRemaining(recipes::add);
        return recipes;
    }

    public Recipe getRecipe(Long id) throws NotFoundException {
        return recipeRepository.findById(id).orElseThrow(() -> new NotFoundException("Recipe of id " + id + " not found."));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);
        Recipe recipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + recipe.getId());
        return recipeToRecipeCommand.convert(recipe);
    }

    @Override
    @Transactional
    public RecipeCommand findRecipeCommandById(Long id) {
        return recipeToRecipeCommand.convert(getRecipe(id));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }


    @Scheduled(cron = "0 * 15 * * ?")
    public void cronJobSch(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println("Java cron job expression:: " + strDate);
    }
}
