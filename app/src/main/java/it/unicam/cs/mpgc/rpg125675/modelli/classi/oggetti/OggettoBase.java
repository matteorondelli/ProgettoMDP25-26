package it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti;

/**
 * Classe base astratta per gli oggetti (anche futuri) che possono essere posseduti dal giocatore.
 * <p>
 * Definisce le proprietà comuni a tutti gli oggetti: nome e prezzo.
 * Le eventuali sottoclassi specializzano il comportamento.
 */
public abstract class OggettoBase {
    private final String nome;
    private final int prezzo;

    /**
     * Crea un nuovo oggetto con nome e prezzo specificati.
     *
     * @param nome nome dell'oggetto
     * @param prezzo prezzo dell'oggetto (potrebbe servire per implementare un negozio in futuro)
     */
    public OggettoBase(String nome, int prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;

    }

    /**
     * Restituisce il nome dell'oggetto.
     *
     * @return nome dell'oggetto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Restituisce il prezzo dell'oggetto.
     *
     * @return prezzo dell'oggetto
     */
    public int getPrezzo() {
        return prezzo;
    }

}
