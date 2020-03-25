package com.ddf.training.springrecipeapp;

import com.ddf.training.springrecipeapp.domain.Category;
import com.ddf.training.springrecipeapp.services.CategoryService;
import com.ddf.training.springrecipeapp.services.CategoryServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class SpringRecipeAppApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringRecipeAppApplication.class, args);
        CategoryService categoryService = (CategoryServiceImpl) applicationContext.getBean("categoryServiceImpl");


        Page<Category> categories = categoryService.search("i", 0, 3);
        categories.stream().forEach(System.out::println);
    }

}
