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
        this.array = new Object[0];
        this.lastIndex = -1;
    }

    @Override
    public E get(int index) {
        try {
            return (E) array[index];
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Index not found: " + e);
            return null;
        }
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean add(E e) {
        try {
            if (e != null && lastIndex < array.length - 1) {
                array[++lastIndex] = e;
                return true;
            }
            if (e !=null && lastIndex == array.length - 1) {
                array = Arrays.copyOf(array, array.length + 1);
                array[++lastIndex] = e;
                return true;
            }
            if (e == null) {
                System.out.println("The value you're trying to add is 'null'!");
                return false;
            }
        }
        catch (IllegalArgumentException a) {
            System.out.println("The element type you're trying to add does not match this collection: " + a);
            return false;
        }
        return false;
    }
}
