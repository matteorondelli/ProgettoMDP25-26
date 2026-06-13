package it.unicam.cs.mpgc.rpg125675.modelli.interfacce;

/**
 * Rappresenta un'entità che, se sconfitta in combattimento, fornisce
 * delle ricompense al giocatore (oro, esperienza, oggetti).
 */
public interface IRicompensante {

    /**
     * Restituisce la quantità di oro ottenuta sconfiggendo l'entità.
     *
     * @return ricompensa in oro
     */
    int getRicompensaOro();

    /**
     * Restituisce la quantità di esperienza ottenuta sconfiggendo l'entità.
     *
     * @return ricompensa in esperienza
     */
    int getRicompensaEsperienza();

    /**
     * Indica se la sconfitta dell'entità garantisce una pozione come ricompensa.
     *
     * @return {@code true} se viene assegnata una pozione, {@code false} altrimenti
     */
    boolean isRicompensaPozione();

    /**
     * Indica se la sconfitta dell'entità garantisce un frammento del portale come ricompensa.
     *
     * @return {@code true} se viene assegnato un frammento, {@code false} altrimenti
     */
    boolean isRicompensaFrammento();
}