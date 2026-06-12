package it.unicam.cs.mpgc.rpg125675.modelli.interfacce;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Boss;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Mostro;
import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.Luoghi;

/**
 * Vista in sola lettura dello stato di gioco, pensata per i componenti
 * (es. la UI) che devono solo leggere informazioni per mostrarle
 * all'utente, senza poter modificare lo stato della partita.
 */
public interface IStatoGiocoLettura {
    String getStatisticheGiocatore();
    Boss getBoss();
    int getCostoRiposo();
    Luoghi getLuogoAttuale();
    boolean isFinePartita();
    boolean isGiocatoreHaVinto();
    Mostro getMostroAttuale();
}
