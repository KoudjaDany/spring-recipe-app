package com.ddf.training.springrecipeapp.controllers;

import com.ddf.training.springrecipeapp.commands.RecipeCommand;
import com.ddf.training.springrecipeapp.services.ImageService;
import com.ddf.training.springrecipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ImageControllerTest {

    ImageController imageController;

    @Mock
    ImageService imageService;

    MockMvc mockMvc;

    @Mock
    private RecipeService recipeService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageController = new ImageController(imageService, recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    public void handleImagePost() throws Exception {
        //Given
        MockMultipartFile file = new MockMultipartFile("imageFile", "testing.txt", "text/plain", "Spring Framework Guru".getBytes());
        //When
        mockMvc.perform(multipart("/recipe/1/image/upload").file(file))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "/recipe/details/1"));
        //Then
        verify(imageService, only()).saveImageFile(any(), any());
    }

    @Test
    public void changeImage() throws Exception {
        //Given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        recipeCommand.setDescription("My Description");
        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);

        //When
        mockMvc.perform(get("/recipe/1/image/change"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/image-upload-form"));
        //Then
        verify(recipeService, only()).findRecipeCommandById(anyLong());
    }

    @Test
    public void renderImageFromDB() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        String s = "Fake image text";
//        Byte[] byteBoxed = new Byte[s.getBytes().length];
//        int i = 0;
//        for (byte primByte :
//                s.getBytes()) {
//            byteBoxed[i++] = primByte;
//        }

        recipeCommand.setImage(s.getBytes());
        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);

        //When
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/image/recipe-image"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responseBytes = response.getContentAsByteArray();
        assertEquals(s.getBytes().length, responseBytes.length);
    }
}