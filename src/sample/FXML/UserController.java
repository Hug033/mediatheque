package sample.FXML;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class UserController {

    private String token;

    @FXML
    private TextField InputRecherche;
    private Button ButtonEmprunt;
    private Button ButtonRecherche;
    private ImageView ImageProfil;
    private CheckBox TypeCheckDVD;
    private CheckBox TypeCheckCD;
    private CheckBox TypeCheckLivre;
    private ListView ListViewCategorie;
    private CheckBox TypeCheck5Stars;
    private CheckBox TypeCheck4Stars;
    private CheckBox TypeCheck3Stars;
    private CheckBox TypeCheck2Stars;
    private CheckBox TypeCheck1Stars;
    private Button ButtonFiltre;
    private Pane PaneResultat;

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

    //Méthode pour remplir la list view des catégories
    @FXML
    private void SelectDVD() {

    }

    @FXML
    private void SelectCD() {

    }

    @FXML
    private void SelectLivre() {

    }

    @FXML
    private void Select5Stars() {

    }

    @FXML
    private void Select4Stars() {

    }

    @FXML
    private void Select3Stars() {

    }

    @FXML
    private void Select2Stars() {

    }

    @FXML
    private void Select1Stars() {

    }
}
