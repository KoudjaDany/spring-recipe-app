package com.ddf.training.springrecipeapp.services;

import com.ddf.training.springrecipeapp.commands.IngredientCommand;
import com.ddf.training.springrecipeapp.converters.IngredientCommandToIngredient;
import com.ddf.training.springrecipeapp.converters.IngredientToIngredientCommand;
import com.ddf.training.springrecipeapp.domain.Ingredient;
import com.ddf.training.springrecipeapp.repositories.IngredientRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    IngredientService ingredientService;

    @Mock
    IngredientRepository ingredientRepository;

    @Mock
    IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    private IngredientCommandToIngredient ingredientCommandToIngredient;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(ingredientRepository, ingredientToIngredientCommand, ingredientCommandToIngredient);
    }

    @Test
    public void findRecipeIdAndIngredientById() {
        //Given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setDescription("My ingredient");

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1L);
        ingredientCommand.setDescription("My ingredient");


        when(ingredientRepository.findByRecipeIdAndId(anyLong(), anyLong())).thenReturn(Optional.of(ingredient));
        when(ingredientToIngredientCommand.convert(any())).thenReturn(ingredientCommand);

        //When
        IngredientCommand returnedIngredient = ingredientService.findRecipeIdAndIngredientById(anyLong(), anyLong());

        //Then
        assertEquals(ingredient.getId(), returnedIngredient.getId());
        assertEquals(ingredient.getDescription(), returnedIngredient.getDescription());

        verify(ingredientToIngredientCommand, only()).convert(any());
        verify(ingredientRepository, only()).findByRecipeIdAndId(anyLong(), anyLong());
    }

    @Test
    public void saveIngredientCommand() {
        //Given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setDescription("My ingredient");

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setDescription("My ingredient");

        IngredientCommand ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(1L);
        ingredientCommand1.setDescription("My ingredient");

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setDescription("My ingredient");

        when(ingredientRepository.save(any())).thenReturn(ingredient);
        when(ingredientCommandToIngredient.convert(any())).thenReturn(ingredient1);
        when(ingredientToIngredientCommand.convert(any())).thenReturn(ingredientCommand1);

        //When
        IngredientCommand result = ingredientService.saveIngredientCommand(ingredientCommand);

        //Then
        assertEquals(ingredient.getId(), result.getId());
        assertEquals(ingredient.getDescription(), result.getDescription());
        verify(ingredientRepository, only()).save(any());
        verify(ingredientToIngredientCommand, only()).convert(any());
        verify(ingredientCommandToIngredient, only()).convert(any());
    }

    @Test
    public void deleteIngredient() {
        //Given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setDescription("My ingredient");

        //When
        ingredientService.deleteIngredient(1L);

        //Then
        verify(ingredientRepository, only()).deleteById(anyLong());
    }
}