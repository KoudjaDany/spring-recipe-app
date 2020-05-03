package com.ddf.training.springrecipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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
