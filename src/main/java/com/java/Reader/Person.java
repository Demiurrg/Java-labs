package com.java.Reader;

import java.util.Date;

public class Person implements AbstractPerson {
    int id;
    String name;
    Gender gender;
    Date date;
    Division division;
    int salary;

    public Person(int id, String name, Gender gender, Date date, Division division, int salary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.date = date;
        this.division = division;
        this.salary = salary;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public Division getDivision() {
        return division;
    }

    @Override
    public int getSalary() {
        return salary;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public void setDivision(Division division) {
        this.division = division;
    }

    @Override
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public void print() {
        System.out.println(id + "   " + name + "   " + gender + "   " + date.toString() + "   " + division.getName() + "   " + salary);
    }
}
