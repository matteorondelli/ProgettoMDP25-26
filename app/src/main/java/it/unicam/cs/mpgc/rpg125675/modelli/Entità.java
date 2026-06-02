package it.unicam.cs.mpgc.rpg125675.modelli;

import java.util.Random;

public abstract class Entità {

    private final String nome;
    private int puntiVita;
    private int puntiVitaMassimi;
    private int attacco;


    public Entità(String nome, int puntiVitaMassimi, int attacco) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Il nome del giocatore non puo essere vuoto");
        }
        if (puntiVitaMassimi <= 0) {
            throw new IllegalArgumentException("I punti vita devono essere positivi");
        }
        if (attacco < 0) {
            throw new IllegalArgumentException("L'attacco non può essere negativo");
        }
        this.nome = nome;
        this.puntiVitaMassimi = puntiVitaMassimi;
        this.puntiVita = puntiVitaMassimi;
        this.attacco = attacco;

    }


    public String getNome() {
        return nome;
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



    public void subisciDanno(int danno) {
        if (danno < 0) {
            throw new IllegalArgumentException("Il danno non puo essere negativo");
        }
        setPuntiVita(Math.max(0, puntiVita - danno));
    }

    public boolean isAlive() {
        return puntiVita > 0;
    }
}
