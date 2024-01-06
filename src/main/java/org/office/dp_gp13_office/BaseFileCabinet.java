package org.office.dp_gp13_office;

public class BaseFileCabinet extends Entity {
    public BaseFileCabinet() {
        this.name ="fileCabinet";
        this.assetPath = App.class.getResource("images/file_cabinet.png").toExternalForm();
    }
}
