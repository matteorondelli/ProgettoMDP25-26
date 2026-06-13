package it.unicam.cs.mpgc.rpg125675.controlli;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Mostro;
import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.Fasi;
import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.Luoghi;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOCombattimento;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTORicompense;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IGestoreSalvataggio;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IStatoGioco;
import it.unicam.cs.mpgc.rpg125675.modelli.util.ConvertitoreSalvataggio;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOSalvataggio;

public class GestoreComandi {

    private final FormattaTesto formattaTesto;
    private final IStatoGioco statoGioco;
    private Fasi faseCorrente;
    private final IGestoreSalvataggio gestoreSalvataggio;

    public GestoreComandi(FormattaTesto formattaTesto, IStatoGioco statoGioco,
                          Fasi faseIniziale, IGestoreSalvataggio gestoreSalvataggio) {
        this.formattaTesto = formattaTesto;
        this.statoGioco = statoGioco;
        this.faseCorrente = faseIniziale;
        this.gestoreSalvataggio = gestoreSalvataggio;
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
            case "3" -> salvaPartita();
            default  -> "Scelta non valida.\n" + formattaTesto.menuEsplorazione();
        };
    }

    private String salvaPartita() {
        DTOSalvataggio dto = ConvertitoreSalvataggio.daStato(statoGioco);
        gestoreSalvataggio.salva(dto);
        return formattaTesto.messaggioSalvataggio() + "\n\n" + formattaTesto.menuEsplorazione();
    }

    private String vaiInForesta() {
        statoGioco.spostati(Luoghi.FORESTA);
        return formattaTesto.menuEsplorazione();
    }

    private String riposatiAllaLocanda() {
        boolean riuscito = statoGioco.riposati();
        return formattaTesto.messaggioLocanda(riuscito, statoGioco.getCostoRiposo())
                + "\n\n" + formattaTesto.menuEsplorazione();
    }



    private String elaboraForesta(String input) {
        return switch (input.trim()) {
            case "1" -> caccia();
            case "2" -> provaAccessoPortale();
            case "3" -> tornaAlVillaggio();
            default  -> "Scelta non valida.\n" + formattaTesto.menuEsplorazione();
        };
    }

    private String caccia() {
        Mostro mostro = statoGioco.generaMostro();
        statoGioco.impostaMostro(mostro);
        faseCorrente = Fasi.COMBATTIMENTO;
        return formattaTesto.messaggioMostroIncontrato(mostro)
                + "\n\n" + formattaTesto.menuCombattimento();
    }

    private String provaAccessoPortale() {
        boolean spostato = statoGioco.spostati(Luoghi.PORTALE);
        if (spostato) return formattaTesto.menuEsplorazione();
        return formattaTesto.messaggioPortaleChiuso() + "\n\n"
                + formattaTesto.menuEsplorazione();
    }

    private String tornaAlVillaggio() {
        statoGioco.spostati(Luoghi.VILLAGGIO);
        return formattaTesto.menuEsplorazione();
    }




    private String elaboraPortale(String input) {
        return switch (input.trim()) {
            case "1" -> combattimentoBoss();
            case "2" -> tornaAlVillaggio();
            default  -> "Scelta non valida.\n\n" + formattaTesto.menuEsplorazione();
        };
    }

    private String combattimentoBoss() {
        faseCorrente = Fasi.COMBATTIMENTO_BOSS;
        return formattaTesto.messaggioMostroIncontrato(statoGioco.getBoss())
                + "\n\n" + formattaTesto.menuCombattimento();
    }




    private String elaboraCombattimento(String input) {
        return switch (input.trim()) {
            case "1" -> attacca();
            case "2" -> usaPozione();
            default  -> "Scelta non valida.\n" + formattaTesto.menuCombattimento();
        };
    }

    private String attacca() {
        DTOCombattimento dto = statoGioco.eseguiTurno(statoGioco.getMostroAttuale());
        if (!dto.isGiocatoreVivo()) return gestisciSconfitta(dto);
        if (!dto.isNemicoVivo()) return gestisciVittoria(dto);
        return formattaTesto.formattaCombattimento(dto) + "\n\n" + formattaTesto.menuCombattimento();
    }
    private String gestisciSconfitta(DTOCombattimento dto) {
        statoGioco.terminaPartita(false);
        faseCorrente = Fasi.FINE_PARTITA;
        return formattaTesto.formattaCombattimento(dto);
    }
    private String gestisciVittoria(DTOCombattimento dto) {
        DTORicompense ricompense = statoGioco.elaboraRicompense(statoGioco.getMostroAttuale());
        faseCorrente = Fasi.ESPLORAZIONE;
        return formattaTesto.formattaCombattimento(dto)
                + "\n" + formattaTesto.formattaRicompense(ricompense)
                + "\n\n" + formattaTesto.menuEsplorazione();
    }

    private String usaPozione() {
        boolean riuscito = statoGioco.usaPozione();
        if (riuscito) return formattaTesto.aggiornaStatistiche()
                + "\n\n" + formattaTesto.menuCombattimento();
        return "Non hai pozioni disponibili.\n\n" + formattaTesto.menuCombattimento();
    }



    private String elaboraCombattimentoBoss(String input) {
        return switch (input.trim()) {
            case "1" -> attaccaBoss();
            case "2" -> usaPozione();
            default  -> "Scelta non valida.\n" + formattaTesto.menuCombattimento();
        };
    }

    private String attaccaBoss() {
        DTOCombattimento dto = statoGioco.eseguiTurno(statoGioco.getBoss());
        if (!dto.isGiocatoreVivo()) return gestisciSconfitta(dto);
        if (!dto.isNemicoVivo()) return gestisciVittoriaBoss(dto);
        return formattaTesto.formattaCombattimento(dto) + "\n\n" + formattaTesto.menuCombattimento();
    }

    private String gestisciVittoriaBoss(DTOCombattimento dto){
        DTORicompense ricompenseFinale = statoGioco.elaboraRicompense(statoGioco.getBoss());
        statoGioco.terminaPartita(true);
        faseCorrente = Fasi.FINE_PARTITA;
        return formattaTesto.formattaCombattimento(dto)
            + "\n" + formattaTesto.formattaRicompense(ricompenseFinale) // Mostra i premi del Boss!
            + "\n\n" + formattaTesto.messaggioVittoria();
}


    private String elaboraFinale() {
        if (statoGioco.isGiocatoreHaVinto()) {
            return formattaTesto.messaggioVittoria();
        }
        return formattaTesto.messaggioSconfitta();
    }
}
