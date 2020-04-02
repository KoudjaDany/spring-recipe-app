package com.ddf.training.springrecipeapp.converters;

import com.ddf.training.springrecipeapp.commands.IngredientCommand;
import com.ddf.training.springrecipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    //private final RecipeToRecipeCommand recipeConverter;
    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        //this.recipeConverter = recipeConverter;
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if (source == null) {
            return null;
        }
        final IngredientCommand target = new IngredientCommand();
        target.setId(source.getId());
        target.setAmount(source.getAmount());
        target.setDescription(source.getDescription());
        target.setUom(uomConverter.convert(source.getUom()));
        //target.setRecipe(recipeConverter.convert(source.getRecipe()));

        return target;
    }
}
