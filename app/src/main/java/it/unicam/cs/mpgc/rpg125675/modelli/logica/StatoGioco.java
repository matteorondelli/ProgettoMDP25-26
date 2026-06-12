package it.unicam.cs.mpgc.rpg125675.modelli.logica;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOCombattimento;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTORicompense;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.luoghi.Locanda;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.*;
import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.Luoghi;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IGeneratoreMostri;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.ILocanda;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IPersonaggioGiocante;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IStatoGioco;


public class StatoGioco implements IStatoGioco {

    private final Giocatore giocatore;
    private Mostro mostroAttuale;
    private final ILocanda locanda;
    private final IGeneratoreMostri generatoreMostri;
    private final MotoreCombattimento motoreCombattimento;
    private final Boss boss;
    private Luoghi luogoAttuale;
    private boolean finePartita;
    private boolean giocatoreHaVinto;

    public StatoGioco(String nomeGiocatore) {
        this.giocatore = new Giocatore(nomeGiocatore);
        this.mostroAttuale = null;
        this.locanda = new Locanda();
        this.generatoreMostri = new GeneratoreMostri();
        this.motoreCombattimento = new MotoreCombattimento();
        this.boss = new Boss("Signore delle Tenebre");
        this.luogoAttuale = Luoghi.VILLAGGIO;
        this.finePartita = false;
        this.giocatoreHaVinto = false;
    }


    @Override
    public boolean spostati(Luoghi destinazione) {
        if (destinazione == Luoghi.PORTALE && !portaleAccessibile()){
            return false;
        }
        else {
            this.luogoAttuale = destinazione;
            return true;
        }
    }

    @Override
    public boolean portaleAccessibile() {
        return giocatore.haTuttiIFrammenti();
    }

    @Override
    public DTOCombattimento eseguiTurno(EntitaCombattente mostro) {
        return motoreCombattimento.eseguiTurno(giocatore, mostro);
    }

    @Override
    public DTORicompense elaboraRicompense(Mostro mostro) {
        return motoreCombattimento.assegnaRicompense(giocatore, mostro);
    }

    @Override
    public boolean riposati() {
        return locanda.riposaGiocatore(giocatore);
    }

    @Override
    public boolean usaPozione() {
        return giocatore.usaPozione();
    }

    @Override
    public Mostro generaMostro() {
        return generatoreMostri.generaMostro();
    }

    @Override
    public void terminaPartita(boolean vittoria) {
        this.finePartita = true;
        this.giocatoreHaVinto = vittoria;
    }

    @Override
    public String getStatisticheGiocatore(){
        return giocatore.getStatistiche();
    }

    @Override
    public Boss getBoss() {
        return boss;
    }

    @Override
    public int getCostoRiposo() {
        return locanda.getCostoRiposo();
    }

    @Override
    public Luoghi getLuogoAttuale(){
        return luogoAttuale;
    }

    @Override
    public boolean isFinePartita() {
        return finePartita;
    }

    @Override
    public boolean isGiocatoreHaVinto(){
        return giocatoreHaVinto;
    }

    @Override
    public Mostro getMostroAttuale() {
        return mostroAttuale;
    }

    @Override
    public void impostaMostro(Mostro m) {
        this.mostroAttuale = m;
    }

    @Override
    public IPersonaggioGiocante getGiocatore() {
        return giocatore;
    }

    @Override
    public void impostaLuogo(Luoghi luogo) {
        this.luogoAttuale = luogo;
    }
}
