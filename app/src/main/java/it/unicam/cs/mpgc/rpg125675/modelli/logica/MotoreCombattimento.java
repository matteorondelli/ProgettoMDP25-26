package it.unicam.cs.mpgc.rpg125675.modelli.logica;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Boss;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.EntitaCombattente;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Giocatore;

import java.util.Random;

public class MotoreCombattimento {


    public MotoreCombattimento() {}

    public RisultatoCombattimento eseguiTurno(Giocatore giocatore, EntitaCombattente nemico) {

        int dannoCausato = 0;
        boolean attaccoCriticoGiocatore = false;
        if (giocatore.eseguiAttaccoCritico()) {
            dannoCausato = giocatore.getDannoCritico();
            attaccoCriticoGiocatore = true;
        }
        else{
            dannoCausato = giocatore.getAttaccoTotale();
        }
        nemico.prendiDanno(dannoCausato);

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

        return new RisultatoCombattimento(
                dannoCausato,
                dannoRicevuto,
                attaccoSpecialeBoss,
                attaccoCriticoGiocatore,
                giocatore.isVivo(),
                nemico.isVivo()
        );
    }
}
