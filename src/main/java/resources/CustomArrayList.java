package resources;

import java.io.UncheckedIOException;
import java.util.AbstractList;
import java.util.Arrays;

public class CustomArrayList<E> extends AbstractList<E> {
    private Object[] array;
    private int lastIndex;

    public CustomArrayList(E [] array) {
        this.array = array;
        this.lastIndex = array.length - 1;
    }

    public CustomArrayList(int size) {
        if (size < 0) throw new IllegalArgumentException("Can't create a custom array with negative size: " + size);
        this.array = new Object[size];
        this.lastIndex = -1;
    }

    public CustomArrayList() {
        this.array = new Object[5];
        this.lastIndex = -1;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index > lastIndex) throw new IndexOutOfBoundsException("There's no such index!");
        return (E) array[index];
    }

    @Override
    public int size() {
        return lastIndex + 1;
    }

    @Override
    public boolean add(E e) {
        if (lastIndex == array.length - 1) {
            array = Arrays.copyOf(array, array.length + 5);
        }
        if (e == null) {
            System.out.println("The value you're trying to add is 'null'!");
            return false;
        }
        array[++lastIndex] = e;
        return true;
    }

    @Override
    public E set(int index, E newValue) {
        if (index < 0 || index > lastIndex) throw new IndexOutOfBoundsException("There's no such index!");
        E oldValue = (E) array[index];
        array[index] = newValue;
        return oldValue;
    }
}
