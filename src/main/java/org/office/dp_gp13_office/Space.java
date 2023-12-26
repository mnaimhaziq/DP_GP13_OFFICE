package org.office.dp_gp13_office;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;


public class Space extends StackPane {
    OfficeSpace office;
    private BackgroundChangeStrategy backgroundChangeStrategy;

    // Lighting
    private LightingManager lightingManager;
    private LightingCommand lightOnCommand;
    private LightingCommand lightOffCommand;
    private LightingCommand setBrightnessCommand;
    private boolean isLightOn = false;
    private int brightness = 0;


    public Space(OfficeSpace office) {
        this.office = office;
        this.backgroundChangeStrategy = new DefaultOfficeBackgroundStrategy();
        this.initializeBackground();

        // Initialize LightingManager and commands
        this.lightingManager = LightingManager.getInstance();
        this.lightOnCommand = new LightOnCommand();
        this.lightOffCommand = new LightOffCommand();
        this.setBrightnessCommand = new SetBrightnessCommand(0); // Set default brightness
    }

    public void increaseBrightnessCommand() {
        updateLightingState();
        lightingManager.executeCommand(setBrightnessCommand);
    }

    public void decreaseBrightnessCommand() {
//        lightingManager.decreaseBrightness();
        lightingManager.undoLastCommand();
    }

    public void turnOnLight() {
        isLightOn = true;
        updateLightingState();
        lightingManager.executeCommand(lightOnCommand);
    }

    public void turnOffLight() {
        isLightOn = false;
        updateLightingState();
        lightingManager.executeCommand(lightOffCommand);
    }

    public void updateLightingState() {
        if (isLightOn) {

                // Apply a faint glow on the top side when brightness is 0
                double glowIntensity = 0.01; // Adjust the intensity of the glow

                Glow glowEffect = new Glow(glowIntensity);

                // Create a Rectangle representing the top portion with a gradient fill
                double topPortionHeight = getHeight() / 5; // Adjust the height of the top portion
                Rectangle topPortion = new Rectangle(0, 0, getWidth(), topPortionHeight);

                topPortion.setFill(new javafx.scene.paint.LinearGradient(0, 0, 0, 1, true, javafx.scene.paint.CycleMethod.NO_CYCLE,
                        new javafx.scene.paint.Stop(0.01, Color.YELLOW), new javafx.scene.paint.Stop(1, Color.TRANSPARENT)));

                // Apply Glow effect directly to the top portion
                topPortion.setEffect(glowEffect);

                // Add the top portion to the StackPane and align it to the top
                getChildren().add(topPortion);
                setAlignment(topPortion, Pos.TOP_CENTER);

                // Clear any effects when lights are off
                setEffect(null);

        } else {
            // Clear any effects when lights are off
            getChildren().clear();
            setEffect(null);
        }
    }



    public void initializeBackground() {
        // Default background initialization
        Image image = new Image(App.class.getResource("images/office.png").toExternalForm());

        BackgroundImage backgroundImage = new BackgroundImage(
                image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));

        this.setBackground(new Background(backgroundImage));
    }

    private BackgroundChangeStrategy[] backgroundStrategies = {
            new DefaultOfficeBackgroundStrategy(),
            new ModernOfficeBackgroundStrategy(),
            new TraditionalOfficeBackgroundStrategy()
    };

    private int currentStrategyIndex = 0;

    public void changeBackground() {
        backgroundStrategies[currentStrategyIndex].changeBackground(this);
        // Move to the next strategy in a loop
        currentStrategyIndex = (currentStrategyIndex + 1) % backgroundStrategies.length;
    }
    // Add this method to set a new strategy
    public void setBackgroundChangeStrategy(BackgroundChangeStrategy strategy) {
        this.backgroundChangeStrategy = strategy;
    }



    public void stop() {
        office.stopOfficeSpace();
    }


}
