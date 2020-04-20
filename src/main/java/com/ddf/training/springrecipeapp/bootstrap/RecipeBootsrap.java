package com.ddf.training.springrecipeapp.bootstrap;

import com.ddf.training.springrecipeapp.domain.Ingredient;
import com.ddf.training.springrecipeapp.domain.Notes;
import com.ddf.training.springrecipeapp.domain.Recipe;
import com.ddf.training.springrecipeapp.enums.Difficulty;
import com.ddf.training.springrecipeapp.repositories.CategoryRepository;
import com.ddf.training.springrecipeapp.repositories.RecipeRepository;
import com.ddf.training.springrecipeapp.repositories.UnitOfMeasureRepository;
import com.ddf.training.springrecipeapp.utils.ImageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;

@Slf4j
@Component
public class RecipeBootsrap implements ApplicationListener<ContextRefreshedEvent> {

    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private CategoryRepository categoryRepository;
    final String URL_TO_STATIC_DIR = "src/main/resources/static";


    public RecipeBootsrap(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadPerfectGuacamole();
        loadSpicyGrilledChikenTacos();
    }

    private void loadSpicyGrilledChikenTacos(){
        Recipe spicyGrilledChickenTacos = new Recipe();
        spicyGrilledChickenTacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        spicyGrilledChickenTacos.setCookTime(15);
        spicyGrilledChickenTacos.setPrepTime(20);
        spicyGrilledChickenTacos.setServings(6);
        spicyGrilledChickenTacos.setDescription("Spicy Grilled Chicken Tacos");
        spicyGrilledChickenTacos.setSource("Simply Recipes");
        spicyGrilledChickenTacos.setImageUrl("/images/GrilledChickenTacos.jpg");
        spicyGrilledChickenTacos.setImage(ImageUtils.getImage(URL_TO_STATIC_DIR.concat(spicyGrilledChickenTacos.getImageUrl())));

        spicyGrilledChickenTacos.setDifficulty(Difficulty.MODERATE);

        spicyGrilledChickenTacos.getCategories().addAll(Arrays.asList(categoryRepository.findByCategoryName("Fast Food").get()));

        Notes notes = new Notes();
        notes.setRecipe(spicyGrilledChickenTacos);
        notes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");

        spicyGrilledChickenTacos.setNotes(notes);

        Ingredient chillPowder = new Ingredient();
        chillPowder.setDescription(" ancho chili powder");
        chillPowder.setAmount(BigDecimal.valueOf(2));
        chillPowder.setUom(unitOfMeasureRepository.findByName("tablespoon").get());
        chillPowder.setRecipe(spicyGrilledChickenTacos);

        Ingredient oregano = new Ingredient();
        oregano.setDescription("  dried oregano");
        oregano.setAmount(BigDecimal.valueOf(1));
        oregano.setUom(unitOfMeasureRepository.findByName("teaspoon").get());
        oregano.setRecipe(spicyGrilledChickenTacos);

        Ingredient cumin = new Ingredient();
        cumin.setDescription("  dried cumin");
        cumin.setAmount(BigDecimal.valueOf(1));
        cumin.setUom(unitOfMeasureRepository.findByName("teaspoon").get());
        cumin.setRecipe(spicyGrilledChickenTacos);

        Ingredient sugar = new Ingredient();
        sugar.setDescription("  sugar");
        sugar.setAmount(BigDecimal.valueOf(1));
        sugar.setUom(unitOfMeasureRepository.findByName("teaspoon").get());
        sugar.setRecipe(spicyGrilledChickenTacos);


        Ingredient salt = new Ingredient();
        salt.setDescription("Salt");
        salt.setAmount(BigDecimal.valueOf(5,1));
        salt.setUom(unitOfMeasureRepository.findByName("teaspoon").get());
        salt.setRecipe(spicyGrilledChickenTacos);

        Ingredient chicken = new Ingredient();
        chicken.setDescription("skinless, boneless chicken thighs (1 1/4 pounds)");
        chicken.setAmount(BigDecimal.valueOf(6));
        chicken.setUom(unitOfMeasureRepository.findByName("unit").get());
        chicken.setRecipe(spicyGrilledChickenTacos);

        Ingredient oliveOil = new Ingredient();
        oliveOil.setDescription("tablespoons olive oil");
        oliveOil.setAmount(BigDecimal.valueOf(2));
        oliveOil.setUom(unitOfMeasureRepository.findByName("tablespoon").get());
        oliveOil.setRecipe(spicyGrilledChickenTacos);


        //Adding ingredients to the recipe
        spicyGrilledChickenTacos.getIngredients()
                .addAll(Arrays.asList(chillPowder, oregano, cumin, sugar, chicken, salt, oliveOil));


        spicyGrilledChickenTacos.setDirections("Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges. ");

        recipeRepository.save(spicyGrilledChickenTacos);
        //log.debug("Spicy Grilled Chicken Tacos recipe : ", spicyGrilledChickenTacos);
    }

