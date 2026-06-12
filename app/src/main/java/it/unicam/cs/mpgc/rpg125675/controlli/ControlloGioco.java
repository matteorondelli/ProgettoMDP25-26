package it.unicam.cs.mpgc.rpg125675.controlli;


import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.Fasi;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IGestoreSalvataggio;
import it.unicam.cs.mpgc.rpg125675.modelli.logica.StatoGioco;
import it.unicam.cs.mpgc.rpg125675.modelli.util.ConvertitoreSalvataggio;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOSalvataggio;
import it.unicam.cs.mpgc.rpg125675.modelli.util.GestoreSalvataggio;
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
    private final IGestoreSalvataggio gestoreSalvataggio = new GestoreSalvataggio();

    private FaseIniziale faseIniziale;

    private enum FaseIniziale {
        SCELTA_INIZIALE,
        INSERIMENTO_NOME,
        IN_GIOCO
    }

    public ControlloGioco() {}

    @FXML
    public void initialize() {
        etichettaStatistiche.setText("");
        if (gestoreSalvataggio.esisteSalvataggio()) {
            faseIniziale = FaseIniziale.SCELTA_INIZIALE;
            areaOutput.appendText(menuScelteIniziali());
        } else {
            faseIniziale = FaseIniziale.INSERIMENTO_NOME;
            areaOutput.appendText(richiestaNome());
        }
    }

    private String menuScelteIniziali() {
        return "E' stato trovato un salvataggio.\n"
                + "1. Carica partita salvata\n"
                + "2. Nuova partita (elimina il salvataggio esistente)\n";
    }

    private String richiestaNome() {
        return "Inserisci il nome del tuo eroe:\n";
    }

    @FXML
    public void gestisciInput() {
        String testo = input.getText().trim();
        if (testo.isEmpty()) return;

        switch (faseIniziale) {
            case SCELTA_INIZIALE -> gestisciSceltaIniziale(testo);
            case INSERIMENTO_NOME -> gestisciInserimentoNome(testo);
            case IN_GIOCO -> gestisciInputDiGioco(testo);
        }

        input.clear();
    }

    private void gestisciSceltaIniziale(String testo) {
        switch (testo) {
            case "1" -> caricaPartitaSalvata();
            case "2" -> {
                gestoreSalvataggio.elimina();
                faseIniziale = FaseIniziale.INSERIMENTO_NOME;
                areaOutput.appendText("\nSalvataggio eliminato.\n" + richiestaNome());
            }
            default -> areaOutput.appendText("Scelta non valida.\n" + menuScelteIniziali());
        }
    }

    private void caricaPartitaSalvata() {
        DTOSalvataggio dto = gestoreSalvataggio.carica();
        this.statoGioco = ConvertitoreSalvataggio.aStato(dto);
        areaOutput.appendText("\nSalvataggio trovato: partita ripristinata.\n\n");
        avviaPartita();
    }

    private void gestisciInserimentoNome(String testo) {
        this.statoGioco = new StatoGioco(testo);
        areaOutput.appendText("\nBenvenuto, " + testo + "!\n\n");
        avviaPartita();
    }

    private void avviaPartita() {
        this.gestoreUI = new GestoreUI(statoGioco);
        this.gestoreComandi = new GestoreComandi(gestoreUI, statoGioco, Fasi.ESPLORAZIONE, gestoreSalvataggio);
        this.faseCorrente = Fasi.ESPLORAZIONE;
        this.faseIniziale = FaseIniziale.IN_GIOCO;

        areaOutput.appendText(gestoreUI.menuEsplorazione() + "\n");
        etichettaStatistiche.setText(statoGioco.getStatisticheGiocatore());
    }

    private void gestisciInputDiGioco(String testo) {
        if (statoGioco.isFinePartita()) return;

        String risposta = gestoreComandi.elabora(testo, faseCorrente);
        faseCorrente = gestoreComandi.getFaseCorrente();

        areaOutput.appendText(risposta + "\n");
        etichettaStatistiche.setText(statoGioco.getStatisticheGiocatore());
    }
}
