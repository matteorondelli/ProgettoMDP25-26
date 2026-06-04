package it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti;

import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.TipiOggetti;


public class Arma extends OggettoBase {

    private final int bonusAttacco;

    public Arma(String nome, int prezzo, TipiOggetti tipoOggetto, int bonusAttacco) {
        super(nome, prezzo, tipoOggetto);
        this.bonusAttacco = bonusAttacco;
    }

    public int getBonusAttacco() {
        return bonusAttacco;
    }
}
