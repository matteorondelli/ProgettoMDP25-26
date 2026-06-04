package it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti;

import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.TipiOggetti;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.Acquistabile;

public abstract class OggettoBase implements Acquistabile {

    private String nome;
    private int prezzo;
    private TipiOggetti tipoOggetto;

    public  OggettoBase(String nome, int prezzo, TipiOggetti tipoOggetto) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.tipoOggetto = tipoOggetto;
    }

    public String getNome() {
        return nome;
    }
    public int getPrezzo() {
        return prezzo;
    }
    public TipiOggetti getTipoOggetto() {
        return tipoOggetto;
    }
}
