package it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi;

import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.Attaccabile;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOAttaccoEseguito;

public abstract class EntitaCombattente implements Attaccabile {

    private final String nome;
    private int puntiVita;
    private int puntiVitaMassimi;
    private int attacco;

    public EntitaCombattente(String nome, int puntiVitaMassimi, int attacco) {
        this.nome = nome;
        this.puntiVitaMassimi = puntiVitaMassimi;
        this.puntiVita = puntiVitaMassimi;
        this.attacco = attacco;
    }

    protected void aumentoPuntiVitaMassimi(int valore) {
        this.puntiVitaMassimi += valore;
    }

    protected void aumentoAttacco(int valore) {
        this.attacco += valore;
    }

    @Override
    public void prendiDanno(int danno){
        this.puntiVita = this.puntiVita - danno;
    }

    @Override
    public boolean isVivo(){
        return this.puntiVita > 0;
    }

    @Override
    public int getPuntiVita(){
        if(puntiVita <= 0) return 0;
        return puntiVita;
    }

    @Override
    public int getPuntiVitaMassimi(){
        return puntiVitaMassimi;
    }

    public  void cura(int quantita) {
        this.puntiVita = Math.min(this.puntiVitaMassimi, this.puntiVita + quantita);
    }

    public int getAttacco(){
        return attacco;
    }

    public String getNome(){
        return nome;
    }

    public DTOAttaccoEseguito eseguiAttacco() {
        return new DTOAttaccoEseguito(attacco, false);
    }

    protected void impostaPuntiVita(int massimi, int correnti) {
        this.puntiVitaMassimi = massimi;
        this.puntiVita = correnti;
    }

    protected void impostaAttacco(int valore) {
        this.attacco = valore;
    }


}
