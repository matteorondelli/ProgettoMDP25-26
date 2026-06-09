package it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti;

import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.TipiDiOggetti;

public abstract class OggettoBase {
    private String nome;
    private int prezzo;
    private TipiDiOggetti tipoOggetto;

    public OggettoBase(String nome, int prezzo, TipiDiOggetti tipoOggetto) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.tipoOggetto =  tipoOggetto;
    }
    public String getNome() {
        return nome;
    }
    public int getPrezzo() {
        return prezzo;
    }
}
