package com.ddf.training.springrecipeapp.converters;

import com.ddf.training.springrecipeapp.commands.CategoryCommand;
import com.ddf.training.springrecipeapp.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    //private final RecipeCommandToRecipe recipeConverter;

//    public CategoryCommandToCategory(RecipeCommandToRecipe recipeConverter) {
//        this.recipeConverter = recipeConverter;
//    }

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        if (source == null) {
            return null;
        }
        final Category target = new Category();
        target.setId(source.getId());
        target.setCategoryName(source.getCategoryName());
//        if (source.getRecipes() != null && !source.getRecipes().isEmpty()) {
//            source.getRecipes().forEach(recipeCommand -> target.getRecipes().add(recipeConverter.convert(recipeCommand)));
//        }
        return target;
    }
}
