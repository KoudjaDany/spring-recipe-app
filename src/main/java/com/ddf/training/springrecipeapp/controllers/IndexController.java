package com.ddf.training.springrecipeapp.controllers;

import com.ddf.training.springrecipeapp.repositories.CategoryRepository;
import com.ddf.training.springrecipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index", "/index.html", "/index.php"})
    public String getIndexPage(){
        System.out.println("Some message to say... kljfs");
        System.out.println(categoryRepository.findByCategoryName("American"));
        System.out.println(unitOfMeasureRepository.findByName("kg"));
        return "index";
    }
}
