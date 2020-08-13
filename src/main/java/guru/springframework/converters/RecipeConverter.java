package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.CategoryConverter.CategoryCommandToCategory;
import guru.springframework.converters.CategoryConverter.CategoryToCategoryCommand;
import guru.springframework.converters.IngredientConverter.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientConverter.IngredientToIngredientCommand;
import guru.springframework.converters.NotesConverter.NotesCommandToNotes;
import guru.springframework.converters.NotesConverter.NotesToNotesCommand;
import guru.springframework.model.Notes;
import guru.springframework.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

public class RecipeConverter {

    @Component
    public static class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
        private final NotesCommandToNotes notesConverter;
        private final CategoryCommandToCategory categoryConverter;
        private final IngredientCommandToIngredient ingredientConverter;

        public RecipeCommandToRecipe(NotesCommandToNotes notesConverter, CategoryCommandToCategory categoryConverter, IngredientCommandToIngredient ingredientConverter) {
            this.notesConverter = notesConverter;
            this.categoryConverter = categoryConverter;
            this.ingredientConverter = ingredientConverter;
        }

        @Synchronized
        @Nullable
        @Override
        public Recipe convert(RecipeCommand command) {
            if (command == null) {
                return null;
            }

            final Recipe recipe = new Recipe();
            recipe.setId(command.getId());
            recipe.setCookTime(command.getCookTime());
            recipe.setPrepTime(command.getPrepTime());
            recipe.setDescription(command.getDescription());
            recipe.setDirections(command.getDirections());
            recipe.setDifficulty(command.getDifficulty());
            recipe.setServings(command.getServings());
            recipe.setSource(command.getSource());
            recipe.setUrl(command.getUrl());
            Notes notes = notesConverter.convert(command.getNotes());
            recipe.setNotes(notes);

            if(command.getCategories() != null){
                command.getCategories().forEach( categoryCommand -> recipe.getCategories().add(categoryConverter.convert(categoryCommand)));
            }

            if(command.getIngredients() != null){
                command.getIngredients().forEach( ingredientCommand -> recipe.getIngredients().add(ingredientConverter.convert(ingredientCommand)));
            }
            return recipe;
        }
    }

    @Component
    public static class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
        private final NotesToNotesCommand notesConverter;
        private final CategoryToCategoryCommand categoryConverter;
        private final IngredientToIngredientCommand ingredientConverter;

        public RecipeToRecipeCommand(NotesToNotesCommand notesConverter, CategoryToCategoryCommand categoryConverter, IngredientToIngredientCommand ingredientConverter) {
            this.notesConverter = notesConverter;
            this.categoryConverter = categoryConverter;
            this.ingredientConverter = ingredientConverter;
        }

        @Synchronized
        @Nullable
        @Override
        public RecipeCommand convert(Recipe recipe) {
            if (recipe == null) {
                return null;
            }
            final RecipeCommand command = new RecipeCommand();
            command.setId(recipe.getId());
            command.setCookTime(recipe.getCookTime());
            command.setPrepTime(recipe.getPrepTime());
            command.setDescription(recipe.getDescription());
            command.setDirections(recipe.getDirections());
            command.setDifficulty(recipe.getDifficulty());
            command.setServings(recipe.getServings());
            command.setSource(recipe.getSource());
            command.setUrl(recipe.getUrl());
            command.setNotes(notesConverter.convert(recipe.getNotes()));

            if(recipe.getCategories() != null){
                recipe.getCategories().forEach( category -> command.getCategories().add(categoryConverter.convert(category)));
            }

            if(recipe.getIngredients() != null){
                recipe.getIngredients().forEach( ingredient -> command.getIngredients().add(ingredientConverter.convert(ingredient)));
            }
            return command;
        }
    }

    }
