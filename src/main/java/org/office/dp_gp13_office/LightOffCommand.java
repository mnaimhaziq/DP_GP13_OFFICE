package org.office.dp_gp13_office;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class LightOffCommand implements LightingCommand{

    public LightOffCommand() {

    }

    @Override
    public void execute() {
        LightingManager.getInstance().turnOff();
    }

    @Override
    public void undo() {

    }
}
