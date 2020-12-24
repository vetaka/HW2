package HW4;

//import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 game of tic-toc-toe*/
    class XOGame {

    public static final char HUMAN_DOT = 'X';
    public static final char PC_DOT = 'O';
    public static final char EMPTY_DOT = '_';

    public static final Scanner SCANNER = new Scanner(System.in);
    public static final Random RANDOM = new Random();

    public static char[][] map;
    public static int mapSizeX;
    public static int mapSizeY;

    public static void initMap() {
        mapSizeX = 3;
        mapSizeY = 3;
        map = new char[mapSizeY][mapSizeX];

        for (int y = 0; y < mapSizeY; y++) {
            for (int x = 0; x < mapSizeX; x++) {
                map[y][x] = EMPTY_DOT;
            }
        }
    }

    public static void printMap() {
        for (int y = 0; y < mapSizeY; y++) {
            System.out.print(y+1);
            for (int x = 0; x < mapSizeX; x++) {
                System.out.print(map[y][x] + "|");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void humanTurn() {
        int x;
        int y;

        do {
            System.out.println("Введите свои координаты: ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;

        } while (!isValidCell(y, x) || !isEmptyCell(y, x));
        map[y][x] = HUMAN_DOT;
    }

    public static void aiTurn() {
        int x;
        int y;

        do {
            x = RANDOM.nextInt(mapSizeX);
            y = RANDOM.nextInt(mapSizeY);
        } while (!isEmptyCell(y, x));
        map[y][x] = PC_DOT;
    }


    public static boolean isValidCell(int y, int x) {
        return x >= 0 && x < mapSizeX && y >= 0 && y < mapSizeY;
    }

    public static boolean isEmptyCell(int y, int x) {
        return map[y][x] == EMPTY_DOT;
    }

    public static boolean checkWin(char inboxChar ) {
        //по горизонтали

        if (map[0][0] == inboxChar && map[0][1] == inboxChar && map[0][2] == inboxChar) return true;
        if (map[1][0] == inboxChar && map[1][1] == inboxChar && map[1][2] == inboxChar) return true;
        if (map[2][0] == inboxChar && map[2][1] == inboxChar && map[2][2] == inboxChar) return true;
        // по вертикали
        if (map[0][0] == inboxChar && map[1][0] == inboxChar && map[2][0] == inboxChar) return true;
        if (map[0][1] == inboxChar && map[1][1] == inboxChar && map[2][1] == inboxChar) return true;
        if (map[0][2] == inboxChar && map[1][2] == inboxChar && map[2][2] == inboxChar) return true;
        // по диагонали
        if (map[0][0] == inboxChar && map[1][1] == inboxChar && map[2][2] == inboxChar) return true;
        if (map[0][2] == inboxChar && map[1][1] == inboxChar && map[2][0] == inboxChar) return true;
        return false;
    }

    public static boolean isMapFull() {
        for (int y = 0; y < mapSizeY; y++) {
            for (int x = 0; x < mapSizeX; x++) {
                if (map[y][x] == EMPTY_DOT) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();

            if (checkWin(HUMAN_DOT)) {
                System.out.println("Вы выиграли!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья!");
                break;
            }

            aiTurn();
            printMap();
            if (checkWin(PC_DOT)) {
                System.out.println("AI выиграл!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья!!!");
                break;
            }
        }
    }

}
