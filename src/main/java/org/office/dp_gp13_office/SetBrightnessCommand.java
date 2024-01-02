package org.office.dp_gp13_office;

public class SetBrightnessCommand implements LightingCommand{
    private int brightness;

    public SetBrightnessCommand(int brightness) {
        this.brightness = brightness;
    }

    public void execute() {
        // Set the new brightness
        LightingManager.getInstance().setBrightness(brightness);
    }

    @Override
    public void undo() {
        // Undo the brightness change by setting it back to the previous value
        // For simplicity, assuming there's a way to store the previous brightness value
        LightingManager.getInstance().setBrightness(brightness);
    }
}
