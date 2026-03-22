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
        if (buses == null || buses.isEmpty()) {
            return;
        }

        List<Integer> evenIndices = new ArrayList<>();
        for (int i = 0; i < buses.size(); i++) {
            Number fieldValue = fieldFunction.apply(buses.get(i));
            if (fieldValue == null) {
                continue;
            }
            if (fieldValue.intValue() % 2 == 0) {
                evenIndices.add(i);
            }
        }

        if (evenIndices.size() < 2) {
            return;
        }

        List<Bus> evenBuses = new ArrayList<>();
        for (int idx : evenIndices) {
            evenBuses.add(buses.get(idx));
        }

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

        baseStrategy.sort(evenBuses, busComparator);

        for (int k = 0; k < evenIndices.size(); k++) {
            buses.set(evenIndices.get(k), evenBuses.get(k));
        }
    }
}