    private void loadPerfectGuacamole() {
        Recipe perfectGuacamole= new Recipe();
        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        perfectGuacamole.setCookTime(0);
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setServings(4);
        perfectGuacamole.setDescription("Perfect Guacamole");
        perfectGuacamole.setSource("Simply Recipes");
        perfectGuacamole.setImageUrl("/images/Guacamole.jpg");
        perfectGuacamole.setImage(ImageUtils.getImage(URL_TO_STATIC_DIR.concat(perfectGuacamole.getImageUrl())));
        perfectGuacamole.setDifficulty(Difficulty.EASY);

        perfectGuacamole.getCategories().addAll(Arrays.asList(categoryRepository.findByCategoryName("Fast Food").get()));

        Notes notes = new Notes();
        notes.setRecipe(perfectGuacamole);
        notes.setRecipeNotes("Be careful handling chiles if using. Wash your hands thoroughly after handling and do not touch your eyes or the area near your eyes with your hands for several hours.");

        perfectGuacamole.setNotes(notes);

        Ingredient avocado = new Ingredient();
        avocado.setDescription("ripe avocados");
        avocado.setAmount(BigDecimal.valueOf(2));
        avocado.setUom(unitOfMeasureRepository.findByName("unit").get());
        avocado.setRecipe(perfectGuacamole);

        Ingredient salt = new Ingredient();
        salt.setDescription("Salt");
        salt.setAmount(BigDecimal.valueOf(25,2));
        salt.setUom(unitOfMeasureRepository.findByName("teaspoon").get());
        salt.setRecipe(perfectGuacamole);

        Ingredient limeJuice = new Ingredient();
        limeJuice.setDescription("Lime Juice or Lemon Juice");
        limeJuice.setAmount(BigDecimal.valueOf(1));
        limeJuice.setUom(unitOfMeasureRepository.findByName("tablespoon").get());
        limeJuice.setRecipe(perfectGuacamole);

        Ingredient onion = new Ingredient();
        onion.setDescription("Minced red onion or thinly sliced green onion");
        onion.setAmount(BigDecimal.valueOf(2));
        onion.setUom(unitOfMeasureRepository.findByName("tablespoon").get());
        onion.setRecipe(perfectGuacamole);


        Ingredient serrano = new Ingredient();
        serrano.setDescription("serrano chiles, stems and seeds removed, minced");
        serrano.setAmount(BigDecimal.valueOf(2));
        serrano.setUom(unitOfMeasureRepository.findByName("unit").get());
        serrano.setRecipe(perfectGuacamole);

        Ingredient cilantro = new Ingredient();
        cilantro.setDescription("tablespoons cilantro (leaves and tender stems), finely chopped");
        cilantro.setAmount(BigDecimal.valueOf(2));
        cilantro.setUom(unitOfMeasureRepository.findByName("tablespoon").get());
        cilantro.setRecipe(perfectGuacamole);

        Ingredient blackPepper = new Ingredient();
        blackPepper.setDescription(" freshly grated black pepper");
        blackPepper.setAmount(BigDecimal.valueOf(1));
        blackPepper.setUom(unitOfMeasureRepository.findByName("dash").get());
        blackPepper.setRecipe(perfectGuacamole);

        Ingredient tomato = new Ingredient();
        tomato.setDescription("ripe tomato, seeds and pulp removed, chopped");
        tomato.setAmount(BigDecimal.valueOf(5,1));
        tomato.setUom(unitOfMeasureRepository.findByName("unit").get());
        tomato.setRecipe(perfectGuacamole);

        Ingredient radishes = new Ingredient();
        radishes.setDescription("Red radishes or jicama, to garnish");
        radishes.setAmount(BigDecimal.valueOf(5));
        radishes.setUom(unitOfMeasureRepository.findByName("unit").get());
        radishes.setRecipe(perfectGuacamole);

        Ingredient tortillaChips = new Ingredient();
        tortillaChips.setDescription("Tortilla chips, to serve");
        tortillaChips.setAmount(BigDecimal.valueOf(5));
        tortillaChips.setUom(unitOfMeasureRepository.findByName("unit").get());
        tortillaChips.setRecipe(perfectGuacamole);

        //Adding ingredients to the recipe
        perfectGuacamole.getIngredients()
                .addAll(Arrays.asList(avocado, salt, onion, tomato, limeJuice, serrano, cilantro, blackPepper, radishes, tortillaChips));

        perfectGuacamole.setDirections(" Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                " Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                " Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                " Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "\n" +
                " \n");

        recipeRepository.save(perfectGuacamole);
        //log.debug("Perfect Guacamole recipe : ", perfectGuacamole);
    }
}
