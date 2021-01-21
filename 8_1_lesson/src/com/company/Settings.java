package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame {
    private static final int WIN_WIDTH = 350; // public было
    private static final int WIN_HEIGHT = 270; // public было

    // константные переменные для выбора длины полей и выигрышной позиции
    private static final int MIN_WIN_LENGTH = 3;
    private static final int MIN_FIELD_LENGTH = 3;
    private static final int MAX_WIN_LENGTH = 10;

    private static final String FIELD_SIZE_TEXT_PREFIX = "Размер поля: ";
    private static final String WIN_LENGTH_TEXT_PREFIX = "Выигрышная длина: ";

    private MainWindow mainWindow;
    private JRadioButton humVSAI;
    private JRadioButton humVSAHum;

    private JSlider sliderFieldSize;
    private JSlider sliderWinLen;

//конструктор
    Settings(MainWindow mainWindow){ //передается все окно mainWindow из MainWindow
       // setVisible(true); //убираем в MainWindow
        this.mainWindow = mainWindow;
        setSize(WIN_WIDTH, WIN_WIDTH);

        Rectangle gameWindowBounds = mainWindow.getBounds();
        // получаем координаты крайней левой точки окна Settings (так, чтобы оно располагалось по центру основного окна):
        int posX = (int)gameWindowBounds.getCenterX() - WIN_WIDTH / 2;
        int posY = (int)gameWindowBounds.getCenterY() - WIN_HEIGHT /2;
        setLocation(posX, posY);
// у меня при моем разрешении Windows внутреннее окно получились смещено вниз
        setResizable(false);
        setTitle("Настройки новой игры");

        setLayout(new GridLayout(10,1)); // компоновщик (столбик др под др)
        addGameModeSettings();
        addFieldSizeControl();

        JButton btnStartGame = new JButton("Начать новую игру");

        btnStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStartClick();
            }
        });
        add(btnStartGame);
    }
// чтобы не перегружать конструктор вынесем несколько методов за его пределы:
    // метод установки типа игры
    private void addGameModeSettings(){
        add(new JLabel("Выберите режим игры:"));
        humVSAI = new JRadioButton("Человек против машины", true);
        humVSAHum = new JRadioButton("Человек против человека");
// объединяем кнопки в группу кнопок:
        ButtonGroup gameMode = new ButtonGroup();
        gameMode.add(humVSAI);
        gameMode.add(humVSAHum);

        add(humVSAI);
        add(humVSAHum);

    }
    // метод установки длин полей и выигрышной позиции
    private void addFieldSizeControl() {
        JLabel lbFieldSize = new JLabel(FIELD_SIZE_TEXT_PREFIX + MIN_FIELD_LENGTH);
        JLabel lbWinLength = new JLabel(WIN_LENGTH_TEXT_PREFIX + MIN_WIN_LENGTH);
        // Слайдеры:
        sliderFieldSize = new JSlider(MIN_FIELD_LENGTH, MAX_WIN_LENGTH, MIN_FIELD_LENGTH);
        //
        sliderFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentValue = sliderFieldSize.getValue();
                lbFieldSize.setText(FIELD_SIZE_TEXT_PREFIX + currentValue);
                sliderWinLen.setMaximum(currentValue);
            }
        });
        sliderWinLen = new JSlider(MIN_WIN_LENGTH, MIN_FIELD_LENGTH, MIN_WIN_LENGTH);
        //
        sliderWinLen.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lbWinLength.setText(WIN_LENGTH_TEXT_PREFIX + sliderWinLen.getValue());
            }
        });

        add(new Label("Выберите размер поля"));
        add(lbFieldSize);
        add(sliderFieldSize);
        add(new JLabel("Выберите выигрышную позицию"));
        add(lbWinLength);
        add(sliderWinLen);
    }

        //
        private void btnStartClick() {
            int gameMode;
            if (humVSAI.isSelected()) {
                gameMode = GameMap.GAME_MODE_HVA;
            } else if(humVSAHum.isSelected()) {
                gameMode = GameMap.GAME_MODE_HVH;
            } else {
                throw new RuntimeException("Неизвестный режим игры");
            }

        int fieldSize = sliderFieldSize.getValue();
        int winLen = sliderWinLen.getValue();

        mainWindow.startNewGame(gameMode, fieldSize, fieldSize, winLen);
        setVisible(false);

        }
    }
