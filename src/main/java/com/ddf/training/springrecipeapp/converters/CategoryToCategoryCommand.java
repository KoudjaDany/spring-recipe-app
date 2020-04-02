package com.ddf.training.springrecipeapp.converters;

import com.ddf.training.springrecipeapp.commands.CategoryCommand;
import com.ddf.training.springrecipeapp.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    private final RecipeToRecipeCommand recipeConverter;

    public CategoryToCategoryCommand(RecipeToRecipeCommand recipeConverter) {
        this.recipeConverter = recipeConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if (source == null) {
            return null;
        }
        final CategoryCommand target = new CategoryCommand();
        target.setId(source.getId());
        target.setCategoryName(source.getCategoryName());
        if (source.getRecipes() != null && !source.getRecipes().isEmpty()) {
            source.getRecipes().forEach(recipeCommand -> target.getRecipes().add(recipeConverter.convert(recipeCommand)));
        }
        return target;
    }
}
