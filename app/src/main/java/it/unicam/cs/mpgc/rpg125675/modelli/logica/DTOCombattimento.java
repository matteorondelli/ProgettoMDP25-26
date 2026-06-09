package it.unicam.cs.mpgc.rpg125675.modelli.logica;

public class DTOCombattimento {

    private final int dannoCausato;
    private final int dannoRicevuto;
    private final boolean attaccoSpecialeBoss;
    private final boolean attaccoCriticoGiocatore;
    private final boolean giocatoreVivo;
    private final boolean nemicoVivo;

    public DTOCombattimento(int dannoCausato,
                            int dannoRicevuto,
                            boolean attaccoSpecialeBoss,
                            boolean attaccoCriticoGiocatore,
                            boolean giocatoreVivo,
                            boolean nemicoVivo) {
        this.dannoCausato = dannoCausato;
        this.dannoRicevuto = dannoRicevuto;
        this.attaccoSpecialeBoss = attaccoSpecialeBoss;
        this.attaccoCriticoGiocatore = attaccoCriticoGiocatore;
        this.giocatoreVivo = giocatoreVivo;
        this.nemicoVivo = nemicoVivo;
    }

    public int getDannoCausato() {
        return dannoCausato;
    }
    public int getDannoRicevuto() {
        return dannoRicevuto;
    }
    public boolean isAttaccoSpecialeBoss() {
        return attaccoSpecialeBoss;
    }
    public boolean isAttaccoCriticoGiocatore(){
        return attaccoCriticoGiocatore;
    }
    public boolean isGiocatoreVivo() {
        return giocatoreVivo;
    }
    public boolean isNemicoVivo() {
        return nemicoVivo;
    }
}
