import SQL.Notebook;
import SQL.User;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Login {

    static List<User> lista;
    static File file = new File("remember.txt");

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
    @FXML
    private CheckBox remember;

    public Login(){



    }
    @FXML
    void enter(){

    }
    @FXML
    void initialize() throws FileNotFoundException {

        DAO.OpenConnection("cfg/HibernateMySQL.cfg.xml");
        lista = new UserDAO().findAll();
        if(file.exists()) {
            String pass = null;
            Scanner scanner = new Scanner(file);

            if(scanner.hasNext()) {
                pass = scanner.nextLine();
                String[] tab = pass.split(" ");
                email.setText(tab[0]);
                password.setText(tab[1]);
            }
        }
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

        for(User u : lista){
            if(u.getEmail().equals(email.getText()) && u.getPassword().equals(password.getText())){
                user = u;
            }
        }
        if(user == null) {
            DialogUtils.errorDialog("Niepoprawne dane!");
        }
        else {


            if(remember.isSelected()) {

                if(!file.exists()) {
                    file.createNewFile();
                    PrintWriter zapis = new PrintWriter("remember.txt");
                    zapis.println(user.getEmail()+" "+user.getPassword());
                    zapis.close();
                }
                // pamiętaj mnie
            }
            System.out.println("Znaleziono uzytkownika");
            SomeNoteController.setUser(user);
            SceneManager.renderScene("someNote");
        }

    }
    @FXML
    void changePassword() throws Exception {
        System.out.println("Zmiana hasła");
        User user = new User();
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
