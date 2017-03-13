/**
 * Created by ChristofferTronje on 2016-11-21.
 */

import controller.PlayGame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.EventHandler;


public class ProgramFX extends Application {

   // Pane pane;
    AnchorPane anch;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        view.GUIView view = new view.GUIView();

        anch = new AnchorPane();
      //  pane = new Pane();
        view.play(anch);

        primaryStage.setScene(new Scene(anch, 450, 450));
        primaryStage.show();
    }

}
