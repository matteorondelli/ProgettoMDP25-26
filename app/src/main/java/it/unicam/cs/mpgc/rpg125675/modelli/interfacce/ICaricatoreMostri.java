package it.unicam.cs.mpgc.rpg125675.modelli.interfacce;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Boss;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Mostro;

import java.util.List;

/**
 * Fornisce l'accesso ai dati delle entità nemiche (mostri e boss)
 * provenienti da una sorgente esterna.
 *
 * Astrarre il caricamento tramite questa interfaccia permette di sostituire
 * la sorgente dei dati senza modificare la logica.
 */

public interface ICaricatoreMostri {

    /**
     * Carica l'elenco dei mostri dalla sorgente.
     *
     * @return lista dei mostri caricati
     */
    List<Mostro> caricaMostri();

    /**
     * Carica il boss dalla sorgente.
     *
     * @return istanza del boss caricato
     */
    Boss caricaBoss();
}
