package it.unicam.cs.mpgc.rpg125675.modelli.classi.locanda;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Giocatore;

public class Locanda {

    private final int costoRiposo;

    public Locanda(int costoRiposo) {
        this.costoRiposo = costoRiposo;
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
