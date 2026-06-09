package it.unicam.cs.mpgc.rpg125675.modelli.classi.locanda;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Giocatore;

public class Locanda {

    private int costoRiposo;

    public Locanda() {
        costoRiposo = 20;
    }

    public int getCostoRiposo() {
        return costoRiposo;
    }

    public boolean riposaGiocatore(Giocatore giocatore) {
        if (!giocatore.spendiOro(costoRiposo)){
            return false;
        }
        else {
            giocatore.ripristinaVitaCompleta();
            return true;
        }
    }
}
