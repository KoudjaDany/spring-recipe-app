package com.ddf.training.springrecipeapp.services;

import com.ddf.training.springrecipeapp.commands.IngredientCommand;
import com.ddf.training.springrecipeapp.converters.IngredientToIngredientCommand;
import com.ddf.training.springrecipeapp.domain.Ingredient;
import com.ddf.training.springrecipeapp.repositories.IngredientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientServiceIT {

    IngredientService ingredientService;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    IngredientToIngredientCommand ingredientToIngredientCommand;

    @Before
    public void setUp() throws Exception {
        ingredientService = new IngredientServiceImpl(ingredientRepository, ingredientToIngredientCommand);
    }

    @Test
    public void findIngredientById() {

        //Given
        Ingredient ingredient = ingredientRepository.findAll().iterator().next();
        Long ingredientId = ingredient.getId();
        Long recipeId = ingredient.getRecipe().getId();

        //When
        IngredientCommand result = ingredientService.findRecipeIdAndIngredientById(recipeId, ingredientId);

        //Then
        assertEquals(ingredient.getId(), result.getId());
        assertEquals(result.getDescription(), ingredient.getDescription());
        assertEquals(result.getUom().getId(), ingredient.getUom().getId());
        assertEquals(result.getUom().getName(), ingredient.getUom().getName());
        assertEquals(result.getAmount(), ingredient.getAmount());
    }
}