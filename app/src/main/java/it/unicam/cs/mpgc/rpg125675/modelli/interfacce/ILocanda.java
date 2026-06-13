package it.unicam.cs.mpgc.rpg125675.modelli.interfacce;

/**
 * Rappresenta un luogo in cui il giocatore, pagando con l'oro, può riposare per ripristinare i punti vita.
 */
public interface ILocanda {

    /**
     * Fa riposare il giocatore, ripristinando i punti vita al massimo
     * se possiede abbastanza oro.
     *
     * @param giocatore il giocatore che desidera riposare
     * @return {@code true} se il riposo è avvenuto con successo,
     *         {@code false} se il giocatore non ha oro sufficiente
     */
    boolean riposaGiocatore(IPersonaggioGiocante giocatore);

    /**
     * Restituisce il costo in oro per riposare.
     *
     * @return costo del riposo
     */
    int getCostoRiposo();
}
