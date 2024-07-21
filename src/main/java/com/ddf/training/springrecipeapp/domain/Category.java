package com.ddf.training.springrecipeapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category extends AbstractEntity {

    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    @ToString.Exclude
    private Set<Recipe> recipes = new HashSet<>();

    public String toString() {
        return "Category(id=" + this.getId() + ", categoryName=" + this.getCategoryName() + ")";
    }
}
