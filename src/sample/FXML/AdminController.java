package sample.FXML;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
}
