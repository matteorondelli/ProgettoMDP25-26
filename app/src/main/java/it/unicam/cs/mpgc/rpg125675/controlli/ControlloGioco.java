package it.unicam.cs.mpgc.rpg125675.controlli;


import it.unicam.cs.mpgc.rpg125675.modelli.classi.luoghi.Locanda;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.GeneratoreMostri;
import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.Fasi;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.*;
import it.unicam.cs.mpgc.rpg125675.modelli.logica.MotoreCombattimento;
import it.unicam.cs.mpgc.rpg125675.modelli.logica.StatoGioco;
import it.unicam.cs.mpgc.rpg125675.modelli.util.CaricaDaJson;
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

    private IStatoGioco statoGioco;
    private Fasi faseCorrente;
    private GestoreComandi gestoreComandi;
    private FormattaTesto formattaTesto;
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
        if (dto == null) {
            areaOutput.appendText("\nSalvataggio corrotto o illeggibile. Nuova partita.\n");
            faseIniziale = FaseIniziale.INSERIMENTO_NOME;
            areaOutput.appendText(richiestaNome());
            return;
        }

        // Inizializziamo i componenti concreti da iniettare
        ILocanda locanda = new Locanda();
        MotoreCombattimento motore = new MotoreCombattimento();
        ICaricatoreMostri caricatore = new CaricaDaJson();
        IGeneratoreMostri generatore = new GeneratoreMostri(caricatore);

        // MODIFICATO: Passiamo le dipendenze al convertitore
        this.statoGioco = ConvertitoreSalvataggio.aStato(dto, locanda, generatore, motore, caricatore);

        areaOutput.appendText("\nSalvataggio trovato: partita ripristinata.\n\n");
        avviaPartita();
    }

    // Nel metodo gestisciInserimentoNome(String testo) in ControlloGioco.java:
    private void gestisciInserimentoNome(String testo) {
        ILocanda locanda = new Locanda();
        IGeneratoreMostri generatore = new GeneratoreMostri();
        MotoreCombattimento motore = new MotoreCombattimento();
        ICaricatoreMostri caricatore = new CaricaDaJson();

        // Creazione dello stato tramite Iniezione pulita di tutte le sue componenti concrete
        this.statoGioco = new StatoGioco(testo, locanda, generatore, motore, caricatore);

        areaOutput.appendText("\nBenvenuto, " + testo + "!\n\n");
        avviaPartita();
    }

    private void avviaPartita() {
        this.formattaTesto = new FormattaTesto(statoGioco);
        this.gestoreComandi = new GestoreComandi(formattaTesto, statoGioco, Fasi.ESPLORAZIONE, gestoreSalvataggio);
        this.faseCorrente = Fasi.ESPLORAZIONE;
        this.faseIniziale = FaseIniziale.IN_GIOCO;

        areaOutput.appendText(formattaTesto.menuEsplorazione() + "\n");
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
