package com.ddf.training.springrecipeapp.controllers;

import com.ddf.training.springrecipeapp.commands.RecipeCommand;
import com.ddf.training.springrecipeapp.domain.Recipe;
import com.ddf.training.springrecipeapp.exceptions.NotFoundException;
import com.ddf.training.springrecipeapp.services.CategoryService;
import com.ddf.training.springrecipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    private RecipeController recipeController;

    @Mock
    private RecipeService recipeService;

    @Mock
    private CategoryService categoryService;

    @Mock
    Model model;

    MockMvc mockMvc;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService, categoryService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
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

    @Test
    public void getRecipeNotFound() throws Exception {
        //Given
        when(recipeService.getRecipe(anyLong())).thenThrow(NotFoundException.class);

        //When
        mockMvc.perform(get("/recipe/details/1"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
        //Then
    }

    @Test
    public void getRecipeNumberFormatException() throws Exception {
        //Given
        //When
        mockMvc.perform(get("/recipe/details/toto"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
        //Then
    }

    @Test
    public void saveRecipe() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        recipeCommand.setDescription("recipe description");


        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);

        mockMvc.perform(post("/recipe/save", recipeCommand)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "recipe description")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/details/1"));

        verify(recipeService, only()).saveRecipeCommand(any());
    }

    @Test
    public void updateRecipe() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        recipeCommand.setDescription("recipe description");

        Recipe recipe = new Recipe();
        recipe.setId(1L);


        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipe-form"))
                .andExpect(model().attributeExists("recipe"));

        verify(recipeService, only()).findRecipeCommandById(anyLong());
    }

    @Test
    public void deleteRecipe() throws Exception {
        //Given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        //When
        mockMvc.perform(get("/recipe/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));
        //Then
        verify(recipeService, only()).deleteById(anyLong());
    }


}