/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ddf.training.springrecipeapp.formaters;

import com.ddf.training.springrecipeapp.commands.UnitOfMeasureCommand;
import com.ddf.training.springrecipeapp.services.UnitOfMeasureService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

/**
 * Instructs Spring MVC on how to parse and print elements of type 'UnitOfMeasureCommand'. Starting
 * from Spring 3.0, Formatters have come as an improvement in comparison to legacy
 * PropertyEditors. See the following links for more details: - The Spring ref doc:
 * https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#format
 *
 * @author Mark Fisher
 * @author Juergen Hoeller
 * @author Michael Isvy
 */
@Component
public class UnitOfMeasureFormatter implements Formatter<UnitOfMeasureCommand> {

    private final UnitOfMeasureService unitOfMeasureService;

    public UnitOfMeasureFormatter(UnitOfMeasureService unitOfMeasureService) {
        this.unitOfMeasureService = unitOfMeasureService;
    }


    @Override
    public String print(UnitOfMeasureCommand petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public UnitOfMeasureCommand parse(String text, Locale locale) throws ParseException {
        Collection<UnitOfMeasureCommand> findPetTypes = unitOfMeasureService.findAllUoms();
        for (UnitOfMeasureCommand type : findPetTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }
        throw new ParseException("type not found: " + text, 0);
    }

}
