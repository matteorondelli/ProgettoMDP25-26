package it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti;

public abstract class OggettoBase {
    private final String nome;
    private final int prezzo;


    public OggettoBase(String nome, int prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;

    }

    public String getNome() {
        return nome;
    }

    public int getPrezzo() {
        return prezzo;
    }

}
