package com.ddf.training.springrecipeapp.commands;

import com.ddf.training.springrecipeapp.domain.Recipe;
import com.ddf.training.springrecipeapp.domain.UnitOfMeasure;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class IngredientCommand {
    private Long id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasure uom;
    private Recipe recipe;

}
