import SQL.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;

import static javax.persistence.Persistence.createEntityManagerFactory;


public class Login {

    @FXML
    private Pane scene;
    @FXML
    private TextField email;
    @FXML
    private TextField pin;
    @FXML
    private TextField name;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;

    public Login(){



    }
    @FXML
    void initialize(){

    }
    @FXML
    void showRegisterPanel() throws IOException {
        SceneManager.renderScene("register");
    }
    @FXML
    void backToLogin() throws IOException {
        SceneManager.renderScene("login");
    }
    @FXML
    void showForgotPasswordPanel() throws IOException {
        SceneManager.renderScene("forgotPassword");
    }
    @FXML
    void tryLogin() throws Exception {

                System.out.println("Znaleziono uzytkownika");
                SceneManager.renderScene("someNote");

    }
    @FXML
    void changePassword(){
        System.out.println("Zmiana hasła");
    }
    @FXML
    void createUser() throws Exception {
        Info info;
        if(email.getText().length() == 0 && password.getText().length() == 0
        && confirmPassword.getText().length() == 0 && pin.getText().length() == 0
        && name.getText().length() == 0){
            info = new Info("Wypełnij pola");
            info.start(new Stage());
        }
        else if(email.getText().length() < 6) {
            info = new Info("Błędny email");
            info.start(new Stage());
        }
        else if(password.getText().length() < 6){
            info = new Info("Hasło powinno zawierać conajmniej 5 znaków");
            info.start(new Stage());
        }
        else if(!(password.getText().equals(confirmPassword.getText()))){
            info = new Info("Hasła muszą być identyczne!");
            info.start(new Stage());
        }
        else if(pin.getText().length() != 4){
            info = new Info("Pin powinien składać się z 4 znaków");
            info.start(new Stage());
        }
        else if(name.getText().length() < 3){
            info = new Info("Podaj poprawne imie");
            info.start(new Stage());
        }
        else{
            //Operations.createUser(email.getText(),password.getText(),name.getText(),pin.getText());
        }
        System.out.println("Tworzenie konta");
    }
}
