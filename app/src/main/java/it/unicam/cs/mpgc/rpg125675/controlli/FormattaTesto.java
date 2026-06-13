package it.unicam.cs.mpgc.rpg125675.controlli;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.EntitaCombattente;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOCombattimento;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTORicompense;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IStatoGiocoLettura;

/**
 * Responsabile della formattazione testuale dei messaggi e dei
 * menu mostrati all'utente.
 *
 * Dipende esclusivamente da {@link IStatoGiocoLettura}, in modo da poter
 * leggere le informazioni necessarie alla formattazione senza poter modificare lo stato di gioco.
 */
public class FormattaTesto {
    private final IStatoGiocoLettura statoGioco;

    /**
     * Crea un nuovo gestore dell'interfaccia testuale.
     *
     * @param statoGioco vista in sola lettura dello stato di gioco da cui
     *                    ottenere le informazioni da mostrare
     */
    public FormattaTesto(IStatoGiocoLettura statoGioco) {
        this.statoGioco = statoGioco;
    }

    /**
     * Restituisce la rappresentazione testuale aggiornata delle statistiche
     * del giocatore.
     *
     * @return statistiche del giocatore formattate come stringa
     */
    public String aggiornaStatistiche() {
        return statoGioco.getStatisticheGiocatore();
    }

    /**
     * Costruisce il testo del menu di esplorazione appropriato per il
     * luogo in cui si trova attualmente il giocatore.
     *
     * @return testo del menu di esplorazione per il luogo corrente
     */
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

    /**
     * Costruisce il testo del menu mostrato durante un combattimento.
     *
     * @return testo del menu di combattimento
     */
    public String menuCombattimento() {
        return "COMBATTIMENTO\n\n 1. Attacca\n 2. Usa pozione\n ";
    }

    /**
     * Costruisce il testo descrittivo dell'esito di un turno di combattimento
     *
     * @param dtoC esito del turno di combattimento da formattare
     * @return descrizione testuale dell'esito del turno
     */
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

    /**
     * Costruisce il testo descrittivo delle ricompense ottenute dal giocatore.
     *
     * @param dtoR dettaglio delle ricompense da formattare
     * @return descrizione testuale delle ricompense ottenute
     */
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

    /**
     * Costruisce il testo del messaggio mostrato quando il giocatore
     * incontra un'entità nemica.
     *
     * @param entita entità nemica incontrata (mostro o boss)
     * @return descrizione testuale dell'entità incontrata
     */
    public String messaggioMostroIncontrato(EntitaCombattente entita) {
        return "Ti si para davanti: " + entita.getNome() +
                " | PV " + entita.getPuntiVita() +
                " | Atk " + entita.getAttacco();
    }

    /**
     * Costruisce il testo del messaggio relativo al tentativo di riposo alla locanda.
     *
     * @param riuscito {@code true} se il riposo è avvenuto con successo
     * @param costo costo in oro del riposo
     * @return descrizione testuale dell'esito del riposo
     */
    public String messaggioLocanda(boolean riuscito, int costo) {
        if (riuscito) {
            return "Hai riposato alla locanda per " + costo + " oro. Vita ripristinata!";
        } else {
            return "Non hai abbastanza oro per riposare.";
        }
    }

    /**
     * Costruisce il testo del messaggio mostrato quando il giocatore tenta
     * di accedere al portale senza possedere tutti i frammenti necessari.
     *
     * @return descrizione testuale dell'accesso negato al portale
     */
    public String messaggioPortaleChiuso() {
        return "Hai bisogno di 3 frammenti per accedere al portale.";

    }

    /**
     * Costruisce il testo del messaggio di vittoria finale, mostrato dopo
     * la sconfitta del boss.
     *
     * @return messaggio di vittoria
     */
    public String messaggioVittoria() {
        return "COMPLIMENTI!!! Hai sconfitto il boss";
    }

    /**
     * Costruisce il testo del messaggio di sconfitta finale, mostrato dopo
     * la sconfitta del giocatore.
     *
     * @return messaggio di sconfitta
     */
    public String messaggioSconfitta() {
        return "Sei stato sconfitto";
    }

    /**
     * Costruisce il testo del messaggio di conferma del salvataggio della partita.
     *
     * @return messaggio di conferma del salvataggio
     */
    public String messaggioSalvataggio() {
        return "Partita salvata.";
    }



}