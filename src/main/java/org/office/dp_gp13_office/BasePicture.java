package org.office.dp_gp13_office;

public class BasePicture extends Entity {
    public BasePicture() {
        this.name ="picture";
        this.assetPath = App.class.getResource("images/picture.png").toExternalForm();
    }
}
