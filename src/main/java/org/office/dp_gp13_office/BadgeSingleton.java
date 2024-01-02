package org.office.dp_gp13_office;

public class BadgeSingleton {
    private static BadgeSingleton uniqueInstance;

    private BadgeSingleton(){

    }

    public static BadgeSingleton getInstance(){
        if (uniqueInstance == null) {
            System.out.println("Creating unique instance of a badge");
            uniqueInstance = new BadgeSingleton();
        }
        return uniqueInstance;
    }
}
