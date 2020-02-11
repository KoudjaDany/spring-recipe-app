package com.ddf.training.springrecipeapp.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() throws Exception {
        category = new Category();
    }

    @Test
    public void getId() {
        Long idValue = 4L;
        category.setId(idValue);
        assertEquals(Long.valueOf(4L), category.getId());
    }

    @Test
    public void getCategoryName() {
    }

    @Test
    public void getRecipes() {
    }
}