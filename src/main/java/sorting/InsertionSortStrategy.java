package sorting;

import java.util.List;

public class InsertionSortStrategy implements SortStrategy {

    @Override
    public void sort(List<Integer> list) {
        if (list == null || list.size() < 2) {
            return;
        }

        int n = list.size();
        for (int i = 1; i < n; i++) {
            Integer current = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j) > current) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }

            list.set(j + 1, current);
        }
    }
}

