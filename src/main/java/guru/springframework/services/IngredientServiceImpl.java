package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientConverter.IngredientToIngredientCommand;
import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService{
    private RecipeRepository recipeRepository;
    private IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipe =recipeRepository.findById(recipeId);

        if(!recipe.isPresent())
            throw new RuntimeException("Error finding ingredient.");


        Optional<IngredientCommand> ingredientCommand = recipe.get().getIngredients().stream()
                .filter(ingredient1 -> ingredient1.getId().equals(ingredientId))
                .map(ingredient1 -> ingredientToIngredientCommand.convert(ingredient1))
                .findFirst();
        if(!ingredientCommand.isPresent()){
            throw new RuntimeException("Ingredient not found");
        }

        return ingredientCommand.get();
    }
}
