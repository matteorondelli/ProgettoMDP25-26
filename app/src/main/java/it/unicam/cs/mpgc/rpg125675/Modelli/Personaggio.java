package it.unicam.cs.mpgc.rpg125675.Modelli;

import it.unicam.cs.mpgc.rpg125675.Modelli.ClassiSpecifiche.ClasseGiocatore;

public class Personaggio extends Entità {

    private int level;
    private String nome;

    public Personaggio(String nome, ClasseGiocatore classeGiocatore) {
        super(nome, classeGiocatore.getHp(), classeGiocatore.getAttack());
        this.level = 1;
    }




}
