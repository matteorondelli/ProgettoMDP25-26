package it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOAttaccoEseguito;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IRicompensante;


import java.util.Random;

public class Boss extends EntitaCombattente implements IRicompensante {

    private static final Random rand = new Random();
    private static final double PROBABILITA_ATTACCO_SPECIALE = 0.3;
    private static final int MOLTIPLICATORE_ATTACCO_SPECIALE = 2;

    // Campi d'istanza dinamici (non più costanti statiche hard-coded!)
    private final int ricompensaOro;
    private final int ricompensaEsperienza;

    // Il costruttore ora riceve tutto dall'esterno (da CaricaDaJson)
    public Boss(String nome, int hpMassimi, int attaccoBase, int oro, int esperienza) {
        super(nome, hpMassimi, attaccoBase);
        this.ricompensaOro = oro;
        this.ricompensaEsperienza = esperienza;
    }

    @Override
    public int getRicompensaOro() { return this.ricompensaOro; }

    @Override
    public int getRicompensaEsperienza() { return this.ricompensaEsperienza; }

    @Override
    public boolean isRicompensaPozione() { return true; }

    @Override
    public boolean isRicompensaFrammento() { return false; }

    private boolean eseguiAttaccoSpeciale() {
        return rand.nextDouble() < PROBABILITA_ATTACCO_SPECIALE;
    }

    @Override
    public DTOAttaccoEseguito eseguiAttacco() {
        if (eseguiAttaccoSpeciale()) {
            return new DTOAttaccoEseguito(getAttacco() * MOLTIPLICATORE_ATTACCO_SPECIALE, true);
        }
        return new DTOAttaccoEseguito(getAttacco(), false);
    }
}
