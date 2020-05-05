package sample.FXML;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

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

    }

    //Méthode Quand on clique sur le selectCD
    @FXML
    private void SelectCD() {

    }

    //Méthode Quand on clique sur le selectLivre
    @FXML
    private void SelectLivre() {

    }

    //Méthode Quand on clique sur le selectValider
    @FXML
    private void SelectValider() {

    }

    //Méthode Quand on clique sur le selectCours
    @FXML
    private void SelectCours() {

    }

    //Méthode Quand on clique sur le selectRetard
    @FXML
    private void SelectRetard() {

    }

    //Méthode Quand on clique sur le selectStock
    @FXML
    private void SelectStock() {

    }

    // Méthode pour filtrer la recherche
    @FXML
    private void Filtrer() {

    }
}
