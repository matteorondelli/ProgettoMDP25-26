package it.unicam.cs.mpgc.rpg125675.modelli;

public abstract class Entita {

    private String nome;
    private int puntiVita;
    private int puntiVitaMassimi;
    private int attacco;


    public Entita(String nome, int puntiVita, int attacco) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Il nome non può essere vuoto");
        }
        if (puntiVita <= 0) {
            throw new IllegalArgumentException("I punti vita devono essere positivi");
        }
        if (attacco < 0) {
            throw new IllegalArgumentException("L'attacco non può essere negativo");
        }

        this.nome = nome;
        this.puntiVita = puntiVita;
        this.puntiVitaMassimi = puntiVita;
        this.attacco = attacco;
    }



    public void attacca(Entita nemico){
        nemico.setPuntiVita(Math.max(0, nemico.getPuntiVita() - this.getAttacco()));
    }
    public boolean vivo() {
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
    public String getNome() {
        return nome;
    }

}


