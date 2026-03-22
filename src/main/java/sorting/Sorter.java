package sorting;

import java.util.Comparator;
import java.util.List;

import entity.Bus;

public class Sorter {
    private BusSortingStrategy strategy;

    public void setStrategy(BusSortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void sort(List<Bus> list, Comparator<Bus> comparator) {
        if (strategy == null) {
            throw new IllegalStateException("Стратегия не установлена");
        }
        strategy.sort(list, comparator);
    }
}