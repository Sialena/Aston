package sorting;

import java.util.Comparator;
import java.util.List;

import entity.Bus;

public class QuickBusSortingStrategy implements BusSortingStrategy {

    @Override
    public void sort(List<Bus> list, Comparator<Bus> comparator) {
        if (list == null || list.size() < 2) {
            return;
        }
        quickSort(list, 0, list.size() - 1, comparator);
    }

    private void quickSort(List<Bus> list, int low, int high, Comparator<Bus> comparator) {
        if (low < high) {
            int pi = partition(list, low, high, comparator);
            quickSort(list, low, pi - 1, comparator);
            quickSort(list, pi + 1, high, comparator);
        }
    }

    private int partition(List<Bus> list, int low, int high, Comparator<Bus> comparator) {
        Bus pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    private void swap(List<Bus> list, int i, int j) {
        if (i == j) return;
        Bus temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}