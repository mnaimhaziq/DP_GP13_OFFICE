package org.office.dp_gp13_office;

import java.util.Stack;

public class LightingManager {
    private static LightingManager instance;
    private boolean isLightOn;
    private int brightness;
    private LightingCommand command;
    private LightingCommand lastExecutedCommand;


    private Stack<LightingCommand> executedCommands;


    private LightingManager() {
        // Private constructor to prevent instantiation outside the class
        executedCommands = new Stack<>();
    }

    public static LightingManager getInstance() {
        if (instance == null) {
            instance = new LightingManager();
        }
        return instance;
    }

    public void turnOn() {
        isLightOn = true;
        System.out.println("Light is now ON");
    }

    public void turnOff() {
        isLightOn = false;
        System.out.println("Light is now OFF");
    }

    public boolean isLightOn() {
        return isLightOn;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
        System.out.println("Brightness set to: " + brightness);
        // You might want to include logic to update lighting effects here
        // or trigger the necessary updates in the UI based on the new brightness value.
    }

    public void increaseBrightness() {
        // Add logic to increase brightness
        if (isLightOn) {
            brightness += 10; // Increase brightness by 10 units
            System.out.println("Brightness increased to: " + brightness);
        }
    }

    public void decreaseBrightness() {
        // Add logic to decrease brightness
        if (isLightOn) {
            brightness -= 10; // Decrease brightness by 10 units
            System.out.println("Brightness decreased to: " + brightness);
        }
    }

//    public void executeCommand(LightingCommand command) {
//        this.command = command;
//        command.execute();
//
//        executedCommands.push(command);
//    }

    public void executeCommand(LightingCommand command) {
        this.command = command;
        command.execute();

        // Track the executed command
        executedCommands.push(command);
    }

    public void undoLastCommand() {
        if (!executedCommands.isEmpty()) {
            LightingCommand lastCommand = executedCommands.pop();
            lastCommand.undo();
        }
    }

//    public void undoLastCommand() {
//        if (!executedCommands.isEmpty()) {
//            LightingCommand lastCommand = executedCommands.pop();
//            lastCommand.undo();
//        }
//    }

    public int getBrightness() {
        return brightness;
    }
}
