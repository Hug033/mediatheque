package sample.FXML;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import sample.Main;

public class UserListCell extends ListCell<User> {

    private final GridPane gridPane = new GridPane();
    private final Label regitrationLabel = new Label();
    private final Label nameLabel = new Label();
    private final Label statusLabel = new Label();
    private final Button moreButton = new Button();
    private final ImageView profilIcon = new ImageView();
    private final AnchorPane content = new AnchorPane();

    public UserListCell() {
        profilIcon.setFitWidth(75);
        profilIcon.setPreserveRatio(true);
        GridPane.setConstraints(profilIcon, 0, 0, 1, 3);
        GridPane.setValignment(profilIcon, VPos.TOP);
        //
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;");
        GridPane.setConstraints(nameLabel, 1, 0);
        //
        regitrationLabel.setStyle("-fx-font-size: 0.9em; -fx-font-style: italic; -fx-opacity: 0.5;");
        GridPane.setConstraints(regitrationLabel, 2, 0);
        //
        statusLabel.setStyle("-fx-font-size: 20.0;");
        GridPane.setConstraints(statusLabel, 1, 1);
        GridPane.setColumnSpan(statusLabel, Integer.MAX_VALUE);
        //
        moreButton.setStyle("-fx-background-color: #00adb5");
        moreButton.setTextFill(Color.WHITE);
        GridPane.setConstraints(moreButton, 2, 2);
        //
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));
        gridPane.setHgap(6);
        gridPane.setVgap(6);
        gridPane.getChildren().setAll(profilIcon, nameLabel, regitrationLabel, statusLabel, moreButton);
        gridPane.setPrefWidth(100.0);
        AnchorPane.setTopAnchor(gridPane, 0d);
        AnchorPane.setLeftAnchor(gridPane, 0d);
        AnchorPane.setBottomAnchor(gridPane, 0d);
        AnchorPane.setRightAnchor(gridPane, 0d);
        content.getChildren().add(gridPane);
    }

    @Override
    protected void updateItem(User item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic(null);
        setText(null);
        setContentDisplay(ContentDisplay.LEFT);
        if (!empty && item != null) {
            nameLabel.setText(item.getFirstname() + " " + item.getLastname());
            String title = "";
            if(item.getState() == 0)
                title += " Banni";
            else if (item.getState() == 1)
                title += " Lecteur";
            else if (item.getState() == 2)
                title += "  Opérateur";
            else if (item.getState() == 3)
                title += "  Administrateur";
            statusLabel.setText(title);
            profilIcon.setImage(new Image(Main.class.getResourceAsStream("FXML/Profil.png")));
            regitrationLabel.setText(item.getRegistration());
            moreButton.setText("+ de détails !");
            setText(null);
            setGraphic(content);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}