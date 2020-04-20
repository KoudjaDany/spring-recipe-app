package com.ddf.training.springrecipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand uom;
    private Long recipeId;

    public boolean isNotEmpty() {
        return description != null && description != ""
                && amount != null
                && uom != null;
    }

    @Override
    public String toString() {
        return amount + " " + " " + uom.toString() + " " + description;
    }
}
