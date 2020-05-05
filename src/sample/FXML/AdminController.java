package sample.FXML;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class AdminController {

    private String token;

    @FXML
    private TextField InputRecherche;
    private Button ButtonRecherche;
    private ImageView ImageProfil;
    private CheckBox TypeCheckClient;
    private CheckBox TypeCheckOperateur;
    private CheckBox TypeCheckAdmin;
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

    // Méthode pour afficher le pane de compte
    @FXML
    private void AfficherCompte() {

    }

    //Méthode Quand on clique sur le selectDVD
    @FXML
    private void SelectClient() {

    }

    //Méthode Quand on clique sur le selectCD
    @FXML
    private void SelectOperateur() {

    }

    //Méthode Quand on clique sur le selectLivre
    @FXML
    private void SelectAdministrateur() {

    }

    // Méthode pour filtrer la recherche
    @FXML
    private void Filtrer() {

    }
}
