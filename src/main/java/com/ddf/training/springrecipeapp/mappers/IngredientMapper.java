package com.ddf.training.springrecipeapp.mappers;

import com.ddf.training.springrecipeapp.commands.IngredientCommand;
import com.ddf.training.springrecipeapp.domain.Ingredient;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class IngredientMapper {
    public abstract Ingredient toEntity(IngredientCommand ingredientCommand);

    public abstract IngredientCommand toDto(Ingredient ingredient);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Ingredient partialUpdate(IngredientCommand ingredientCommand, @MappingTarget Ingredient ingredient);
}
