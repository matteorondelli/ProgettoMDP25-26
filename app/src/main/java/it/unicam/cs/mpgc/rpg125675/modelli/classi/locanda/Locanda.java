package it.unicam.cs.mpgc.rpg125675.modelli.classi.locanda;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Giocatore;


public class Locanda {

    private static final int COSTO_RIPOSO = 20;

    public int getCostoRiposo() {
        return COSTO_RIPOSO;
    }

    public boolean riposaGiocatore(Giocatore giocatore) {
        if (!giocatore.spendiOro(COSTO_RIPOSO)) {
            return false;
        }
        giocatore.ripristinaVitaCompleta();
        return true;
    }
}
