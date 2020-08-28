package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientConverter;
import guru.springframework.converters.IngredientConverter.IngredientToIngredientCommand;
import guru.springframework.converters.UnitOfMeasureConverter;
import guru.springframework.model.Ingredient;
import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureConverter.UnitOfMeasureToUnitOfMeasureCommand());

    @Mock
    RecipeRepository recipeRepository;

    IngredientService ingredientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand);
    }

    @Test
    void findByRecipeIdAndIngredientId() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(2L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(3L);

        recipe.addIngredient(ingredient);
        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }
}