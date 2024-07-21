package com.ddf.training.springrecipeapp.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString(exclude = {"recipe"})
@Entity
public class Ingredient extends AbstractEntity {


    private String description;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Recipe recipe;

}
