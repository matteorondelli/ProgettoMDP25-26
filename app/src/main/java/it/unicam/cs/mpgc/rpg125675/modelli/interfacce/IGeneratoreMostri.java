package it.unicam.cs.mpgc.rpg125675.modelli.interfacce;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Mostro;

/**
 * Genera istanze di mostri che il giocatore dovrà sconfiggere.
 *
 * L'implementazione decide quale mostro generare e con quali eventuali
 * ricompense aggiuntive (pozioni, frammenti).
 */
public interface IGeneratoreMostri {

    /**
     * Genera un nuovo mostro.
     *
     * @return un nuovo mostro.
     */
    Mostro generaMostro();
}
