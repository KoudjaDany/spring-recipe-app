package com.ddf.training.springrecipeapp.controllers;

import com.ddf.training.springrecipeapp.domain.Recipe;
import com.ddf.training.springrecipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

        mockMvc.perform(get("details/"))
                .andExpect(status().is4xxClientError());

        mockMvc.perform(get("detail/"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getIndexPage() {

        //given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        Recipe recipe = new Recipe();
        recipe.setId(2L);
        recipes.add(recipe);
        when(recipeService.listAll()).thenReturn(recipes);
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //When
        String viewName = indexController.getIndexPage(model);

        //Then
        assertEquals("index", viewName);
        verify(recipeService, atLeast(1)).listAll();
        verify(model, only()).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
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
        verify(recipeService, atLeast(1)).getRecipe(anyLong());
        verify(model, only()).addAttribute(eq("recipe"), any());
    }
}