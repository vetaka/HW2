package com.company;

//import java.util.Arrays;

/* Домашка Java. Уровень 1. Урок 4



//7** При создании экземпляра класса Сотрудник присваивать ему уникальный порядковый номер.

 */
//1 Создать класс "Сотрудник" с полями: ФИО, должность, телефон, зарплата, возраст;


import java.util.Arrays;

public class CollegTest {

    public static void main(String[] args) {
        Colleg collegs = new Colleg("Иванов Иван Иванович", "Начальник отд.", "1234567", 50000, 45);
        //4 Вывести при помощи методов из пункта 3 ФИО и должность.
        System.out.println("ФИО: " + collegs.getName() + " должность: " + collegs.getPos());
        //5 Создать массив из 5 сотрудников. С помощью цикла вывести информацию только о сотрудниках старше 40 лет;
        Colleg[] staff = {
                collegs,
                new Colleg("Петров Петр Петрович", "инженер", "67891011", 40000, 50),
                new Colleg("Сидоров Сидор Сидорович", "инженер", "67891012", 35000, 30),
                new Colleg("Сергеев Сергей Сергеевич", "ст.инженер", "67891013", 45000, 53),
                new Colleg("Федоров Федор Федорович", "стажер", "67891014", 25000, 25)
        };
        for (int i = 0; i < staff.length; i++) {
            if (staff[i].getOld() > 40) System.out.println(staff[i].getAllinfo());
        }
        //6* Создать метод, повышающий зарплату всем сотрудникам старше 35 лет на 10000;
        for (int i = 0; i < staff.length; i++) {
            if (staff[i].getOld() > 35) {
                float a = (float) (staff[i].getSalary() + 10000.00);
                staff[i].setSalary((int) a);
                System.out.println("Повышение зарплаты:");
                System.out.println(staff[i].getAllinfo());
            }
        }

    }
}
