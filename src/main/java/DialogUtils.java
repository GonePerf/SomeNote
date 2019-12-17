import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;


public class DialogUtils {

    public static void errorDialog(String info){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Błąd!");
        alert.setHeaderText(info);
        alert.showAndWait();


    }
    public static void creatingAccountDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sukces");
        alert.setHeaderText("Konto zostało utworzone");
        alert.showAndWait();
    }
    public static void changingPasswordDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sukces");
        alert.setHeaderText("Hasło zostało zmienione");
        alert.showAndWait();
    }
    public static void badNameDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Niepoprawna nazwa...");
        alert.setHeaderText("3 - 12 znaków!");
        alert.showAndWait();
    }

    public static String getNameDialog(String alias){
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setHeaderText("Tworzenie "+alias);
        textInputDialog.setContentText("Podaj nazwę "+alias);


        Optional<String> result = textInputDialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your name: " + result.get());
        }
        return result.get();
    }
}
