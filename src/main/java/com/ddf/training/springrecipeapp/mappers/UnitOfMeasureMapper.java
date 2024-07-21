package com.ddf.training.springrecipeapp.mappers;

import com.ddf.training.springrecipeapp.commands.UnitOfMeasureCommand;
import com.ddf.training.springrecipeapp.domain.UnitOfMeasure;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class UnitOfMeasureMapper {
    public abstract UnitOfMeasure toEntity(UnitOfMeasureCommand unitOfMeasureCommand);

    public abstract UnitOfMeasureCommand toDto(UnitOfMeasure unitOfMeasure);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract UnitOfMeasure partialUpdate(UnitOfMeasureCommand unitOfMeasureCommand, @MappingTarget UnitOfMeasure unitOfMeasure);
}
