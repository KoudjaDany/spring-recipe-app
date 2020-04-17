package com.ddf.training.springrecipeapp.controllers;

import com.ddf.training.springrecipeapp.commands.IngredientCommand;
import com.ddf.training.springrecipeapp.commands.RecipeCommand;
import com.ddf.training.springrecipeapp.commands.UnitOfMeasureCommand;
import com.ddf.training.springrecipeapp.services.IngredientService;
import com.ddf.training.springrecipeapp.services.RecipeService;
import com.ddf.training.springrecipeapp.services.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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

    @Mock
    private UnitOfMeasureService unitOfMeasureService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientController = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
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

    @Test
    public void saveOrUpdateIngredient() throws Exception {
        //Given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setDescription("My Description");
        ingredientCommand.setId(1L);

        when(ingredientService.saveIngredientCommand(any())).thenReturn(ingredientCommand);
        //When
        mockMvc.perform(get("/recipe/1/ingredients/save"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/1/ingredients/details/1"))
        ;
        //Then
        verify(ingredientService, only()).saveIngredientCommand(any());
    }

    @Test
    public void addIngredient() throws Exception {
        //Given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        recipeCommand.setDescription("recipe description");

        UnitOfMeasureCommand uomc1 = new UnitOfMeasureCommand();
        uomc1.setId(1L);
        uomc1.setName("unit");

        UnitOfMeasureCommand uomc2 = new UnitOfMeasureCommand();
        uomc2.setId(2L);
        uomc2.setName("unit2");

        Set<UnitOfMeasureCommand> uomcs = new HashSet<>();
        uomcs.add(uomc1);
        uomcs.add(uomc2);


        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);
        when(unitOfMeasureService.findAllUoms()).thenReturn(uomcs);

        //When
        mockMvc.perform(get("/recipe/1/ingredients/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredients/ingredient-form"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(model().attributeExists("uomList"))
        ;

        //Then
        verify(unitOfMeasureService, only()).findAllUoms();
        verify(recipeService, only()).findRecipeCommandById(anyLong());
    }

    @Test
    public void updateIngredient() throws Exception {
        //Given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        recipeCommand.setDescription("recipe description");

        UnitOfMeasureCommand uomc1 = new UnitOfMeasureCommand();
        uomc1.setId(1L);
        uomc1.setName("unit");

        UnitOfMeasureCommand uomc2 = new UnitOfMeasureCommand();
        uomc2.setId(2L);
        uomc2.setName("unit2");

        Set<UnitOfMeasureCommand> uomcs = new HashSet<>();
        uomcs.add(uomc1);
        uomcs.add(uomc2);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipeCommand.getId());
        ingredientCommand.setId(1L);
        ingredientCommand.setDescription("ingredient");
        ingredientCommand.setAmount(BigDecimal.valueOf(25L));
        ingredientCommand.setUom(uomc1);


        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);
        when(unitOfMeasureService.findAllUoms()).thenReturn(uomcs);
        when(ingredientService.findRecipeIdAndIngredientById(anyLong(), anyLong())).thenReturn(ingredientCommand);

        //When
        mockMvc.perform(get("/recipe/1/ingredients/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredients/ingredient-form"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(model().attributeExists("uomList"))
        ;

        //Then
        verify(ingredientService, only()).findRecipeIdAndIngredientById(anyLong(), anyLong());
        verify(unitOfMeasureService, only()).findAllUoms();
        verify(recipeService, only()).findRecipeCommandById(anyLong());
    }

    @Test
    public void deleteIngredient() throws Exception {
        //Given
        UnitOfMeasureCommand uomc1 = new UnitOfMeasureCommand();
        uomc1.setId(1L);
        uomc1.setName("unit");
        //When
        mockMvc.perform(get("/recipe/1/ingredients/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/1/ingredients"))
        ;
        //Then
        verify(ingredientService, only()).deleteIngredient(anyLong());
    }
}