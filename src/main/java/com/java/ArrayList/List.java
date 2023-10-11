package com.java.ArrayList;

public interface List<Type> {

    void add(Type value, int pos) throws IndexOutOfBoundsException;

    void pushBack(Type value);

    void replace(Type value, int pos) throws IndexOutOfBoundsException;

    void pop();

    void remove(int pos) throws IndexOutOfBoundsException;

    void find(Type value);

    void getValue(int pos) throws IndexOutOfBoundsException;

    void clear();

}
