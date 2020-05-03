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

    @Min(1)
    @Max(999)
    private Integer prepTime;

    @Min(1)
    @Max(999)
    private Integer cookTime;

    @Min(1)
    @Max(999)
    private Integer servings;
    private String source;

    @URL
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
