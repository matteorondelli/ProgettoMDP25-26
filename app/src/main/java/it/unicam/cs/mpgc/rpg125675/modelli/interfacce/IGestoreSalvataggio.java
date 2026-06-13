package it.unicam.cs.mpgc.rpg125675.modelli.interfacce;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOSalvataggio;

/**
 * Gestisce la persistenza dello stato di gioco su un supporto di memorizzazione.
 *
 * Astrarre la persistenza tramite questa interfaccia permette di sostituire
 * il meccanismo di salvataggio senza dover modificare la logica di gioco.
 */
public interface IGestoreSalvataggio {

    /**
     * Salva lo stato di gioco rappresentato dal DTO fornito.
     *
     * @param dto dati di salvataggio da persistere
     */
    void salva(DTOSalvataggio dto);

    /**
     * Carica l'ultimo stato di gioco salvato.
     *
     * @return il DTO con i dati salvati, oppure {@code null} se il salvataggio non esiste
     */
    DTOSalvataggio carica();

    /**
     * Verifica se esiste un salvataggio precedente.
     *
     * @return {@code true} se è presente un salvataggio, {@code false} altrimenti
     */
    boolean esisteSalvataggio();

    /**
     * Elimina il salvataggio esistente.
     */
    void elimina();
}
