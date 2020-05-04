package sample.FXML;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    public TextField TextEmail;
    public PasswordField TextPass;
    public Label LoginError;
    public Button ButtonConn;

    private String Login;
    private String Password;

    // Méthode lors du click sur le bouton de connexion pour vérifier la connexion de l'utilisateur et son rôle.
    @FXML
    private void Connexion() {
        this.Login = TextEmail.getText(); //TODO Verification du mail
        this.Password = TextPass.getText();
        System.out.println(this.Login);
        System.out.println(this.Password);
        // Lancer la vérification de la connexion
        // Try Catch si pas de connexion afficher le messge d'erreur
        // Récuérer le login, l'image et le role pour afficher l'interface
        LoginError.setVisible(true);
    }
}
