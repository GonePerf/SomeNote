import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Hashtable;

public class SceneManager {
    private static Stage stage;
    private static Hashtable<String,String> view = new Hashtable<String, String>();

    public static void addScene(String name, String path) throws IOException {
        view.put(name, path);
    }

    public static void removeScene(String name){
        view.remove(name);
    }

    public static void renderScene(String name){
        String path = "";
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        try{
            path = view.get(name);
            Parent root = FXMLLoader.load(SceneManager.class.getResource(path));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            if(name.equals("someNote")) {
                stage.setX(primaryScreenBounds.getMinX());
                stage.setY(primaryScreenBounds.getMinY());
                stage.setMinHeight(primaryScreenBounds.getHeight()/1.2);
                stage.setMinWidth(primaryScreenBounds.getWidth()/2);
            }
            else if(name.equals("login")){
                stage.setX((primaryScreenBounds.getWidth()/2)-404);
                stage.setY((primaryScreenBounds.getHeight()/2)-250);
                stage.setMaxWidth(809);
                stage.setMaxHeight(500);
                stage.setMinHeight(500);
                stage.setMinWidth(809);
            }
            stage.show();
        } catch (IOException e) {
            System.err.println("Nie można załadować pliku XML z widokiem: "+path);
        } catch (RuntimeException ex){
            System.err.println("Nazwa widoku nie jest prawidłowa");
        }
    }

    public static void setStage(Stage _stage){
        stage = _stage;
    }
}
