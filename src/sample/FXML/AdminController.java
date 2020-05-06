package sample.FXML;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import sample.ClientConnexion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.security.*;


public class AdminController implements Initializable {

    private String token;

    @FXML
    private TextField InputRecherche;

    @FXML
    private TextField NewPass;

    @FXML
    private TextField ConfirmPass;

    @FXML
    private Label birthday;

    @FXML
    private Label email;

    @FXML
    private Label Name;

    @FXML
    private Label phone;

    @FXML
    private Label inscription;

    @FXML
    private Label InfoLabel;

    @FXML
    private Label state;

    @FXML
    private TextField emailForm;

    @FXML
    private PasswordField passwordForm;

    @FXML
    private TextField nomForm;

    @FXML
    private TextField prenomForm;

    @FXML
    private TextField telephoneForm;

    @FXML
    private ComboBox statusForm;

    @FXML
    private TextField photoForm;

    @FXML
    private DatePicker dateForm;

    @FXML
    private Button ButtonRecherche;

    @FXML
    private ImageView ImageProfil;

    @FXML
    private CheckBox TypeCheckClient;

    @FXML
    private CheckBox TypeCheckOperateur;

    @FXML
    private CheckBox TypeCheckBanni;

    @FXML
    private CheckBox TypeCheckAdmin;

    @FXML
    private Button ButtonFiltre;

    @FXML
    private Button ChangeStatus;

    @FXML
    private Pane PaneResultat;

    @FXML
    private Pane AddUser;

    @FXML
    private ListView<User> ListViewResultat = new ListView<User>();

