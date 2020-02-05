package com.ddf.training.springrecipeapp.repositories;

import com.ddf.training.springrecipeapp.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitOfMeasureRepository  extends CrudRepository<UnitOfMeasure, Long> {
}
