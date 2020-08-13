package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.model.Recipe;
import guru.springframework.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

public class UnitOfMeasureConverter {
    @Component
    public static class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {
        @Synchronized
        @Nullable
        @Override
        public UnitOfMeasure convert(UnitOfMeasureCommand command) {
            if (command == null) {
                return null;
            }
            final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
            unitOfMeasure.setId(command.getId());
            unitOfMeasure.setDescription(command.getDescription());
            return unitOfMeasure;
        }
    }

    @Component
    public static class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {
        @Synchronized
        @Nullable
        @Override
        public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
            if (unitOfMeasure == null) {
                return null;
            }
            final UnitOfMeasureCommand command = new UnitOfMeasureCommand();
            command.setId(unitOfMeasure.getId());
            command.setDescription(unitOfMeasure.getDescription());
            return command;
        }
    }
}
