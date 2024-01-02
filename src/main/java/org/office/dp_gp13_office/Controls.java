package org.office.dp_gp13_office;

import java.util.Stack;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Controls extends GridPane {
    public Button[] entityButtons = new Button[5];
    public String[] entityButtonNames = { "Bubbles", "Wrecked Ship", "Crab", "Seahorse", "Seaweed" };
    public Space space;
    private Stack<Button> stackBtn = new Stack<>();
    private Stack<Integer> stackInt = new Stack<>();
    private Stack<String> stackStr = new Stack<>();
    Button bgMusicBtn;
    Button nextMusicBtn;
    Button prevMusicBtn;
    Button tvBtn;
    Button nextTvBtn;
    Button prevTvBtn;
    Button pauseTvBtn;
    Button resumeTvBtn;

    public Controls(Space space) {
        this.space = space;
        initializeButtons();
    }

    public void initializeButtons() {
        int btn_minWidth = 150;
        int btn_minHeight = 50;
        int padding = 15;

        Button changeBackgroundBtn = new Button("Change Background");
        changeBackgroundBtn.setMinSize(btn_minWidth, btn_minHeight);
        changeBackgroundBtn.setPadding(new Insets(padding));
        changeBackgroundBtn.setOnAction(e -> {
            // Use a strategy based on your application logic
            // For example, switch between Default, Traditional, and Modern strategies
            space.changeBackground();
        });
        this.addRow(0, changeBackgroundBtn);

        tvBtn = new Button("TV: Play");
        tvBtn.setMinSize(btn_minWidth, btn_minHeight);
        tvBtn.setPadding(new Insets(padding));
        tvBtn.setOnAction(e -> toggleTV(tvBtn));

        nextTvBtn = new Button("TV: Next");
        nextTvBtn.setMinSize(btn_minWidth, btn_minHeight);
        nextTvBtn.setPadding(new Insets(padding));
        nextTvBtn.setVisible(false);
        nextTvBtn.setOnAction(e -> space.playNextVideo());

        prevTvBtn = new Button("TV: Prev");
        prevTvBtn.setMinSize(btn_minWidth, btn_minHeight);
        prevTvBtn.setPadding(new Insets(padding));
        prevTvBtn.setVisible(false);
        prevTvBtn.setOnAction(e -> space.playPreviousVideo());

        pauseTvBtn = new Button("TV: Pause");
        pauseTvBtn.setMinSize(btn_minWidth, btn_minHeight);
        pauseTvBtn.setPadding(new Insets(padding));
        pauseTvBtn.setVisible(false);
        pauseTvBtn.setOnAction(e -> space.pauseVideo());

        resumeTvBtn = new Button("TV: Resume");
        resumeTvBtn.setMinSize(btn_minWidth, btn_minHeight);
        resumeTvBtn.setPadding(new Insets(padding));
        resumeTvBtn.setVisible(false);
        resumeTvBtn.setOnAction(e -> space.resumeVideo());

        this.addRow(0, tvBtn, nextTvBtn, prevTvBtn, pauseTvBtn, resumeTvBtn);

        bgMusicBtn = new Button("Play BG Music");
        bgMusicBtn.setMinSize(btn_minWidth, btn_minHeight);
        bgMusicBtn.setPadding(new Insets(padding));
        bgMusicBtn.setOnAction(e -> toggleMusic(bgMusicBtn));

        nextMusicBtn = new Button("Next BG Music");
        nextMusicBtn.setMinSize(btn_minWidth, btn_minHeight);
        nextMusicBtn.setPadding(new Insets(padding));
        nextMusicBtn.setVisible(false);
        nextMusicBtn.setOnAction(e -> space.playNextMusic());

        prevMusicBtn = new Button("Prev BG Music");
        prevMusicBtn.setMinSize(btn_minWidth, btn_minHeight);
        prevMusicBtn.setPadding(new Insets(padding));
        prevMusicBtn.setVisible(false);
        prevMusicBtn.setOnAction(e -> space.playPreviousMusic());

        this.addRow(1, bgMusicBtn, nextMusicBtn, prevMusicBtn);

        Button exitBtn = new Button("Exit");
        exitBtn.setMinSize(btn_minWidth, btn_minHeight);
        exitBtn.setPadding(new Insets(padding));
        exitBtn.setOnAction(e -> space.stop());
        this.addRow(1, exitBtn);

        this.setBackground(new Background(new BackgroundFill(Color.web("#C2C5CC"), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setPadding(new Insets(5, 5, 10, 5));
        this.setAlignment(Pos.CENTER);
        this.setHgap(7);
        this.setVgap(7);
        this.setPrefHeight(145);

        // Add ToggleButton for turning on/off the light
        ToggleButton toggleLightBtn = new ToggleButton("Turn On Light");
        toggleLightBtn.setMinSize(btn_minWidth, btn_minHeight);
        toggleLightBtn.setPadding(new Insets(padding));

        toggleLightBtn.setOnAction(e -> {
            if (toggleLightBtn.isSelected()) {
                // The button is selected, turn on the light
                space.turnOnLight();
                toggleLightBtn.setText("Turn Off Light");
            } else {
                // The button is not selected, turn off the light
                space.turnOffLight();
                toggleLightBtn.setText("Turn On Light");
            }
        });

        this.addRow(0, toggleLightBtn);

        Button increaseBrightnessBtn = new Button("Increase Brightness");
        increaseBrightnessBtn.setMinSize(btn_minWidth, btn_minHeight);
        increaseBrightnessBtn.setPadding(new Insets(padding));
        increaseBrightnessBtn.setOnAction(e -> {
            // Increase brightness logic
            space.increaseBrightnessCommand(); // Use the correct method for increasing brightness
        });

        Button decreaseBrightnessBtn = new Button("Decrease Brightness");
        decreaseBrightnessBtn.setMinSize(btn_minWidth, btn_minHeight);
        decreaseBrightnessBtn.setPadding(new Insets(padding));
        decreaseBrightnessBtn.setOnAction(e -> {
            // Decrease brightness logic
            space.decreaseBrightnessCommand(); // Use the correct method for decreasing brightness
        });

        this.addRow(0, increaseBrightnessBtn, decreaseBrightnessBtn);
    }

    public void toggleMusic(Button button) {
        boolean playing = space.toggleMusic();
        if (!playing) {
            button.setText("Stop Music");
            nextMusicBtn.setVisible(true);
            prevMusicBtn.setVisible(true);
        } else {
            button.setText("Play Music");
            nextMusicBtn.setVisible(false);
            prevMusicBtn.setVisible(false);
        }
    }

    public void toggleTV(Button button) {
        boolean playing = space.toggleTV();
        if (playing) {
            button.setText("TV: Stop");
            nextTvBtn.setVisible(true);
            prevTvBtn.setVisible(true);
            pauseTvBtn.setVisible(true);
            resumeTvBtn.setVisible(true);
        } else {
            button.setText("TV: Play");
            nextTvBtn.setVisible(false);
            prevTvBtn.setVisible(false);
            pauseTvBtn.setVisible(false);
            resumeTvBtn.setVisible(false);
        }
    }

}
