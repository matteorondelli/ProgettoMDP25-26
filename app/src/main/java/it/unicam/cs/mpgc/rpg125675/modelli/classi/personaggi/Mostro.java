package it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi;


import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IRicompensante;

/**
 * Rappresenta un mostro nemico generico che il giocatore può incontrare
 * durante l'esplorazione.
 *
 * Oltre alle caratteristiche di combattimento ereditate da
 * {@link EntitaCombattente}, definisce le ricompense ottenute dal giocatore in caso di vittoria.
 */
public class Mostro extends EntitaCombattente implements IRicompensante {

    private final int ricompensaOro;
    private final int ricompensaEsperienza;
    private final boolean ricompensaPozione;
    private final boolean ricompensaFrammento;

    /**
     * Crea un nuovo mostro.
     *
     * @param nome nome del mostro
     * @param puntiVitaMassimi punti vita massimi
     * @param attacco valore di attacco
     * @param ricompensaOro oro ottenuto sconfiggendo il mostro
     * @param ricompensaEsperienza esperienza ottenuta sconfiggendo il mostro
     * @param ricompensaPozione {@code true} se la sconfitta del mostro garantisce una pozione
     * @param ricompensaFrammento {@code true} se la sconfitta del mostro garantisce un frammento del portale
     */
    public Mostro(String nome, int puntiVitaMassimi, int attacco, int ricompensaOro,
                  int ricompensaEsperienza, boolean ricompensaPozione, boolean ricompensaFrammento) {
        super(nome, puntiVitaMassimi, attacco);
        this.ricompensaOro = ricompensaOro;
        this.ricompensaEsperienza = ricompensaEsperienza;
        this.ricompensaPozione = ricompensaPozione;
        this.ricompensaFrammento = ricompensaFrammento;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRicompensaOro() {
        return ricompensaOro;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRicompensaFrammento() {
        return ricompensaFrammento;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRicompensaEsperienza() {
        return ricompensaEsperienza;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRicompensaPozione() {
        return ricompensaPozione;
    }
}
