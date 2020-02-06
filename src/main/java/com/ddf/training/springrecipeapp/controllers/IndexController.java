package com.ddf.training.springrecipeapp.controllers;

import com.ddf.training.springrecipeapp.repositories.CategoryRepository;
import com.ddf.training.springrecipeapp.repositories.UnitOfMeasureRepository;
import com.ddf.training.springrecipeapp.services.RecipeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeServiceImpl recipeService;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeServiceImpl recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index", "/index.html", "/index.php"})
    public String getIndexPage(Model model){
        System.out.println("Some message to say... kljfs");
        System.out.println(categoryRepository.findByCategoryName("American"));
        System.out.println(unitOfMeasureRepository.findByName("kg"));
        model.addAttribute("recipes", recipeService.listAll());
        System.out.println("recipeService = " + recipeService.listAll());
        return "index";
    }

    @RequestMapping({"/details/{id}"})
    public String getDetailPage(Model model, @PathVariable Long id){
        System.out.println("Some message to say... kljfs");
        System.out.println(categoryRepository.findByCategoryName("American"));
        System.out.println(unitOfMeasureRepository.findByName("kg"));
        model.addAttribute("recipe", recipeService.getRecipe(id));
        return "recipe-detail";
    }
}
