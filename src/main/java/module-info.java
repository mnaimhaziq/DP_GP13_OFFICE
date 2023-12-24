module org.office.dp_gp13_office {
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires transitive javafx.media;
    requires javafx.fxml;


    opens org.office.dp_gp13_office to javafx.fxml;
    exports org.office.dp_gp13_office;
}