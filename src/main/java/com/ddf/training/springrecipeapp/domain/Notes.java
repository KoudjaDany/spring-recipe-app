package com.ddf.training.springrecipeapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"recipe"})
@Entity
public class Notes extends AbstractEntity {


    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;
}
