package com.ddf.training.springrecipeapp.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NotesTest {

    Notes notes;
    Long id = 1L;
    Recipe recipe;

    @Before
    public void setUp() throws Exception {
        notes = new Notes();
        notes.setId(id);
        recipe = new Recipe();
        recipe.setId(1L);
        recipe.setNotes(notes);
        notes.setRecipe(recipe);
    }

    @Test
    public void getId() {
        notes.setId(id);
        assertEquals(id, notes.getId());
    }

    @Test
    public void getRecipe() {
        assertEquals(recipe, notes.getRecipe());
    }

    @Test
    public void getRecipeNotes() {
        String recipeNotes = "My notes on the recipe";
        notes.setRecipeNotes(recipeNotes);
        assertEquals(recipeNotes,notes.getRecipeNotes());
    }

    @Test
    public void setId() {
        notes.setId(2L);
        assertEquals(Long.valueOf(2L), notes.getId());
    }

    @Test
    public void setRecipe() {
        Recipe newRecipe = new Recipe();
        newRecipe.setId(2L);
        newRecipe.setNotes(notes);
        notes.setRecipe(newRecipe);
        assertEquals(newRecipe, notes.getRecipe());
    }

    @Test
    public void setRecipeNotes() {

    }
}