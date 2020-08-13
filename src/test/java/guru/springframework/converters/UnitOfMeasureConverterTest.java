package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureConverter.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.converters.UnitOfMeasureConverter.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureConverterTest {

    static final String UMO_DESCRIPTION = "UOM Description";
    static final Long UOM_ID = 1L;

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    @BeforeEach
    void setUp() {
        unitOfMeasureCommandToUnitOfMeasure = new UnitOfMeasureCommandToUnitOfMeasure();
        unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void testNullParameter() {
        assertNull(unitOfMeasureCommandToUnitOfMeasure.convert(null));
        assertNull(unitOfMeasureToUnitOfMeasureCommand.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(unitOfMeasureToUnitOfMeasureCommand.convert(new UnitOfMeasure()));
        assertNotNull(unitOfMeasureCommandToUnitOfMeasure.convert(new UnitOfMeasureCommand()));
    }

    @Test
    void convertCommandToObject() {
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(UOM_ID);
        command.setDescription(UMO_DESCRIPTION);

        UnitOfMeasure commandRet = unitOfMeasureCommandToUnitOfMeasure.convert(command);

        assertNotNull(commandRet);
        assertEquals(UOM_ID, commandRet.getId());
        assertEquals(UMO_DESCRIPTION, commandRet.getDescription());
    }

    @Test
    void convertObjectToCommand() {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        uom.setDescription(UMO_DESCRIPTION);

        UnitOfMeasureCommand uomRet = unitOfMeasureToUnitOfMeasureCommand.convert(uom);

        assertNotNull(uomRet);
        assertEquals(UOM_ID, uomRet.getId());
        assertEquals(UMO_DESCRIPTION, uomRet.getDescription());
    }
}