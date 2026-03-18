package sorting;

import java.util.List;

public class QuickSortStrategy implements SortStrategy {

    @Override
    public void sort(List<Integer> list) {
        if (list == null || list.size() < 2) {
            return;
        }
        quickSort(list, 0, list.size() - 1);
    }

    private void quickSort(List<Integer> list, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotIndex = partition(list, left, right);
        quickSort(list, left, pivotIndex - 1);
        quickSort(list, pivotIndex + 1, right);
    }

    private int partition(List<Integer> list, int left, int right) {
        int pivot = list.get(right);
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (list.get(j) <= pivot) {
                i = i + 1;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, right);
        return i + 1;
    }

    private void swap(List<Integer> list, int i, int j) {
        if (i == j) {
            return;
        }
        Integer temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}

