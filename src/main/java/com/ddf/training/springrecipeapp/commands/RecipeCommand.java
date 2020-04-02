package com.ddf.training.springrecipeapp.commands;

import com.ddf.training.springrecipeapp.domain.Category;
import com.ddf.training.springrecipeapp.domain.Ingredient;
import com.ddf.training.springrecipeapp.domain.Notes;
import com.ddf.training.springrecipeapp.enums.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String direction;
    private Difficulty difficulty;
    private Byte[] image;
    private String imageUrl;
    private Notes notes;
    private Set<Ingredient> ingredients = new HashSet<>();
    private Set<Category> categories = new HashSet<>();
}
