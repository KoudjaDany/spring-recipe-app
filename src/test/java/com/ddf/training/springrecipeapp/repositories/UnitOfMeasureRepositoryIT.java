package com.ddf.training.springrecipeapp.repositories;

import com.ddf.training.springrecipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    @DirtiesContext
    public void findByNameTeaspoon() {
        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByName("teaspoon");

        assertEquals("teaspoon", uom.get().getName());
    }

    @Test
    @DirtiesContext
    public void findByNameUnit() {
        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByName("unit");

        assertEquals("unit", uom.get().getName());
    }

    @Test
    @DirtiesContext
    public void findByNameCup() {
        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByName("cup");

        assertEquals("cup", uom.get().getName());
    }

    @Test
    @DirtiesContext
    @Ignore
    public void findByNamePinch() {
        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByName("pinch");

        assertEquals("pinch", uom.get().getName());
    }
}