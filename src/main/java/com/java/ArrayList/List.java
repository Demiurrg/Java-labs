package com.java.ArrayList;

public interface List<Type> {
    /*
    * Добавление элемента в список по индексу
    *
    * @param value Значение нового элемента
    * @param pos Индекс, на который нужно поместить элемент
    * @throws IndexOutOfBoundsException Исключение - выход за пределы массива
    */
    void add(Type value, int pos) throws IndexOutOfBoundsException;

    /*
     * Добавление элемента в список на последнее место
     *
     * @param value Значение нового элемента
     */
    void pushBack(Type value);

    /*
     * Замена элемента по индексу
     *
     * @param value Значение нового элемента
     * @param pos Индекс элемента, значение которого нужно изменить
     * @throws IndexOutOfBoundsException Исключение - выход за пределы массива
     */
    void replace(Type value, int pos) throws IndexOutOfBoundsException;

    /*
     * Удаление последнего элемента списка
     */
    void pop();

    /*
     * Удаление элемента в список по индексу
     *
     * @param pos Индекс удаляемого элемента
     * @throws IndexOutOfBoundsException Исключение - выход за пределы массива
     */
    void remove(int pos) throws IndexOutOfBoundsException;

    /*
     * Поиск элемента в списке по значению
     *
     * @param value Значение искомого элемента
     * @return Возвращает индекс элемента в массиве, если он найден, иначе -1
     */
    int find(Type value);

    /*
     * Получение значение элемента по индексу
     *
     * @param pos Индекс, значение элемент котрого нужно получить
     * @return Возвращает значение нужного элемента
     * @throws IndexOutOfBoundsException Исключение - выход за пределы массива
     */
    Type getValue(int pos) throws IndexOutOfBoundsException;

    /*
    * Очистка списка - удаление всех элементов
    */
    void clear();
}
