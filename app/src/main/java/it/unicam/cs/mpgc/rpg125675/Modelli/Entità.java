package it.unicam.cs.mpgc.rpg125675.Modelli;

public abstract class Entità {

    private String nome;
    private int puntiVita;
    private int puntiVitaMassimi;
    private int attacco;


    public Entità(String nome, int puntiVita, int attacco) {
        this.nome = nome;
        this.puntiVita = puntiVita;
        this.puntiVitaMassimi = puntiVita;
        this.attacco = attacco;
    }



    public void attacca(Entità nemico){
        nemico.setPuntiVita(Math.max(0, nemico.getPuntiVita() - this.getAttacco()));
    }
    public boolean vivo(){
        return this.puntiVita > 0;
    }



    public int getPuntiVita() {
        return puntiVita;
    }
    public void setPuntiVita(int puntiVita) {
        this.puntiVita = puntiVita;
    }
    public int getPuntiVitaMassimi() {
        return puntiVitaMassimi;
    }
    public int getAttacco() {
        return attacco;
    }
    public void setAttacco(int attacco) {
        this.attacco = attacco;
    }
    public String getNome() {
        return nome;
    }

}


