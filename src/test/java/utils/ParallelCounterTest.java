package utils;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParallelCounterTest {

    @Test
    void shouldReturnZeroForEmptyCollection() {
        List<String> empty = Collections.emptyList();
        int count = ParallelCounter.countOccurrences(empty, "x");
        assertEquals(0, count);
    }

    @Test
    void shouldReturnZeroForNullCollection() {
        int count = ParallelCounter.countOccurrences(null, "x");
        assertEquals(0, count);
    }

    @Test
    void shouldCountOccurrencesInList() {
        List<String> list = Arrays.asList("a", "b", "a", "c", "a");
        int count = ParallelCounter.countOccurrences(list, "a");
        assertEquals(3, count);
    }

    @Test
    void shouldCountOccurrencesInSet() {
        Set<String> set = new HashSet<>(Arrays.asList("a", "b", "a", "c"));
        int count = ParallelCounter.countOccurrences(set, "a");
        assertEquals(1, count);
    }

    @Test
    void shouldCountOccurrencesInLinkedList() {
        List<String> list = new ArrayList<>(Arrays.asList("x", "y", "x", "x"));
        LinkedList<String> linkedList = new LinkedList<>(list);
        int count = ParallelCounter.countOccurrences(linkedList, "x");
        assertEquals(3, count);
    }

    @Test
    void shouldReturnZeroIfElementNotFound() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        int count = ParallelCounter.countOccurrences(list, 100);
        assertEquals(0, count);
    }
}
