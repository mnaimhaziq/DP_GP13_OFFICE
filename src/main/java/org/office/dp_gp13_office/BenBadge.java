package org.office.dp_gp13_office;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;

public class BenBadge extends Badge {
    private static BenBadge uniqueBenBadge;

    private BenBadge(OfficeSpace office) {
        this.office = office;
    }

    public static BenBadge getInstance(OfficeSpace office){
        if (uniqueBenBadge == null) {
            System.out.println("Creating unique instance of a badge");
            uniqueBenBadge = new BenBadge(office);
            return uniqueBenBadge;
        }
        else {
            return null;
        }
    }

    @Override
    public void printBadgeWithDescription() {
        Image badge = new Image(App.class.getResourceAsStream("images/ben-badge.png"));
        ImageView badgeView = new ImageView(badge);
        Popup popup = new Popup();
        Label label = new Label();
        Button close = new Button();

        badgeView.setFitHeight(400);
        badgeView.setFitWidth(280);
        label.setMinHeight(500);
        label.setMinWidth(400);
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
