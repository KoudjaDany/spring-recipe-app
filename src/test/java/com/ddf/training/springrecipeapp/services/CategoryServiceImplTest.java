package com.ddf.training.springrecipeapp.services;

import com.ddf.training.springrecipeapp.commands.CategoryCommand;
import com.ddf.training.springrecipeapp.converters.CategoryCommandToCategory;
import com.ddf.training.springrecipeapp.converters.CategoryToCategoryCommand;
import com.ddf.training.springrecipeapp.domain.Category;
import com.ddf.training.springrecipeapp.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CategoryToCategoryCommand categoryToCategoryCommand;

    @Mock
    CategoryCommandToCategory categoryCommandToCategory;

    Long id = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository, categoryCommandToCategory, categoryToCategoryCommand);
    }

    @Test
    public void findAll() {
        Category category = new Category();
        category.setId(id);
        category.setCategoryName("My Category");

        Set<Category> categories = new HashSet<>();
        categories.add(category);

        when(categoryRepository.findAll()).thenReturn(categories);
        Set<Category> results = categoryService.findAll();
        assertEquals(1, results.size());
        assertEquals(id, results.iterator().next().getId());

        verify(categoryRepository, only()).findAll();

    }

    @Test
    public void searchAll() {
        Category category = new Category();
        category.setId(id);
        category.setCategoryName("Pizza");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setCategoryName("Sushi");

        Category category3 = new Category();
        category3.setId(2L);
        category3.setCategoryName("Cake");

        Set<Category> categories = new HashSet<>();
        categories.add(category);
        categories.add(category2);
        categories.add(category3);
        List<Category> categoryList = new ArrayList<>(categories);

        when(categoryRepository.findByCategoryNameLike(anyString())).thenReturn(categoryList);

        Set<Category> results = categoryService.searchAll("s");
        assertEquals(3, results.size());

        verify(categoryRepository, only()).findByCategoryNameLike(anyString());
    }

    @Test
    public void search() {
        Category category = new Category();
        category.setId(id);
        category.setCategoryName("Pizza");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setCategoryName("Sushi");

        Category category3 = new Category();
        category3.setId(2L);
        category3.setCategoryName("Cake");

        Set<Category> categories = new HashSet<>();
        categories.add(category);
        categories.add(category2);
        categories.add(category3);
        List<Category> categoryList = new ArrayList<>(categories);

        when(categoryRepository.findByCategoryNameLike(anyString(), any())).thenReturn(new PageImpl<>(categoryList, PageRequest.of(0, 3), 3L));

        Page<Category> results = categoryService.search("s", 0, 3);
        assertEquals(1, results.getTotalPages());
        assertEquals(3L, results.getTotalElements());

        verify(categoryRepository, only()).findByCategoryNameLike(anyString(), any());
    }

    @Test
    public void saveCategoryCommand() {
        Category savedCategory = new Category();
        savedCategory.setId(id);
        savedCategory.setCategoryName("Pizzas");

        Category category = new Category();
        category.setId(id);
        category.setCategoryName("Pizzas");

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setCategoryName("Pizzas");

        CategoryCommand categoryCommandResult = new CategoryCommand();
        categoryCommandResult.setId(id);
        categoryCommandResult.setCategoryName("Pizzas");

        when(categoryCommandToCategory.convert(any())).thenReturn(category);
        when(categoryToCategoryCommand.convert(any())).thenReturn(categoryCommandResult);
        when(categoryRepository.save(any())).thenReturn(savedCategory);

        CategoryCommand returnedCategoryCommand = categoryService.saveCategoryCommand(categoryCommand);

        assertEquals(id, returnedCategoryCommand.getId());
        assertEquals("Pizzas", returnedCategoryCommand.getCategoryName());

        verify(categoryRepository, only()).save(any());
        verify(categoryCommandToCategory, only()).convert(any());
        verify(categoryToCategoryCommand, only()).convert(any());


    }
}