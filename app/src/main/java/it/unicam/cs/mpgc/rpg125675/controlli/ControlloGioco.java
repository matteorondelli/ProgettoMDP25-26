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

/**
 * Controller JavaFX associato alla vista principale ({@code Mappa.fxml}).
 *
 * Gestisce il ciclo di vita iniziale dell'applicazione (scelta tra nuova
 * partita e caricamento di un salvataggio esistente, inserimento del nome
 * del personaggio) e, una volta avviata la partita, delega l'elaborazione
 * dei comandi di gioco a {@link GestoreComandi} e la formattazione dei
 * messaggi a {@link FormattaTesto}.
 */
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

    /**
     * Rappresenta le fasi della schermata iniziale, precedenti all'avvio
     * effettivo della partita.
     */
    private enum FaseIniziale {
        /** L'utente deve scegliere se caricare un salvataggio o iniziarne uno nuovo. */
        SCELTA_INIZIALE,
        INSERIMENTO_NOME,
        /** La partita è stata avviata e i comandi vengono gestiti da {@link GestoreComandi}. */
        IN_GIOCO
    }

    /**
     * Costruttore di default richiesto da JavaFX.
     */
    public ControlloGioco() {}

    /**
     * Metodo di inizializzazione chiamato automaticamente da JavaFX dopo il
     * caricamento del file FXML.
     *
     * Verifica se esiste un salvataggio precedente: in caso
     * mostra il menu di scelta iniziale, altrimenti richiede
     * il nome del personaggio per iniziare una nuova partita.
     */
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

    /**
     * Costruisce il testo mostrato quando esiste un salvataggio.
     *
     * @return testo del menu di scelta iniziale
     */
    private String menuScelteIniziali() {
        return "E' stato trovato un salvataggio.\n"
                + "1. Carica partita salvata\n"
                + "2. Nuova partita (elimina il salvataggio esistente)\n";
    }

    /**
     * Costruisce il testo del messaggio di richiesta del nome del personaggio.
     *
     * @return testo della richiesta del nome
     */
    private String richiestaNome() {
        return "Inserisci il nome del tuo eroe:\n";
    }

    /**
     * Gestisce l'input testuale inserito dall'utente nel campo di testo,
     * invocato al momento della pressione di Invio.
     *
     * In base alla {@link FaseIniziale} corrente, l'input viene instradato
     * verso il gestore corretto. L'input vuoto viene ignorato.
     */
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

    /**
     * Gestisce la scelta dell'utente nella schermata iniziale, tra il
     * caricamento di un salvataggio esistente e l'inizio di una nuova partita.
     *
     * @param testo comando dell'utente
     */
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

    /**
     * Carica una partita salvata precedentemente, ricostruendo lo stato di
     * gioco tramite {@link ConvertitoreSalvataggio} e avviando la partita.
     *
     * Se il salvataggio risulta corrotto o illeggibile,crea una nuova partita.
     */
    private void caricaPartitaSalvata() {
        DTOSalvataggio dto = gestoreSalvataggio.carica();
        if (dto == null) {
            areaOutput.appendText("\nSalvataggio corrotto o illeggibile. Nuova partita.\n");
            faseIniziale = FaseIniziale.INSERIMENTO_NOME;
            areaOutput.appendText(richiestaNome());
            return;
        }
        ILocanda locanda = new Locanda();
        MotoreCombattimento motore = new MotoreCombattimento();
        ICaricatoreMostri caricatore = new CaricaDaJson();
        IGeneratoreMostri generatore = new GeneratoreMostri(caricatore);
        this.statoGioco = ConvertitoreSalvataggio.aStato(dto, locanda, generatore, motore, caricatore);
        areaOutput.appendText("\nSalvataggio trovato: partita ripristinata.\n\n");
        avviaPartita();
    }

    /**
     * Gestisce l'inserimento del nome del personaggio per una nuova partita,
     * creando un nuovo {@link StatoGioco} con tutte le dipendenze
     * necessarie e avvia la partita.
     *
     * @param testo nome personaggio
     */
    private void gestisciInserimentoNome(String testo) {
        ILocanda locanda = new Locanda();
        IGeneratoreMostri generatore = new GeneratoreMostri();
        MotoreCombattimento motore = new MotoreCombattimento();
        ICaricatoreMostri caricatore = new CaricaDaJson();
        this.statoGioco = new StatoGioco(testo, locanda, generatore, motore, caricatore);
        areaOutput.appendText("\nBenvenuto, " + testo + "!\n\n");
        avviaPartita();
    }

    /**
     * Avvia la partita una volta che lo stato di gioco è stato creato o
     * ripristinato: inizializza {@link FormattaTesto} e {@link GestoreComandi},
     * imposta la fase corrente su {@link Fasi#ESPLORAZIONE} e mostra il
     * primo menu di gioco insieme alle statistiche del giocatore.
     */
    private void avviaPartita() {
        this.formattaTesto = new FormattaTesto(statoGioco);
        this.gestoreComandi = new GestoreComandi(formattaTesto, statoGioco, Fasi.ESPLORAZIONE, gestoreSalvataggio);
        this.faseCorrente = Fasi.ESPLORAZIONE;
        this.faseIniziale = FaseIniziale.IN_GIOCO;

        areaOutput.appendText(formattaTesto.menuEsplorazione() + "\n");
        etichettaStatistiche.setText(statoGioco.getStatisticheGiocatore());
    }

    /**
     * Gestisce l'input dell'utente durante la partita, delegandone
     * l'elaborazione a {@link GestoreComandi} e aggiornando l'output e le
     * statistiche mostrate.
     *
     * Se la partita è già terminata, l'input viene ignorato.
     *
     * @param testo input testuale inserito dall'utente durante la partita
     */
    private void gestisciInputDiGioco(String testo) {
        if (statoGioco.isFinePartita()) return;

        String risposta = gestoreComandi.elabora(testo, faseCorrente);
        faseCorrente = gestoreComandi.getFaseCorrente();

        areaOutput.appendText(risposta + "\n");
        etichettaStatistiche.setText(statoGioco.getStatisticheGiocatore());
    }
}
