package it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO;

import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.Luoghi;

import java.util.List;

/**
 * DTO immutabile che trasporta i dati di salvataggio del giocatore,
 * senza esporre logica di dominio ai livelli superiori.
 */
public class DTOSalvataggio {

    private final String nome;
    private final int livello;
    private final int esperienza;
    private final int esperienzaPerLivello;
    private final int puntiVita;
    private final int puntiVitaMassimi;
    private final int attacco;
    private final int oro;
    private final int frammenti;
    private final Luoghi luogoAttuale;
    private final List<Integer> pozioniCura;

    /**
     * Crea un nuovo DTO con i dati completi di una partita da salvare o ripristinare.
     *
     * @param nome  nome del personaggio
     * @param livello livello del giocatore
     * @param esperienza esperienza nel livello attuale
     * @param esperienzaPerLivello esperienza necessaria per il prossimo livello
     * @param puntiVita punti vita correnti
     * @param puntiVitaMassimi punti vita massimi
     * @param attacco valore di attacco corrente
     * @param oro oro posseduto
     * @param frammenti numero di frammenti del portale posseduti
     * @param luogoAttuale luogo in cui si trova il giocatore
     * @param pozioniCura lista delle pozioni in inventario
     */
    public DTOSalvataggio(String nome, int livello, int esperienza, int esperienzaPerLivello,
                          int puntiVita, int puntiVitaMassimi, int attacco, int oro,
                          int frammenti, Luoghi luogoAttuale, List<Integer> pozioniCura) {
        this.nome = nome;
        this.livello = livello;
        this.esperienza = esperienza;
        this.esperienzaPerLivello = esperienzaPerLivello;
        this.puntiVita = puntiVita;
        this.puntiVitaMassimi = puntiVitaMassimi;
        this.attacco = attacco;
        this.oro = oro;
        this.frammenti = frammenti;
        this.luogoAttuale = luogoAttuale;
        this.pozioniCura = pozioniCura;
    }

    /**
     * Restituisce il nome del personaggio salvato.
     *
     * @return nome del personaggio
     */
    public String getNome() {
        return nome;
    }

    /**
     * Restituisce il livello del personaggio salvato.
     *
     * @return livello salvato
     */
    public int getLivello() {
        return livello;
    }

    /**
     * Restituisce l'esperienza del personaggio salvato.
     *
     * @return esperienza salvata
     */
    public int getEsperienza() {
        return esperienza;
    }

    /**
     * Restituisce l'esperienza necessaria per il prossimo livello.
     *
     * @return esperienza per il prossimo livello
     */
    public int getEsperienzaPerLivello() {
        return esperienzaPerLivello;
    }

    /**
     * Restituisce i punti vita al momento del salvataggio.
     *
     * @return punti vita salvati
     */
    public int getPuntiVita() {
        return puntiVita;
    }

    /**
     * Restituisce i punti vita massimi al momento del salvataggio.
     *
     * @return punti vita massimi salvati
     */
    public int getPuntiVitaMassimi() {
        return puntiVitaMassimi;
    }

    /**
     * Restituisce il valore di attacco al momento del salvataggio.
     *
     * @return attacco salvato
     */
    public int getAttacco() {
        return attacco;
    }

    /**
     * Restituisce l'oro posseduto al momento del salvataggio.
     *
     * @return oro salvato
     */
    public int getOro() {
        return oro;
    }

    /**
     * Restituisce il numero di frammenti del portale posseduti al momento del salvataggio.
     *
     * @return frammenti salvati
     */
    public int getFrammenti() {
        return frammenti;
    }

    /**
     * Restituisce il luogo in cui si trovava il giocatore al momento del salvataggio.
     *
     * @return luogo salvato
     */
    public Luoghi getLuogoAttuale() {
        return luogoAttuale;
    }

    /**
     * Restituisce la lista delle pozioni possedute
     * al momento del salvataggio.
     *
     * @return lista dei valori di cura delle pozioni salvate
     */
    public List<Integer> getPozioniCura() {
        return pozioniCura;
    }

}
