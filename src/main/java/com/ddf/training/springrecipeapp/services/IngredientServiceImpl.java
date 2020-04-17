package com.ddf.training.springrecipeapp.services;

import com.ddf.training.springrecipeapp.commands.IngredientCommand;
import com.ddf.training.springrecipeapp.converters.IngredientToIngredientCommand;
import com.ddf.training.springrecipeapp.repositories.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
@Transactional
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findRecipeIdAndIngredientById(Long recipeId, Long ingredientId) {
        return ingredientRepository.findByRecipeIdAndId(recipeId, ingredientId)
                .map(ingredientToIngredientCommand::convert)
                .orElseThrow(() -> new EntityNotFoundException("No ingredient of id:" + ingredientId)); //TODO implement error handling
    }
}
