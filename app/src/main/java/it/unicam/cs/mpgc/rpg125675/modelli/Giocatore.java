package it.unicam.cs.mpgc.rpg125675.modelli;

import java.util.Random;


public class Giocatore extends Entità {

    private Random rand = new Random();
    private int pozione;

    public Giocatore(String nome, int puntiVitaMassimi, int attacco, int pozione) {
        super(nome, puntiVitaMassimi, attacco);
        if (pozione < 0) {
            throw new IllegalArgumentException("Il numero delle pozioni non può essere negative");
        }
        this.pozione = pozione;

    }

    public int getNumeroPozioni() {
        return pozione;
    }

    public int  cura() {
        if (pozione <= 0) {
            throw new IllegalArgumentException("Pozioni finite");
        }
        int puntiVitaCurati = rand.nextInt(11)+20;
        this.setPuntiVita(Math.min(this.getPuntiVitaMassimi(), this.getPuntiVita() + puntiVitaCurati));
        pozione --;
        return puntiVitaCurati;
    }



}
