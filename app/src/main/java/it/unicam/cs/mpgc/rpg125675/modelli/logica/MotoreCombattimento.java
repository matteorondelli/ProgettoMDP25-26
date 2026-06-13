package it.unicam.cs.mpgc.rpg125675.modelli.logica;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOAttaccoEseguito;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOCombattimento;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTORicompense;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.EntitaCombattente;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IAttaccante;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IPersonaggioGiocante;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IRicompensante;


public class MotoreCombattimento {

    public DTOCombattimento eseguiTurno(IAttaccante giocatore, EntitaCombattente nemico) {

        int dannoCausato;
        boolean attaccoCriticoGiocatore = false;
        if (giocatore.eseguiAttaccoCritico()) {
            dannoCausato = giocatore.getDannoCritico();
            attaccoCriticoGiocatore = true;
        } else {
            dannoCausato = giocatore.getAttacco();
        }
        nemico.prendiDanno(dannoCausato);
        int pvNemicoRimasti = nemico.getPuntiVita();

        int dannoRicevuto = 0;
        boolean attaccoSpecialeBoss = false;

        if (nemico.isVivo()) {
            DTOAttaccoEseguito attacco = nemico.eseguiAttacco();
            dannoRicevuto = attacco.getDanno();
            attaccoSpecialeBoss = attacco.isSpeciale();
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

    public DTORicompense assegnaRicompense(IPersonaggioGiocante giocatore, IRicompensante mostro) {
        giocatore.aggiungiOro(mostro.getRicompensaOro());
        int livelloPrima = giocatore.getLivello();
        giocatore.aggiungiEsperienza(mostro.getRicompensaEsperienza());
        boolean livelloSalito = giocatore.getLivello() > livelloPrima;
        if (mostro.isRicompensaPozione()) {
            giocatore.aggiungiPozione();
        }
        if (mostro.isRicompensaFrammento()) {
            giocatore.aggiungiFrammento();
        }

        return new DTORicompense(
                mostro.getRicompensaOro(),
                mostro.getRicompensaEsperienza(),
                mostro.isRicompensaPozione(),
                mostro.isRicompensaFrammento(),
                livelloSalito

        );
    }

}

