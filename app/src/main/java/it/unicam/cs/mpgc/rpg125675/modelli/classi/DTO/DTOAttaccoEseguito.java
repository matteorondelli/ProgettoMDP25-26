package it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO;

/**
 * Rappresenta l'esito di un attacco eseguito da un'entità,
 * indicando il danno causato e se si è trattato di un attacco speciale.
 */
public class DTOAttaccoEseguito {

    private final int danno;
    private final boolean speciale;

    public DTOAttaccoEseguito(int danno, boolean speciale) {
        this.danno = danno;
        this.speciale = speciale;
    }

    public int getDanno() {
        return danno;
    }

    public boolean isSpeciale() {
        return speciale;
    }
}
