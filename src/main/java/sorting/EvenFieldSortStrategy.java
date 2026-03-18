package sorting;

import entity.Bus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class EvenFieldSortStrategy implements BusSortingStrategy {

    private final BusSortingStrategy baseStrategy;
    private final Function<Bus, Number> fieldFunction;
    private final Comparator<Number> numberComparator;

    public EvenFieldSortStrategy(BusSortingStrategy baseStrategy,
                                 Function<Bus, Number> fieldFunction,
                                 Comparator<Number> numberComparator) {
        this.baseStrategy = baseStrategy;
        this.fieldFunction = fieldFunction;
        this.numberComparator = numberComparator;
    }

    @Override
    public void sort(List<Bus> buses, Comparator<Bus> ignoredComparator) {
        if (buses == null || buses.size() < 2) {
            return;
        }

        List<Bus> copy = new ArrayList<>(buses);

        Comparator<Bus> busComparator = new Comparator<Bus>() {
            @Override
            public int compare(Bus first, Bus second) {
                Number firstValue = fieldFunction.apply(first);
                Number secondValue = fieldFunction.apply(second);

                if (firstValue == null && secondValue == null) {
                    return 0;
                }

                if (firstValue == null) {
                    return -1;
                }

                if (secondValue == null) {
                    return 1;
                }

                return numberComparator.compare(firstValue, secondValue);
            }
        };

        baseStrategy.sort(copy, busComparator);

        int positionInSorted = 0;

        for (int i = 0; i < buses.size(); i++) {
            Bus currentBus = buses.get(i);
            Number fieldValue = fieldFunction.apply(currentBus);

            if (fieldValue == null) {
                continue;
            }

            int intValue = fieldValue.intValue();

            if (intValue % 2 == 0) {
                if (positionInSorted < copy.size()) {
                    Bus newBus = copy.get(positionInSorted);
                    buses.set(i, newBus);
                    positionInSorted = positionInSorted + 1;
                }
            }
        }
    }
}

