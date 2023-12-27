package org.office.dp_gp13_office;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.Stack;

public class Controls extends GridPane {
    public Button[] entityButtons = new Button[5];
    public String[] entityButtonNames = { "Bubbles", "Wrecked Ship", "Crab", "Seahorse", "Seaweed" };
    public Space space;
    private Stack<Button> stackBtn = new Stack<>();
    private Stack<Integer> stackInt = new Stack<>();
    private Stack<String> stackStr = new Stack<>();
    Button bgMusicBtn, nextMusicBtn, prevMusicBtn;

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

        bgMusicBtn= new Button("Play BG Music");
        bgMusicBtn.setMinSize(btn_minWidth, btn_minHeight);
        bgMusicBtn.setPadding(new Insets(padding));    
        bgMusicBtn.setOnAction(e -> {toggleMusic(bgMusicBtn);});

        nextMusicBtn= new Button("Next BG Music");
        nextMusicBtn.setMinSize(btn_minWidth, btn_minHeight);
        nextMusicBtn.setPadding(new Insets(padding));
        nextMusicBtn.setVisible(false);
        nextMusicBtn.setOnAction(e -> space.playNextMusic());

        prevMusicBtn= new Button("Prev BG Music");
        prevMusicBtn.setMinSize(btn_minWidth, btn_minHeight);
        prevMusicBtn.setPadding(new Insets(padding));
        prevMusicBtn.setVisible(false);
        prevMusicBtn.setOnAction(e -> space.playPreviousMusic());

        this.addRow(1,bgMusicBtn, nextMusicBtn, prevMusicBtn);

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

}
