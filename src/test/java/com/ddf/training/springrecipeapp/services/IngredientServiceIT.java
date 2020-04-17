package com.ddf.training.springrecipeapp.services;

import com.ddf.training.springrecipeapp.commands.IngredientCommand;
import com.ddf.training.springrecipeapp.converters.IngredientCommandToIngredient;
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

    public static final String MY_INGREDIENT = "My Ingredient";
    IngredientService ingredientService;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    IngredientToIngredientCommand ingredientToIngredientCommand;

    @Autowired
    private IngredientCommandToIngredient ingredientCommandToIngredient;

    @Before
    public void setUp() throws Exception {
        ingredientService = new IngredientServiceImpl(ingredientRepository, ingredientToIngredientCommand, ingredientCommandToIngredient);
    }

    @Test
    public void findRecipeIdAndIngredientById() {

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


    @Test
    public void saveIngredientCommand() {
        //Given
        Ingredient ingredient = ingredientRepository.findAll().iterator().next();
        IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(ingredient);
        ingredientCommand.setDescription(MY_INGREDIENT);

        //When
        IngredientCommand result = ingredientService.saveIngredientCommand(ingredientCommand);

        //Then
        assertEquals(ingredientCommand.getId(), result.getId());
        assertEquals(result.getDescription(), ingredientCommand.getDescription());
        assertEquals(MY_INGREDIENT, ingredientCommand.getDescription());
        assertEquals(result.getUom().getId(), ingredientCommand.getUom().getId());
        assertEquals(result.getUom().getName(), ingredientCommand.getUom().getName());
        assertEquals(result.getAmount(), ingredientCommand.getAmount());

    }
}