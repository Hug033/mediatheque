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

import java.awt.event.ActionListener;

public class MediaListCell extends ListCell<Media> {

    private final GridPane gridPane = new GridPane();
    private final Label authorLabel = new Label();
    private final Label titleLabel = new Label();
    private final Label descriptionLabel = new Label();
    private final Label rateLabel = new Label();
    private final Button moreButton = new Button();
    private final ImageView mediaIcon = new ImageView();
    private final AnchorPane content = new AnchorPane();

    public MediaListCell() {
        mediaIcon.setFitWidth(75);
        mediaIcon.setPreserveRatio(true);
        GridPane.setConstraints(mediaIcon, 0, 0, 1, 3);
        GridPane.setValignment(mediaIcon, VPos.TOP);
        //
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;");
        GridPane.setConstraints(titleLabel, 1, 0);
        //
        authorLabel.setStyle("-fx-font-size: 0.9em; -fx-font-style: italic; -fx-opacity: 0.5;");
        GridPane.setConstraints(authorLabel, 2, 0);
        //
        descriptionLabel.setStyle("-fx-opacity: 0.75;");
        GridPane.setConstraints(descriptionLabel, 1, 1);
        GridPane.setColumnSpan(descriptionLabel, Integer.MAX_VALUE);
        //
        rateLabel.setStyle("-fx-opacity: 0.75;");
        GridPane.setConstraints(rateLabel, 1, 2);
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
        gridPane.getChildren().setAll(mediaIcon, titleLabel, authorLabel, descriptionLabel, rateLabel, moreButton);
        gridPane.setPrefWidth(100.0);
        AnchorPane.setTopAnchor(gridPane, 0d);
        AnchorPane.setLeftAnchor(gridPane, 0d);
        AnchorPane.setBottomAnchor(gridPane, 0d);
        AnchorPane.setRightAnchor(gridPane, 0d);
        content.getChildren().add(gridPane);
    }

    @Override
    protected void updateItem(Media item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic(null);
        setText(null);
        setContentDisplay(ContentDisplay.LEFT);
        if (!empty && item != null) {
            authorLabel.setText(item.getAuthor());
            String title = item.getTitle();
            if(item.getState() == 1)
                title += " (a valider)";
            else if (item.getState() == 2)
                title += " (en cours)";
            else if (item.getState() == 3)
                title += " (en retard)";
            else if (item.getState() == 0)
                title += " (en stock)";
            titleLabel.setText(title);
            mediaIcon.setImage(new Image(Main.class.getResourceAsStream("FXML/logo.png")));
            descriptionLabel.setText(item.getDescription());
            rateLabel.setText(String.format("%d/5 étoiles (%d évaluations)", item.getRate(), item.getNbRate()));
            moreButton.setText("+ de détails !");
            setText(null);
            setGraphic(content);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}