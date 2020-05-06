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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    private String token;

    @FXML
    private TextField InputRecherche;

    @FXML
    private Button ButtonEmprunt;

    @FXML
    private Button ButtonRecherche;

    @FXML
    private ImageView ImageProfil;

    @FXML
    private CheckBox TypeCheckDVD;

    @FXML
    private CheckBox TypeCheckCD;

    @FXML
    private CheckBox TypeCheckLivre;

    @FXML
    private ListView<String> ListViewCategorie = new ListView<String>();

    @FXML
    private CheckBox TypeCheck5Stars;

    @FXML
    private CheckBox TypeCheck4Stars;

    @FXML
    private CheckBox TypeCheck3Stars;

    @FXML
    private CheckBox TypeCheck2Stars;

    @FXML
    private CheckBox TypeCheck1Stars;

    @FXML
    private Label titleMedia;

    @FXML
    private TextArea descriptionMedia;

    @FXML
    private Label noteMedia;

    @FXML
    private Label statusMedia;

    @FXML
    private DatePicker dateEmprunt;

    @FXML
    private Button ButtonActu;

    @FXML
    private Pane detailPane;

    @FXML
    private Pane canReserve;

    @FXML
    private ListView<Media> ListViewResultat = new ListView<Media>();

    ArrayList<Media> saveList = new ArrayList<Media>();

    @FXML
    private Pane PaneListViewCate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> commandes = new ArrayList<>();
        commandes.add("LIST");
        commandes.add("DVD");
        ClientConnexion connexion = new ClientConnexion("127.0.0.1", 2345, commandes);
        List<Serializable> response = connexion.run();
        ArrayList<Media> back = new Gson().fromJson((String)response.get(0), ArrayList.class);
        saveList = back;
        ObservableList<Media> items = FXCollections.observableArrayList();
        TypeCheckDVD.setSelected(true);

        for(int i = 0; i < back.size(); i++) {
            Media temp = new Gson().fromJson(String.valueOf(back.get(i)), Media.class);
            System.out.println(temp);
            items.add(temp);
        }
        ListViewResultat.setCellFactory(lv -> new MediaListCell());
        ListViewResultat.setItems(items);
        ListViewResultat.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AfficherCompte(ListViewResultat.getSelectionModel().getSelectedItem());
            }
        });
    }

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

    }

    // Méthode pour afficher le pane d'emprunt
    @FXML
    private void AfficherEmprunt() {

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
        titleMedia.setText(m.getTitle());
        descriptionMedia.setText(SplitDescription(m.getDescription()));
        noteMedia.setText(String.valueOf(m.getRate()) + "/5");
        String status = "";
        statusMedia.setTextFill(Color.RED);
        if(m.getState() == 1)
            status = "Réservé";
        else if(m.getState() == 2)
            status = "Réservé";
        else if(m.getState() == 3)
            status = "Réservé";
        else {
            statusMedia.setTextFill(Color.GREEN);
            status = "Libre";
        }
        statusMedia.setText(status);
        canReserve.setVisible(m.getState() == 0);
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

    @FXML
    private void reserve() {
        LocalDate localDate = dateEmprunt.getValue();
        LocalDate current30 = localDate.plusMonths(30);
        List<String> commandes = new ArrayList<>();
        commandes.add("ADD_RESERVE");
        commandes.add(localDate.toString());
        commandes.add(current30.toString());
        commandes.add(ListViewResultat.getSelectionModel().getSelectedItem().getRef());
        ClientConnexion connexion = new ClientConnexion("127.0.0.1", 2345, commandes);
        List<Serializable> response = connexion.run();

    }

    //Méthode Lors de la séléction d'un type de média
    public void SelectTypeMedia(String type) {
        ListViewResultat.getItems().clear();
        TypeCheckDVD.setSelected(type.equals("DVD"));
        TypeCheckCD.setSelected(type.equals("CD"));
        TypeCheckLivre.setSelected(type.equals("LIVRE"));
        TypeCheck1Stars.setSelected(false);
        TypeCheck2Stars.setSelected(false);
        TypeCheck3Stars.setSelected(false);
        TypeCheck4Stars.setSelected(false);
        TypeCheck5Stars.setSelected(false);

        List<String> commandes = new ArrayList<>();
        commandes.add("LIST");
        commandes.add(type);
        ClientConnexion connexion = new ClientConnexion("127.0.0.1", 2345, commandes);
        List<Serializable> response = connexion.run();
        ArrayList<Media> back = new Gson().fromJson((String)response.get(0), ArrayList.class);
        saveList = back;
        ObservableList<Media> items = FXCollections.observableArrayList();

        for(int i = 0; i < back.size(); i++) {
            Media temp = new Gson().fromJson(String.valueOf(back.get(i)), Media.class);
            System.out.println(temp);
            items.add(temp);
        }
        ListViewResultat.setCellFactory(lv -> new MediaListCell());
        ListViewResultat.setItems(items);
    }

    // Méthode quand on clique sur le Select5
    @FXML
    private void Select5Stars() {
        SelectRateMedia(5);
    }

    // Méthode quand on clique sur le Select4
    @FXML
    private void Select4Stars() {
        SelectRateMedia(4);
    }

    // Méthode quand on clique sur le Select3
    @FXML
    private void Select3Stars() {
        SelectRateMedia(3);
    }

    // Méthode quand on clique sur le Select2
    @FXML
    private void Select2Stars() {
        SelectRateMedia(2);
    }

    // Méthode quand on clique sur le Select1
    @FXML
    private void Select1Stars() {
        SelectRateMedia(1);
    }

    //Méthode Lors de la séléction d'une note de media
    private void SelectRateMedia(int Rate) {
        TypeCheck1Stars.setSelected(Rate == 1);
        TypeCheck2Stars.setSelected(Rate == 2);
        TypeCheck3Stars.setSelected(Rate == 3);
        TypeCheck4Stars.setSelected(Rate == 4);
        TypeCheck5Stars.setSelected(Rate == 5);
        ListViewResultat.getItems().clear();
        ObservableList<Media> items = FXCollections.observableArrayList();

        for(int i = 0; i < saveList.size(); i++) {
            Media temp = new Gson().fromJson(String.valueOf(saveList.get(i)), Media.class);
            if(temp.getRate() >= Rate)
                items.add(temp);
        }
        ListViewResultat.setCellFactory(lv -> new MediaListCell());
        ListViewResultat.setItems(items);
    }
}
