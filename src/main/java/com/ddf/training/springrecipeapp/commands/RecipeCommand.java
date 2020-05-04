package com.ddf.training.springrecipeapp.commands;

import com.ddf.training.springrecipeapp.enums.Difficulty;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.Valid;
import javax.validation.constraints.*;
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

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    @NotNull
    @Min(1)
    @Max(999)
    private Integer prepTime;

    @NotNull
    @Min(1)
    @Max(999)
    private Integer cookTime;

    @NotNull
    @Min(1)
    @Max(999)
    private Integer servings;
    private String source;

    @URL
    @NotNull
    private String url;

    @NotBlank
    private String directions;

    @NotNull
    private Difficulty difficulty;
    private byte[] image;
    private String imageUrl;

    @Valid
    private NotesCommand notes;
    private Set<@Valid IngredientCommand> ingredients = new HashSet<>();
    private Set<@Valid CategoryCommand> categories = new HashSet<>();

    public void setIngredients(Set<IngredientCommand> ingredients) {
        ingredients.forEach(this::addIngredient);
    }
    public void addIngredient(IngredientCommand ingredientCommand) {
        if (Objects.nonNull(ingredientCommand)) {
            ingredients.add(ingredientCommand);
            ingredientCommand.setRecipeId(this.id);
        }
    }

    public boolean isNew() {
        return id == null;
    }
}
