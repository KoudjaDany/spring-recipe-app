package com.ddf.training.springrecipeapp.mappers;

import com.ddf.training.springrecipeapp.commands.NotesCommand;
import com.ddf.training.springrecipeapp.domain.Notes;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class NotesMapper {
    public abstract Notes toEntity(NotesCommand notesCommand);

    public abstract NotesCommand toDto(Notes notes);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Notes partialUpdate(NotesCommand notesCommand, @MappingTarget Notes notes);
}
