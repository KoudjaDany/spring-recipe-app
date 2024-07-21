package com.ddf.training.springrecipeapp.commands;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {
    private Long id;

    @Valid
    private RecipeCommand recipe;

    @NotBlank
    private String recipeNotes;
}
