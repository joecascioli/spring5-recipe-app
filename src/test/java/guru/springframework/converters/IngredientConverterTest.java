package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.IngredientConverter.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientConverter.IngredientToIngredientCommand;
import guru.springframework.converters.UnitOfMeasureConverter.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.converters.UnitOfMeasureConverter.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.model.Ingredient;
import guru.springframework.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientConverterTest {

    static final Long ID = 1L;
    static final String DESCRIPTION = "Description";
    static final BigDecimal AMOUNT = BigDecimal.ONE;
    static final Long UOM_ID = 2L;

    IngredientCommandToIngredient ingredientCommandToIngredient;
    IngredientToIngredientCommand ingredientToIngredientCommand;

    @BeforeEach
    void setUp() {
        ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void testNullObject() {
        assertNull(ingredientCommandToIngredient.convert(null));
        assertNull(ingredientToIngredientCommand.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(ingredientCommandToIngredient.convert(new IngredientCommand()));
        assertNotNull(ingredientToIngredientCommand.convert(new Ingredient()));
    }

    @Test
    void convertCommandToObject() {
        IngredientCommand command = new IngredientCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);
        command.setAmount(AMOUNT);

        UnitOfMeasureCommand uom = new UnitOfMeasureCommand();
        uom.setId(UOM_ID);
        command.setUom(uom);

        Ingredient ingredient = ingredientCommandToIngredient.convert(command);

        assertNotNull(ingredient);
        assertEquals(ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UOM_ID, ingredient.getUom().getId());
    }

    @Test
    void convertObjectToCommand() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        ingredient.setUom(uom);

        IngredientCommand command = ingredientToIngredientCommand.convert(ingredient);

        assertNotNull(command);
        assertEquals(ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(AMOUNT, command.getAmount());
        assertEquals(UOM_ID, command.getUom().getId());
    }
}