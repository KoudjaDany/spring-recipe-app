package com.ddf.training.springrecipeapp.services;

import com.ddf.training.springrecipeapp.domain.Category;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface CategoryService {

    Set<Category> findAll();
    Page<Category> search(String criteria, int page, int size);
    Set<Category> searchAll(String criteria);
}
