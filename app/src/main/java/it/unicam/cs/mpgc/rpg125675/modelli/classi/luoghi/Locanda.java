package it.unicam.cs.mpgc.rpg125675.modelli.classi.luoghi;

import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.ILocanda;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IPersonaggioGiocante;


public class Locanda implements ILocanda {

    private static final int COSTO_RIPOSO = 20;

    @Override
    public int getCostoRiposo() {
        return COSTO_RIPOSO;
    }

    @Override
    public boolean riposaGiocatore(IPersonaggioGiocante giocatore) {
        if (!giocatore.spendiOro(COSTO_RIPOSO)) {
            return false;
        }
        giocatore.ripristinaVitaCompleta();
        return true;
    }
}
