package com.ddf.training.springrecipeapp.controllers;

import com.ddf.training.springrecipeapp.commands.IngredientCommand;
import com.ddf.training.springrecipeapp.commands.RecipeCommand;
import com.ddf.training.springrecipeapp.services.IngredientService;
import com.ddf.training.springrecipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

    IngredientController ingredientController;

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientController = new IngredientController(recipeService, ingredientService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    public void getIngredientList() throws Exception {

        //Given
        RecipeCommand recipeCommand = new RecipeCommand();
        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);

        //When
        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredients/list"))
                .andExpect(model().attributeExists("recipe"));
        //Then
        verify(recipeService, only()).findRecipeCommandById(anyLong());
    }

    @Test
    public void showIngredient() throws Exception {
        //Given
        IngredientCommand ingredientCommand = new IngredientCommand();
        when(ingredientService.findRecipeIdAndIngredientById(anyLong(), anyLong())).thenReturn(ingredientCommand);

        //When
        mockMvc.perform(get("/recipe/1/ingredients/details/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredients/show-ingredient"))
                .andExpect(model().attributeExists("ingredient"));
        //Then
        verify(ingredientService, only()).findRecipeIdAndIngredientById(anyLong(), anyLong());
    }
}