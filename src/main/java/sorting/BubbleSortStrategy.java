package sorting;

import java.util.List;

public class BubbleSortStrategy implements SortStrategy {

    @Override
    public void sort(List<Integer> list) {
        if (list == null || list.size() < 2) {
            return;
        }

        int n = list.size();
        boolean swapped = true;

        while (swapped) {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                Integer left = list.get(i);
                Integer right = list.get(i + 1);

                if (left > right) {
                    list.set(i, right);
                    list.set(i + 1, left);
                    swapped = true;
                }
            }
            n = n - 1;
        }
    }
}

