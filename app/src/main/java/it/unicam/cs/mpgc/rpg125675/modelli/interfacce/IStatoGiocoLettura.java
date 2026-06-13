package it.unicam.cs.mpgc.rpg125675.modelli.interfacce;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Boss;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Mostro;
import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.Luoghi;

/**
 * Permette solo la lettura dello stato di gioco, pensata per i componenti
 * che devono solo leggere informazioni per mostrarle
 * all'utente, senza poter modificare lo stato della partita.
 */
public interface IStatoGiocoLettura {

    /**
     * Restituisce una rappresentazione testuale delle statistiche del giocatore.
     *
     * @return statistiche del giocatore formattate come stringa
     */
    String getStatisticheGiocatore();

    /**
     * Restituisce il boss.
     *
     * @return il boss della partita
     */
    Boss getBoss();

    /**
     * Restituisce il costo in oro per riposare alla locanda.
     *
     * @return costo del riposo
     */
    int getCostoRiposo();

    /**
     * Restituisce il luogo in cui si trova attualmente il giocatore.
     *
     * @return luogo attuale
     */
    Luoghi getLuogoAttuale();

    /**
     * Indica se la partita è terminata.
     *
     * @return {@code true} se la partita è finita, {@code false} altrimenti
     */
    boolean isFinePartita();

    /**
     * Indica se il giocatore ha vinto la partita.
     *
     * @return {@code true} se il giocatore ha vinto, {@code false} altrimenti
     */
    boolean isGiocatoreHaVinto();

    /**
     * Restituisce il mostro attualmente coinvolto nel combattimento.
     *
     * @return il mostro corrente, oppure {@code null} se nessun combattimento è in corso
     */
    Mostro getMostroAttuale();
}
