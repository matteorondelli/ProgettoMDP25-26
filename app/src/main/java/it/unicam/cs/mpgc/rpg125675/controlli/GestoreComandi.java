package it.unicam.cs.mpgc.rpg125675.controlli;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Mostro;
import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.Fasi;
import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.Luoghi;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOCombattimento;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTORicompense;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IGestoreSalvataggio;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IStatoGioco;
import it.unicam.cs.mpgc.rpg125675.modelli.util.ConvertitoreSalvataggio;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOSalvataggio;
import it.unicam.cs.mpgc.rpg125675.viste.FormattaTesto;

/**
 * Responsabile dell'interpretazione dei comandi testuali
 * dell'utente durante la partita e dell'orchestrazione delle transizioni
 * tra le {@link Fasi} di gioco.
 *
 * In base alla fase corrente e al luogo in cui si trova il giocatore,
 * interpreta l'input ricevuto e produce il testo di risposta da mostrare,
 * delegando la formattazione dei messaggi a {@link FormattaTesto}.
 */
public class GestoreComandi {

    private final FormattaTesto formattaTesto;
    private final IStatoGioco statoGioco;
    private Fasi faseCorrente;
    private final IGestoreSalvataggio gestoreSalvataggio;

    /**
     * Crea un nuovo gestore di comandi.
     *
     * @param formattaTesto componente per la formattazione dei messaggi e dei menu
     * @param statoGioco stato di gioco su cui operare
     * @param faseIniziale fase di gioco da cui iniziare
     * @param gestoreSalvataggio componente per la persistenza
     */
    public GestoreComandi(FormattaTesto formattaTesto, IStatoGioco statoGioco,
                          Fasi faseIniziale, IGestoreSalvataggio gestoreSalvataggio) {
        this.formattaTesto = formattaTesto;
        this.statoGioco = statoGioco;
        this.faseCorrente = faseIniziale;
        this.gestoreSalvataggio = gestoreSalvataggio;
    }

    /**
     * Elabora l'input dell'utente in base alla fase di gioco specificata,
     * instradandolo verso il gestore appropriato.
     *
     * @param input input testuale inserito dall'utente
     * @param fase fase di gioco
     * @return il testo di risposta da mostrare all'utente
     */
    public String elabora(String input, Fasi fase) {
        return switch (fase) {
            case ESPLORAZIONE -> esplorazione(input);
            case COMBATTIMENTO -> elaboraCombattimento(input);
            case COMBATTIMENTO_BOSS -> elaboraCombattimentoBoss(input);
            case FINE_PARTITA -> elaboraFinale();
        };
    }

    /**
     * Restituisce la fase di gioco
     *
     * @return fase di gioco corrente
     */
    public Fasi getFaseCorrente() {
        return faseCorrente;
    }

    /**
     * Elabora l'input dell'utente durante la fase di esplorazione,
     * in base al luogo in cui si trova attualmente il giocatore.
     *
     * @param input input testuale inserito dall'utente
     * @return il testo di risposta da mostrare all'utente
     */
    private String esplorazione(String input) {
        return switch (statoGioco.getLuogoAttuale()) {
            case VILLAGGIO -> elaboraVillaggio(input);
            case FORESTA   -> elaboraForesta(input);
            case PORTALE   -> elaboraPortale(input);
        };
    }

    /**
     * Scelte quando l'utente si trova nel villaggio.
     *
     * @param input input testuale inserito dall'utente
     * @return il testo di risposta da mostrare all'utente
     */
    private String elaboraVillaggio(String input) {
        return switch (input.trim()) {
            case "1" -> vainellaForesta();
            case "2" -> riposatiAllaLocanda();
            case "3" -> salvaPartita();
            default  -> "Scelta non valida.\n" + formattaTesto.menuEsplorazione();
        };
    }

    /**
     * Salva la partita corrente convertendo lo stato di gioco in un
     * {@link DTOSalvataggio} e persistendolo tramite il gestore di
     * salvataggio.
     *
     * @return il testo di confermo del salvataggio, seguito dal menu di esplorazione
     */
    private String salvaPartita() {
        DTOSalvataggio dto = ConvertitoreSalvataggio.daStato(statoGioco);
        gestoreSalvataggio.salva(dto);
        return formattaTesto.messaggioSalvataggio() + "\n\n" + formattaTesto.menuEsplorazione();
    }

    /**
     * Sposta il giocatore nella foresta.
     *
     * @return il testo del menu di esplorazione per il nuovo luogo
     */
    private String vainellaForesta() {
        statoGioco.spostati(Luoghi.FORESTA);
        return formattaTesto.menuEsplorazione();
    }

