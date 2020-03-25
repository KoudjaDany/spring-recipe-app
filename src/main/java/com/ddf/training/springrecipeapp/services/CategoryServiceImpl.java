package com.ddf.training.springrecipeapp.services;

import com.ddf.training.springrecipeapp.domain.Category;
import com.ddf.training.springrecipeapp.repositories.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> findAll() {
        return (Set<Category>) categoryRepository.findAll();
    }

    @Override
    public Page<Category> search(String criteria, int page, int size) {

        return categoryRepository.findByCategoryNameLike(criteria, PageRequest.of(page, size));
    }

    @Override
    public Set<Category> searchAll(String criteria) {
        return null;
    }
}
