package it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOAttaccoEseguito;

import java.util.Random;

public class Boss extends EntitaCombattente {


    private static final Random rand = new Random();
    private static final int BOSS_HP = 150;
    private static final int BOSS_ATTACCO = 20;
    private static final double PROBABILITA_ATTACCO_SPECIALE = 0.3;
    private static final int MOLTIPLICATORE_ATTACCO_SPECIALE = 2;

    public Boss(String nome) {
        super(nome, BOSS_HP, BOSS_ATTACCO);
    }

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
