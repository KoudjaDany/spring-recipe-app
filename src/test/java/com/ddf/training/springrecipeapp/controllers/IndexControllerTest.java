package com.ddf.training.springrecipeapp.controllers;

import com.ddf.training.springrecipeapp.domain.Recipe;
import com.ddf.training.springrecipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() {
        Set<Recipe> recipes = new HashSet<>();
        when(recipeService.listAll()).thenReturn(recipes);

        //Test that the returned page is index
        assertEquals("index", indexController.getIndexPage(model));

        verify(model, only()).addAttribute("recipes", recipeService.listAll());
    }

    @Test
    public void getDetailPage() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        when(recipeService.getRecipe(1L)).thenReturn(recipe);

        //Test for getting an existing recipe
        assertEquals("recipe-detail", indexController.getDetailPage(model, recipe.getId()));

        //Test for asking for details without mention the id of the recipe to return. We stay on the index page
        assertEquals("index", indexController.getDetailPage(model, null));

        //Test for asking for a recipe which doesn't exist
        assertEquals("error-page", indexController.getDetailPage(model, 0L));

        //Verifications
        verify(model, only()).addAttribute("recipe", recipeService.getRecipe(1L));
    }
}