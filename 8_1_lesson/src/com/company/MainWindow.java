package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame { // обычное Windows-окно
    private static final int WIN_WIDTH = 500; //ШИРИНА
    private static final int WIN_HEIGHT = 555; // ДЛИНА
    private static final int WIN_POSX= 650; //КООРДИНАТЫ ЛЕВОГО ВЕРХНЕГО УГЛА
    private static final int WIN_POSY = 100;//

    private Settings settingsWindow;
    private GameMap gameMap; //ссылка на объект (переменная типа класса)
// конструктор (инициализирует основное окно)
    MainWindow () {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIN_WIDTH, WIN_HEIGHT);
        setLocation(WIN_POSX, WIN_POSY);
        setTitle("Игра крестики-нолики");
        setResizable(false);

        settingsWindow = new Settings(this); //создается объект - невидимое пока окно с установками
        gameMap = new GameMap(); // создаем объект класса: поле игры (GameMap), запуская его конструктор
//Кнопки:

        // Кнопка Новая игра
        JButton btnStartGame = new JButton("Новая игра");
        // Слушатель кнопки:
        btnStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsWindow.setVisible(true);

            }
        });
        // Кнопка Выход из игры
        JButton btnExit = new JButton("Выход из игры");
        // Слушатель кнопки:
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

// Отрисовка кнопок:
        JPanel panelBottom = new JPanel();
        panelBottom.setLayout(new GridLayout(1,2));
        panelBottom.add(btnStartGame);
        panelBottom.add(btnExit); // эта кнопка не всегда отрисовывается,почему-то?
        add(panelBottom, BorderLayout.SOUTH);
// немного теории:
        //компоновщики: BorderLayout (по умолчанию), GridLayout, FlowLayout(слева направо сверху вниз потоком), BoxLayout и др
        // например
        //add(btnStartGame, BorderLayout.SOUTH);
        //add(btnExit, BorderLayout.SOUTH);
        // setLayout(new GridLayout(1,2));
        // компоновщики можно комбинировать
// добавляем на панель карту игры (объект GameMap):
        add(gameMap);
        setVisible(true);
    }
// создаем метод, передающий данные для построения карты объекту gameMap класса GameMap, используя его одноименный метод - правильно или нет?
    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength) {
        gameMap.startNewGame(mode, fieldSizeX, fieldSizeY, winLength);
    }

}
