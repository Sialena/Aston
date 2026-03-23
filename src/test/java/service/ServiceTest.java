package service;

import entity.Bus;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {

    @Test
    public void testBusListRandomInput_NotEmpty() {
        Service service = new Service();

        List<Bus> buses = service.busListRandomInput(3);

        assertNotNull(buses);
        assertEquals(3, buses.size());
    }

    @Test
    public void testBusListFileInput_NotNull() {
        Service service = new Service();

        List<Bus> buses = service.busListFileInput("src/test/resources/testBuses.json");

        assertNotNull(buses);

        assertTrue(buses.size() >= 0);
    }

    @Test
    public void testBusListManualInput_OneBus() {
        Service service = new Service();

        String input =
                "AB-1234\n" +
                        "1\n" +
                        "10000\n";

        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        List<Bus> buses = service.busListManualInput(1, scanner);

        assertNotNull(buses);
        assertEquals(1, buses.size());

        Bus bus = buses.get(0);
        assertEquals("AB-1234", bus.getBusNumber());
        assertNotNull(bus.getModel());
        assertEquals(10000, bus.getMileage());
    }
}