package com.ddf.training.springrecipeapp.repositories;

import com.ddf.training.springrecipeapp.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByCategoryName(String name);

    @Query("SELECT category from Category category where category.categoryName like :categoryName")
    List<Category> findByCategoryNameLike(@Param("categoryName") String categoryName);

    @Query("SELECT category from Category category where category.categoryName like %:categoryName%")
    Page<Category> findByCategoryNameLike( @Param("categoryName") String categoryName, Pageable pageable);
}
