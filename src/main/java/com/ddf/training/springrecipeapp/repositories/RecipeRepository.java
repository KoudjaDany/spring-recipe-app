package com.ddf.training.springrecipeapp.repositories;

import com.ddf.training.springrecipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
