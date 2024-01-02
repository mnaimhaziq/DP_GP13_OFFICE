package org.office.dp_gp13_office;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;

public class TomBadge extends Badge {
    private static TomBadge uniqueTomBadge;

    private TomBadge(OfficeSpace office) {
        this.office = office;
    }

    public static TomBadge getInstance(OfficeSpace office){
        if (uniqueTomBadge == null) {
            System.out.println("Creating unique instance of a badge");
            uniqueTomBadge = new TomBadge(office);
            return uniqueTomBadge;
        }
        else {
            return null;
        }
    }

    @Override
    public void printBadgeWithDescription() {
        Image badge = new Image(App.class.getResourceAsStream("images/tom-badge.png"));
        ImageView badgeView = new ImageView(badge);
        Popup popup = new Popup();
        Label label = new Label();
        Button close = new Button();

        label.setMinHeight(350);
        label.setMinWidth(650);
        label.setStyle("-fx-background-color: #808080; -fx-font-size:25");

        close.setText("Close");
        close.setOnAction(e -> {
            popup.hide();
        });

        popup.getContent().add(label);
        popup.getContent().add(close);
        popup.getContent().add(badgeView);
        System.out.println(office.getStage());
        popup.show(office.getStage());
    }
}
