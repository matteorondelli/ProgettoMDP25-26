package it.unicam.cs.mpgc.rpg125675.modelli.logica;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.locanda.Locanda;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.*;
import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.Luoghi;



public class StatoGioco {

    private final Giocatore giocatore;
    private Mostro mostroAttuale;
    private final Locanda locanda;
    private final GeneratoreMostri generatoreMostri;
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



    public boolean spostati(Luoghi destinazione) {
        if (destinazione == Luoghi.PORTALE && !portaleAccessibile()){
            return false;
        }
        else {
            this.luogoAttuale = destinazione;
            return true;
        }
    }
    public boolean portaleAccessibile() {
        return giocatore.haTuttiIFrammenti();
    }
    public DTOCombattimento eseguiTurno(EntitaCombattente mostro) {
        return motoreCombattimento.eseguiTurno(giocatore, mostro);
    }
    public DTORicompense elaboraRicompense(Mostro mostro) {
        return motoreCombattimento.assegnaRicompense(giocatore, mostro);
    }
    public boolean riposati() {
        return locanda.riposaGiocatore(giocatore);
    }
    public boolean usaPozione() {
        return giocatore.usaPozione();
    }
    public Mostro generaMostro() {
        return generatoreMostri.generaMostro();
    }
    public void terminaPartita(boolean vittoria) {
        this.finePartita = true;
        this.giocatoreHaVinto = vittoria;
    }


    public String getStatisticheGiocatore(){
        return giocatore.getStatistiche();
    }

    public Boss getBoss() { return boss; }


    public int getCostoRiposo() {
        return locanda.getCostoRiposo();
    }
    public Luoghi getLuogoAttuale(){
        return luogoAttuale;
    }
    public boolean isFinePartita() {
        return finePartita;
    }
    public boolean isGiocatoreHaVinto(){
        return giocatoreHaVinto;
    }

    public Mostro getMostroAttuale() { return mostroAttuale; }
    public void impostaMostro(Mostro m) { this.mostroAttuale = m; }
}
