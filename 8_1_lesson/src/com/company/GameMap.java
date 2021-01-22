package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

// создаем карту поля игры:
public class GameMap extends JPanel {
    //наш класс является наследником класса JPanel из биб-ки Swing
    /*При построении интерфейсов нужны компоненты-контерйнеры, которые будут содержать другие компоненты
    пользовательского интерфейса. В Swing одним из таких компонентов-контейнеров является JPanel.
    По умолчанию JPanel сама по себе ничего не отрисовывает за исключением фона.
    При работе с контейнерами, разработчику надо решить, как правило, две основные проблемы.
    Первая – задать расположение дочерних компонентов и вторая – осуществить добавление компонентов на контейнер.(из доп лит-ры)
     */
// Объявление констант:
    public static final int GAME_MODE_HVA = 0;
    public static final int GAME_MODE_HVH = 1;

    private static final int DOT_EMPTY = 0;
    private static final int DOT_HUMAN1 = 1;
    private static final int DOT_AI = 2;
    private static final int DOT_HUMAN3 = 3;


    private static final int STATE_DRAW = 0; //ничья
    private static final int STATE_WIN_HUMAN1 = 1;// выиграл человек 1
    private static final int STATE_WIN_AI = 2; // выиграл искусственный интелект
    private static final int STATE_WIN_HUMAN3 = 3;// выиграл человек 2


//Объявление переменных
    private boolean isGameOver; // флаг конца игры
    private boolean initializedMap; // флаг инициализации загрузки ? карты

    private int stateGameOver;

    public static final Random RANDOM = new Random();
    //Размеры поля, длина выигрышной позиции и размеры ячеек поля
    private int fieldSizeX;
    private int fieldSizeY;
    private int winLength;
    //! добавила mode - тип игры:
    private int mode;
    //! добавила переменную счетчик ходов:
    private int numMove = 0;
    //! переменную № игрока:
    private int dotHuman = DOT_HUMAN1; //1 начальное значение
    //! и проверка состояния победы игрока
    private int stateWinHuman = STATE_WIN_HUMAN1; //1

    private int[][] field;
    //
    private int cellWidth;
    private int cellHeight;

// Конструктор класса
    GameMap(){
        setBackground(Color.BLACK);
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                super.mouseReleased(e);
//                update(e);
//            }
//        });
//переопределяем стандартный метод mouseClicked внутри addMouseListener
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { // метод обработки события клика мыши, вызывается при нажатии (нажатии и отпускании) кнопки мыши на компоненте.
                super.mouseClicked(e);
                update(e); // каждый раз при нажатии кнопки мыши вызывается update
            }
        });
        initializedMap = false;
    }
// Метод старта игры:
    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength) {
        //Заглушка: System.out.println("mode = " + mode + ", fieldSizeX = " + fieldSizeX + ", fieldSizeY = " + fieldSizeY + ", winLength = " + winLength); //заглушка
        this.mode = mode;
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.winLength = winLength;
        field = new int[fieldSizeX][fieldSizeY];
        isGameOver = false;
        initializedMap = true;
        repaint();//перерисовать окно
    }
// Метод выхода из игры
    private void setGameOver(int gameOverState) {
        stateGameOver = gameOverState;
        isGameOver = true;
        repaint();
    }
// Перегрузка метода отрисовки paintComponent из биб-ки Swing :
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g); //не имея возможность обращаться напрямую к paintComponent, просто переопределяем его:
        render(g); // каждый раз в момент отрисовки он будет выполнять этот наш метод (полиморфизм)
    }
// Метод обработки действий мышки:
    private void update(MouseEvent e) {   // MouseEvent - событие из java.awt.event, происходит при любом событии мыши
        if (!initializedMap) return;
        if (isGameOver) return;

        int cellX = e.getX() / cellWidth; //вычисляем номер столбца
        int cellY = e.getY() / cellHeight; // и номер строки
        //если невалидная или не пустая ячейка:
        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)) {
            return;
        }
        field[cellY][cellX] = DOT_HUMAN1;
        dotHuman = DOT_HUMAN1;
        stateWinHuman = STATE_WIN_HUMAN1;
        if (mode == GAME_MODE_HVH) {
            numMove++;
            if( numMove % 2 == 0 ){
                field[cellY][cellX] = DOT_HUMAN3;
                dotHuman = DOT_HUMAN3;
                stateWinHuman = STATE_WIN_HUMAN3;

            }
        }

        if (checkWin(dotHuman)) {
            setGameOver(stateWinHuman); //победил человек
            return;
        }

        if (isFullMap()) {
            setGameOver(STATE_DRAW); //ничья
            return;
        }
        if (mode == GAME_MODE_HVA) {
            aiTurn();
            repaint();
            if (checkWin(DOT_AI)) {
                setGameOver(STATE_WIN_AI);
                return;
            }
            if (isFullMap()) {
                setGameOver(STATE_DRAW);
                return;
            }
        }
        repaint();
