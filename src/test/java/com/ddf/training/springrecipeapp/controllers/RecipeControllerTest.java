package com.ddf.training.springrecipeapp.controllers;

import com.ddf.training.springrecipeapp.domain.Recipe;
import com.ddf.training.springrecipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    private RecipeController recipeController;

    @Mock
    private RecipeService recipeService;

    @Mock
    Model model;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
    }

    @Test
    public void getDetailPage() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        when(recipeService.getRecipe(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/details/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipe-details"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void getDetailPage1() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        when(recipeService.getRecipe(1L)).thenReturn(recipe);

        //Test for getting an existing recipe
        assertEquals("recipe/recipe-details", recipeController.getDetailPage(model, recipe.getId()));

        //Test for asking for details without mention the id of the recipe to return. We stay on the index page
        assertEquals("index", recipeController.getDetailPage(model, null));

        //Test for asking for a recipe which doesn't exist
        assertEquals("error-page", recipeController.getDetailPage(model, 0L));

        //Verifications
        verify(recipeService, atLeast(1)).getRecipe(anyLong());
        verify(model, only()).addAttribute(eq("recipe"), any());
    }
}