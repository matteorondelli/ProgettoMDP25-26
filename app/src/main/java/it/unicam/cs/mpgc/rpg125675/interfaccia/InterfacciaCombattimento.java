package it.unicam.cs.mpgc.rpg125675.interfaccia;

import it.unicam.cs.mpgc.rpg125675.modelli.Nemico;
import it.unicam.cs.mpgc.rpg125675.modelli.Personaggio;

public interface InterfacciaCombattimento {

    void mostraStato(Personaggio giocatore, Nemico nemico);
    int leggiScelta();
    void mostraMessaggio(String messaggio);

}
