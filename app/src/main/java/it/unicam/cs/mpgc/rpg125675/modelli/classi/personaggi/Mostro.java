package it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi;


import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IRicompensante;

public class Mostro extends EntitaCombattente implements IRicompensante {

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

    @Override
    public int getRicompensaOro() {
        return ricompensaOro;
    }
    
    @Override
    public boolean isRicompensaFrammento() {
        return ricompensaFrammento;
    }

    @Override
    public int getRicompensaEsperienza() {
        return ricompensaEsperienza;
    }

    @Override
    public boolean isRicompensaPozione() {
        return ricompensaPozione;
    }
}
