package it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi;

import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.Attaccabile;

public abstract class EntitaCombattente implements Attaccabile {

    private String nome;
    private int puntiVita;
    private int puntiVitaMassimi;
    private int attacco;

    public EntitaCombattente(String nome, int puntiVitaMassimi, int attacco) {
        this.nome = nome;
        this.puntiVitaMassimi = puntiVitaMassimi;
        this.puntiVita = puntiVitaMassimi;
        this.attacco = attacco;
    }

    protected void aumentoPuntiVitaMassimi(int valore) { this.puntiVitaMassimi += valore; }

    protected void aumentoAttacco(int valore) { this.attacco += valore; }

    @Override
    public void prendiDanno(int danno){
        this.puntiVita = this.puntiVita - danno;
    }

    @Override
    public boolean vivo(){
        return this.puntiVita > 0;
    }

    @Override
    public int getPuntiVita(){
        return puntiVita;
    }

    @Override
    public int getPuntiVitaMassimi(){
        return puntiVitaMassimi;
    }

    public void cura(int quantita){
        this.puntiVita = Math.min(this.puntiVitaMassimi, this.puntiVita + quantita);
    }

    public int getAttacco(){
        return attacco;
    }

    public String getNome(){
        return nome;
    }








}
