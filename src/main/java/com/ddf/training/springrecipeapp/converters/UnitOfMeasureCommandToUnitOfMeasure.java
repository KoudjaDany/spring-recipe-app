package com.ddf.training.springrecipeapp.converters;

import com.ddf.training.springrecipeapp.commands.UnitOfMeasureCommand;
import com.ddf.training.springrecipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if (source == null) {
            return null;
        }
        final UnitOfMeasure target = new UnitOfMeasure();
        target.setId(source.getId());
        target.setName(source.getName());
        return target;
    }
}
