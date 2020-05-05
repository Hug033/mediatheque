package sample.FXML;

import javafx.scene.control.ListCell;

public class SimpleMediaListCell extends ListCell<Media> {
    @Override
    protected void updateItem(Media item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        System.out.println("Cell");
        if (!empty && item != null) {
            System.out.println("Update");
            final String text = String.format("%s %s", item.getNom(), item.getRate());
        }
    }
}
