package it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi;



public class Mostro extends EntitaCombattente {

    private final int ricompensaOro;
    private final int ricompensaEsperienza;
    private final boolean ricompensaPozione;
    private final boolean ricompensaFrammento;

    public Mostro(String nome, int puntiVitaMassimi, int attacco, int ricompensaOro, int ricompensaEsperienza, boolean ricompensaPozione, boolean ricompensaFrammento) {
        super(nome, puntiVitaMassimi, attacco);
        this.ricompensaOro = ricompensaOro;
        this.ricompensaEsperienza = ricompensaEsperienza;
        this.ricompensaPozione = ricompensaPozione;
        this.ricompensaFrammento = ricompensaFrammento;
    }

    public int getRicompensaOro() {
        return ricompensaOro;
    }

    public boolean getRicompensaFrammento() {
        return ricompensaFrammento;
    }

    public int getRicompensaEsperienza() {
        return ricompensaEsperienza;
    }

    public boolean getRicompensaPozione() {
        return ricompensaPozione;
    }
}
