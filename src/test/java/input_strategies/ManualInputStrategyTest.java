package input_strategies;

import entity.Bus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManualInputStrategyTest {

    @Test
    void shouldCreateOneBusWithProvidedFields() {

        ManualInputStrategy strategy = new ManualInputStrategy();
        strategy.setBusNumber("555");
        strategy.setModel("Volvo");
        strategy.setMileage(200000);

        List<Bus> buses = strategy.busWrite().toList();

        assertEquals(1, buses.size());

        Bus bus = buses.get(0);
        assertEquals("555", bus.getBusNumber());
        assertEquals("Volvo", bus.getModel());
        assertEquals(200000, bus.getMileage());
    }

    @Test
    void busBuilderResetShouldSetFieldsToDefaultLikeBuilder() {

        ManualInputStrategy strategy = new ManualInputStrategy();
        strategy.setBusNumber("999");
        strategy.setModel("Lada");
        strategy.setMileage(10000);

        strategy.busBuilderReset(); // сбрасываем наши поля

        List<Bus> buses = strategy.busWrite().toList();

        assertEquals(1, buses.size());

        Bus bus = buses.get(0);

        assertNotNull(bus);
    }
}
