package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusTest {

    @Test
    void shouldCreateBusViaConstructor() {
        Bus bus = new Bus("100", "Mercedes", 100000);

        assertEquals("100", bus.getBusNumber());
        assertEquals("Mercedes", bus.getModel());
        assertEquals(100000, bus.getMileage());
    }

    @Test
    void shouldCreateBusViaBuilder() {
        Bus bus = new Bus.Builder()
                .busNumber("200")
                .model("Iveco")
                .mileage(120000)
                .build();

        assertEquals("200", bus.getBusNumber());
        assertEquals("Iveco", bus.getModel());
        assertEquals(120000, bus.getMileage());
    }

    @Test
    void shouldBuildWithDefaultValues() {
        Bus bus = new Bus.Builder().build();

        assertEquals("", bus.getBusNumber());
        assertEquals("", bus.getModel());
        assertEquals(0, bus.getMileage());
    }

    @Test
    void equalsAndHashCodeWorkCorrectly() {
        Bus bus1 = new Bus("100", "Mercedes", 50000);
        Bus bus2 = new Bus("100", "Mercedes", 50000);
        Bus bus3 = new Bus("200", "Iveco", 50000);

        assertEquals(bus1, bus2);
        assertEquals(bus1.hashCode(), bus2.hashCode());
        assertNotEquals(bus1, bus3);
    }

    @Test
    void toStringReturnsCorrectFormat() {
        Bus bus = new Bus("100", "Mercedes", 50000);
        String expected = "Bus{busNumber='100', model='Mercedes', mileage=50000}";

        assertEquals(expected, bus.toString());
    }
}
