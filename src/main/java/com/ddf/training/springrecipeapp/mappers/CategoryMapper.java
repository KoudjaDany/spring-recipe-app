package com.ddf.training.springrecipeapp.mappers;

import com.ddf.training.springrecipeapp.commands.CategoryCommand;
import com.ddf.training.springrecipeapp.domain.Category;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CategoryMapper {

    public abstract Category toEntity(CategoryCommand categoryCommand);

    public abstract CategoryCommand toDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Category partialUpdate(CategoryCommand categoryCommand, @MappingTarget Category category);
}
