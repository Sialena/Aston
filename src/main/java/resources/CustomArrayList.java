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
    //вместо null выбрасывает ошибку
    @Override
    public E get(int index) {
<<<<<<< Updated upstream
        if(index < 0 || index > lastIndex) throw new IndexOutOfBoundsException("There's no such index!");
=======
        if (index < 0 || index > lastIndex) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
>>>>>>> Stashed changes
        return (E) array[index];
    }

    @Override
    public int size() {
<<<<<<< Updated upstream
        return lastIndex + 1;
=======
        return lastIndex + 1; // +1 количество элементов
>>>>>>> Stashed changes
    }

    @Override
    public boolean add(E e) {
<<<<<<< Updated upstream
        if (lastIndex == array.length - 1) {
            array = Arrays.copyOf(array, array.length + 5);
        }
=======
>>>>>>> Stashed changes
        if (e == null) {
            System.out.println("The value you're trying to add is 'null'!");
            return false;
        }
<<<<<<< Updated upstream
=======
        // Если массив заполнен, расширяем его
        if (lastIndex == array.length - 1) {
            array = Arrays.copyOf(array, array.length + 5);
        }
>>>>>>> Stashed changes
        array[++lastIndex] = e;
        return true;
    }

    @Override
<<<<<<< Updated upstream
    public E set(int index, E newValue) {
        if (index < 0 || index > lastIndex) throw new IndexOutOfBoundsException("There's no such index!");
        E oldValue = (E) array[index];
        array[index] = newValue;
=======
    public E set(int index, E element) {
        if (index < 0 || index > lastIndex) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
        E oldValue = (E) array[index];
        array[index] = element;
>>>>>>> Stashed changes
        return oldValue;
    }
}
