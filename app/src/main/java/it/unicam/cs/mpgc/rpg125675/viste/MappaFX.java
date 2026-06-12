package it.unicam.cs.mpgc.rpg125675.viste;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MappaFX extends Application {

    private static final String PERCORSO_FXML = "/it.unicam.cs.mpgc.rpg125675/viste/Mappa.fxml";

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PERCORSO_FXML));
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("RPG - MDP 25/26");
        stage.setMinWidth(650);
        stage.setMinHeight(450);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void avviaFx() {
        launch();
    }


}
