package it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO;

/**
 * DTO che rappresenta l'esito di un singolo attacco eseguito da un'entità combattente
 */
public class DTOAttaccoEseguito {

    private final int danno;
    private final boolean speciale;

    /**
     * Crea un nuovo DTO con l'esito dell'attacco.
     *
     * @param danno quantità di danno causato dall'attacco
     * @param speciale {@code true} se l'attacco eseguito è un attacco speciale
     */
    public DTOAttaccoEseguito(int danno, boolean speciale) {
        this.danno = danno;
        this.speciale = speciale;
    }

    /**
     * Restituisce il danno causato dall'attacco.
     *
     * @return quantità di danno
     */
    public int getDanno() {
        return danno;
    }

    /**
     * Indica se l'attacco eseguito è un attacco speciale.
     *
     * @return {@code true} se l'attacco è speciale, {@code false} altrimenti
     */
    public boolean isSpeciale() {
        return speciale;
    }
}
