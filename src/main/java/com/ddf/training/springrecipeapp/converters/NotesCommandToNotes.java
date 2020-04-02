package com.ddf.training.springrecipeapp.converters;

import com.ddf.training.springrecipeapp.commands.NotesCommand;
import com.ddf.training.springrecipeapp.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    private final RecipeCommandToRecipe recipeConverter;

    public NotesCommandToNotes(RecipeCommandToRecipe recipeConverter) {
        this.recipeConverter = recipeConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand source) {
        if (source == null) {
            return null;
        }
        final Notes target = new Notes();
        target.setId(source.getId());
        target.setRecipeNotes(source.getRecipeNotes());
        target.setRecipe(recipeConverter.convert(source.getRecipe()));

        return target;
    }
}
