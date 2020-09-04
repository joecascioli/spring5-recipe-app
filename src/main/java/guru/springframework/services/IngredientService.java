package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.model.Ingredient;

public interface IngredientService {
    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id);
}
