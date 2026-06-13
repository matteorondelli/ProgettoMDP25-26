package it.unicam.cs.mpgc.rpg125675.viste;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Punto di ingresso dell'interfaccia grafica JavaFX dell'applicazione.
 *
 * Carica la vista principale da {@code Mappa.fxml}, associata al controller
 * {@code ControlloGioco}, e la mostra nella finestra principale
 * dell'applicazione.
 */
public class MappaFX extends Application {

    private static final String PERCORSO_FXML = "/it.unicam.cs.mpgc.rpg125675/viste/Mappa.fxml";

    /**
     * Metodo richiamato da JavaFX all'avvio dell'applicazione: carica il
     * file FXML della vista principale, costruisce la scena e la mostra
     * nello stage fornito.
     *
     * @param stage finestra principale dell'applicazione, fornita da JavaFX
     * @throws Exception se il caricamento del file FXML fallisce
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PERCORSO_FXML));
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("IL PORTALE DIMENTICATO");
        stage.setMinWidth(650);
        stage.setMinHeight(450);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    /**
     * Avvia l'applicazione JavaFX.
     *
     * Punto di ingresso invocato da {@code Main}.
     */
     public static void avviaFx() {
        launch();
     }


}
