package sorting;

import java.util.Comparator;
import java.util.List;

import entity.Bus;

public class InsertionBusSortingStrategy implements BusSortingStrategy {

    @Override
    public void sort(List<Bus> list, Comparator<Bus> comparator) {
        if (list == null || list.size() < 2) {
            return;
        }
        for (int i = 1; i < list.size(); i++) {
            Bus key = list.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }
}