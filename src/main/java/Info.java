import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static java.awt.Color.BLACK;


public class Info extends Application {
    String info = "Błędny login";

    public Info(String info) {
        this.info = info;
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {


        VBox vBox = new VBox(20);
        vBox.setStyle("-fx-background-color: #010D25;");
        vBox.setMinSize(350,100);
        vBox.setAlignment(Pos.BOTTOM_CENTER);
        Label label = new Label();
        label.setStyle("-fx-text-fill: white;");
        label.setText(info);
        Button button = new Button("Ok");
        button.setStyle("-fx-background-color: #091F41; -fx-text-fill:white;");
        button.setMinSize(168,34);
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        vBox.getChildren().addAll(label,button);
        Scene scene = new Scene(vBox);
        //scene.getStylesheets().addAll("modenadark.css");

        primaryStage.setScene(scene);
        primaryStage.setMinWidth(350);
        primaryStage.setMinHeight(100);
        primaryStage.show();
    }

}
