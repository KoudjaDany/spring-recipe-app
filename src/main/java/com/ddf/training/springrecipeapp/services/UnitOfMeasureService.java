package com.ddf.training.springrecipeapp.services;

import com.ddf.training.springrecipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> findAllUoms();
}
