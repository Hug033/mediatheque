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

    @FXML
    private void VerifTouche(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            Recherche();
        }
    }

    @FXML
    private void Recherche() {
        //Recherche
    }

    @FXML
    private void AfficherEmprunt() {

    }

    @FXML
    private void AfficherCompte() {

    }

    @FXML
    private void Filtrer() {

    }
}
