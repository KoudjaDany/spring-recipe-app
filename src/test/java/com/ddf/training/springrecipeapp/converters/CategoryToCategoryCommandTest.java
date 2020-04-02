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

public class CategoryToCategoryCommandTest {


    public static final String NAME = "category";
    public static final long ID = 1L;
    public static final long RECIPE_ID1 = 3L;
    public static final long RECIPE_ID2 = 2L;
    public static final Set<Recipe> recipes = new HashSet<>();

    CategoryToCategoryCommand converter;

    @Mock
    RecipeToRecipeCommand recipeConverter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        //converter = new CategoryToCategoryCommand(recipeConverter);
        converter = new CategoryToCategoryCommand();
    }


    //TODO create an abstraction
    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert() {
        //Given
        Recipe recipe1 = new Recipe();
        recipe1.setId(RECIPE_ID1);
        Recipe recipe2 = new Recipe();
        recipe2.setId(RECIPE_ID2);

        recipes.add(recipe1);
        recipes.add(recipe2);

        Category category = new Category();
        category.setCategoryName(NAME);
        category.setId(ID);
        category.setRecipes(recipes);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID1);

        RecipeCommand recipeCommand2 = new RecipeCommand();
        recipeCommand2.setId(RECIPE_ID2);

        when(recipeConverter.convert(recipe1)).thenReturn(recipeCommand);
        when(recipeConverter.convert(recipe2)).thenReturn(recipeCommand2);

        //When
        CategoryCommand categoryCommand = converter.convert(category);
        assertNotNull(categoryCommand);
        //then
        assertEquals(Optional.ofNullable(ID), java.util.Optional.ofNullable(categoryCommand.getId()));
        assertEquals(NAME, categoryCommand.getCategoryName());
        assertEquals(Optional.ofNullable(2), java.util.Optional.of(categoryCommand.getRecipes().size()));
        //verify(recipeConverter, atLeast(2)).convert(any());
    }
}