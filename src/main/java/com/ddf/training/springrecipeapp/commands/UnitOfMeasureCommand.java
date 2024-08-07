package com.ddf.training.springrecipeapp.commands;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UnitOfMeasureCommand {
    private Long id;

    @NotBlank
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
