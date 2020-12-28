package com.company;

public class Colleg {
    private String name;
    private String pos;
    private String tel;
    private float salary;
    private int old;
    private static int id = 0;
    private int nid;

    //public Colleg(String name) {
    //    this.name = name;
    //}

    //2 Конструктор класса должен заполнять эти поля при создании объекта;
    Colleg(String name, String pos, String tel, float salary, int old) {
        this.name = name;
        this.pos = pos;
        this.tel = tel;
        this.salary = salary;
        this.old = old;
        this.nid = ++id;

    }

    //3 Внутри класса «Сотрудник» написать методы, которые возвращают значение каждого поля;
    String getName() {return name;}

    String getPos() {
        return pos;
    }

    String getTel() {
        return tel;
    }

    float getSalary() {
        return salary;
    }

    int getOld() {
        return old;
    }

    String getAllinfo() { return "ФИО:" + name + " Должность:"+ pos + " Телефон:" + tel + " Зарплата:" + salary + " Возраст:"+ old; }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
