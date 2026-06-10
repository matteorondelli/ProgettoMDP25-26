package it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti;



public class Pozione extends OggettoBase {
    private final int quantitaCura;

    public Pozione(String nome, int prezzo, int quantitaCura) {
        super(nome,prezzo);
        this.quantitaCura = quantitaCura;
    }

    public int getQuantitaCura(){
        return quantitaCura;
    }
}
