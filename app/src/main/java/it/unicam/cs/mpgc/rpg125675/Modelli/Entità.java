package it.unicam.cs.mpgc.rpg125675.Modelli;

import it.unicam.cs.mpgc.rpg125675.Interfacce.Azioni;

public abstract class Entità implements Azioni {

    private String nome;
    private int hp;
    private int maxHp;
    private int dmg;

    public Entità(String nome, int hp, int dmg){
        this.nome = nome;
        this.hp = hp;
        this.maxHp = hp;
        this.dmg = dmg;

    }

    @Override
    public void attacca(Entità entità){
        entità.setHp(entità.getHp()-dmg);
    }
    @Override
    public int getHp() {
        return hp;
    }
    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }
    @Override
    public void cura(Entità entità){
        int hpCurati = entità.getHp() + 10;
        entità.setHp(Math.min(hpCurati, entità.getMaxHp()));
    }
    @Override
    public int  getDmg() {
        return dmg;
    }
    @Override
    public int getMaxHp() {
        return maxHp;
    }

    public String getNome() {
        return nome;
    }


}
