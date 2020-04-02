package com.ddf.training.springrecipeapp.converters;

import com.ddf.training.springrecipeapp.commands.NotesCommand;
import com.ddf.training.springrecipeapp.commands.RecipeCommand;
import com.ddf.training.springrecipeapp.domain.Notes;
import com.ddf.training.springrecipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class NotesCommandToNotesTest {

    public static final String NOTES = "notes";
    public static final long LONG_VALUE = 1L;
    NotesCommandToNotes converter;

    @Mock
    RecipeCommandToRecipe recipeConverter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        converter = new NotesCommandToNotes(recipeConverter);
    }


    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(LONG_VALUE);
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(LONG_VALUE);
        notesCommand.setRecipeNotes(NOTES);
        notesCommand.setRecipe(recipeCommand);

        Recipe recipe = new Recipe();
        recipe.setId(LONG_VALUE);
        when(recipeConverter.convert(any())).thenReturn(recipe);

        //When
        Notes notes = converter.convert(notesCommand);

        //Then
        assertNotNull(notes);
        assertEquals(java.util.Optional.of(LONG_VALUE), java.util.Optional.ofNullable(notes.getId()));
        assertEquals(NOTES, notes.getRecipeNotes());
        assertEquals(java.util.Optional.of(LONG_VALUE), java.util.Optional.ofNullable(notes.getRecipe().getId()));
    }
}