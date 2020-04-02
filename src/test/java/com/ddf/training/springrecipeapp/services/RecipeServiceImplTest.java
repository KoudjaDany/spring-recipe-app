package com.ddf.training.springrecipeapp.services;

import com.ddf.training.springrecipeapp.converters.RecipeCommandToRecipe;
import com.ddf.training.springrecipeapp.converters.RecipeToRecipeCommand;
import com.ddf.training.springrecipeapp.domain.Recipe;
import com.ddf.training.springrecipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeToRecipeCommand, recipeCommandToRecipe);
    }

    @Test
    public void getRecipeByIdTest(){
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(1L)).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.getRecipe(1L);
        assertNotNull("Null recipe returned",recipeReturned);
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void listAll() {
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);
        recipesData.add(recipe);
        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.listAll();
        assertEquals(1, recipes.size());
        verify(recipeRepository, atMost(2)).findAll();
    }

    @Test
    public void getRecipe() {
        Recipe recipe= new Recipe();
        recipe.setId(1L);

        when(recipeRepository.findById(recipe.getId())).thenReturn(Optional.of(recipe));

        Recipe recipe1 = recipeService.getRecipe(1L);
        assertEquals(recipe1.getId(), recipe.getId());
    }
}