//        else if (mode == GAME_MODE_HVH) {
//           repaint();
//            if (!initializedMap) return;
//            if (isGameOver) return;
//            cellX = e.getX() / cellWidth; //вычисляем номер столбца
//            cellY = e.getY() / cellHeight; // и номер строки
//            //если невалидная или не пустая ячейка:
//            if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)) {
//                return;
//            }
//            field[cellY][cellX] = DOT_HUMAN3;
//            repaint();
//            if (checkWin(DOT_HUMAN3)) {
//                setGameOver(STATE_WIN_HUMAN3); //победил человек
//                return;
//            }
//
//            if (isFullMap()) {
//                setGameOver(STATE_DRAW); //ничья
//                return;
//            }
//
//        }
    }
// Метод отрисовки поля
    private void render(Graphics g) {
        if (!initializedMap) return;

        int width = getWidth();
        int height = getHeight();

        cellWidth = width / fieldSizeX;
        cellHeight = height / fieldSizeY;
        g.setColor(Color.WHITE);
    // Рисуем заново решетку поля:
        for (int i = 0; i < fieldSizeY ; i++) {
            int y = i * cellHeight;
            g.drawLine(0,y,width,y);
        }

        for (int i = 0; i < fieldSizeX; i++) {
            int x = i * cellWidth;
            g.drawLine(x,0, x, height);
        }
    // поверяем все клетки на содержимое:
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isEmptyCell(x,y)) {
                    continue;
                }
            //расставляем крестики-нолики (кружочки) согласно содержимому массива field:
                if (field[y][x] == DOT_HUMAN1) {
                    g.setColor(new Color(1,1,255));
                    g.fillOval(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
                } else if (field[y][x] == DOT_AI) {
                    g.setColor(Color.RED);
                    g.fillOval(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
                } else if (field[y][x] == DOT_HUMAN3) {
                    g.setColor(new Color(255, 1, 255));
                    g.fillOval(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
                } else {
                    throw new RuntimeException("Can't paint cellX " + x + " cellY " + y);
                }

            }
        }
        if (isGameOver) {
            showMessageGameOver(g);
        }
    }

    private void showMessageGameOver(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 200, getWidth(), 70);
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Times New Roman", Font.BOLD, 45));

        switch (stateGameOver) {
            case STATE_DRAW:
                g.drawString("Ничья", 180, getHeight() / 2);
                break;
            case STATE_WIN_HUMAN1:
                g.drawString("Победил человек 1", 100, getHeight() / 2);
                break;
            case STATE_WIN_AI:
                g.drawString("Победил ИИ", 120, getHeight() / 2);
                break;
            case STATE_WIN_HUMAN3:
                g.drawString("Победил человек 2", 100, getHeight() / 2);
                break;
            default:
                throw new RuntimeException("Unexpected game over state: " + stateGameOver);
        }
    }

    public void aiTurn() {

        if (turnAIWinCell()) { //выиграет-ли игрок на следующем ходу?
            return;
        }
        if (turnHumanWinCell()) { //выиграет-ли комп на следующем ходу?
            return;
        }
        int x;
        int y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[y][x] = DOT_AI;
    }

    private boolean turnAIWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (isEmptyCell(j, i)) {
                    field[i][j] = DOT_AI;               // поставим нолик в каждую клетку поля по очереди
                    if (checkWin(DOT_AI)) {
                        return true;    // если мы выиграли, вернём истину, оставив нолик в выигрышной позиции
                    }
                    field[i][j] = DOT_EMPTY;            // если нет - вернём обратно пустоту в клетку и пойдём дальше
                }
            }
        }
        return false;
    }

    // Проверка, выиграет-ли игрок своим следующим ходом
    private boolean turnHumanWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (isEmptyCell(j, i)) {                 // если клетка пустая
                    field[i][j] = DOT_HUMAN1;            // поставим крестик в каждую клетку по очереди
                    if (checkWin(DOT_HUMAN1)) {            // если игрок победит
                        field[i][j] = DOT_AI;            // поставить на то место нолик
                        return true;
                    }
                    field[i][j] = DOT_EMPTY;            // в противном случае (если не победит) вернуть на место пустоту
                }
            }
        }
        return false;
    }

    // проверка на победу
    private boolean checkWin(int c) { // где с - константа игрока
        for (int i = 0; i < fieldSizeX; i++) {            // ползём по всему полю
            for (int j = 0; j < fieldSizeY; j++) {
                if (checkLine(i, j, 1, 0, winLength, c)) {
                    return true;    // проверим линию по х
                }
                if (checkLine(i, j, 1, 1, winLength, c)) {
                    return true;    // проверим по диагонали х у
                }
                if (checkLine(i, j, 0, 1, winLength, c)) {
                    return true;    // проверим линию по у
                }
                if (checkLine(i, j, 1, -1, winLength, c)) {
                    return true;    // проверим по диагонали х -у
                }
            }
        }
        return false;
    }

    // проверка линии
    private boolean checkLine(int x, int y, int vx, int vy, int len, int c) {
        final int farX = x + (len - 1) * vx;            // посчитаем конец проверяемой линии
        final int farY = y + (len - 1) * vy;
        if (!isValidCell(farX, farY)) {
            return false;    // проверим не выйдет-ли проверяемая линия за пределы поля
        }
        for (int i = 0; i < len; i++) {                    // ползём по проверяемой линии
            if (field[y + i * vy][x + i * vx] != c) {
                return false;    // проверим одинаковые-ли символы в ячейках
            }
        }
        return true;
    }

    public boolean isFullMap() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    public boolean isEmptyCell(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }



}
