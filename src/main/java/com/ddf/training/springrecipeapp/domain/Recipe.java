package com.ddf.training.springrecipeapp.domain;

import com.ddf.training.springrecipeapp.enums.Difficulty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe extends AbstractEntity {


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
        if (notes != null) {
            this.notes = notes;
            notes.setRecipe(this);
        }
    }

    public  Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

}
