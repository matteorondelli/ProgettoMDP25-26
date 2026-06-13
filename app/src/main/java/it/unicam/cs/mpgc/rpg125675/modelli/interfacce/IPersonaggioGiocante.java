package it.unicam.cs.mpgc.rpg125675.modelli.interfacce;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOSalvataggio;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti.OggettoBase;

import java.util.List;

/**
 * Rappresenta il personaggio controllato dal giocatore, con le sue
 * statistiche, risorse e capacità di combattimento.
 *
 * Estende {@link IAttaccante} e {@link IAttaccabile} poiché il giocatore
 * può prendere e recare danno a un nemico.
 */

public interface IPersonaggioGiocante extends IAttaccante, IAttaccabile {

    /**
     * Restituisce il nome del personaggio.
     *
     * @return nome del giocatore
     */
    String getNome();

    /**
     * Aggiunge una quantità di oro al giocatore.
     *
     * @param quantita quantità di oro da aggiungere
     */
    void aggiungiOro(int quantita);

    /**
     * Tenta di spendere una quantità di oro.
     *
     * @param quantita quantità di oro da spendere
     * @return {@code true} se il giocatore ha abbastanza oro, {@code false} altrimenti
     */
    boolean spendiOro(int quantita);

    /**
    * Restituisce la quantità di oro posseduta dal giocatore.
    *
    * @return oro posseduto
    */
    int getOro();

    /**
     * Aggiunge punti esperienza al giocatore, eventualmente innescando
     * un avanzamento di livello.
     *
     * @param quantita punti esperienza da aggiungere
     */
    void aggiungiEsperienza(int quantita);

    /**
     * Restituisce i punti esperienza correnti del giocatore.
     *
     * @return esperienza corrente nel livello attuale
     */
    int getEsperienza();

    /**
     * Restituisce la quantità di esperienza necessaria per salire al livello successivo.
     *
     * @return soglia di esperienza per il prossimo livello
     */
    int getEsperienzaPerLivello();

    /**
     * Restituisce il livello corrente del giocatore.
     *
     * @return livello corrente
     */
    int getLivello();

    /**
     * Aggiunge un oggetto all'inventario del giocatore.
     *
     * @param oggetto oggetto da aggiungere
     */
    void aggiungiOggetto(OggettoBase oggetto);

    /**
     * Aggiunge un frammento del portale al giocatore.
     */
    void aggiungiFrammento();

    /**
     * Restituisce il numero di frammenti del portale posseduti dal giocatore.
     *
     * @return numero di frammenti posseduti
     */
    int getFrammenti();

    /**
     * Ripristina completamente i punti vita del giocatore al valore massimo.
     */
    void ripristinaVitaCompleta();

    /**
     * Restituisce l'elenco delle quantità di cura delle pozioni presenti
     * nell'inventario del giocatore.
     *
     * @return lista delle pozioni possedute
     */
    List<Integer> getInventarioPozioni();

    /**
     * Ripristina lo stato del giocatore a partire dai dati di un salvataggio.
     *
     * @param dto dati di salvataggio da cui ripristinare lo stato
     */
    void ripristinaDaSalvataggio(DTOSalvataggio dto);

    /**
     * Aggiunge una pozione all'inventario del giocatore.
     */
    void aggiungiPozione();
}
