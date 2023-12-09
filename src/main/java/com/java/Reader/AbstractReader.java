package com.java.Reader;

public interface AbstractReader {

    /**
     * Ввод данных из .csv файла
     * @param csvFilePath путь к .csv файлу
     */
    void input(String csvFilePath);

    /**
     * Вывод данных в консоль
     */
    void print();
}
