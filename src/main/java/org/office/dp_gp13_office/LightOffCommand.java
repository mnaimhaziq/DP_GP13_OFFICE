package org.office.dp_gp13_office;

public class LightOffCommand implements LightingCommand{
    @Override
    public void execute() {
        LightingManager.getInstance().turnOff();
    }

    @Override
    public void undo() {

    }
}
