package com.java.ArrayList;

public class ArrayList<Type> implements List<Type> {

    private Object[] array;
    private int size = 0;

    ArrayList() {
        array = new Object[0];
    }

    private void arrayRightShift(int pos) {
        for (int i = size-1; i > pos; i--)
            array[i] = array[i-1];
    }

    private void arrayLeftShift(int pos) {
        for (int i = pos; i < size; i++)
            array[i] = array[i+1];
    }

    private void increaseSize() {
        size++;
        Object[] new_array = new Object[size];
        System.arraycopy(array, 0, new_array, 0, size-1);
        array = new_array;
    }

    private void decreaseSize() {
        size--;
        Object[] new_array = new Object[size];
        System.arraycopy(array, 0, new_array, 0, size);
        array = new_array;
    }

    @Override
    public void add(Object value, int pos) throws IndexOutOfBoundsException {
        if (pos < 0 || pos >= size))
            throw new IndexOutOfBoundsException("Index is outside the bounds of the array");
        increaseSize();
        arrayRightShift(pos);
        array[pos] = value;
    }

    @Override
    public void pushBack(Object value) {
        increaseSize();
        array[size-1] = value;
    }

    @Override
    public void replace(Object value, int pos) throws IndexOutOfBoundsException {
        if (pos < 0 || pos >= size)
            throw new IndexOutOfBoundsException("Index is outside the bounds of the array");
        array[pos] = value;
    }

    @Override
    public void pop() {
        decreaseSize();
    }

    @Override
    public void remove(int pos) throws IndexOutOfBoundsException {
        if (pos < 0 || pos >= size)
            throw new IndexOutOfBoundsException("Index is outside the bounds of the array");
        arrayLeftShift(pos);
        decreaseSize();
    }

    @Override
    public int find(Object value) {
        for (int i = 0; i < size; i++)
            if (array[i].equals(value))
                return i;
        return -1;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Type getValue(int pos) throws IndexOutOfBoundsException {
        if (pos < 0 || pos >= size)
            throw new IndexOutOfBoundsException("Index is outside the bounds of the array");
        return (Type) array[pos];
    }

    @Override
    public void clear() {
        size = 0;
        array = new Object[0];
    }
}
