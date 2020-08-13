package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.converters.CategoryConverter.CategoryCommandToCategory;
import guru.springframework.converters.CategoryConverter.CategoryToCategoryCommand;
import guru.springframework.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryConverterTest {

    static final Long ID = 1L;
    static final String DESCRIPTION = "Description";

    CategoryCommandToCategory categoryCommandToCategory;
    CategoryToCategoryCommand categoryToCategoryCommand;

    @BeforeEach
    void setUp() {
        categoryCommandToCategory = new CategoryCommandToCategory();
        categoryToCategoryCommand = new CategoryToCategoryCommand();
    }

    @Test
    void testNullObject() {
        assertNull(categoryCommandToCategory.convert(null));
        assertNull(categoryToCategoryCommand.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(categoryCommandToCategory.convert(new CategoryCommand()));
        assertNotNull(categoryToCategoryCommand.convert(new Category()));
    }

    @Test
    void convertCommandToObject() {
        CategoryCommand command = new CategoryCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);

        Category category = categoryCommandToCategory.convert(command);

        assertNotNull(category);
        assertEquals(ID, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }

    @Test
    void convertObjectToCommand() {
        Category category = new Category();
        category.setId(ID);
        category.setDescription(DESCRIPTION);

        CategoryCommand command = categoryToCategoryCommand.convert(category);

        assertNotNull(command);
        assertEquals(ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
    }
}