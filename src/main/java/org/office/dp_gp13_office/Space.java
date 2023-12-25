package org.office.dp_gp13_office;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;

public class Space extends StackPane {
    OfficeSpace office;
    private BackgroundChangeStrategy backgroundChangeStrategy;
    public Space(OfficeSpace office) {
        this.office = office;
        this.backgroundChangeStrategy = new DefaultOfficeBackgroundStrategy();
        this.initializeBackground();
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
