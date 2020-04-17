package com.ddf.training.springrecipeapp.services;

import com.ddf.training.springrecipeapp.commands.UnitOfMeasureCommand;
import com.ddf.training.springrecipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.ddf.training.springrecipeapp.domain.UnitOfMeasure;
import com.ddf.training.springrecipeapp.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureService unitOfMeasureService;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Mock
    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    public void findAllUoms() {
        //Given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(1L);
        uom.setName("unit");

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom.setId(2L);
        uom.setName("unit2");

        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        unitOfMeasures.add(uom);
        unitOfMeasures.add(uom2);

        UnitOfMeasureCommand uomc1 = new UnitOfMeasureCommand();
        uomc1.setId(1L);
        uomc1.setName("unit");

        UnitOfMeasureCommand uomc2 = new UnitOfMeasureCommand();
        uomc2.setId(2L);
        uomc2.setName("unit2");


        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);
        when(unitOfMeasureToUnitOfMeasureCommand.convert(uom)).thenReturn(uomc1);
        when(unitOfMeasureToUnitOfMeasureCommand.convert(uom2)).thenReturn(uomc2);

        //When
        Set<UnitOfMeasureCommand> uoms = unitOfMeasureService.findAllUoms();

        //Then
        assertEquals(2, uoms.size());
        verify(unitOfMeasureToUnitOfMeasureCommand, times(2)).convert(any());
        verify(unitOfMeasureRepository, only()).findAll();
    }
}