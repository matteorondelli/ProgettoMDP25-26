package it.unicam.cs.mpgc.rpg125675.modelli.classi.luoghi;

import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.ILocanda;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IPersonaggioGiocante;

/**
 * Implementazione di {@link ILocanda} che permette al giocatore di
 * ripristinare completamente i punti vita pagando un costo in oro.
 */

public class Locanda implements ILocanda {

    private static final int COSTO_RIPOSO = 20;

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCostoRiposo() {
        return COSTO_RIPOSO;
    }

    /**
     * {@inheritDoc}
     *
     * Riposo effettuato solo se il giocatore dispone di oro
     * sufficiente per pagare il costo; in tal caso l'oro viene sottratto
     * e i punti vita vengono ripristinati al massimo.
     */
    @Override
    public boolean riposaGiocatore(IPersonaggioGiocante giocatore) {
        if (!giocatore.spendiOro(COSTO_RIPOSO)) {
            return false;
        }
        giocatore.ripristinaVitaCompleta();
        return true;
    }
}
