package it.unicam.cs.mpgc.rpg125675.gestione;


import it.unicam.cs.mpgc.rpg125675.interfaccia.InterfacciaCombattimento;
import it.unicam.cs.mpgc.rpg125675.modelli.Nemico;
import it.unicam.cs.mpgc.rpg125675.modelli.Personaggio;



public class GestioneCombattimento {

    private static final int SCELTA_ATTACCO = 1;
    private static final int SCELTA_CURA = 2;

    private final Personaggio giocatore;
    private final Nemico nemico;
    private final InterfacciaCombattimento interfaccia;

    public GestioneCombattimento(Personaggio giocatore, Nemico nemico, InterfacciaCombattimento interfaccia) {
        this.giocatore = giocatore;
        this.nemico = nemico;
        this.interfaccia = interfaccia;
    }

    public void avvia() {
        while (combattimentoInCorso()) {
            interfaccia.mostraStato(giocatore, nemico);
            turnoGiocatore();

            if(combattimentoInCorso()){
                turnoNemico();
            }
        }

        esitoCombattimento();
    }

    public boolean combattimentoInCorso(){
        return (giocatore.vivo() && nemico.vivo());
    }

    private void turnoGiocatore() {
        int scelta = interfaccia.leggiScelta();

        switch(scelta) {
            case SCELTA_ATTACCO -> giocatore.attacca(nemico);
            case SCELTA_CURA -> giocatore.cura();
            default -> interfaccia.mostraMessaggio("Mossa non valida. Perdi il turno");
        }

    }

    private void turnoNemico() {
            nemico.attacca(giocatore);
    }

    private void esitoCombattimento() {
        if (giocatore.vivo()) {
            interfaccia.mostraMessaggio("Hai vinto!");
        } else {
            interfaccia.mostraMessaggio("Game Over!");
        }
    }

}
