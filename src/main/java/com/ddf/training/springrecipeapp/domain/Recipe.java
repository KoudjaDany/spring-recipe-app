package com.ddf.training.springrecipeapp.domain;

import com.ddf.training.springrecipeapp.enums.Difficulty;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer prepTime = 20;
    private Integer cookTime = 10;
    private Integer servings = 3;
    private String source;
    private String url = "/images/marecette.jpg";
    @Lob
    private String directions;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty = Difficulty.EASY;

    @Lob
    private Byte[] image;

    private String imageUrl;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public  Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

}
