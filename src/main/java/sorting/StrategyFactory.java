package sorting;

import entity.Bus;
import sorting.InsertionBusSortingStrategy;
import sorting.QuickBusSortingStrategy;

import java.util.Comparator;
import java.util.function.Function;

public class StrategyFactory {

    public SortStrategy createNumberStrategy(StrategyType type) {
        if (type == null) {
            throw new IllegalArgumentException("StrategyType is null");
        }

        switch (type) {
            case BUBBLE:
                return new BubbleSortStrategy();
            case QUICK:
                return new QuickSortStrategy();
            case INSERTION:
                return new InsertionSortStrategy();
            default:
                throw new IllegalArgumentException("Unsupported number strategy: " + type);
        }
    }

    public BusSortingStrategy createBusStrategy(StrategyType type) {
        if (type == null) {
            throw new IllegalArgumentException("StrategyType is null");
        }

        switch (type) {
            case BUBBLE:
                return new BubbleBusSortingStrategy();
            //добавила QUICK и INSERTION
            case QUICK:
            return new QuickBusSortingStrategy();
            case INSERTION:
            return new InsertionBusSortingStrategy(); 
            case EVEN_ODD:
                return new EvenFieldSortStrategy(
                        new BubbleBusSortingStrategy(),
                        new Function<Bus, Number>() {
                            @Override
                            public Number apply(Bus bus) {
                                return bus.getMileage();
                            }
                        },
                        new Comparator<Number>() {
                            @Override
                            public int compare(Number a, Number b) {
                                return Integer.compare(a.intValue(), b.intValue());
                            }
                        }
                );
            default:
                throw new IllegalArgumentException("Unsupported bus strategy: " + type);
        }
    }
}

