package org.office.dp_gp13_office;

public class BaseVase extends Entity {
    public BaseVase() {
        this.name ="vase";
        this.assetPath = App.class.getResource("images/vase.png").toExternalForm();
    }
}
