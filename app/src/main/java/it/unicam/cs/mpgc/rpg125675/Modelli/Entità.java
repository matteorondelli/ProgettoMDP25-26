package it.unicam.cs.mpgc.rpg125675.Modelli;



public abstract class Entità {

    private String nome;
    private int hp;
    private int attacco;

    public Entità(){}

    public Entità(String nome, int hp, int attacco) {
        this.nome = nome;
        this.hp = hp;
        this.attacco = attacco;
    }

    public void attacca(Entità entità1, Entità entità2) {
       entità2.setHp(entità2.getHp() - entità1.getAttacco());
    }
    public boolean isAlive() {
        return hp > 0;
    }
    public int getHp() {
        return hp;
    }
    public int setHp(int hp) {
        this.hp = hp;
        return hp;
    }
    public int getAttacco() {
        return attacco;
    }
    public String getNome() {
        return nome; }
}
