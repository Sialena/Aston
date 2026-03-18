package sorting;

import entity.Bus;

import java.util.Comparator;
import java.util.List;

public interface BusSortingStrategy {
    void sort(List<Bus> buses, Comparator<Bus> comparator);
}

