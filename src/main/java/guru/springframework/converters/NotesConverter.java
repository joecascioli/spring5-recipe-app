package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.model.Ingredient;
import guru.springframework.model.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

public class NotesConverter {
    @Component
    public static class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
        @Synchronized
        @Nullable
        @Override
        public Notes convert(NotesCommand source) {
            if (source == null) {
                return null;
            }
            Notes ret = new Notes();
            ret.setId(source.getId());
            ret.setRecipeNotes(source.getRecipeNotes());
            return ret;
        }
    }

    @Component
    public static class NotesToNotesCommand implements Converter<Notes, NotesCommand> {
        @Synchronized
        @Nullable
        @Override
        public NotesCommand convert(Notes source) {
            if (source == null) {
                return null;
            }
            NotesCommand ret = new NotesCommand();
            ret.setId(source.getId());
            ret.setRecipeNotes(source.getRecipeNotes());
            return ret;
        }
    }

}