    private ArrayList<User> saveList = null;

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
        reset(-1);
        ObservableList<User> items = FXCollections.observableArrayList();
        for (int i = 0; i < saveList.size(); i++) {
            User temp = new Gson().fromJson(String.valueOf(saveList.get(i)), User.class);
            if (temp.getLastname().toUpperCase().contains(InputRecherche.getText().toUpperCase()) ||
                    temp.getFirstname().toUpperCase().contains(InputRecherche.getText().toUpperCase()))
                items.add(temp);
        }
        ListViewResultat.getItems().clear();
        ListViewResultat.setCellFactory(lv -> new UserListCell());
        ListViewResultat.setItems(items);
    }

    // Méthode pour afficher le pane de compte
    private void AfficherCompte(User u) {
        ListViewResultat.setVisible(false);
        email.setText(u.getLogin());
        phone.setText(u.getPhone());
        birthday.setText(u.getBirthday());
        inscription.setText(u.getRegistration());
        Name.setText(u.getFirstname() + " " + u.getLastname().toUpperCase());

        String status = "";
        if (1 == u.getState())
            status = "Lecteur";
        else if (2 == u.getState())
            status = "Opérateur";
        else if (3 == u.getState())
            status = "Administrateur";
        else
            status = "Banni";
        state.setText(status);
        if (u.getState() == 0) {
            ChangeStatus.setVisible(true);
            ChangeStatus.setText("Débannir");
        } else if (u.getState() == 1) {
            ChangeStatus.setVisible(true);
            ChangeStatus.setText("Bannir");
        } else {
            ChangeStatus.setVisible(false);
        }
        PaneResultat.setVisible(true);
    }

    @FXML
    private void AddUser() {
        ObservableList<String> items = FXCollections.observableArrayList("Lecteur", "Opérateur", "Administrateur");
        statusForm.setItems(items);
        ListViewResultat.setVisible(false);
        AddUser.setVisible(true);
    }

    @FXML
    private void AddUserForm() {
        URL url = null;
        try {
            url = new URL(photoForm.getText());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = null;
        try {
            is = url.openStream();
            byte[] byteChunk = new byte[4096];
            int n;

            while ((n = is.read(byteChunk)) > 0) {
                baos.write(byteChunk, 0, n);
            }
            String photo = new String(byteChunk, "UTF-8");

            List<String> commandes = new ArrayList<>();
            commandes.add("ADD_USER");
            commandes.add(emailForm.getText());
            int hash = 7;
            for (int i = 0; i < passwordForm.getText().length(); i++)
                hash = hash * 31 + passwordForm.getText().charAt(i);
            commandes.add(String.valueOf(hash));
            commandes.add(nomForm.getText());
            commandes.add(prenomForm.getText());
            commandes.add(telephoneForm.getText());
            String status = statusForm.getSelectionModel().getSelectedItem().toString();
            if (status.equals("Lecteur")) {
                commandes.add("1");
            } else if (status.equals("Opérateur"))
                commandes.add("2");
            else
                commandes.add("3");
            commandes.add(photo);
            commandes.add(dateForm.getValue().toString());
            ClientConnexion connexion = new ClientConnexion("127.0.0.1", 2345, commandes);
            List<Serializable> response = connexion.run();

        } catch (IOException e) {
            System.err.printf("Failed while reading bytes from %s: %s", url.toExternalForm(), e.getMessage());
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                    emailForm.clear();
                    passwordForm.clear();
                    nomForm.clear();
                    prenomForm.clear();
                    telephoneForm.clear();
                    photoForm.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void back() {
        ListViewResultat.setVisible(true);
        PaneResultat.setVisible(false);
        AddUser.setVisible(false);
        NewPass.clear();
        ConfirmPass.clear();
        InfoLabel.setVisible(false);
    }

    private void selectUserByState(int state) {
        ObservableList<User> items = FXCollections.observableArrayList();
        for (int i = 0; i < saveList.size(); i++) {
            User temp = new Gson().fromJson(String.valueOf(saveList.get(i)), User.class);
            if (temp.getState() == state)
                items.add(temp);
        }
        ListViewResultat.getItems().clear();
        ListViewResultat.setCellFactory(lv -> new UserListCell());
        ListViewResultat.setItems(items);
    }

    private void reset(int state) {
        if (state != 1)
            TypeCheckClient.setSelected(false);
        if (state != 2)
            TypeCheckOperateur.setSelected(false);
        if (state != 3)
            TypeCheckAdmin.setSelected(false);
        if (state != 0)
            TypeCheckBanni.setSelected(false);
        ObservableList<User> items = FXCollections.observableArrayList();
        for (int i = 0; i < saveList.size(); i++) {
            User temp = new Gson().fromJson(String.valueOf(saveList.get(i)), User.class);
            items.add(temp);
        }
        ListViewResultat.getItems().clear();
        ListViewResultat.setCellFactory(lv -> new UserListCell());
        ListViewResultat.setItems(items);
    }

    //Méthode Quand on clique sur le selectDVD
    @FXML
    private void SelectClient() {
        reset(1);
        if (TypeCheckClient.isSelected())
            selectUserByState(1);
    }

    //Méthode Quand on clique sur le selectCD
    @FXML
    private void SelectOperateur() {
        reset(2);
        if (TypeCheckOperateur.isSelected())
            selectUserByState(2);
    }

    //Méthode Quand on clique sur le selectLivre
    @FXML
    private void SelectAdministrateur() {
        reset(3);
        if (TypeCheckAdmin.isSelected())
            selectUserByState(3);
    }

    //Méthode Quand on clique sur le selectLivre
    @FXML
    private void SelectBan() {
        reset(0);
        if (TypeCheckBanni.isSelected())
            selectUserByState(0);
    }

    // Méthode pour filtrer la recherche
    @FXML
    private void changePassword() {
        InfoLabel.setVisible(false);
        if (NewPass.getText().equals(ConfirmPass.getText())) {
            if (verifPassword(NewPass.getText())) {
                List<String> commandes = new ArrayList<>();
                int hash = 7;
                for (int i = 0; i < NewPass.getText().length(); i++)
                    hash = hash * 31 + NewPass.getText().charAt(i);
                commandes.add("CHANGE_PASSWORD");
                commandes.add(String.valueOf(ListViewResultat.getSelectionModel().getSelectedItem().getId()));
                commandes.add(String.valueOf(hash));
                ClientConnexion connexion = new ClientConnexion("127.0.0.1", 2345, commandes);
                List<Serializable> response = connexion.run();
                if (response.get(0).equals("OK")) {
                    NewPass.clear();
                    ConfirmPass.clear();
                } else {
                    InfoLabel.setText("Erreur lors du changement !");
                    InfoLabel.setVisible(true);
                }
            } else {
                InfoLabel.setText("Le mot de passe doit contenir au moins une majuscule, une mminuscule, un chiffre et faire au moins 8 caractères !");
                InfoLabel.setVisible(true);
            }
        } else {
            InfoLabel.setText("Les mots de passe ne correspondent pas !");
            InfoLabel.setVisible(true);
        }
    }

    @FXML
    private void changeStatus() {
        List<String> commandes = new ArrayList<>();
        commandes.add("CHANGE_STATUS");
        commandes.add(String.valueOf(ListViewResultat.getSelectionModel().getSelectedItem().getId()));
        if (ListViewResultat.getSelectionModel().getSelectedItem().getState() == 0)
            commandes.add("1");
        else
            commandes.add("0");
        ClientConnexion connexion = new ClientConnexion("127.0.0.1", 2345, commandes);
        List<Serializable> response = connexion.run();
        if (response.get(0).equals("OK")) {
            if (commandes.get(2).equals("1")) {
                state.setText("Lecteur");
                ChangeStatus.setText("Bannir");
            } else {
                state.setText("Banni");
                ChangeStatus.setText("Débannir");
            }
            loadList();
        }
    }

    private boolean verifPassword(String s) {
        char ch;
        boolean capitalFlag = false;
        boolean numberFlag = false;
        if (s.length() >= 8) {
            for (int i = 0; i < s.length(); i++) {
                ch = s.charAt(i);
                if (Character.isDigit(ch)) {
                    numberFlag = true;
                } else if (Character.isUpperCase(ch)) {
                    capitalFlag = true;
                }
                if (numberFlag && capitalFlag)
                    return true;
            }
            return false;
        } else {
            return false;
        }
    }

    public void loadList() {
        InputRecherche.clear();
        TypeCheckClient.setSelected(false);
        TypeCheckOperateur.setSelected(false);
        TypeCheckAdmin.setSelected(false);
        TypeCheckBanni.setSelected(false);
        List<String> commandes = new ArrayList<>();
        commandes.add("USERS");
        ClientConnexion connexion = new ClientConnexion("127.0.0.1", 2345, commandes);
        List<Serializable> response = connexion.run();
        ArrayList<User> back = new Gson().fromJson((String) response.get(0), ArrayList.class);
        saveList = back;
        ObservableList<User> items = FXCollections.observableArrayList();

        for (int i = 0; i < back.size(); i++) {
            User temp = new Gson().fromJson(String.valueOf(back.get(i)), User.class);
            items.add(temp);
        }
        ListViewResultat.setCellFactory(lv -> new UserListCell());
        ListViewResultat.setItems(items);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadList();
        ListViewResultat.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AfficherCompte(ListViewResultat.getSelectionModel().getSelectedItem());
            }
        });
    }
}
