package sample.FXML;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class OperateurController {

    private String token;

    @FXML
    private TextField InputRecherche;
    private Button ButtonRecherche;
    private ImageView ImageProfil;
    private CheckBox TypeCheckDVD;
    private CheckBox TypeCheckCD;
    private CheckBox TypeCheckLivre;
    private CheckBox TypeCheckValider;
    private CheckBox TypeCheckCours;
    private CheckBox TypeCheckRetard;
    private CheckBox TypeCheckStock;
    private Button ButtonFiltre;
    private Pane PaneResultat;

    //Init du controller
    public void init(String token) {
        this.token = token;
    }
}
