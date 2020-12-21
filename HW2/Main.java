package HW2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
// 1
        int[] myArr1 = new int[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println("Задание №1.");
        System.out.println("Исходный массив:" + Arrays.toString(myArr1));
        System.out.println("Измененный массив:" + Arrays.toString(changeAr(myArr1)));
// 2
        int size = 8;
        int[] myArr2 = new int[size];
        System.out.println("Задание №2.");
        System.out.println("Массив с арифм.прогрессией из " + size + " элементов:\n" + Arrays.toString(arifmArr(myArr2, size)));
// 3
        int[] myArr3 = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("Задание №3.");
        System.out.println("Исходный массив 3:" + Arrays.toString(myArr3));
        System.out.println("Измененный массив 3 (в котором числа меньше 6 умножены на 2:" + Arrays.toString(newArr(myArr3)));
// 4
        int[] myArr4 = new int[]{5, 20, 35, 1, -6, -20, 40};
        System.out.println("Задание №4.");
        System.out.println("Исходный массив 4:" + Arrays.toString(myArr4));
        System.out.println("Максимальный элемент:  " + fMaxMin(myArr4)[0] + " и минимальный элемент: " + fMaxMin(myArr4)[1]);
// 5
/* Создать квадратный целочисленный массив (количество строк и столбцов одинаковое),
заполнить его диагональные элементы единицами, используя цикл(ы);*/
        int n = 6; /* size of array */
        int[][] myArr5 = new int[n][n];
        System.out.println("Задание №5.");
        System.out.println("Массив с диагональными единицами):\n ");
        for (int i = 0; i < myArr5.length; i++) {
            for (int j = 0; j < myArr5.length; j++) {
                if (i == j || i == n - j - 1) myArr5[i][j] = 1;
                else myArr5[i][j] = 0;
                System.out.print(myArr5[i][j] + "\t");
            }
            System.out.println();
        }
// 6
        int[] myArr6 = new int[]{1, 1, 1, 2, 1};
        System.out.println("Задание №6.");
        String str = (checkBalance(myArr6)) ? "есть место," : "нет места,";
        System.out.println("В массиве " + Arrays.toString(myArr6) + str + "\n" + "в котором сумма левой и правой части массива равны. ");
// 7
        int[] myArr7 = new int[] {1,2,3,4,5};
        int m = -8;
        System.out.println("Задание №7.");
        System.out.println("Сдвиг на " + m + ". Исходный массив 7:" + Arrays.toString(myArr7));
        System.out.println("Измененный массив 7:" + Arrays.toString(arrMov(myArr7, m)));
    }

    /*1 Задать целочисленный массив, состоящий из элементов 0 и 1.
    Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. Написать метод, заменяющий в  принятом массиве 0 на 1, 1 на 0;
     */
    public static int[] changeAr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] == 1) ? 0 : 1;
        }
        return arr;
    }

    /*2 Задать пустой целочисленный массив размером 8.
    Написать метод, который c помощью цикла заполнит его значениями 1 4 7 10 13 16 19 22 (арифм.прогрессия +3);
    */
    public static int[] arifmArr(int[] arrPr, int numEl) {
        for (int i = 0; i < numEl; i++) arrPr[i] = (i * 3) + 1;
        return arrPr;
    }

    /*3 Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ], написать метод, принимающий на вход массив и умножающий числа меньше 6 на 2;
     */
    public static int[] newArr(int[] someArr) {
        for (int i = 0; i < someArr.length; i++) someArr[i] = (someArr[i] < 6) ? someArr[i] * 2 : someArr[i];
        return someArr;
    }

    /*4 Задать одномерный массив. Написать методы поиска в нём минимального и максимального элемента;
     */
    public static int[] fMaxMin(int[] searchArr) {
        int[] maxMin = new int[]{searchArr[0], searchArr[0]}; /* first valuable of max and min */
        for (int index : searchArr) {
            if (index > maxMin[0]) maxMin[0] = index;
            if (index < maxMin[1]) maxMin[1] = index;
        }
        return maxMin;
    }

    /* 6 ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true если в массиве есть место,
     в котором сумма левой и правой части массива равны. Примеры:
    checkBalance([1, 1, 1, || 2, 1]) → true,
    checkBalance ([2, 1, 1, 2, 1]) → false,
    checkBalance ([10, || 1, 2, 3, 4]) → true.
    Абстрактная граница показана символами ||, эти символы в массив не входят.
    */
    public static boolean checkBalance(int[] arr6) {
        int sum1, sum2, i, k;
        for (i = 0; i < arr6.length; i++) {
            sum1 = 0;
            sum2 = 0;
            for (k = 0; k <= i; k++) sum1 += arr6[k];
            for (k = i + 1; k < arr6.length; k++) sum2 += arr6[k];
            if (sum1 == sum2) {
                return true;
            }
        }
        return false;
    }

    /*7 *** Написать метод, которому на вход подаётся одномерный массив и число n (может быть положительным, или отрицательным),
    при этом метод должен циклически сместить все элементы массива на n позиций.
    [1,2,3,4,5], -2 => [3,4,5,1,2]
    [1,2,3,4,5], 2 => [4,5,1,2,3]
    8 **** Не пользоваться вспомогательным массивом при решении задачи 7.*/
    public static int[] arrMov(int[] arr7, int n) {
        n = n % arr7.length;
        int tmp;
//Check
        if (n < 0) {  /*  n - negative */
            for (int k = 1; k <= -n; k++) {
                tmp = arr7[0];
                for (int i = 0; i < arr7.length - 1; i++) arr7[i] = arr7[i + 1];
                arr7[arr7.length - 1] = tmp;
            }
        } else for (int k = 1; k <= n; k++) {   /*  n - positive or 0 */
            tmp = arr7[arr7.length - 1];
            for (int i = arr7.length - 1; i > 0; i--) arr7[i] = arr7[i - 1];
            arr7[0] = tmp;
        }

    return arr7;
    }
}



