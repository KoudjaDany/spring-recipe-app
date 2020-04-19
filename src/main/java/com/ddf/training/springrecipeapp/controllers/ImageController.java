package com.ddf.training.springrecipeapp.controllers;

import com.ddf.training.springrecipeapp.commands.RecipeCommand;
import com.ddf.training.springrecipeapp.services.ImageService;
import com.ddf.training.springrecipeapp.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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

    @RequestMapping("/recipe-image")
    public void renderImageFromDB(@PathVariable Long recipeId, HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand = recipeService.findRecipeCommandById(recipeId);
        response.setContentType("image/jpeg");
        InputStream inputStream = new ByteArrayInputStream(recipeCommand.getImage());
        IOUtils.copy(inputStream, response.getOutputStream());
    }
}
