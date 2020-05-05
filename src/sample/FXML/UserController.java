package sample.FXML;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import sample.ClientConnexion;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URL;
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
    private Button ButtonFiltre;

    @FXML
    private ListView<Media> ListViewResultat = new ListView<Media>();

    @FXML
    private Pane PaneListViewCate;

    @Override
    public void initialize(URL url, ResourceBundle rb) { //TODO Faire un compteur de mot (3 lignes max)


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

    // Méthode pour afficher le pane de compte
    @FXML
    private void AfficherCompte() {

    }

    // Méthode pour filtrer la recherche
    @FXML
    private void Filtrer() {

    }

    //Méthode Quand on clique sur le selectDVD
    @FXML
    private void SelectDVD() {
        ListViewResultat.getItems().clear();
        TypeCheckLivre.setSelected(false);
        TypeCheckCD.setSelected(false);
        TypeCheck1Stars.setSelected(false);
        TypeCheck2Stars.setSelected(false);
        TypeCheck3Stars.setSelected(false);
        TypeCheck4Stars.setSelected(false);
        TypeCheck5Stars.setSelected(false);

        List<String> commandes = new ArrayList<>();
        commandes.add("LIST");
        commandes.add("DVD");
        ClientConnexion connexion = new ClientConnexion("127.0.0.1", 2345, commandes);
        List<Serializable> response = connexion.run();
        ArrayList<Media> back = new Gson().fromJson((String)response.get(0), ArrayList.class);
        ObservableList<Media> items = FXCollections.observableArrayList();

        for(int i = 0; i < back.size(); i++) {
            Media temp = new Gson().fromJson(String.valueOf(back.get(i)), Media.class);
            System.out.println(temp);
            items.add(temp);
        }
        ListViewResultat.setCellFactory(lv -> new MediaListCell());
        ListViewResultat.setItems(items);
    }

    //Méthode Quand on clique sur le selectCD
    @FXML
    private void SelectCD() {
        ListViewResultat.getItems().clear();
        TypeCheckLivre.setSelected(false);
        TypeCheckDVD.setSelected(false);
        TypeCheck1Stars.setSelected(false);
        TypeCheck2Stars.setSelected(false);
        TypeCheck3Stars.setSelected(false);
        TypeCheck4Stars.setSelected(false);
        TypeCheck5Stars.setSelected(false);

        List<String> commandes = new ArrayList<>();
        commandes.add("LIST");
        commandes.add("CD");
        ClientConnexion connexion = new ClientConnexion("127.0.0.1", 2345, commandes);
        List<Serializable> response = connexion.run();
        ArrayList<Media> back = new Gson().fromJson((String)response.get(0), ArrayList.class);
        ObservableList<Media> items = FXCollections.observableArrayList();

        for(int i = 0; i < back.size(); i++) {
            Media temp = new Gson().fromJson(String.valueOf(back.get(i)), Media.class);
            System.out.println(temp);
            items.add(temp);
        }
        ListViewResultat.setCellFactory(lv -> new MediaListCell());
        ListViewResultat.setItems(items);

    }

    //Méthode Quand on clique sur le selectLivre
    @FXML
    private void SelectLivre() {
        ListViewResultat.getItems().clear();
        TypeCheckDVD.setSelected(false);
        TypeCheckCD.setSelected(false);
        TypeCheck1Stars.setSelected(false);
        TypeCheck2Stars.setSelected(false);
        TypeCheck3Stars.setSelected(false);
        TypeCheck4Stars.setSelected(false);
        TypeCheck5Stars.setSelected(false);

        List<String> commandes = new ArrayList<>();
        commandes.add("LIST");
        commandes.add("LIVRE");
        ClientConnexion connexion = new ClientConnexion("127.0.0.1", 2345, commandes);
        List<Serializable> response = connexion.run();
        ArrayList<Media> back = new Gson().fromJson((String)response.get(0), ArrayList.class);
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

    }

    // Méthode quand on clique sur le Select4
    @FXML
    private void Select4Stars() {

    }

    // Méthode quand on clique sur le Select3
    @FXML
    private void Select3Stars() {

    }

    // Méthode quand on clique sur le Select2
    @FXML
    private void Select2Stars() {

    }

    // Méthode quand on clique sur le Select1
    @FXML
    private void Select1Stars() {

    }
}
