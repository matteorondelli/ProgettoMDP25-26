package it.unicam.cs.mpgc.rpg125675.controlli;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti.OggettoBase;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Mostro;
import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.Luoghi;
import it.unicam.cs.mpgc.rpg125675.modelli.logica.RisultatoCombattimento;
import it.unicam.cs.mpgc.rpg125675.modelli.logica.StatoGioco;

public class ServizioGioco {

    private final StatoGioco stato;

    public ServizioGioco(StatoGioco stato) {
        this.stato = stato;
    }

    public boolean spostaVerso(Luoghi destinazione) {
        return stato.spostati(destinazione);
    }

    public boolean riposa() {
        return stato.getLocanda().riposaGiocatore(stato.getGiocatore());
    }

    public boolean acquista(int indice) {
        var oggetti = stato.getNegozio().getNegozio();
        if (indice < 0 || indice >= oggetti.size()) return false;
        return stato.getNegozio().acquista(stato.getGiocatore(), oggetti.get(indice));
    }

    public Mostro generaNemico() {
        return stato.getGeneratoreMostri().generaMostro();
    }

    public RisultatoCombattimento eseguiTurno(Mostro nemico) {
        return stato.getMotoreCombattimento().eseguiTurno(stato.getGiocatore(), nemico);
    }

    public boolean usaPozione() {
        return stato.getGiocatore().usaPozione();
    }

    public StatoGioco getStato() {
        return stato;
    }
}
