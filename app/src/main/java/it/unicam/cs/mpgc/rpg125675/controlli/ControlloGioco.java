package it.unicam.cs.mpgc.rpg125675.controlli;


import it.unicam.cs.mpgc.rpg125675.modelli.logica.StatoGioco;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControlloGioco {

    @FXML
    private TextArea areaOutput;
    @FXML
    private Label etichettaStatistiche;
    @FXML
    private TextField input;

    private StatoGioco statoGioco;
    private Fasi faseCorrente;
    private GestoreComandi gestoreComandi;
    private GestoreUI gestoreUI;

    public ControlloGioco() {}

    @FXML
    public void initialize() {
        this.statoGioco    = new StatoGioco("Eroe");
        this.gestoreUI     = new GestoreUI(statoGioco);
        this.gestoreComandi = new GestoreComandi(gestoreUI, statoGioco, Fasi.ESPLORAZIONE);
        this.faseCorrente  = Fasi.ESPLORAZIONE;

        // mostra lo stato iniziale
        areaOutput.appendText(gestoreUI.menuEsplorazione() + "\n");
        etichettaStatistiche.setText(statoGioco.getStatisticheGiocatore());
    }

    @FXML
    public void gestisciInput() {
        if (statoGioco.isFinePartita()) return; // ← blocca input a fine partita

        String testo = input.getText().trim();
        if (testo.isEmpty()) return;

        String risposta = gestoreComandi.elabora(testo, faseCorrente);
        faseCorrente = gestoreComandi.getFaseCorrente();

        areaOutput.appendText(risposta + "\n");
        etichettaStatistiche.setText(statoGioco.getStatisticheGiocatore());
        input.clear();
    }
}