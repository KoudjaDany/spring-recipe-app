package com.ddf.training.springrecipeapp.services;

import com.ddf.training.springrecipeapp.commands.CategoryCommand;
import com.ddf.training.springrecipeapp.converters.CategoryCommandToCategory;
import com.ddf.training.springrecipeapp.converters.CategoryToCategoryCommand;
import com.ddf.training.springrecipeapp.domain.Category;
import com.ddf.training.springrecipeapp.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private final CategoryCommandToCategory categoryCommandToCategory;
    private final CategoryToCategoryCommand categoryToCategoryCommand;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryCommandToCategory categoryCommandToCategory, CategoryToCategoryCommand categoryToCategoryCommand) {
        this.categoryRepository = categoryRepository;
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }

    @Override
    public Set<Category> findAll() {
        return new HashSet<>((Collection<? extends Category>) categoryRepository.findAll());
    }

    @Override
    public Page<Category> search(String criteria, int page, int size) {

        return categoryRepository.findByCategoryNameLike(criteria, PageRequest.of(page, size));
    }

    @Override
    public Set<Category> searchAll(String criteria) {
        return new HashSet<>(categoryRepository.findByCategoryNameLike(criteria));
    }

    @Override
    public CategoryCommand saveCategoryCommand(CategoryCommand categoryCommand) {
        Category category = categoryCommandToCategory.convert(categoryCommand);
        category = categoryRepository.save(category);
        log.debug("Saved CategoryId:" + category.getId());
        return categoryToCategoryCommand.convert(category);
    }
}
