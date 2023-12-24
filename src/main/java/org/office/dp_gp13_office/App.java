package org.office.dp_gp13_office;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    OfficeSpace office;

    @Override
    public void start(Stage stage) throws IOException {
        office = new OfficeSpace(stage);
        office.startOfficeSpace();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        office.stopOfficeSpace();
    }

    public static void main(String[] args) {
        launch();
    }
}