package it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti;


/**
 * Estende la classe OggettoBase
 *
 * Oggetto consumabile che, se utilizzato, ripristina una quantità
 * di punti vita al personaggio che lo utilizza.
 */
public class Pozione extends OggettoBase {

    private final int quantitaCura;

    /**
     * Crea una nuova pozione con nome, prezzo e quantità di cura specificati.
     *
     * @param nome nome della pozione
     * @param prezzo prezzo della pozione
     * @param quantitaCura punti vita ripristinati dalla pozione
     */
    public Pozione(String nome, int prezzo, int quantitaCura) {
        super(nome,prezzo);
        this.quantitaCura = quantitaCura;
    }

    /**
     * Restituisce la quantità di punti vita ripristinati dalla pozione.
     *
     * @return quantità di cura
     */
    public int getQuantitaCura(){
        return quantitaCura;
    }
}
