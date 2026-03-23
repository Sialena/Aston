package resources;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayListTest {

    @Test
    public void testAddAndGet() {
        CustomArrayList<String> list = new CustomArrayList<>();

        boolean result1 = list.add("one");
        boolean result2 = list.add("two");

        assertTrue(result1);
        assertTrue(result2);
        assertEquals(2, list.size());
        assertEquals("one", list.get(0));
        assertEquals("two", list.get(1));
    }

    @Test
    public void testSizeEmptyList() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        assertEquals(0, list.size());
    }

    @Test
    public void testConstructorWithSize() {
        CustomArrayList<Integer> list = new CustomArrayList<>(10);
        assertEquals(0, list.size());
        assertTrue(list.add(5));
        assertEquals(1, list.size());
        assertEquals(5, list.get(0));
    }

    @Test
    public void testSet() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("old");
        list.add("second");

        String oldValue = list.set(0, "new");

        assertEquals("old", oldValue);
        assertEquals("new", list.get(0));
        assertEquals("second", list.get(1));
    }

    @Test
    public void testExpandArrayWhenFull() {
        CustomArrayList<Integer> list = new CustomArrayList<>(1);

        list.add(1);
        list.add(2);

        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    public void testAddNullReturnsFalse() {
        CustomArrayList<String> list = new CustomArrayList<>();

        boolean result = list.add(null);

        assertFalse(result);
        assertEquals(0, list.size());
    }

    @Test
    public void testGetWithWrongIndexThrowsException() {
        CustomArrayList<String> list = new CustomArrayList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    public void testSetWithWrongIndexThrowsException() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("one");

        assertThrows(IndexOutOfBoundsException.class, () -> list.set(2, "test"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "test"));
    }

    @Test
    public void testConstructorFromArray() {
        String[] data = {"a", "b", "c"};
        CustomArrayList<String> list = new CustomArrayList<>(data);

        assertEquals(3, list.size());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
    }
}