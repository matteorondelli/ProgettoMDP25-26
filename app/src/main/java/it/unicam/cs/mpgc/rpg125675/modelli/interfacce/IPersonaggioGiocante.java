package it.unicam.cs.mpgc.rpg125675.modelli.interfacce;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti.OggettoBase;

import java.util.List;

public interface IPersonaggioGiocante extends IAttaccante, Attaccabile {
    String getNome();
    void aggiungiOro(int quantita);
    boolean spendiOro(int quantita);
    int getOro();
    void aggiungiEsperienza(int quantita);
    int getEsperienza();
    int getEsperienzaPerLivello();
    int getLivello();
    void aggiungiOggetto(OggettoBase oggetto);
    void aggiungiFrammento();
    int getFrammenti();
    void ripristinaVitaCompleta();
    List<Integer> getInventarioPozioni();
}
