package it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOAttaccoEseguito;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IRicompensante;
import java.util.Random;

/**
 * Rappresenta il boss, affrontabile solo dopo aver
 * raggiunto il portale con tutti i frammenti necessari.
 *
 * Il boss ha una probabilità di eseguire un attacco speciale che infligge un danno raddoppiato,
 * e garantisce sempre una pozione come ricompensa.
 */
public class Boss extends EntitaCombattente implements IRicompensante {

    private static final Random rand = new Random();
    private static final double PROBABILITA_ATTACCO_SPECIALE = 0.3;
    private static final int MOLTIPLICATORE_ATTACCO_SPECIALE = 2;

    private final int ricompensaOro;
    private final int ricompensaEsperienza;

    /**
     * Crea un nuovo boss.
     *
     * @param nome nome del boss
     * @param hpMassimi punti vita massimi
     * @param attaccoBase valore di attacco base
     * @param oro oro ottenuto sconfiggendo il boss
     * @param esperienza esperienza ottenuta sconfiggendo il boss
     */
    public Boss(String nome, int hpMassimi, int attaccoBase, int oro, int esperienza) {
        super(nome, hpMassimi, attaccoBase);
        this.ricompensaOro = oro;
        this.ricompensaEsperienza = esperienza;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRicompensaOro() { return this.ricompensaOro; }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRicompensaEsperienza() { return this.ricompensaEsperienza; }

    /**
     * {@inheritDoc}
     *
     * Pozione garantita
     */
    @Override
    public boolean isRicompensaPozione() { return true; }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRicompensaFrammento() { return false; }

    /**
     * Determina, in base a una probabilità fissa, se il boss eseguirà
     * un attacco speciale.
     *
     * @return {@code true} se l'attacco è speciale, {@code false} altrimenti
     */
    private boolean eseguiAttaccoSpeciale() {
        return rand.nextDouble() < PROBABILITA_ATTACCO_SPECIALE;
    }

    /**
     * {@inheritDoc}
     *
     * Con probabilità fissa il boss esegue un attacco speciale che infligge un danno pari all'attacco base
     * moltiplicato per {@link #MOLTIPLICATORE_ATTACCO_SPECIALE}; altrimenti
     * esegue un attacco normale.
     */
    @Override
    public DTOAttaccoEseguito eseguiAttacco() {
        if (eseguiAttaccoSpeciale()) {
            return new DTOAttaccoEseguito(getAttacco() * MOLTIPLICATORE_ATTACCO_SPECIALE, true);
        }
        return new DTOAttaccoEseguito(getAttacco(), false);
    }
}
