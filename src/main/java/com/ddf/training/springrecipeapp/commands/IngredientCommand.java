package com.ddf.training.springrecipeapp.commands;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;

    @NotBlank
    private String description;

    @NotNull
    @Positive
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
