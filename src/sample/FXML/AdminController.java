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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    private String token;

    @FXML
    private TextField InputRecherche;

    @FXML
    private Button ButtonRecherche;

    @FXML
    private ImageView ImageProfil;

    @FXML
    private CheckBox TypeCheckClient;

    @FXML
    private CheckBox TypeCheckOperateur;

    @FXML
    private CheckBox TypeCheckBanni;

    @FXML
    private CheckBox TypeCheckAdmin;

    @FXML
    private Button ButtonFiltre;

    @FXML
    private Pane PaneResultat;

    @FXML
    private ListView<User> ListViewResultat = new ListView<User>();

    private ArrayList<User> saveList = null;

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

    private void selectUserByState(int state) {
        ObservableList<User> items = FXCollections.observableArrayList();
        for (int i = 0; i < saveList.size(); i++) {
            User temp = new Gson().fromJson(String.valueOf(saveList.get(i)), User.class);
            if (temp.getState() == state)
                items.add(temp);
        }
        ListViewResultat.getItems().clear();
        ListViewResultat.setCellFactory(lv -> new UserListCell());
        ListViewResultat.setItems(items);
    }

    private void reset(int state) {
        if (state != 1)
            TypeCheckClient.setSelected(false);
        if (state != 2)
            TypeCheckOperateur.setSelected(false);
        if (state != 3)
            TypeCheckAdmin.setSelected(false);
        if (state != 0)
            TypeCheckBanni.setSelected(false);
        ObservableList<User> items = FXCollections.observableArrayList();
        for (int i = 0; i < saveList.size(); i++) {
            User temp = new Gson().fromJson(String.valueOf(saveList.get(i)), User.class);
            items.add(temp);
        }
        ListViewResultat.getItems().clear();
        ListViewResultat.setCellFactory(lv -> new UserListCell());
        ListViewResultat.setItems(items);
    }

    //Méthode Quand on clique sur le selectDVD
    @FXML
    private void SelectClient() {
        reset(1);
        if (TypeCheckClient.isSelected())
            selectUserByState(1);
    }

    //Méthode Quand on clique sur le selectCD
    @FXML
    private void SelectOperateur() {
        reset(2);
        if (TypeCheckOperateur.isSelected())
            selectUserByState(2);
    }

    //Méthode Quand on clique sur le selectLivre
    @FXML
    private void SelectAdministrateur() {
        reset(3);
        if (TypeCheckAdmin.isSelected())
            selectUserByState(3);
    }

    //Méthode Quand on clique sur le selectLivre
    @FXML
    private void SelectBan() {
        reset(0);
        if (TypeCheckBanni.isSelected())
            selectUserByState(0);
    }

    // Méthode pour filtrer la recherche
    @FXML
    private void Filtrer() {

    }

    public void loadList() {
        TypeCheckClient.setSelected(false);
        TypeCheckOperateur.setSelected(false);
        TypeCheckAdmin.setSelected(false);
        TypeCheckBanni.setSelected(false);
        List<String> commandes = new ArrayList<>();
        commandes.add("USERS");
        ClientConnexion connexion = new ClientConnexion("127.0.0.1", 2345, commandes);
        List<Serializable> response = connexion.run();
        ArrayList<User> back = new Gson().fromJson((String) response.get(0), ArrayList.class);
        saveList = back;
        ObservableList<User> items = FXCollections.observableArrayList();

        for (int i = 0; i < back.size(); i++) {
            User temp = new Gson().fromJson(String.valueOf(back.get(i)), User.class);
            items.add(temp);
        }
        ListViewResultat.setCellFactory(lv -> new UserListCell());
        ListViewResultat.setItems(items);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadList();
    }
}