    /**
     * Riposa il giocatore alla locanda
     *
     * @return il testo dell'esito del riposo, seguito dal menu di esplorazione
     */
    private String riposatiAllaLocanda() {
        boolean riuscito = statoGioco.riposati();
        return formattaTesto.messaggioLocanda(riuscito, statoGioco.getCostoRiposo())
                + "\n\n" + formattaTesto.menuEsplorazione();
    }

    /**
     * Scelte quando l'utente si trova nella foresta.
     *
     * @param input input testuale inserito dall'utente
     * @return il testo di risposta da mostrare all'utente
     */
    private String elaboraForesta(String input) {
        return switch (input.trim()) {
            case "1" -> caccia();
            case "2" -> provaAccessoPortale();
            case "3" -> tornaAlVillaggio();
            default  -> "Scelta non valida.\n" + formattaTesto.menuEsplorazione();
        };
    }

    /**
     * Genera un nuovo mostro, lo imposta come avversario corrente e avvia
     * il combattimento, aggiornando la fase corrente a
     * {@link Fasi#COMBATTIMENTO}.
     *
     * @return il messaggio di incontro con il mostro, seguito dal menu di combattimento
     */
    private String caccia() {
        Mostro mostro = statoGioco.generaMostro();
        statoGioco.impostaMostro(mostro);
        faseCorrente = Fasi.COMBATTIMENTO;
        return formattaTesto.messaggioMostroIncontrato(mostro)
                + "\n\n" + formattaTesto.menuCombattimento();
    }

    /**
     * Sposta il giocatore nel portale solo se ha tutti i frammenti
     *
     * @return il testo di risposta da mostrare all'utente
     */
    private String provaAccessoPortale() {
        boolean spostato = statoGioco.spostati(Luoghi.PORTALE);
        if (spostato) return formattaTesto.menuEsplorazione();
        return formattaTesto.messaggioPortaleChiuso() + "\n\n"
                + formattaTesto.menuEsplorazione();
    }

    /**
     * Sposta il giocatore al villaggio.
     *
     * @return il testo del menu di esplorazione per il nuovo luogo
     */
    private String tornaAlVillaggio() {
        statoGioco.spostati(Luoghi.VILLAGGIO);
        return formattaTesto.menuEsplorazione();
    }

    /**
     * Scelte quando l'utente si trova nel portale
     *
     * @param input input testuale inserito dall'utente
     * @return il testo di risposta da mostrare all'utente
     */
    private String elaboraPortale(String input) {
        return switch (input.trim()) {
            case "1" -> combattimentoBoss();
            case "2" -> tornaAlVillaggio();
            default  -> "Scelta non valida.\n\n" + formattaTesto.menuEsplorazione();
        };
    }

    /**
     * Avvia il combattimento contro il boss, aggiornando la fase corrente a
     * {@link Fasi#COMBATTIMENTO_BOSS}.
     *
     * @return il messaggio di incontro con il boss, seguito dal menu di combattimento
     */
    private String combattimentoBoss() {
        faseCorrente = Fasi.COMBATTIMENTO_BOSS;
        return formattaTesto.messaggioMostroIncontrato(statoGioco.getBoss())
                + "\n\n" + formattaTesto.menuCombattimento();
    }

    /**
     * Elabora l'input dell'utente durante un combattimento contro un mostro
     * generico, interpretando le scelte di attaccare o usare una pozione.
     *
     * @param input input testuale inserito dall'utente
     * @return il testo di risposta da mostrare all'utente
     */
    private String elaboraCombattimento(String input) {
        return switch (input.trim()) {
            case "1" -> attacca();
            case "2" -> usaPozione();
            default  -> "Scelta non valida.\n" + formattaTesto.menuCombattimento();
        };
    }

    /**
     * Esegue un turno di attacco del giocatore contro il mostro corrente.
     * <p>
     * In base all'esito del turno, gestisce l'eventuale sconfitta del
     * giocatore, la vittoria sul mostro, oppure restituisce il riepilogo
     * del turno se il combattimento continua.
     *
     * @return il testo di risposta da mostrare all'utente
     */
    private String attacca() {
        DTOCombattimento dto = statoGioco.eseguiTurno(statoGioco.getMostroAttuale());
        if (!dto.isGiocatoreVivo()) return gestisciSconfitta(dto);
        if (!dto.isNemicoVivo()) return gestisciVittoria(dto);
        return formattaTesto.formattaCombattimento(dto) + "\n\n" + formattaTesto.menuCombattimento();
    }

