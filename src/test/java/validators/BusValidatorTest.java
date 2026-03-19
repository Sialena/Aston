package validators;

import entity.Bus;
import entity.Models;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusValidatorTest {

    private final BusValidator validator = new BusValidator();

    @Test
    void validateBusNumber_shouldAcceptValidFormat() {
        assertTrue(validator.validateBusNumber("AA-1234"));
        assertTrue(validator.validateBusNumber("xx-9999"));
    }

    @Test
    void validateBusNumber_shouldRejectNullOrEmpty() {
        assertFalse(validator.validateBusNumber(null));
        assertFalse(validator.validateBusNumber(""));
        assertFalse(validator.validateBusNumber("   "));
    }

    @Test
    void validateBusNumber_shouldRejectWrongFormat() {
        assertFalse(validator.validateBusNumber("A-1234"));
        assertFalse(validator.validateBusNumber("AAA-1234"));
        assertFalse(validator.validateBusNumber("AB-123"));
        assertFalse(validator.validateBusNumber("AB1234"));
        assertFalse(validator.validateBusNumber("AB-12345"));
    }

    @Test
    void validateBusModel_shouldAcceptValidRange() {
        assertTrue(validator.validateBusModel(1));
        assertTrue(validator.validateBusModel(Models.values().length));
    }

    @Test
    void validateBusModel_shouldRejectOutOfRange() {
        assertFalse(validator.validateBusModel(0));
        assertFalse(validator.validateBusModel(-1));
        assertFalse(validator.validateBusModel(Models.values().length + 1));
    }

    @Test
    void validateMileage_shouldAcceptPositiveOrZero() {
        assertTrue(validator.validateMileage(0));
        assertTrue(validator.validateMileage(1000));
        assertTrue(validator.validateMileage(499999));
    }

    @Test
    void validateMileage_shouldRejectNegative() {
        assertFalse(validator.validateMileage(-1));
        assertFalse(validator.validateMileage(-100000));
    }

    @Test
    void validateBusObject_shouldAcceptValidBus() {
        Bus validBus = new Bus("AA-1234", Models.values()[0].getValue(), 100000);
        assertTrue(validator.validateBusObject(validBus));
    }

    @Test
    void validateBusObject_shouldRejectBusWithInvalidNumber() {
        Bus invalidBus = new Bus("A-1234", Models.values()[0].getValue(), 100000);
        assertFalse(validator.validateBusObject(invalidBus));
    }

    @Test
    void validateBusObject_shouldRejectBusWithInvalidModel() {
        Bus invalidBus = new Bus("AA-1234", "NonExistentModel", 100000);
        assertFalse(validator.validateBusObject(invalidBus));
    }

    @Test
    void validateBusObject_shouldRejectBusWithNegativeMileage() {
        Bus invalidBus = new Bus("AA-1234", Models.values()[0].getValue(), -1000);
        assertFalse(validator.validateBusObject(invalidBus));
    }
}
