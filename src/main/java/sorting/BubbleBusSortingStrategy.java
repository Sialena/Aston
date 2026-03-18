package sorting;

import entity.Bus;

import java.util.Comparator;
import java.util.List;

public class BubbleBusSortingStrategy implements BusSortingStrategy {

    @Override
    public void sort(List<Bus> buses, Comparator<Bus> comparator) {
        if (buses == null || comparator == null) {
            return;
        }

        if (buses.size() < 2) {
            return;
        }

        int n = buses.size();
        boolean swapped = true;

        while (swapped) {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                Bus left = buses.get(i);
                Bus right = buses.get(i + 1);

                if (comparator.compare(left, right) > 0) {
                    buses.set(i, right);
                    buses.set(i + 1, left);
                    swapped = true;
                }
            }
            n = n - 1;
        }
    }
}

