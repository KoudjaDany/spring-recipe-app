package com.ddf.training.springrecipeapp.services;

import com.ddf.training.springrecipeapp.commands.CategoryCommand;
import com.ddf.training.springrecipeapp.converters.CategoryCommandToCategory;
import com.ddf.training.springrecipeapp.converters.CategoryToCategoryCommand;
import com.ddf.training.springrecipeapp.domain.Category;
import com.ddf.training.springrecipeapp.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceIT {

    private final String CATEGORY_NAME = "Pizzas";
    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryToCategoryCommand categoryToCategoryCommand;

    @Autowired
    CategoryCommandToCategory categoryCommandToCategory;

    Long id = 1L;

    @Before
    public void setUp() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void search() {
    }

    @Test
    public void searchAll() {
    }

    @Test
    @Transactional
    public void saveCategoryCommand() {

        Category savedCategory = categoryRepository.findAll().iterator().next();
        CategoryCommand categoryCommand = categoryToCategoryCommand.convert(savedCategory);
        categoryCommand.setCategoryName(CATEGORY_NAME);

        CategoryCommand returnedCategoryCommand = categoryService.saveCategoryCommand(categoryCommand);

        assertEquals(id, returnedCategoryCommand.getId());
        assertEquals(CATEGORY_NAME, returnedCategoryCommand.getCategoryName());
        assertEquals(savedCategory.getId(), returnedCategoryCommand.getId());
        assertEquals(savedCategory.getRecipes().size(), returnedCategoryCommand.getRecipes().size());
    }
}