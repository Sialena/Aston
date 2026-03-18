import entity.Bus;
import org.junit.jupiter.api.Test;
import sorting.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortingStrategiesTest {

    @Test
    void bubbleSort_sortsInPlace() {
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 1, 4, 2, 8));
        SortStrategy strategy = new BubbleSortStrategy();

        strategy.sort(list);

        assertEquals(Arrays.asList(1, 2, 4, 5, 8), list);
    }

    @Test
    void quickSort_sortsInPlace() {
        List<Integer> list = new ArrayList<>(Arrays.asList(3, -1, 0, 3, 2, 2));
        SortStrategy strategy = new QuickSortStrategy();

        strategy.sort(list);

        assertEquals(Arrays.asList(-1, 0, 2, 2, 3, 3), list);
    }

    @Test
    void insertionSort_sortsInPlace() {
        List<Integer> list = new ArrayList<>(Arrays.asList(9, 7, 5, 3, 1));
        SortStrategy strategy = new InsertionSortStrategy();

        strategy.sort(list);

        assertEquals(Arrays.asList(1, 3, 5, 7, 9), list);
    }

    @Test
    void bubbleBusSorting_sortsByComparator() {
        List<Bus> buses = new ArrayList<>();
        buses.add(new Bus("AA-0001", "X", 10));
        buses.add(new Bus("AA-0002", "X", 5));
        buses.add(new Bus("AA-0003", "X", 20));

        BusSortingStrategy strategy = new BubbleBusSortingStrategy();
        Comparator<Bus> byMileageAsc = new Comparator<Bus>() {
            @Override
            public int compare(Bus a, Bus b) {
                return Integer.compare(a.getMileage(), b.getMileage());
            }
        };

        strategy.sort(buses, byMileageAsc);

        assertEquals(5, buses.get(0).getMileage());
        assertEquals(10, buses.get(1).getMileage());
        assertEquals(20, buses.get(2).getMileage());
    }

    @Test
    void evenFieldSort_replacesOnlyEvenFieldElements() {
        Bus b1 = new Bus("AA-0001", "X", 3);  // odd -> stays
        Bus b2 = new Bus("AA-0002", "X", 10); // even -> replaced
        Bus b3 = new Bus("AA-0003", "X", 5);  // odd -> stays
        Bus b4 = new Bus("AA-0004", "X", 2);  // even -> replaced

        List<Bus> original = new ArrayList<>(Arrays.asList(b1, b2, b3, b4));

        BusSortingStrategy base = new BubbleBusSortingStrategy();
        EvenFieldSortStrategy strategy = new EvenFieldSortStrategy(
                base,
                bus -> bus.getMileage(),
                new Comparator<Number>() {
                    @Override
                    public int compare(Number a, Number b) {
                        return Integer.compare(a.intValue(), b.intValue());
                    }
                }
        );

        strategy.sort(original, null);

        // copy sorted by mileage asc: [2, 3, 5, 10]
        // replace even-mileage positions (b2 and b4) with next from sorted copy in order -> 2 then 3
        assertSame(b1, original.get(0));          // odd stays
        assertEquals(2, original.get(1).getMileage());
        assertSame(b3, original.get(2));          // odd stays
        assertEquals(3, original.get(3).getMileage());
    }

    @Test
    void strategyType_fromString_parsesIgnoringCaseAndSpaces() {
        assertEquals(StrategyType.BUBBLE, StrategyType.fromString(" bubble "));
        assertEquals(StrategyType.QUICK, StrategyType.fromString("QUICK"));
        assertEquals(StrategyType.INSERTION, StrategyType.fromString("InSeRtIoN"));
        assertEquals(StrategyType.EVEN_ODD, StrategyType.fromString("even_odd"));
    }

    @Test
    void strategyFactory_createsNumberStrategies() {
        StrategyFactory factory = new StrategyFactory();

        SortStrategy bubble = factory.createNumberStrategy(StrategyType.BUBBLE);
        SortStrategy quick = factory.createNumberStrategy(StrategyType.QUICK);
        SortStrategy insertion = factory.createNumberStrategy(StrategyType.INSERTION);

        assertTrue(bubble instanceof BubbleSortStrategy);
        assertTrue(quick instanceof QuickSortStrategy);
        assertTrue(insertion instanceof InsertionSortStrategy);
    }

    @Test
    void strategyFactory_createsBusStrategies() {
        StrategyFactory factory = new StrategyFactory();

        BusSortingStrategy bubble = factory.createBusStrategy(StrategyType.BUBBLE);
        BusSortingStrategy evenOdd = factory.createBusStrategy(StrategyType.EVEN_ODD);

        assertTrue(bubble instanceof BubbleBusSortingStrategy);
        assertTrue(evenOdd instanceof EvenFieldSortStrategy);
    }
}

