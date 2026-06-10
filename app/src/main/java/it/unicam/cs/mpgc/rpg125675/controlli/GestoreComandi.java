package it.unicam.cs.mpgc.rpg125675.controlli;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Mostro;
import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.Luoghi;
import it.unicam.cs.mpgc.rpg125675.modelli.logica.DTOCombattimento;
import it.unicam.cs.mpgc.rpg125675.modelli.logica.DTORicompense;
import it.unicam.cs.mpgc.rpg125675.modelli.logica.StatoGioco;

public class GestoreComandi {

    private GestoreUI gestoreUI;
    private StatoGioco statoGioco;
    private Fasi faseCorrente;

    public GestoreComandi(GestoreUI gestoreUI, StatoGioco statoGioco, Fasi faseIniziale) {
        this.gestoreUI = gestoreUI;
        this.statoGioco = statoGioco;
        this.faseCorrente = faseIniziale;
    }


    public String elabora(String input, Fasi fase) {
        return switch (fase) {
            case ESPLORAZIONE -> esplorazione(input);
            case COMBATTIMENTO -> elaboraCombattimento(input);
            case COMBATTIMENTO_BOSS -> elaboraCombattimentoBoss(input);
            case FINE_PARTITA -> elaboraFinale();
        };
    }

    public Fasi getFaseCorrente() {
        return faseCorrente;
    }

    private String esplorazione(String input) {
        return switch (statoGioco.getLuogoAttuale()) {
            case VILLAGGIO -> elaboraVillaggio(input);
            case FORESTA   -> elaboraForesta(input);
            case PORTALE   -> elaboraPortale(input);
        };
    }

    private String elaboraVillaggio(String input) {
        return switch (input.trim()) {
            case "1" -> vaiInForesta();
            case "2" -> riposatiAllaLocanda();
            default  -> "Scelta non valida.\n" + gestoreUI.menuEsplorazione();
        };
    }

    private String vaiInForesta() {
        statoGioco.spostati(Luoghi.FORESTA);
        return gestoreUI.menuEsplorazione();
    }

    private String riposatiAllaLocanda() {
        boolean riuscito = statoGioco.riposati();
        return gestoreUI.messaggioLocanda(riuscito, statoGioco.getCostoRiposo())
                + "\n\n" + gestoreUI.menuEsplorazione();
    }



    private String elaboraForesta(String input) {
        return switch (input.trim()) {
            case "1" -> caccia();
            case "2" -> provaAccessoPortale();
            case "3" -> tornaAlVillaggio();
            default  -> "Scelta non valida.\n" + gestoreUI.menuEsplorazione();
        };
    }

    private String caccia() {
        Mostro mostro = statoGioco.generaMostro();
        statoGioco.impostaMostro(mostro);
        faseCorrente = Fasi.COMBATTIMENTO;
        return gestoreUI.messaggioMostroIncontrato(mostro)
                + "\n\n" + gestoreUI.menuCombattimento();
    }

    private String provaAccessoPortale() {
        boolean spostato = statoGioco.spostati(Luoghi.PORTALE);
        if (spostato) return gestoreUI.menuEsplorazione();
        return gestoreUI.messaggioPortaleChiuso() + "\n\n"
                + gestoreUI.menuEsplorazione();
    }

    private String tornaAlVillaggio() {
        statoGioco.spostati(Luoghi.VILLAGGIO);
        return gestoreUI.menuEsplorazione();
    }




    private String elaboraPortale(String input) {
        return switch (input.trim()) {
            case "1" -> combattimentoBoss();
            case "2" -> tornaAlVillaggio();
            default  -> "Scelta non valida.\n\n" + gestoreUI.menuEsplorazione();
        };
    }

    private String combattimentoBoss() {
        faseCorrente = Fasi.COMBATTIMENTO_BOSS;
        return gestoreUI.messaggioMostroIncontrato(statoGioco.getBoss())
                + "\n\n" + gestoreUI.menuCombattimento();
    }




    private String elaboraCombattimento(String input) {
        return switch (input.trim()) {
            case "1" -> attacca();
            case "2" -> usaPozione();
            default  -> "Scelta non valida.\n" + gestoreUI.menuCombattimento();
        };
    }

    private String attacca() {
        DTOCombattimento dto = statoGioco.eseguiTurno(statoGioco.getMostroAttuale());
        if (!dto.isGiocatoreVivo()) return gestisciSconfitta(dto);
        if (!dto.isNemicoVivo()) return gestisciVittoria(dto);
        return gestoreUI.formattaCombattimento(dto) + "\n\n" + gestoreUI.menuCombattimento();
    }
    private String gestisciSconfitta(DTOCombattimento dto) {
        statoGioco.terminaPartita(false);
        faseCorrente = Fasi.FINE_PARTITA;
        return gestoreUI.formattaCombattimento(dto);
    }
    private String gestisciVittoria(DTOCombattimento dto) {
        DTORicompense ricompense = statoGioco.elaboraRicompense(statoGioco.getMostroAttuale());
        faseCorrente = Fasi.ESPLORAZIONE;
        return gestoreUI.formattaCombattimento(dto)
                + "\n" + gestoreUI.formattaRicompense(ricompense)
                + "\n\n" + gestoreUI.menuEsplorazione();
    }

    private String usaPozione() {
        boolean riuscito = statoGioco.usaPozione();
        if (riuscito) return gestoreUI.aggiornaStatistiche()
                + "\n\n" + gestoreUI.menuCombattimento();
        return "Non hai pozioni disponibili.\n\n" + gestoreUI.menuCombattimento();
    }



    private String elaboraCombattimentoBoss(String input) {
        return switch (input.trim()) {
            case "1" -> attaccaBoss();
            case "2" -> usaPozione();
            default  -> "Scelta non valida.\n" + gestoreUI.menuCombattimento();
        };
    }

    private String attaccaBoss() {
        DTOCombattimento dto = statoGioco.eseguiTurno(statoGioco.getBoss());
        if (!dto.isGiocatoreVivo()) return gestisciSconfitta(dto);
        if (!dto.isNemicoVivo()) return gestisciVittoriaBoss(dto);
        return gestoreUI.formattaCombattimento(dto) + "\n\n" + gestoreUI.menuCombattimento();
    }

    private String gestisciVittoriaBoss(DTOCombattimento dto) {
        statoGioco.terminaPartita(true);
        faseCorrente = Fasi.FINE_PARTITA;
        return gestoreUI.formattaCombattimento(dto) + elaboraFinale();
    }



    private String elaboraFinale() {
        if (statoGioco.isGiocatoreHaVinto()) {
            return gestoreUI.messaggioVittoria();
        }
        return gestoreUI.messaggioSconfitta();
    }
}
