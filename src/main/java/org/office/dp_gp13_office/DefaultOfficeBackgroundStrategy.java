package org.office.dp_gp13_office;

public class DefaultOfficeBackgroundStrategy implements BackgroundChangeStrategy {
    @Override
    public void changeBackground(Space space) {
        // Default background implementation
        space.initializeBackground();
    }

}
