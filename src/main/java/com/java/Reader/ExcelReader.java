package com.java.Reader;

import com.java.ArrayList.ArrayList;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExcelReader implements AbstractReader {

    ArrayList<Person> people;
    ArrayList<Division> divisions;

    private int indexOfDivision(String name) {
        for (int i = 0; i < divisions.getSize(); i++)
            if (divisions.getValue(i).getName().equals(name))
                return i;
        return -1;
    }

    public ExcelReader() {
        people = new ArrayList<>();
        divisions = new ArrayList<>();
    }

    @Override
    public void input(String csvFilePath) {
        int id, salary, division_id;
        String name;
        Gender gender;
        Date date;
        Division division;
        try {
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader csvReader = new CSVReaderBuilder(new FileReader(csvFilePath))
                    .withCSVParser(parser)
                    .build();
            List<String[]> allData = csvReader.readAll();

            for (String[] row : allData.subList(1, allData.size())) {
                id = Integer.parseInt(row[0]);
                name = row[1];
                gender = Gender.valueOf(row[2]);
                date = new SimpleDateFormat("dd.MM.yyyy").parse(row[3]);
                division_id = indexOfDivision(row[4]);
                if (division_id == -1) {
                    division_id = divisions.getSize();
                    division = new Division(division_id, row[4]);
                    divisions.pushBack(division);
                }
                else
                    division = new Division(division_id, row[4]);
                salary = Integer.parseInt(row[5]);
                people.pushBack(new Person(id, name, gender, date, division, salary));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

        @Override
        public void print() {
            for (int i = 0; i < people.getSize(); i++)
                people.getValue(i).print();
        }
}
