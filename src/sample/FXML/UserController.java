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

    //Méthode Quand on clique sur le selectDVD
    @FXML
    private void SelectDVD() {

    }

    //Méthode Quand on clique sur le selectCD
    @FXML
    private void SelectCD() {

    }

    //Méthode Quand on clique sur le selectLivre
    @FXML
    private void SelectLivre() {

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
