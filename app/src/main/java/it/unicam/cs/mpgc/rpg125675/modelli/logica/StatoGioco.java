package it.unicam.cs.mpgc.rpg125675.modelli.logica;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOCombattimento;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTORicompense;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.*;
import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.Luoghi;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.*;

/**
 * Rappresenta lo stato completo di una partita: il giocatore, il mostro attualmente in
 * combattimento, il boss, il luogo corrente e l'esito della partita.
 *
 * Tutte le dipendenze esterne sono iniettate tramite
 * costruttore, in modo da disaccoppiare lo stato di gioco dalle specifiche
 * implementazioni e favorirne la sostituibilità.
 */
public class StatoGioco implements IStatoGioco {

    private final Giocatore giocatore;
    private Mostro mostroAttuale;
    private final ILocanda locanda;
    private final IGeneratoreMostri generatoreMostri;
    private final MotoreCombattimento motoreCombattimento;
    private final Boss boss;
    private Luoghi luogoAttuale;
    private boolean finePartita;
    private boolean giocatoreHaVinto;

    /**
     * Crea un nuovo stato di gioco per una partita appena iniziata.
     *
     * Il giocatore viene creato con il nome specificato e le statistiche
     * iniziali di default; il boss viene caricato tramite il
     * {@code caricatoreMostri} fornito; il luogo iniziale è
     * {@link Luoghi#VILLAGGIO} e la partita non è ancora terminata.
     *
     * @param nomeGiocatore nome del personaggio del giocatore
     * @param locanda implementazione della locanda per il riposo
     * @param generatoreMostri generatore di mostri per l'esplorazione
     * @param motoreCombattimento motore per la risoluzione dei combattimenti
     * @param caricatoreMostri caricatore da cui ottenere il boss di fine gioco
     */
    public StatoGioco(String nomeGiocatore, ILocanda locanda,
                      IGeneratoreMostri generatoreMostri,
                      MotoreCombattimento motoreCombattimento,
                      ICaricatoreMostri caricatoreMostri) {
        this.giocatore = new Giocatore(nomeGiocatore);
        this.mostroAttuale = null;
        this.locanda = locanda;
        this.generatoreMostri = generatoreMostri;
        this.motoreCombattimento = motoreCombattimento;
        this.boss = caricatoreMostri.caricaBoss();
        this.luogoAttuale = Luoghi.VILLAGGIO;
        this.finePartita = false;
        this.giocatoreHaVinto = false;
    }

    /**
     * {@inheritDoc}
     *
     * Lo spostamento verso {@link Luoghi#PORTALE} è consentito solo se
     * {@link #portaleAccessibile()} restituisce {@code true};
     * in tutti gli altri casi lo spostamento è sempre consentito.
     */
    @Override
    public boolean spostati(Luoghi destinazione) {
        if (destinazione == Luoghi.PORTALE && !portaleAccessibile()){
            return false;
        }
        else {
            this.luogoAttuale = destinazione;
            return true;
        }
    }

    /**
     * {@inheritDoc}
     *
     * Il portale è accessibile se il giocatore possiede tutti i frammenti
     * necessari.
     */
    @Override
    public boolean portaleAccessibile() {
        return giocatore.haTuttiIFrammenti();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DTOCombattimento eseguiTurno(EntitaCombattente mostro) {
        return motoreCombattimento.eseguiTurno(giocatore, mostro);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DTORicompense elaboraRicompense(IRicompensante mostro) {
        return motoreCombattimento.assegnaRicompense(giocatore, mostro);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean riposati() {
        return locanda.riposaGiocatore(giocatore);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean usaPozione() {
        return giocatore.usaPozione();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mostro generaMostro() {
        return generatoreMostri.generaMostro();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void terminaPartita(boolean vittoria) {
        this.finePartita = true;
        this.giocatoreHaVinto = vittoria;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStatisticheGiocatore(){
        return giocatore.getStatistiche();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boss getBoss() {
        return boss;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCostoRiposo() {
        return locanda.getCostoRiposo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Luoghi getLuogoAttuale(){
        return luogoAttuale;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFinePartita() {
        return finePartita;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGiocatoreHaVinto(){
        return giocatoreHaVinto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mostro getMostroAttuale() {
        return mostroAttuale;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void impostaMostro(Mostro m) {
        this.mostroAttuale = m;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPersonaggioGiocante getGiocatore() {
        return giocatore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void impostaLuogo(Luoghi luogo) {
        this.luogoAttuale = luogo;
    }
}
