package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeConverter;
import guru.springframework.converters.RecipeConverter.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeConverter.RecipeToRecipeCommand;
import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    static final String DESCRIPTION = "Description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    @Test
    public void testSaveOfDescription() {
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        testRecipeCommand.setDescription(DESCRIPTION);
        RecipeCommand savRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        assertEquals(DESCRIPTION, savRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(), savRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(), savRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savRecipeCommand.getIngredients().size());
    }
}