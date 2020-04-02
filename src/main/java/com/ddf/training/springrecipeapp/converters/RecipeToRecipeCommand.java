package com.ddf.training.springrecipeapp.converters;

import com.ddf.training.springrecipeapp.commands.RecipeCommand;
import com.ddf.training.springrecipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final NotesToNotesCommand notesConverter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final CategoryToCategoryCommand categoryConverter;

    public RecipeToRecipeCommand(NotesToNotesCommand notesConverter, IngredientToIngredientCommand ingredientConverter, CategoryToCategoryCommand categoryConverter) {
        this.notesConverter = notesConverter;
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }
        final RecipeCommand target = new RecipeCommand();
        target.setId(source.getId());
        target.setImageUrl(source.getImageUrl());
        target.setPrepTime(source.getPrepTime());
        target.setCookTime(source.getCookTime());
        target.setDescription(source.getDescription());
        target.setDifficulty(source.getDifficulty());
        target.setDirections(source.getDirections());
        target.setServings(source.getServings());
        target.setSource(source.getSource());
        target.setImage(source.getImage());
        target.setUrl(source.getUrl());
        target.setNotes(Objects.requireNonNull(notesConverter.convert(source.getNotes())));
        if (source.getIngredients() != null && !source.getIngredients().isEmpty()) {
            source.getIngredients().forEach(ingredient -> target.getIngredients().add(ingredientConverter.convert(ingredient)));
        }
        if (source.getCategories() != null && !source.getCategories().isEmpty()) {
            source.getCategories().forEach(category -> target.getCategories().add(categoryConverter.convert(category)));
        }
        return target;
    }
}
