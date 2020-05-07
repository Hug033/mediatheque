package sample.FXML;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sample.ClientConnexion;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OperateurController implements Initializable {

    private String token;

    @FXML
    private TextField InputRecherche;

    @FXML
    private Button actionButton;

    @FXML
    private Button actionButton2;

    @FXML
    private ImageView ImageProfil;

    @FXML
    private CheckBox TypeCheckDVD;

    @FXML
    private CheckBox TypeCheckCD;

    @FXML
    private CheckBox TypeCheckLivre;

    @FXML
    private CheckBox TypeCheckValider;

    @FXML
    private CheckBox TypeCheckCours;

    @FXML
    private CheckBox TypeCheckRetard;

    @FXML
    private CheckBox TypeCheckStock;

    @FXML
    private Button ButtonFiltre;

    @FXML
    private Pane PaneResultat;

    @FXML
    private Label titleMedia;

    @FXML
    private TextArea descriptionMedia;

    @FXML
    private Label noteMedia;

    @FXML
    private Label statusMedia;

    @FXML
    private Pane detailPane;

    @FXML
    private ListView<Media> ListViewResultat = new ListView<Media>();

    private ArrayList<Media> saveList = null;


    //Méthode d'initilisation

    //Init du controller
    public void init(String token) {
        this.token = token;
    }

    //Verif si on tape entrer pour lancer la recherche plus vite
    @FXML
    private void VerifTouche(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            Recherche();
        }
    }

    // Méthode de Recherche sur ce qu'on à rentrer
    @FXML
    private void Recherche() {
        ObservableList<Media> items = FXCollections.observableArrayList();
        TypeCheckStock.setSelected(false);
        TypeCheckRetard.setSelected(false);
        TypeCheckValider.setSelected(false);
        TypeCheckCours.setSelected(false);

        for (int i = 0; i < saveList.size(); i++) {
            Media temp = new Gson().fromJson(String.valueOf(saveList.get(i)), Media.class);
            if (temp.getTitle().contains(InputRecherche.getText()))
                items.add(temp);
        }
        ListViewResultat.setCellFactory(lv -> new MediaListCell());
        ListViewResultat.setItems(items);
    }

    //Methode pour split la description
    public String SplitDescription(String description) {
        if (description.length() < 136) {
            return description;
        } else if (description.length() >= 136 && description.length() < 272) {
            return description.substring(0, 135) + "\n" + description.substring(136, 271);
        } else {
            return description.substring(0, 135) + "\n" + description.substring(136, 271) + "\n" + description.substring(272, 407);
        }
    }

    // Méthode pour afficher le pane de compte
    @FXML
    private void AfficherCompte(Media m) {
        ListViewResultat.setVisible(false);
        actionButton.setVisible(true);
        actionButton2.setVisible(true);
        titleMedia.setText(m.getTitle());
        descriptionMedia.setText(SplitDescription(m.getDescription()));
        noteMedia.setText(String.valueOf(m.getRate()) + "/5");
        String status = "";
        statusMedia.setTextFill(Color.RED);
        if (m.getState() == 1) {
            status = "Réservé";
            actionButton.setText("A valider");
            actionButton2.setDisable(true);
        } else if (m.getState() == 2) {
            status = "En cours";
            actionButton.setText("Indiquer retard");
            actionButton2.setText("Rendu");
        } else if (m.getState() == 3) {
            status = "En retard";
            actionButton.setText("Rendu");
            actionButton2.setDisable(true);
        } else {
            statusMedia.setTextFill(Color.GREEN);
            status = "Libre";
            actionButton.setVisible(false);
            actionButton2.setVisible(false);
        }
        statusMedia.setText(status);
        detailPane.setVisible(true);
    }

    @FXML
    private void back() {
        ListViewResultat.setVisible(true);
        detailPane.setVisible(false);
    }

    //Méthode Quand on clique sur le selectDVD
    @FXML
    private void SelectDVD() {
        SelectTypeMedia("DVD");
    }

    //Méthode Quand on clique sur le selectCD
    @FXML
    private void SelectCD() {
        SelectTypeMedia("CD");
    }

    //Méthode Quand on clique sur le selectLivre
    @FXML
    private void SelectLivre() {
        SelectTypeMedia("LIVRE");
    }

    //Méthode Lors de la séléction d'un type de média
    public void SelectTypeMedia(String type) {
        ListViewResultat.getItems().clear();
        TypeCheckDVD.setSelected(type.equals("DVD"));
        TypeCheckCD.setSelected(type.equals("CD"));
        TypeCheckLivre.setSelected(type.equals("LIVRE"));
        TypeCheckValider.setSelected(false);
        TypeCheckCours.setSelected(false);
        TypeCheckRetard.setSelected(false);
        TypeCheckStock.setSelected(false);

        List<String> commandes = new ArrayList<>();
        commandes.add("LIST");
        commandes.add(type);
        ClientConnexion connexion = new ClientConnexion("127.0.0.1", 2345, commandes);
        List<Serializable> response = connexion.run();
        ArrayList<Media> back = new Gson().fromJson((String) response.get(0), ArrayList.class);
        saveList = back;
        ObservableList<Media> items = FXCollections.observableArrayList();

        for (int i = 0; i < back.size(); i++) {
            Media temp = new Gson().fromJson(String.valueOf(back.get(i)), Media.class);
            System.out.println(temp);
            items.add(temp);
        }
        ListViewResultat.setCellFactory(lv -> new MediaListCell());
        ListViewResultat.setItems(items);
    }

    private void selectByState(int state) {
        ObservableList<Media> items = FXCollections.observableArrayList();
        for (int i = 0; i < saveList.size(); i++) {
            Media temp = new Gson().fromJson(String.valueOf(saveList.get(i)), Media.class);
            if (temp.getState() == state)
                items.add(temp);
        }
        ListViewResultat.getItems().clear();
        ListViewResultat.setCellFactory(lv -> new MediaListCell());
        ListViewResultat.setItems(items);
    }

    private void reset(int state) {
        if (state != 1)
            TypeCheckValider.setSelected(false);
        if (state != 2)
            TypeCheckCours.setSelected(false);
        if (state != 3)
            TypeCheckRetard.setSelected(false);
        if (state != 0)
            TypeCheckStock.setSelected(false);
        ObservableList<Media> items = FXCollections.observableArrayList();
        for (int i = 0; i < saveList.size(); i++) {
            Media temp = new Gson().fromJson(String.valueOf(saveList.get(i)), Media.class);
            items.add(temp);
        }
        ListViewResultat.getItems().clear();
        ListViewResultat.setCellFactory(lv -> new MediaListCell());
        ListViewResultat.setItems(items);
    }

    //Méthode Quand on clique sur le selectValider
    @FXML
    private void SelectValider() {
        reset(1);
        if (TypeCheckValider.isSelected())
            selectByState(1);
    }

    //Méthode Quand on clique sur le selectCours
    @FXML
    private void SelectCours() {
        reset(2);
        if (TypeCheckCours.isSelected())
            selectByState(2);
    }

    //Méthode Quand on clique sur le selectRetard
    @FXML
    private void SelectRetard() {
        reset(3);
        if (TypeCheckRetard.isSelected())
            selectByState(3);
    }

    //Méthode Quand on clique sur le selectStock
    @FXML
    private void SelectStock() {
        reset(0);
        if (TypeCheckStock.isSelected())
            selectByState(0);
    }

    // Méthode pour filtrer la recherche
    @FXML
    private void Filtrer() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SelectTypeMedia("DVD");
        ListViewResultat.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (ListViewResultat.getSelectionModel().getSelectedItem() != null)
                    AfficherCompte(ListViewResultat.getSelectionModel().getSelectedItem());
            }
        });
    }

    @FXML
    private void refresh() {
        SelectTypeMedia("DVD");
    }

    @FXML
    private void updateBorrow() {
        Media m = ListViewResultat.getSelectionModel().getSelectedItem();
        List<String> commandes = new ArrayList<>();
        commandes.add("CHANGE_BORROW");
        commandes.add(String.valueOf(m.getBorrowId()));
        if (m.getState() == 1)
            commandes.add("2");
        else if (m.getState() == 2)
            commandes.add("3");
        else
            commandes.add("0");

        ClientConnexion connexion = new ClientConnexion("127.0.0.1", 2345, commandes);
        List<Serializable> response = connexion.run();
        if (response.get(0).equals("OK")) {
            back();
            refresh();
        }
    }

    @FXML
    private void borrowStock() {
        Media m = ListViewResultat.getSelectionModel().getSelectedItem();
        List<String> commandes = new ArrayList<>();
        commandes.add("CHANGE_BORROW");
        commandes.add(String.valueOf(m.getBorrowId()));
        commandes.add("0");

        ClientConnexion connexion = new ClientConnexion("127.0.0.1", 2345, commandes);
        List<Serializable> response = connexion.run();
        if (response.get(0).equals("OK")) {
            actionButton.setVisible(false);
            actionButton2.setVisible(false);
        }
    }
}
