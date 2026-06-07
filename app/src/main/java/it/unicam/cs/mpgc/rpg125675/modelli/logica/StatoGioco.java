package it.unicam.cs.mpgc.rpg125675.modelli.logica;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.locanda.Locanda;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.negozio.Negozio;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Boss;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.GeneratoreMostri;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Giocatore;
import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.Luoghi;

public class StatoGioco {

    private static final int HP_INIZIALI        = 100;
    private static final int ATTACCO_INIZIALE   = 18;
    private static final int ORO_INIZIALE       = 50;
    private static final int COSTO_RIPOSO       = 20;
    private static final int BOSS_HP            = 150;
    private static final int BOSS_ATTACCO       = 20;


    private final Giocatore giocatore;
    private final Negozio negozio;
    private final Locanda locanda;
    private final GeneratoreMostri generatoreMostri;
    private final MotoreCombattimento motoreCombattimento;
    private final Boss boss;

    private Luoghi luogoAttuale;
    private boolean finePartita;
    private boolean giocatoreHaVinto;

    public StatoGioco(String nomeGiocatore) {
        this.giocatore           = new Giocatore(nomeGiocatore, HP_INIZIALI, ATTACCO_INIZIALE, ORO_INIZIALE);
        this.negozio             = new Negozio();
        this.locanda             = new Locanda(COSTO_RIPOSO);
        this.generatoreMostri    = new GeneratoreMostri();
        this.motoreCombattimento = new MotoreCombattimento();
        this.boss                = new Boss("Signore delle Tenebre", BOSS_HP, BOSS_ATTACCO);
        this.luogoAttuale = Luoghi.VILLAGGIO;
        this.finePartita         = false;
        this.giocatoreHaVinto    = false;
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

    public void terminaPartita(boolean vittoria) {
        this.finePartita = true;
        this.giocatoreHaVinto = vittoria;
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }
    public Negozio getNegozio(){
        return negozio;
    }
    public Locanda getLocanda(){
        return locanda;
    }
    public GeneratoreMostri getGeneratoreMostri(){
        return generatoreMostri;
    }
    public MotoreCombattimento getMotoreCombattimento(){
        return motoreCombattimento;
    }
    public Boss getBoss(){
        return boss;
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