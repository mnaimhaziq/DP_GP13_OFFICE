package org.office.dp_gp13_office;

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

import java.util.Stack;

public class Controls extends GridPane {
    public Button[] entityButtons = new Button[5];
    public String[] entityButtonNames = { "Bubbles", "Wrecked Ship", "Crab", "Seahorse", "Seaweed" };
    public Space space;
    private Stack<Button> stackBtn = new Stack<>();
    private Stack<Integer> stackInt = new Stack<>();
    private Stack<String> stackStr = new Stack<>();

    public Controls(Space space) {
        this.space = space;
        initializeButtons();
    }


    public void initializeButtons() {
        int btn_minWidth = 200;
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

}
