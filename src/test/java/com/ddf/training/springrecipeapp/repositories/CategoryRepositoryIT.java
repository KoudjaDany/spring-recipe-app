package com.ddf.training.springrecipeapp.repositories;

import com.ddf.training.springrecipeapp.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryIT {

    @Autowired
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByCategoryName() {
        Optional<Category> category = categoryRepository.findByCategoryName("Fast Food");

        assertEquals("Fast Food", category.get().getCategoryName());
        assertEquals(Optional.of(4L), Optional.of(category.get().getId()));
    }

    @Test
    public void findByCategoryNameEqualsMexican() {
        Optional<Category> category = categoryRepository.findByCategoryName("Mexican");

        assertEquals("Mexican", category.get().getCategoryName());
        assertEquals(Optional.of(3L), Optional.of(category.get().getId()));
    }

    @Test
    public void findByCategoryNameEqualsItalian() {
        Optional<Category> category = categoryRepository.findByCategoryName("Italian");

        assertEquals("Italian", category.get().getCategoryName());
        assertEquals(Optional.of(2L), Optional.of(category.get().getId()));
    }

    @Test
    public void findByCategoryNameEqualsAmerican() {
        Optional<Category> category = categoryRepository.findByCategoryName("American");

        assertEquals("American", category.get().getCategoryName());
        assertEquals(Optional.of(1L), Optional.of(category.get().getId()));
    }

    @Test
    public void findByCategoryNameEmptyOrNull() {
        Optional<Category> category = categoryRepository.findByCategoryName("");
        assertEquals(Optional.empty(), category);
        category = categoryRepository.findByCategoryName(null);
        assertEquals(Optional.empty(), category);
       // assertEquals(Optional.of(4L), Optional.of(category.get().getId()));
    }
}