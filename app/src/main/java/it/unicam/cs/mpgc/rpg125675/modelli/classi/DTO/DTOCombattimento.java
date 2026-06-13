package it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO;

/**
 * DTO che rappresenta l'esito completo di un turno di combattimento.
 * Comprende i danni scambiati tra giocatore e nemico e lo stato di vita
 * di entrambi al termine del turno.
 *
 * Utilizzato per trasportare i risultati verso i livelli di controllo e presentazione
 */
public class DTOCombattimento {

    private final int dannoCausato;
    private final int dannoRicevuto;
    private final boolean attaccoSpecialeBoss;
    private final boolean attaccoCriticoGiocatore;
    private final int puntiVitaNemicoRimasti;
    private final boolean giocatoreVivo;
    private final boolean nemicoVivo;

    /**
     * Crea un nuovo DTO con l'esito di un turno di combattimento.
     *
     * @param dannoCausato danno inflitto dal giocatore
     * @param dannoRicevuto danno subito dal giocatore
     * @param attaccoSpecialeBoss {@code true} se il boss ha eseguito un attacco speciale
     * @param attaccoCriticoGiocatore {@code true} se il giocatore ha eseguito un colpo critico
     * @param puntiVitaNemicoRimasti punti vita rimanenti al nemico dopo il turno
     * @param giocatoreVivo {@code true} se il giocatore è ancora vivo dopo il turno
     * @param nemicoVivo {@code true} se il nemico è ancora vivo dopo il turno
     */
    public DTOCombattimento(int dannoCausato, int dannoRicevuto, boolean attaccoSpecialeBoss,
                            boolean attaccoCriticoGiocatore, int puntiVitaNemicoRimasti,
                            boolean giocatoreVivo, boolean nemicoVivo) {
        this.dannoCausato = dannoCausato;
        this.dannoRicevuto = dannoRicevuto;
        this.attaccoSpecialeBoss = attaccoSpecialeBoss;
        this.attaccoCriticoGiocatore = attaccoCriticoGiocatore;
        this.puntiVitaNemicoRimasti = puntiVitaNemicoRimasti;
        this.giocatoreVivo = giocatoreVivo;
        this.nemicoVivo = nemicoVivo;
    }

    /**
     * Restituisce il danno inflitto dal giocatore al nemico.
     *
     * @return danno causato
     */
    public int getDannoCausato() {
        return dannoCausato;
    }

    /**
     * Restituisce il danno subito dal giocatore.
     *
     * @return danno ricevuto
     */
    public int getDannoRicevuto() {
        return dannoRicevuto;
    }

    /**
     * Restituisce i punti vita rimanenti del nemico dopo il turno.
     *
     * @return punti vita residui del nemico
     */
    public int getPuntiVitaNemicoRimasti() {
        return puntiVitaNemicoRimasti;
    }

    /**
     * Indica se il boss ha eseguito un attacco speciale durante il turno.
     *
     * @return {@code true} se è stato eseguito un attacco speciale
     */
    public boolean isAttaccoSpecialeBoss() {
        return attaccoSpecialeBoss;
    }

    /**
     * Indica se il giocatore ha eseguito un colpo critico durante il turno.
     *
     * @return {@code true} se l'attacco del giocatore è stato critico
     */
    public boolean isAttaccoCriticoGiocatore(){
        return attaccoCriticoGiocatore;
    }

    /**
     * Indica se il giocatore è ancora vivo dopo il turno.
     *
     * @return {@code true} se il giocatore è vivo
     */
    public boolean isGiocatoreVivo() {
        return giocatoreVivo;
    }

    /**
     * Indica se il nemico è ancora vivo dopo il turno.
     *
     * @return {@code true} se il nemico è vivo
     */
    public boolean isNemicoVivo() {
        return nemicoVivo;
    }

}
