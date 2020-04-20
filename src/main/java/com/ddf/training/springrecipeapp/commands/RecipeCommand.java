package com.ddf.training.springrecipeapp.commands;

import com.ddf.training.springrecipeapp.enums.Difficulty;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@XmlRootElement
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Difficulty difficulty;
    private byte[] image;
    private String imageUrl;
    private NotesCommand notes;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Set<CategoryCommand> categories = new HashSet<>();

    public void addIngredient(IngredientCommand ingredientCommand) {
        if (Objects.nonNull(ingredientCommand)) {
            ingredients.add(ingredientCommand);
            ingredientCommand.setRecipeId(null);
        }
    }

    public boolean isNew() {
        return id == null;
    }
}
