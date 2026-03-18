package input_strategies;

import entity.Bus;
import entity.Models;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RandomInputStrategyTest {

    @Test
    void shouldCreateBusWithRandomBusNumberInExpectedFormat() {
        RandomInputStrategy strategy = new RandomInputStrategy();

        List<Bus> buses = strategy.busWrite().toList();

        assertEquals(1, buses.size());

        Bus bus = buses.get(0);
        String busNumber = bus.getBusNumber();
        String model = bus.getModel();
        int mileage = bus.getMileage();

        assertNotNull(busNumber);
        assertEquals(7, busNumber.length());
        char first = busNumber.charAt(0);
        char second = busNumber.charAt(1);
        char dash = busNumber.charAt(2);

        assertTrue(first >= 'A' && first <= 'Z');
        assertTrue(second >= 'A' && second <= 'Z');
        assertEquals('-', dash);

        boolean isModelValid = false;
        for (Models m : Models.values()) {
            if (m.getValue().equals(model)) {
                isModelValid = true;
                break;
            }
        }
        assertTrue(isModelValid);

        assertTrue(mileage >= 0 && mileage < 500000);
    }

    @Test
    void shouldNotFailOnMultipleCalls() {
        RandomInputStrategy strategy = new RandomInputStrategy();

        for (int i = 0; i < 10; i++) {
            List<Bus> buses = strategy.busWrite().toList();
            assertEquals(1, buses.size());
            int mileage = buses.get(0).getMileage();
            assertTrue(mileage >= 0 && mileage < 500000);
        }
    }

    @Test
    void busBuilderResetDoesNotBreakNextBuild() {
        RandomInputStrategy strategy = new RandomInputStrategy();

        List<Bus> first = strategy.busWrite().toList();
        assertNotNull(first.get(0).getBusNumber());

        strategy.busBuilderReset();

        List<Bus> second = strategy.busWrite().toList();
        assertNotNull(second.get(0).getBusNumber());
        assertTrue(second.get(0).getMileage() >= 0);
        assertTrue(second.get(0).getMileage() < 500000);
    }
}
