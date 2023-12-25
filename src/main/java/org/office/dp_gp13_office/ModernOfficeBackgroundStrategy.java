package org.office.dp_gp13_office;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class ModernOfficeBackgroundStrategy implements BackgroundChangeStrategy {
    @Override
    public void changeBackground(Space space) {
        // Modern background implementation
        Image image = new Image(App.class.getResource("images/office2.png").toExternalForm());

        BackgroundImage backgroundImage = new BackgroundImage(
                image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));

        space.setBackground(new Background(backgroundImage));
    }
}