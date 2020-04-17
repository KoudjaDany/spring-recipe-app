package com.ddf.training.springrecipeapp.converters;

import com.ddf.training.springrecipeapp.commands.IngredientCommand;
import com.ddf.training.springrecipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) {
            return null;
        }
        final Ingredient target = new Ingredient();
        target.setId(source.getId());
        target.setAmount(source.getAmount());
        target.setDescription(source.getDescription());
        target.setUom(uomConverter.convert(source.getUom()));
        return target;
    }
}
