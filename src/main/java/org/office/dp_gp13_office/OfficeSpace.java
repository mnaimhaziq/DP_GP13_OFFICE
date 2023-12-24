package org.office.dp_gp13_office;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class OfficeSpace {
    Stage stage;

    public OfficeSpace(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return this.stage;
    }

    public void startOfficeSpace() {
        BorderPane root = new BorderPane();
        Space space = new Space(this);
        Controls controls = new Controls(space);
        root.setTop(space);
        root.setBottom(controls);

        // set to full screen at start
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setFullScreen(true);
        stage.setHeight(screenBounds.getHeight());
        stage.setWidth(screenBounds.getWidth());

        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        // set application icon
        Image image = new Image(getClass().getResourceAsStream("/org/office/dp_gp13_office/images/appIcon.png"));
        stage.getIcons().add(image);

        stage.setTitle("Office Space");

        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();

        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            space.setPrefWidth((100.0 * newVal.doubleValue()) / oldVal.doubleValue());
        });

        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            space.setPrefHeight((100.0 * newVal.doubleValue()) / oldVal.doubleValue());
        });
    }

    public void stopOfficeSpace() {
        stage.close();
    }
}
