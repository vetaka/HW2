package com.company;

import java.io.*;
import java.util.Scanner;

/*
1.	Создать 2 текстовых файла, примерно по 50-100 символов в каждом (особого значения не имеет);
*/

public class Main  {

    public static void main(String[] args) {
        method1();
        method2();
    }
//        2.	Написать программу, «склеивающую» эти файлы, то есть вначале идет текст из первого файла, потом текст из второго.
    public static void method1() {
        try {

            FileInputStream fis = new FileInputStream("text1.txt"); // файлы в основной папке пректа не требуют полного пути
            FileOutputStream fos = new FileOutputStream("text3.txt");
            int outbox;
            while ((outbox = fis.read()) != -1) {
                fos.write(outbox);
            }
            fis.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            FileInputStream fis = new FileInputStream("text2.txt");
            FileOutputStream fos = new FileOutputStream("text3.txt", true);
            int outbox;
            while ((outbox = fis.read()) != -1) {
                fos.write((char) outbox);
            }
            fis.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//   3.	* Написать программу, которая проверяет присутствует ли указанное пользователем слово в файле.
    public static void method2()  {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите слово для поиска в тексте text3.txt: ");
        String s;
        s = scanner.next();// сканируем строку с консоли
        byte[] mB; // массив байтов
        mB = s.getBytes();// строка s конвертируется в массив байтов
        int lMb = mB.length; // длина массива байтов
        try {
            FileInputStream fis = new FileInputStream("text3.txt");// создаем обект входного байтового потока чтения из файла
            byte outbox = 0; // байтовая переменная для чтения по 1 байту из файла
            int numByt = 0; // счетчик числа совпадающих байтов
            int i = 0; // счетчик байтов
//            int N = 0; // номер байта в тексте
            while (((outbox = (byte) fis.read()) != -1)) {
//При каждом вызове метод read() читает байт из файла и возвращает его в виде целочисленного значения. По достижении конца файла возвращает значение –1
//                N++;
                if (!(mB[i] == outbox)) {
                    numByt = 0;
                    i = 0;
                } else {
                    numByt++;
                    i++;
                    if (numByt == lMb) {
//                        N = N - lMb;
                        break;
                    }
                }
            }

            if(numByt == lMb) System.out.println("Данное слово присутствует в тексте text3.txt!");
            else System.out.println("Данного слова в тексте нет!");
           fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
//4.  ** Написать метод, проверяющий, есть ли указанное слово в папке



