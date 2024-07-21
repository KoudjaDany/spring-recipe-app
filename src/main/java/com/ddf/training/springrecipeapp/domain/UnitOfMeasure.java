package com.ddf.training.springrecipeapp.domain;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class UnitOfMeasure extends AbstractEntity {

    private String name;

}
