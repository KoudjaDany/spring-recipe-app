package com.ddf.training.springrecipeapp.mappers;

import com.ddf.training.springrecipeapp.commands.RecipeCommand;
import com.ddf.training.springrecipeapp.domain.Recipe;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class RecipeMapper {

    public abstract Recipe toEntity(RecipeCommand recipeCommand);

    @AfterMapping
    public void linkIngredients(@MappingTarget Recipe recipe) {
        recipe.getIngredients().forEach(ingredient -> ingredient.setRecipe(recipe));
    }

    public abstract RecipeCommand toDto(Recipe recipe);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Recipe partialUpdate(RecipeCommand recipeCommand, @MappingTarget Recipe recipe);
}
