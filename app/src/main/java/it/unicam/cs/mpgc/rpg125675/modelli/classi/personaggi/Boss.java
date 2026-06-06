package it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi;

import java.util.Random;

public class Boss extends EntitaCombattente {

    private final int dannoAttaccoSpeciale;
    private final double probabilitaAttaccoSpeciale;
    private static final Random rand = new Random();

    public Boss(String nome, int puntiVitaMassimi, int attacco, int dannoAttaccoSpeciale, double probabilitaAttaccoSpeciale) {
        super(nome, puntiVitaMassimi, attacco);
        this.dannoAttaccoSpeciale = dannoAttaccoSpeciale;
        this.probabilitaAttaccoSpeciale = probabilitaAttaccoSpeciale;
    }

    public boolean eseguiAttaccoSpeciale() {
        return rand.nextDouble() < probabilitaAttaccoSpeciale;
    }

    public int getDannoAttaccoSpeciale() {
        return dannoAttaccoSpeciale;
    }
}
