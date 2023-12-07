module org.office.dp_gp13_office {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.office.dp_gp13_office to javafx.fxml;
    exports org.office.dp_gp13_office;
}