package it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti;


import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.TipiDiOggetti;

public class Pozione extends OggettoBase {
    private final int quantitaCura;

    public Pozione(String nome, int prezzo, TipiDiOggetti tipoOggetto, int quantitaCura) {
        super(nome,prezzo,tipoOggetto);
        this.quantitaCura = quantitaCura;
    }

    public int getQuantitaCura(){
        return quantitaCura;
    }
}
