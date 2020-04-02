package com.ddf.training.springrecipeapp.converters;

import com.ddf.training.springrecipeapp.commands.CategoryCommand;
import com.ddf.training.springrecipeapp.commands.RecipeCommand;
import com.ddf.training.springrecipeapp.domain.Category;
import com.ddf.training.springrecipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CategoryCommandToCategoryTest {

    public static final String NAME = "category";
    public static final long ID = 1L;
    public static final long RECIPE_ID1 = 3L;
    public static final long RECIPE_ID2 = 2L;
    public static final Set<RecipeCommand> recipeCommands = new HashSet<>();

    CategoryCommandToCategory converter;

    @Mock
    RecipeCommandToRecipe recipeConverter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        //converter = new CategoryCommandToCategory(recipeConverter);
        converter = new CategoryCommandToCategory();
    }


    //TODO create an abstraction
    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {
        //Given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID1);

        RecipeCommand recipeCommand2 = new RecipeCommand();
        recipeCommand2.setId(RECIPE_ID2);

        recipeCommands.add(recipeCommand);
        recipeCommands.add(recipeCommand2);

        Recipe recipe1 = new Recipe();
        recipe1.setId(RECIPE_ID1);
        Recipe recipe2 = new Recipe();
        recipe2.setId(RECIPE_ID2);

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setCategoryName(NAME);
        categoryCommand.setId(ID);
        categoryCommand.setRecipes(recipeCommands);

        when(recipeConverter.convert(recipeCommand)).thenReturn(recipe1);
        when(recipeConverter.convert(recipeCommand2)).thenReturn(recipe2);

        //When
        Category category = converter.convert(categoryCommand);
        assertNotNull(category);
        //then
        assertEquals(Optional.ofNullable(ID), java.util.Optional.ofNullable(category.getId()));
        assertEquals(NAME, category.getCategoryName());
        assertEquals(Optional.ofNullable(2), java.util.Optional.of(category.getRecipes().size()));
        //verify(recipeConverter, atLeast(2)).convert(any());
    }
}