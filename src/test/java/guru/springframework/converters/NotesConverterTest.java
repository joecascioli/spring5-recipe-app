package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.converters.NotesConverter.NotesCommandToNotes;
import guru.springframework.converters.NotesConverter.NotesToNotesCommand;
import guru.springframework.model.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesConverterTest {

    static final Long NOTES_ID = 1L;
    static final String RECIPE_NOTES= "Recipe Notes";

    NotesCommandToNotes notesCommandToNotes;
    NotesToNotesCommand notesToNotesCommand;

    @BeforeEach
    void setUp() {
        notesCommandToNotes = new NotesCommandToNotes();
        notesToNotesCommand = new NotesToNotesCommand();
    }

    @Test
    void testNullParameter() {
        assertNull(notesCommandToNotes.convert(null));
        assertNull(notesToNotesCommand.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(notesCommandToNotes.convert(new NotesCommand()));
        assertNotNull(notesToNotesCommand.convert(new Notes()));
    }

    @Test
    void convertCommandToObject() {
        NotesCommand command = new NotesCommand();
        command.setId(NOTES_ID);
        command.setRecipeNotes(RECIPE_NOTES);

        Notes notes = notesCommandToNotes.convert(command);

        assertNotNull(notes);
        assertEquals(NOTES_ID, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }

    @Test
    void convertObjectToCommand() {
        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        notes.setRecipeNotes(RECIPE_NOTES);

        NotesCommand notesCommand = notesToNotesCommand.convert(notes);

        assertNotNull(notesCommand);
        assertEquals(NOTES_ID, notesCommand.getId());
        assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
    }
}