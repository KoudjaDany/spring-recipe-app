package com.ddf.training.springrecipeapp.commands;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {
    private Long id;

    @NotBlank
    private String categoryName;
    private Set<RecipeCommand> recipes = new HashSet<>();
}
