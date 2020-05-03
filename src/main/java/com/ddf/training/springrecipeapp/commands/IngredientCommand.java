package com.ddf.training.springrecipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;

    @NotBlank
    private String description;

    @NotNull
    @PositiveOrZero
    private BigDecimal amount;

    @Valid
    private UnitOfMeasureCommand uom;

    private Long recipeId;

    public boolean isNotEmpty() {
        return description != null && description != ""
                && amount != null
                && uom != null;
    }
}
