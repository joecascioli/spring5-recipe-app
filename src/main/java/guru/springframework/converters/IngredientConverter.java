package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.model.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


public class IngredientConverter {
    @Component
    public static class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {
        private final UnitOfMeasureConverter.UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureConverter;

        public IngredientCommandToIngredient(UnitOfMeasureConverter.UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureConverter) {
            this.unitOfMeasureConverter = unitOfMeasureConverter;
        }

        @Synchronized
        @Nullable
        @Override
        public Ingredient convert(IngredientCommand source) {
            if (source == null) {
                return null;
            }

            final Ingredient ret = new Ingredient();
            ret.setId(source.getId());
            ret.setDescription(source.getDescription());
            ret.setAmount(source.getAmount());
            ret.setUom(unitOfMeasureConverter.convert(source.getUom()));
            return ret;
        }
    }

    @Component
    public static class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {
        private final UnitOfMeasureConverter.UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureConverter;

        public IngredientToIngredientCommand(UnitOfMeasureConverter.UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureConverter) {
            this.unitOfMeasureConverter = unitOfMeasureConverter;
        }

        @Synchronized
        @Nullable
        @Override
        public IngredientCommand convert(Ingredient source) {
            if (source == null) {
                return null;
            }

            final IngredientCommand ret = new IngredientCommand();
            ret.setId(source.getId());
            ret.setAmount(source.getAmount());
            ret.setDescription(source.getDescription());
            ret.setUom(unitOfMeasureConverter.convert(source.getUom()));
            return ret;
        }
    }
}
