package com.ddf.training.springrecipeapp.controllers;

import com.ddf.training.springrecipeapp.services.ImageService;
import com.ddf.training.springrecipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/recipe/{recipeId}/image")
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @RequestMapping("/change")
    public String changeImage(@PathVariable Long recipeId, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeCommandById(recipeId));
        return "recipe/image-upload-form";
    }


    @PostMapping("/upload")
    public String handleImagePost(@PathVariable Long recipeId, @RequestParam("imageFile") MultipartFile file) {

        imageService.saveImageFile(recipeId, file);
        return "redirect:/recipe/details/" + recipeId;
    }
}
