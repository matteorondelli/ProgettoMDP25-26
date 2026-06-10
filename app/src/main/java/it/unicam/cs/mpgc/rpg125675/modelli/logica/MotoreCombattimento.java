package it.unicam.cs.mpgc.rpg125675.modelli.logica;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti.Pozione;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Boss;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.EntitaCombattente;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Giocatore;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Mostro;


public class MotoreCombattimento {


    public MotoreCombattimento() {}

    public DTOCombattimento eseguiTurno(Giocatore giocatore, EntitaCombattente nemico) {

        int dannoCausato;
        boolean attaccoCriticoGiocatore = false;
        if (giocatore.eseguiAttaccoCritico()) {
            dannoCausato = giocatore.getDannoCritico();
            attaccoCriticoGiocatore = true;
        }
        else{
            dannoCausato = giocatore.getAttacco();
        }
        nemico.prendiDanno(dannoCausato);
        int pvNemicoRimasti = nemico.getPuntiVita();

        int dannoRicevuto = 0;
        boolean attaccoSpecialeBoss = false;

        if (nemico.isVivo()) {
            if (nemico instanceof Boss boss && boss.eseguiAttaccoSpeciale()) {
                dannoRicevuto = boss.getDannoAttaccoSpeciale();
                attaccoSpecialeBoss = true;
            } else {
                dannoRicevuto = nemico.getAttacco();
            }
            giocatore.prendiDanno(dannoRicevuto);
        }

        return new DTOCombattimento(
                dannoCausato,
                dannoRicevuto,
                attaccoSpecialeBoss,
                attaccoCriticoGiocatore,
                pvNemicoRimasti,
                giocatore.isVivo(),
                nemico.isVivo()
        );
    }

    public DTORicompense assegnaRicompense(Giocatore giocatore, Mostro mostro) {
        giocatore.aggiungiOro(mostro.getRicompensaOro());
        int livelloPrima = giocatore.getLivello();
        giocatore.aggiungiEsperienza(mostro.getRicompensaEsperienza());
        boolean livelloSalito = giocatore.getLivello() > livelloPrima;
        if (mostro.getRicompensaPozione()) {
            giocatore.aggiungiOggetto(new Pozione("Pozione", 0,  30));
        }
        if (mostro.getRicompensaFrammento()) {
            giocatore.aggiungiFrammento();
        }

        return new DTORicompense(
                mostro.getRicompensaOro(),
                mostro.getRicompensaEsperienza(),
                mostro.getRicompensaPozione(),
                mostro.getRicompensaFrammento(),
                livelloSalito

        );
    }

}

