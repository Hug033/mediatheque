package sample.FXML;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.ClientConnexion;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LoginController {

    private boolean isRunning = false;

    // Init des composant FXML
    @FXML
    public TextField TextEmail;

    @FXML
    public PasswordField TextPass;

    @FXML
    public Label LoginError;

    @FXML
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
        if(!isRunning)
        {
            isRunning = true;
            if (Pattern.matches("^(.){1,}@(.){1,}\\.(.){1,}$", TextEmail.getText())) {
                List<String> commandes = new ArrayList<>();
                commandes.add("auth");
                commandes.add(TextEmail.getText());

                int hash = 7;
                for (int i = 0; i < TextPass.getText().length(); i++)
                    hash = hash * 31 + TextPass.getText().charAt(i);
                commandes.add(String.valueOf(hash));

                ClientConnexion connexion = new ClientConnexion("127.0.0.1", 2345, commandes);
                List<Serializable> response = connexion.run();

                if (response.get(0).equals("OK")) {
                    if (response.get(2).equals("0")) {
                        LoginError.setVisible(true);
                        LoginError.setText("Vous êtes banni veuillez contacter un administrateur");
                        isRunning = false;
                    } else if (response.get(2).equals("1")) {
                        ChangePane("UserInterface.fxml", (String)response.get(1));
                    } else if (response.get(2).equals("2")) {
                        ChangePane("OperateurInterface.fxml", (String)response.get(1));
                    } else if (response.get(2).equals("3")) {
                        ChangePane("AdminInterface.fxml", (String)response.get(1));
                    }
                } else {
                    LoginError.setVisible(true);
                    LoginError.setText("Votre mot de passe ou/et identifiant sont incorrects");
                    isRunning = false;
                }
            } else {
                LoginError.setVisible(true);
                LoginError.setText("La saisie de l'email est incorrect");
                isRunning = false;
            }
        }
    }

    private void ChangePane(String pane, String token) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pane));
        Parent root1;
        try {
            root1 = (Parent) fxmlLoader.load();
            String title = "";

            if (pane.equals("UserInterface.fxml")) {
                UserController userController = fxmlLoader.getController();
                userController.init(token);
                title = "Utilisateur";
            } else if (pane.equals("OperateurInterface.fxml")) {
                OperateurController operateurController = fxmlLoader.getController();
                operateurController.init(token);
                title = "Opérateur";
            } else {
                AdminController adminController = fxmlLoader.getController();
                adminController.init(token);
                title = "Administrateur";
            }

            Stage stage = new Stage();
            Scene scene1 = new Scene(root1, 1024, 768);
            stage.setScene(scene1);
            stage.setResizable(false);
            stage.getIcons().add(new Image(LoginController.class.getResourceAsStream("logo.png")));
            stage.setTitle("xMediatek - " + title);
            stage.show();

            stage.setOnCloseRequest(windowEvent -> {
                FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root2;
                try {
                    root2 = (Parent) fxmlLoader1.load();

                    Stage stage1 = new Stage();
                    stage1.setScene(new Scene(root2, 1024, 768));
                    stage1.setResizable(false);
                    stage1.getIcons().add(new Image(LoginController.class.getResourceAsStream("logo.png")));
                    stage1.setTitle("xMediatek");
                    stage1.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Stage stage1 = (Stage) ButtonConn.getScene().getWindow();
            stage1.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
