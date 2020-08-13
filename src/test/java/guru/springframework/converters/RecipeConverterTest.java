package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.CategoryConverter.CategoryCommandToCategory;
import guru.springframework.converters.CategoryConverter.CategoryToCategoryCommand;
import guru.springframework.converters.IngredientConverter.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientConverter.IngredientToIngredientCommand;
import guru.springframework.converters.NotesConverter.NotesCommandToNotes;
import guru.springframework.converters.NotesConverter.NotesToNotesCommand;
import guru.springframework.converters.UnitOfMeasureConverter.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.converters.UnitOfMeasureConverter.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeConverterTest  {

    static final Long RECIPE_ID = 1L;
    static final Integer RECIPE_COOK_TIME = 5;
    static final Integer RECIPE_PREP_TIME = 7;
    static final String RECIPE_DESCRIPTION = "My Recipe";
    static final String RECIPE_DIRECTIONS = "Directions";
    static final Difficulty RECIPE_DIFFICULTY = Difficulty.EASY;
    static final Integer RECIPE_SERVINGS = 3;
    static final String RECIPE_SOURCE = "Source";
    static final String RECIPE_URL = "www.website.com";
    static final Long RECIPE_CAT_ID1 = 1L;
    static final Long RECIPE_CAT_ID2 = 2L;
    static final Long RECIPE_INGRED_ID1 = 3L;
    static final Long RECIPE_INGRED_ID2 = 4L;
    static final Long RECIPE_NOTES_ID = 9L;
    RecipeConverter.RecipeCommandToRecipe recipeCommandToRecipe;
    RecipeConverter.RecipeToRecipeCommand recipeToRecipeCommand;

    @BeforeEach
    void setUp() {
        recipeCommandToRecipe = new RecipeConverter.RecipeCommandToRecipe(
                new NotesCommandToNotes(), new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()));

        recipeToRecipeCommand = new RecipeConverter.RecipeToRecipeCommand(
                new NotesToNotesCommand(), new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()));
    }

    @Test
    void testNullObject() {
        assertNull(recipeCommandToRecipe.convert(null));
        assertNull(recipeToRecipeCommand.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(recipeCommandToRecipe.convert(new RecipeCommand()));
        assertNotNull(recipeToRecipeCommand.convert(new Recipe()));
    }

    @Test
    void convertCommandToObject() {
        RecipeCommand command = new RecipeCommand();
        command.setId(RECIPE_ID);
        command.setCookTime(RECIPE_COOK_TIME);
        command.setPrepTime(RECIPE_PREP_TIME);
        command.setServings(RECIPE_SERVINGS);
        command.setDifficulty(RECIPE_DIFFICULTY);
        command.setDescription(RECIPE_DESCRIPTION);
        command.setDirections(RECIPE_DIRECTIONS);
        command.setSource(RECIPE_SOURCE);
        command.setUrl(RECIPE_URL);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(RECIPE_NOTES_ID);
        command.setNotes(notesCommand);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(RECIPE_INGRED_ID1);
        command.getIngredients().add(ingredientCommand);

        IngredientCommand ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(RECIPE_INGRED_ID2);
        command.getIngredients().add(ingredientCommand1);

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(RECIPE_CAT_ID1);
        command.getCategories().add(categoryCommand);

        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(RECIPE_CAT_ID2);
        command.getCategories().add(categoryCommand1);

        Recipe recipe = recipeCommandToRecipe.convert(command);

        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(RECIPE_COOK_TIME, recipe.getCookTime());
        assertEquals(RECIPE_PREP_TIME, recipe.getPrepTime());
        assertEquals(RECIPE_DESCRIPTION, recipe.getDescription());
        assertEquals(RECIPE_DIRECTIONS, recipe.getDirections());
        assertEquals(RECIPE_DIFFICULTY, recipe.getDifficulty());
        assertEquals(RECIPE_SERVINGS, recipe.getServings());
        assertEquals(RECIPE_SOURCE, recipe.getSource());
        assertEquals(RECIPE_URL, recipe.getUrl());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }

    @Test
    void convertObjectToCommand() {
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setCookTime(RECIPE_COOK_TIME);
        recipe.setPrepTime(RECIPE_PREP_TIME);
        recipe.setServings(RECIPE_SERVINGS);
        recipe.setDifficulty(RECIPE_DIFFICULTY);
        recipe.setDescription(RECIPE_DESCRIPTION);
        recipe.setDirections(RECIPE_DIRECTIONS);
        recipe.setSource(RECIPE_SOURCE);
        recipe.setUrl(RECIPE_URL);

        Notes notes = new Notes();
        notes.setId(RECIPE_NOTES_ID);
        recipe.setNotes(notes);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(RECIPE_INGRED_ID1);
        recipe.getIngredients().add(ingredient);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(RECIPE_INGRED_ID2);
        recipe.getIngredients().add(ingredient1);

        Category category = new Category();
        category.setId(RECIPE_CAT_ID1);
        recipe.getCategories().add(category);

        Category category1 = new Category();
        category1.setId(RECIPE_CAT_ID2);
        recipe.getCategories().add(category1);

        //WHEN
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);

        assertNotNull(recipeCommand);
        assertEquals(RECIPE_ID, recipeCommand.getId());
        assertEquals(RECIPE_COOK_TIME, recipeCommand.getCookTime());
        assertEquals(RECIPE_PREP_TIME, recipeCommand.getPrepTime());
        assertEquals(RECIPE_DESCRIPTION, recipeCommand.getDescription());
        assertEquals(RECIPE_DIRECTIONS, recipeCommand.getDirections());
        assertEquals(RECIPE_DIFFICULTY, recipeCommand.getDifficulty());
        assertEquals(RECIPE_SERVINGS, recipeCommand.getServings());
        assertEquals(RECIPE_SOURCE, recipeCommand.getSource());
        assertEquals(RECIPE_URL, recipeCommand.getUrl());
        assertEquals(2, recipeCommand.getCategories().size());
        assertEquals(2, recipeCommand.getIngredients().size());
    }

}