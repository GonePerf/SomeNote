import SQL.Notebook;
import SQL.User;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Login {


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
        User user = null;
        UserDAO userDAO = new UserDAO();
        DAO.OpenConnection("cfg/HibernateMySQL.cfg.xml");
        List<User> lista = userDAO.findAll();
        for(User u : lista){
            if(u.getEmail().equals(email.getText()) && u.getPassword().equals(password.getText())){
                user = u;
            }
        }
        if(user == null) {
            DialogUtils.errorDialog();
        }
        else {



            System.out.println("Znaleziono uzytkownika");
            SomeNoteController.setUser(user);
            SceneManager.renderScene("someNote");
        }

    }
    @FXML
    void changePassword() throws Exception {
        System.out.println("Zmiana hasła");
        User user = new User();
        HibernateUtil.OpenConnection("cfg/HibernateMySQL.cfg.xml");
        UserDAO userDAO = new UserDAO();
        List<User> lista = userDAO.findAll();
        for(int i = 0; i < lista.size(); i++){
            user = lista.get(i);
            if(user.getEmail().equals(email.getText()) && user.getPin().equals(pin.getText())){
                if(password.getText().equals(confirmPassword.getText()) && password.getText().length() > 5){
                    user.setPassword(password.getText());
                    userDAO.update(user);
                    DialogUtils.changingPasswordDialog();
                }
            }
        }
    }
    @FXML
    void createUser() throws Exception {
        Info info;
        User user = new User();
        if(email.getText().length() == 0 && password.getText().length() == 0
        && confirmPassword.getText().length() == 0 && pin.getText().length() == 0
        && name.getText().length() == 0){
            DialogUtils.errorDialog("Wypełinj pola!");
        }
        else if(email.getText().length() < 6) {
            DialogUtils.errorDialog("Niepoprawny email!");
        }
        else if(password.getText().length() < 6){
            DialogUtils.errorDialog("Hasło musi zawierać minimum 6 znaków!");
        }
        else if(!(password.getText().equals(confirmPassword.getText()))){
            DialogUtils.errorDialog("Hasła nie są identyczne!");
        }
        else if(pin.getText().length() != 4){
            DialogUtils.errorDialog("Pin ma mieć 4 znaki!");
        }
        else if(name.getText().length() < 3){
            DialogUtils.errorDialog("Podaj poprawne imie!");
        }
        else{
            HibernateUtil.OpenConnection("cfg/HibernateMySQL.cfg.xml");
            UserDAO userDAO = new UserDAO();
            user.setPin(pin.getText());
            user.setEmail(email.getText());
            user.setPassword(password.getText());
            user.setFirstName(name.getText());
            user.setNotebooks(new ArrayList<Notebook>());
            userDAO.save(user);

        }
        SceneManager.renderScene("login");
        DialogUtils.creatingAccountDialog();
    }
}
