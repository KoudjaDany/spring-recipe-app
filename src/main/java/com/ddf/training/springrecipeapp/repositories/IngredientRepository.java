package com.ddf.training.springrecipeapp.repositories;

import com.ddf.training.springrecipeapp.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    Optional<Ingredient> findByRecipeIdAndId(Long recipeId, Long ingredientId);
}
