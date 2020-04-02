package com.ddf.training.springrecipeapp.converters;

import com.ddf.training.springrecipeapp.commands.RecipeCommand;
import com.ddf.training.springrecipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final NotesCommandToNotes notesConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final CategoryCommandToCategory categoryConverter;

    public RecipeCommandToRecipe(NotesCommandToNotes notesConverter, IngredientCommandToIngredient ingredientConverter, CategoryCommandToCategory categoryConverter) {
        this.notesConverter = notesConverter;
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
    }


    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }
        final Recipe target = new Recipe();
        target.setId(source.getId());
        target.setImageUrl(source.getImageUrl());
        target.setPrepTime(source.getPrepTime());
        target.setCookTime(source.getCookTime());
        target.setDescription(source.getDescription());
        target.setDifficulty(source.getDifficulty());
        target.setDirection(source.getDirection());
        target.setServings(source.getServings());
        target.setSource(source.getSource());
        target.setImage(source.getImage());
        target.setUrl(source.getUrl());
        target.setNotes(Objects.requireNonNull(notesConverter.convert(source.getNotes())));
        if (source.getIngredients() != null && !source.getIngredients().isEmpty()) {
            source.getIngredients().forEach(ingredientCommand -> target.getIngredients().add(ingredientConverter.convert(ingredientCommand)));
        }
        if (source.getCategories() != null && !source.getCategories().isEmpty()) {
            source.getCategories().forEach(categoryCommand -> target.getCategories().add(categoryConverter.convert(categoryCommand)));
        }
        return target;
    }
}
