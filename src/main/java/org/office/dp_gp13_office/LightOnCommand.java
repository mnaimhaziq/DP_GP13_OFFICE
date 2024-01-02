package org.office.dp_gp13_office;

public class LightOnCommand implements LightingCommand{
    @Override
    public void execute() {
        LightingManager.getInstance().turnOn();
    }

    @Override
    public void undo() {

    }
}
