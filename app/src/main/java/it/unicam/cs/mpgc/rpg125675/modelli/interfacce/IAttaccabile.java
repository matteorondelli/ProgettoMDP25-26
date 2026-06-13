package it.unicam.cs.mpgc.rpg125675.modelli.interfacce;

/**
 * Rappresentazione di un entità che può subire danni
 */
public interface IAttaccabile {

    /**
     * Metodo che riduce i punti vita dell'entità in base al danno subito
     * @param danno , quantità da sottrarre ai punti vita
     */
    void prendiDanno(int danno);

    /**
     * Indica se l'entità è viva o morta
     * @return {@code true} se l'entità è viva, {@code false} altrimenti
     */
    boolean isVivo();

    /**
     * Restituisce i punti vita dell'entità
     * @return punti vita
     */
    int getPuntiVita();

    /**
     * Restituisce i punti vita massimi dell'entità
     * @return punti vita massimi
     */
    int getPuntiVitaMassimi();
}