    /**
     * Gestisce la sconfitta del giocatore: termina la partita registrando
     * la sconfitta e aggiorna la fase corrente a {@link Fasi#FINE_PARTITA}.
     *
     * @param dto esito del turno di combattimento in cui il giocatore è stato sconfitto
     * @return il testo del riepilogo del turno
     */
    private String gestisciSconfitta(DTOCombattimento dto) {
        statoGioco.terminaPartita(false);
        faseCorrente = Fasi.FINE_PARTITA;
        return formattaTesto.formattaCombattimento(dto);
    }

    /**
     * Gestisce la vittoria del giocatore contro un mostro generico:
     * assegna le ricompense, torna alla fase di {@link Fasi#ESPLORAZIONE}
     * e produce il messaggio combinato di riepilogo del turno e delle
     * ricompense ottenute.
     *
     * @param dto esito del turno di combattimento in cui il mostro è stato sconfitto
     * @return il testo di risposta da mostrare all'utente
     */
    private String gestisciVittoria(DTOCombattimento dto) {
        DTORicompense ricompense = statoGioco.elaboraRicompense(statoGioco.getMostroAttuale());
        faseCorrente = Fasi.ESPLORAZIONE;
        return formattaTesto.formattaCombattimento(dto)
                + "\n" + formattaTesto.formattaRicompense(ricompense)
                + "\n\n" + formattaTesto.menuEsplorazione();
    }

    /**
     * Tenta di far utilizzare al giocatore una pozione dal proprio inventario.
     *
     * @return il testo di risposta da mostrare all'utente, seguito dal menu
     *         di combattimento
     */
    private String usaPozione() {
        boolean riuscito = statoGioco.usaPozione();
        if (riuscito) return formattaTesto.aggiornaStatistiche()
                + "\n\n" + formattaTesto.menuCombattimento();
        return "Non hai pozioni disponibili.\n\n" + formattaTesto.menuCombattimento();
    }

    /**
     * Elabora l'input dell'utente durante il combattimento contro il boss,
     * interpretando le scelte di attaccare o usare una pozione.
     *
     * @param input input testuale inserito dall'utente
     * @return il testo di risposta da mostrare all'utente
     */
    private String elaboraCombattimentoBoss(String input) {
        return switch (input.trim()) {
            case "1" -> attaccaBoss();
            case "2" -> usaPozione();
            default  -> "Scelta non valida.\n" + formattaTesto.menuCombattimento();
        };
    }

    /**
     * Esegue un turno di attacco del giocatore contro il boss.
     *
     * In base all'esito del turno, gestisce l'eventuale sconfitta del
     * giocatore, la vittoria sul boss (e quindi della partita), oppure
     * restituisce il riepilogo del turno se il combattimento continua.
     *
     * @return il testo di risposta da mostrare all'utente
     */
    private String attaccaBoss() {
        DTOCombattimento dto = statoGioco.eseguiTurno(statoGioco.getBoss());
        if (!dto.isGiocatoreVivo()) return gestisciSconfitta(dto);
        if (!dto.isNemicoVivo()) return gestisciVittoriaBoss(dto);
        return formattaTesto.formattaCombattimento(dto) + "\n\n" + formattaTesto.menuCombattimento();
    }

    /**
     * Gestisce la vittoria del giocatore contro il boss: assegna le
     * ricompense, termina la partita registrando la vittoria,
     * aggiorna la fase corrente a {@link Fasi#FINE_PARTITA} e produce il
     * messaggio combinato di riepilogo del turno, delle ricompense e di
     * vittoria.
     *
     * @param dto esito del turno di combattimento in cui il boss è stato sconfitto
     * @return il testo di risposta da mostrare all'utente
     */
    private String gestisciVittoriaBoss(DTOCombattimento dto){
        DTORicompense ricompenseFinale = statoGioco.elaboraRicompense(statoGioco.getBoss());
        statoGioco.terminaPartita(true);
        faseCorrente = Fasi.FINE_PARTITA;
        return formattaTesto.formattaCombattimento(dto)
            + "\n" + formattaTesto.formattaRicompense(ricompenseFinale)
            + "\n\n" + formattaTesto.messaggioVittoria();
    }

    /**
     * Elabora la richiesta dell'utente quando la partita è terminata,
     * restituendo il messaggio finale di vittoria o sconfitta in base
     * all'esito registrato nello stato di gioco.
     *
     * @return il messaggio finale di vittoria o sconfitta
     */
    private String elaboraFinale() {
        if (statoGioco.isGiocatoreHaVinto()) {
            return formattaTesto.messaggioVittoria();
        }
        return formattaTesto.messaggioSconfitta();
    }
}
