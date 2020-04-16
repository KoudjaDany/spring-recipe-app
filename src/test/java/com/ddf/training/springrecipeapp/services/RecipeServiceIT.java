package com.ddf.training.springrecipeapp.services;

import com.ddf.training.springrecipeapp.commands.RecipeCommand;
import com.ddf.training.springrecipeapp.converters.RecipeCommandToRecipe;
import com.ddf.training.springrecipeapp.converters.RecipeToRecipeCommand;
import com.ddf.training.springrecipeapp.domain.Recipe;
import com.ddf.training.springrecipeapp.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    private static final String DESCRIPTION = "New description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;


    @Test
    @Transactional
    public void saveRecipeCommand() {
        //Given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        //When
        testRecipeCommand.setDescription(DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        //Then
        assertNotNull(savedRecipeCommand);
        assertEquals(DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
    }

    @Test
    @Transactional
    public void deleteById() {
        //Given
        Set<Recipe> recipes = new HashSet<>((Collection<? extends Recipe>) recipeRepository.findAll());
        int length = recipes.size();
        Recipe testRecipe = recipes.iterator().next();
        long id = testRecipe.getId();

        //When
        recipeService.deleteById(id);

        //Then
        assertEquals(Optional.empty(), recipeRepository.findById(id));
        Set<Recipe> recipes2 = new HashSet<>((Collection<? extends Recipe>) recipeRepository.findAll());
        assertEquals(length - 1, recipes2.size());
    }
}