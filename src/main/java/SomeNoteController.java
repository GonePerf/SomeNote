import SQL.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SomeNoteController {



    private static User user;
    @FXML
    Label imie;
    @FXML
    Label email;


    public SomeNoteController(){

    }
    @FXML
    void initialize(){

    }
    @FXML
    public void addNotebook(){}

    @FXML
    public void addNote(){}

    @FXML
    public void showAccountPanel(){}

    @FXML
    public void logout(){
        SceneManager.renderScene("login");
    }

    public static void setUser(User user) {
        SomeNoteController.user = user;
    }
}
