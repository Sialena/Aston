package input_strategies;

import entity.Bus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StrategySelectorTest {

    private StrategySelector selector;

    @BeforeEach
    void setUp() {
        selector = new StrategySelector();
    }

    @Test
    void shouldReturnBusesFromSetStrategy() {

        InputStrategyInterface mockStrategy = new ManualInputStrategy();
        mockStrategy.setBusNumber("100");
        mockStrategy.setModel("Mercedes");
        mockStrategy.setMileage(50000);

        selector.setStrategy(mockStrategy);

        List<Bus> buses = selector.getBuses().toList();

        assertEquals(1, buses.size());
        assertEquals("100", buses.get(0).getBusNumber());
        assertEquals("Mercedes", buses.get(0).getModel());
        assertEquals(50000, buses.get(0).getMileage());
    }

    @Test
    void shouldThrowNPEWhenStrategyIsNull() {

        selector.setStrategy(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            selector.getBuses().toList();
        });
    }
}
