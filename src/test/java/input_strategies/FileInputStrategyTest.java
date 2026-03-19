package input_strategies;

import entity.Bus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class FileInputStrategyTest {

    private static final String VALID_JSON_PATH = "src/test/resources/testBuses.json";

    @Test
    void shouldReadBusesFromFile() throws Exception {
        FileInputStrategy strategy = new FileInputStrategy();
        strategy.setFilepath(VALID_JSON_PATH);

        List<Bus> buses = strategy.busWrite().toList();

        assertEquals(2, buses.size());

        assertEquals("100", buses.get(0).getBusNumber());
        assertEquals("Mercedes", buses.get(0).getModel());
        assertEquals(100000, buses.get(0).getMileage());

        assertEquals("200", buses.get(1).getBusNumber());
        assertEquals("Iveco", buses.get(1).getModel());
        assertEquals(150000, buses.get(1).getMileage());
    }

    @Test
    void shouldThrowExceptionWhenFileNotFound() {
        FileInputStrategy strategy = new FileInputStrategy();
        strategy.setFilepath("not_existing_file.json");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            strategy.busWrite().toList();
        });

        assertTrue(exception.getMessage().contains("Can't read from this file"));
    }
}
