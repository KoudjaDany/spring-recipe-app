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

public class NotesToNotesCommandTest {


    public static final String NOTES = "notes";
    public static final long LONG_VALUE = 1L;
    NotesToNotesCommand converter;

    @Mock
    RecipeToRecipeCommand recipeConverter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        converter = new NotesToNotesCommand(recipeConverter);
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(LONG_VALUE);

        Notes notes = new Notes();
        notes.setId(LONG_VALUE);
        notes.setRecipeNotes(NOTES);
        notes.setRecipe(recipe);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(LONG_VALUE);
        when(recipeConverter.convert(any())).thenReturn(recipeCommand);

        //When
        NotesCommand notesCommand = converter.convert(notes);

        //Then
        assertNotNull(notesCommand);
        assertEquals(java.util.Optional.of(LONG_VALUE), java.util.Optional.ofNullable(notesCommand.getId()));
        assertEquals(NOTES, notesCommand.getRecipeNotes());
        assertEquals(java.util.Optional.of(LONG_VALUE), java.util.Optional.ofNullable(notesCommand.getRecipe().getId()));
    }
}