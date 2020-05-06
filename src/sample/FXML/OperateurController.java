package sample.FXML;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import sample.ClientConnexion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OperateurController {

    private String token;

    @FXML
    private TextField InputRecherche;

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
    private ListView<Media> ListViewResultat = new ListView<Media>();

    private ArrayList<Media> saveList = null;


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

    // Méthode pour afficher le pane de compte
    @FXML
    private void AfficherCompte() {

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

    private void selectByState(int state) {
        ObservableList<Media> items = FXCollections.observableArrayList();
        for(int i = 0; i < saveList.size(); i++) {
            Media temp = new Gson().fromJson(String.valueOf(saveList.get(i)), Media.class);
            if(temp.getState() == state)
                items.add(temp);
        }
        ListViewResultat.getItems().clear();
        ListViewResultat.setCellFactory(lv -> new MediaListCell());
        ListViewResultat.setItems(items);
    }

    private void reset(int state) {
        if(state != 1)
            TypeCheckValider.setSelected(false);
        if(state != 2)
            TypeCheckCours.setSelected(false);
        if(state != 3)
            TypeCheckRetard.setSelected(false);
        if( state != 0)
            TypeCheckStock.setSelected(false);
        ObservableList<Media> items = FXCollections.observableArrayList();
        for(int i = 0; i < saveList.size(); i++) {
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
        if(TypeCheckValider.isSelected())
            selectByState(1);
    }

    //Méthode Quand on clique sur le selectCours
    @FXML
    private void SelectCours() {
        reset(2);
        if(TypeCheckCours.isSelected())
            selectByState(2);
    }

    //Méthode Quand on clique sur le selectRetard
    @FXML
    private void SelectRetard() {
        reset(3);
        if(TypeCheckRetard.isSelected())
            selectByState(3);
    }

    //Méthode Quand on clique sur le selectStock
    @FXML
    private void SelectStock() {
        reset(0);
        if(TypeCheckStock.isSelected())
            selectByState(0);
    }

    // Méthode pour filtrer la recherche
    @FXML
    private void Filtrer() {

    }
}
