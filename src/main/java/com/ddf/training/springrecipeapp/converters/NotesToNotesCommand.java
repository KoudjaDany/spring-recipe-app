package com.ddf.training.springrecipeapp.converters;

import com.ddf.training.springrecipeapp.commands.NotesCommand;
import com.ddf.training.springrecipeapp.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {
//
//    private final RecipeToRecipeCommand recipeConverter;
//
//    public NotesToNotesCommand(RecipeToRecipeCommand recipeConverter) {
//        this.recipeConverter = recipeConverter;
//    }

    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes source) {
        if (source == null) {
            return null;
        }
        final NotesCommand target = new NotesCommand();
        target.setId(source.getId());
        target.setRecipeNotes(source.getRecipeNotes());
        // target.setRecipe(recipeConverter.convert(source.getRecipe()));

        return target;
    }
}
