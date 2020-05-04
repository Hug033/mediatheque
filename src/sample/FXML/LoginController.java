package sample.FXML;

import sample.ClientConnexion;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LoginController {

    // Init des composant FXML
    @FXML
    public TextField TextEmail;
    public PasswordField TextPass;
    public Label LoginError;
    public Button ButtonConn;

    // Méthode sur le PasswordField pour lancer la connexion
    @FXML
    private void ConnexionEnter(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            Connexion();
        }
    }

    // Méthode sur le bouton connexion pour lancer la connexion.
    @FXML
    private void Connexion() {
        if (Pattern.matches("^(.){1,}@(.){1,}\\.(.){1,}$", TextEmail.getText())) {
            List<String> commandes = new ArrayList<>();
            commandes.add("auth");
            commandes.add(TextEmail.getText());
            commandes.add(TextPass.getText());

            ClientConnexion connexion = new ClientConnexion("127.0.0.1", 2345, commandes);
            List<String> response = connexion.run();

            if (response.get(0) == "OK") {
                if (response.get(2) == "0") {
                    LoginError.setVisible(true);
                    LoginError.setText("Vous êtes banni veuillez contacter un administrateur");
                } else if (response.get(2) == "1") {
                    // Afficher Interface user
                } else if (response.get(2) == "2") {
                    // Afficher Interface operateur
                } else if (response.get(2) == "3") {
                    // Afficher interface Administrateur
                }
            } else {
                LoginError.setVisible(true);
                LoginError.setText("Votre mot de passe ou/et identifiant sont incorrects");
            }
        } else {
            LoginError.setVisible(true);
            LoginError.setText("La saisie de l'email est incorrect");
        }
    }
}
