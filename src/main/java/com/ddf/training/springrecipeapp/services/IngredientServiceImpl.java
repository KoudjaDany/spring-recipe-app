package com.ddf.training.springrecipeapp.services;

import com.ddf.training.springrecipeapp.commands.IngredientCommand;
import com.ddf.training.springrecipeapp.converters.IngredientCommandToIngredient;
import com.ddf.training.springrecipeapp.converters.IngredientToIngredientCommand;
import com.ddf.training.springrecipeapp.domain.Ingredient;
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
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Override
    public IngredientCommand findRecipeIdAndIngredientById(Long recipeId, Long ingredientId) {
        return ingredientRepository.findByRecipeIdAndId(recipeId, ingredientId)
                .map(ingredientToIngredientCommand::convert)
                .orElseThrow(() -> new EntityNotFoundException("No ingredient of id:" + ingredientId)); //TODO implement error handling
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Ingredient ingredientToSave = ingredientCommandToIngredient.convert(ingredientCommand);
        Ingredient savedIngredient = ingredientRepository.save(ingredientToSave);
        log.debug("Saved ingredient of id:" + savedIngredient.getId());
        return ingredientToIngredientCommand.convert(savedIngredient);
    }

    @Override
    public void deleteIngredient(Long ingredientId) {
        ingredientRepository.deleteById(ingredientId);
        log.debug("The ingredient of id:" + ingredientId + " was deleted.");
    }
}
