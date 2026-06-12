package it.unicam.cs.mpgc.rpg125675.controlli;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.EntitaCombattente;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOCombattimento;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTORicompense;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IStatoGiocoLettura;


public class GestoreUI  {
    private IStatoGiocoLettura statoGioco;


    public GestoreUI(IStatoGiocoLettura statoGioco) {
        this.statoGioco = statoGioco;
    }

    public String aggiornaStatistiche() {
        return statoGioco.getStatisticheGiocatore();
    }

    public String menuEsplorazione() {
        return switch (statoGioco.getLuogoAttuale()) {
            case VILLAGGIO -> "VILLAGGIO\n\n " +
                    "1. Vai nella foresta\n " +
                    "2. Riposa alla locanda (costo:" + statoGioco.getCostoRiposo() + ")\n " +
                    "3. Salva partita\n ";
            case FORESTA -> "FORESTA\n\n " +
                    "1. Caccia\n " +
                    "2. Vai al portale (solo con 3 frammenti)\n " +
                    "3. Torna al villaggio\n" ;
            case PORTALE -> "PORTALE\n\n " +
                    "1. Affronta il boss \n " +
                    "2. Torna al villaggio\n ";
        };
    }

    public String menuCombattimento() {
        return "COMBATTIMENTO\n\n 1. Attacca\n 2. Usa pozione\n ";
    }

    public String formattaCombattimento(DTOCombattimento dtoC) {
        StringBuilder risultatoCombattimento = new StringBuilder();

        if (dtoC.isAttaccoCriticoGiocatore()) {
            risultatoCombattimento.append("Hai colpito per ").append(dtoC.getDannoCausato()).append(" danni (CRITICO!).");
        } else {
            risultatoCombattimento.append("Hai colpito per ").append(dtoC.getDannoCausato()).append(" danni.");
        }

        if (!dtoC.isNemicoVivo()) {
            risultatoCombattimento.append(" \nIl nemico è stato sconfitto!\n");
        } else if (dtoC.isAttaccoSpecialeBoss()) {
            risultatoCombattimento.append(" Hai subito ").append(dtoC.getDannoRicevuto())
                    .append(" danni (ATTACCO SPECIALE!).");
            risultatoCombattimento.append(" Vita nemica rimasta: ").append(dtoC.getPuntiVitaNemicoRimasti());
        } else {
            risultatoCombattimento.append(" Hai subito ").append(dtoC.getDannoRicevuto()).append(" danni.");
            risultatoCombattimento.append(" Vita nemica rimasta: ").append(dtoC.getPuntiVitaNemicoRimasti());
        }

        if (!dtoC.isGiocatoreVivo()) {
            risultatoCombattimento.append("\nSei stato sconfitto...");
        }

        return risultatoCombattimento.toString();
    }

    public String formattaRicompense(DTORicompense dtoR) {
        StringBuilder ricompense = new StringBuilder();

        ricompense.append("Hai guadagnato ").append(dtoR.getOroGuadagnato()).append(" oro e ")
                .append(dtoR.getEsperienzaGuadagnata()).append(" exp.");

        if (dtoR.isPozioneOttenuta()) {
            ricompense.append(" Hai trovato una pozione!\n");
        }

        if (dtoR.isFrammentoOttenuto()) {
            ricompense.append(" Hai trovato un frammento!\n");
        }
        if (dtoR.isLivelloSalito()) {
            ricompense.append(" Sei salito di livello!\n");
        }

        return ricompense.toString();
    }

    public String messaggioMostroIncontrato(EntitaCombattente entita) {
        return "Ti si para davanti: " + entita.getNome() +
                " | PV " + entita.getPuntiVita() +
                " | Atk " + entita.getAttacco();
    }



    public String messaggioLocanda(boolean riuscito, int costo) {
        if (riuscito) {
            return "Hai riposato alla locanda per " + costo + " oro. Vita ripristinata!";
        } else {
            return "Non hai abbastanza oro per riposare.";
        }
    }

    public String messaggioPortaleChiuso() {
        return "Hai bisogno di 3 frammenti per accedere al portale.";

    }

    public String messaggioVittoria() {
        return "Hai sconfitto il boss";
    }

    public String messaggioSconfitta() {
        return "Sei stato sconfitto...";
    }
    public String messaggioSalvataggio() {
        return "Partita salvata.";
    }



}