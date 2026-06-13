package it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi;

import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IAttaccabile;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOAttaccoEseguito;

/**
 * Classe base astratta per tutte le entità capaci di partecipare a un
 * combattimento (giocatore, mostri, boss).
 *
 * Fornisce la gestione dei punti vita, dei punti vita massimi e
 * dell'attacco base, e l'implementazione di default di {@link #eseguiAttacco()},
 * che le sottoclassi possono ridefinire per comportamenti speciali.
 */
public abstract class EntitaCombattente implements IAttaccabile {

    private final String nome;
    private int puntiVita;
    private int puntiVitaMassimi;
    private int attacco;

    /**
     * Crea una nuova entità combattente con nome, punti vita massimi e attacco.
     * I punti vita correnti vengono inizializzati al valore massimo.
     *
     * @param nome nome dell'entità
     * @param puntiVitaMassimi punti vita massimi iniziali
     * @param attacco valore di attacco iniziale
     */
    public EntitaCombattente(String nome, int puntiVitaMassimi, int attacco) {
        this.nome = nome;
        this.puntiVitaMassimi = puntiVitaMassimi;
        this.puntiVita = puntiVitaMassimi;
        this.attacco = attacco;
    }

    /**
     * Aumenta i punti vita massimi dell'entità di una quantità specificata.
     *
     * @param valore quantità da aggiungere ai punti vita massimi
     */
    protected void aumentoPuntiVitaMassimi(int valore) {
        this.puntiVitaMassimi += valore;
    }

    /**
     * Aumenta il valore di attacco dell'entità di una quantità specificata.
     *
     * @param valore quantità da aggiungere all'attacco
     */
    protected void aumentoAttacco(int valore) {
        this.attacco += valore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void prendiDanno(int danno){
        this.puntiVita = this.puntiVita - danno;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVivo(){
        return this.puntiVita > 0;
    }

    /**
     * {@inheritDoc}
     * Se i punti vita sono scesi sotto zero, viene restituito {@code 0}.
     */
    @Override
    public int getPuntiVita(){
        if(puntiVita <= 0) return 0;
        return puntiVita;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPuntiVitaMassimi(){
        return puntiVitaMassimi;
    }

    /**
     * Cura l'entità di una quantità specificata, senza superare i punti vita massimi.
     *
     * @param quantita quantità di punti vita da ripristinare
     */
    public  void cura(int quantita) {
        this.puntiVita = Math.min(this.puntiVitaMassimi, this.puntiVita + quantita);
    }

    /**
     * Restituisce il valore di attacco dell'entità.
     *
     * @return valore di attacco
     */
    public int getAttacco(){
        return attacco;
    }

    /**
     * Restituisce il nome dell'entità.
     *
     * @return nome dell'entità
     */
    public String getNome(){
        return nome;
    }

    /**
     * Esegue un attacco standard, infliggendo un danno pari al valore di attacco.
     *
     * Le sottoclassi possono ridefinire questo metodo per implementare
     * comportamenti di attacco diversi.
     *
     * @return un DTO con il danno causato e l'indicazione se si tratta o meno
     *         di un attacco speciale
     */
    public DTOAttaccoEseguito eseguiAttacco() {
        return new DTOAttaccoEseguito(attacco, false);
    }

    /**
     * Imposta direttamente i punti vita massimi e correnti dell'entità.
     *
     * Utilizzato per ripristinare lo stato di un'entità a partire da un
     * salvataggio.
     *
     * @param massimi  nuovo valore di punti vita massimi
     * @param correnti nuovo valore di punti vita correnti
     */
    protected void impostaPuntiVita(int massimi, int correnti) {
        this.puntiVitaMassimi = massimi;
        this.puntiVita = correnti;
    }

    /**
     * Imposta direttamente il valore di attacco dell'entità.
     *
     * Utilizzato per ripristinare lo stato di un'entità a partire da un
     * salvataggio.
     *
     * @param valore nuovo valore di attacco
     */
    protected void impostaAttacco(int valore) {
        this.attacco = valore;
    }


}
