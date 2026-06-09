package it.unicam.cs.mpgc.rpg125675.modelli.logica;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.locanda.Locanda;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.*;
import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.Luoghi;

public class StatoGioco {

    private final Giocatore giocatore;
    private final Locanda locanda;
    private final GeneratoreMostri generatoreMostri;
    private final MotoreCombattimento motoreCombattimento;
    private final Boss boss;

    private Luoghi luogoAttuale;
    private boolean finePartita;
    private boolean giocatoreHaVinto;

    public StatoGioco(String nomeGiocatore) {
        this.giocatore = new Giocatore(nomeGiocatore);
        this.locanda = new Locanda();
        this.generatoreMostri = new GeneratoreMostri();
        this.motoreCombattimento = new MotoreCombattimento();
        this.boss = new Boss("Signore delle Tenebre");
        this.luogoAttuale = Luoghi.VILLAGGIO;
        this.finePartita = false;
        this.giocatoreHaVinto = false;
    }

            // AZIONI

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
    public String getNomeGiocatore(){
        return giocatore.getNome();
    }
    public int getPuntiVitaGiocatore(){
        return giocatore.getPuntiVita();
    }
    public int getPuntiVitaMassimiGiocatore(){
        return giocatore.getPuntiVitaMassimi();
    }
    public int getOroGiocatore(){
        return giocatore.getOro();
    }
    public int getLivelloGiocatore(){
        return giocatore.getLivello();
    }
    public int getEsperienzaGiocatore(){
        return giocatore.getEsperienza();
    }
    public int getEsperienzaPerLivelloGiocatore(){
        return giocatore.getEsperienzaPerLivello();
    }
    public int getFrammentiGiocatore(){
        return giocatore.getFrammenti();
    }
    public int getNumPozioniGiocatore(){
        return giocatore.getInventario().size();
    }
    public String getStatisticheGiocatore(){
        return giocatore.getStatistiche();
    }
    public String getNomeBoss(){
        return boss.getNome();
    }
    public int getPuntiVitaBoss(){
        return  boss.getPuntiVita();
    }
    public int getAttaccoBoss(){
        return  boss.getAttacco();
    }
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
}
