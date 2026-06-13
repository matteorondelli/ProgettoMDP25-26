package it.unicam.cs.mpgc.rpg125675.modelli.logica;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOAttaccoEseguito;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOCombattimento;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTORicompense;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.EntitaCombattente;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IAttaccante;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IPersonaggioGiocante;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IRicompensante;

/**
 * Motore che implementa le regole di combattimento del gioco.
 *
 * Si occupa di calcolare lo scambio di colpi tra il giocatore e un nemico
 * in un singolo turno, e di assegnare le ricompense al giocatore in caso
 * di vittoria.
 */
public class MotoreCombattimento {

    /**
     * Esegue un turno completo di combattimento tra il giocatore e un nemico.
     *
     * Il giocatore attacca per primo: se il colpo è critico il danno causato è quello
     * critico, altrimenti è il danno base. Il danno viene applicato al
     * nemico. Se il nemico è ancora vivo dopo aver subito il danno, esegue
     * a sua volta un attacco verso il giocatore.
     *
     * @param giocatore
     * @param nemico
     * @return un DTO con il riepilogo completo dell'esito del turno
     */
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

    /**
     * Assegna al giocatore le ricompense previste per la sconfitta di
     * un'entità nemica.
     *
     * Aggiunge oro ed esperienza al giocatore, verificando se quest'ultimo
     * sale di livello. Se previsto dal nemico sconfitto, aggiunge inoltre una pozione e/o un frammento del
     * portale all'inventario del giocatore.
     *
     * @param giocatore
     * @param mostro
     * @return un DTO con il dettaglio delle ricompense assegnate
     */
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

