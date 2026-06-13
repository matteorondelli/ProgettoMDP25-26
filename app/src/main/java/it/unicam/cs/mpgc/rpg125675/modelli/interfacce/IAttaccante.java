package it.unicam.cs.mpgc.rpg125675.modelli.interfacce;

/**
 * Rappresentazione di un entità che può infliggere danno e ha la possibilità
 * di infliggere danni critici
 */
public interface IAttaccante {

    /**
     * Restituisce il valore di attacco base dell'entità.
     *
     * @return danno base di un attacco normale
     */
    int getAttacco();

    /**
     * Determina, in base a una probabilità interna, se l'attacco sarà un colpo critico.
     *
     * @return {@code true} se l'attacco è critico, {@code false} altrimenti
     */
    boolean eseguiAttaccoCritico();

    /**
     * Restituisce il danno di un attacco critico.
     *
     * @return danno del colpo critico
     */
    int getDannoCritico();

    /**
     * Metodo che riduce i punti vita dell'entità in base al danno subito
     *
     * @param danno , quantità da sottrarre ai punti vita
     */
    void prendiDanno(int danno);

    /**
     * Indica se l'entità è viva o morta
     *
     * @return {@code true} se l'entità è viva, {@code false} altrimenti
     */
    boolean isVivo();
}