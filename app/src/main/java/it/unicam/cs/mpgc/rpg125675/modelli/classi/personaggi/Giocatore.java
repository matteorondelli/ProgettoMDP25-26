package it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti.OggettoBase;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti.Pozione;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.Attaccabile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Giocatore extends EntitaCombattente implements Attaccabile {

    private int livello;
    private int esperienza;
    private int esperienzaPerLivello;
    private int oro;
    private int frammenti;
    private final List<OggettoBase> inventario;

    private static final Random rand = new Random();
    private static final int HP_INIZIALI        = 100;
    private static final int ATTACCO_INIZIALE   = 10;
    private static final int ORO_INIZIALE       = 50;
    private static final double PROBABILITA_CRITICO = 0.3;
    private static final int MOLTIPLICATORE_CRITICO = 2;
    private static final int FRAMMENTI_NECESSARI = 3;
    private static final int ESPERIENZA_BASE = 50;
    private static final int PUNTIVITA_PER_LIVELLO = 10;
    private static final int ATTACCO_PER_LIVELLO = 5;


    public Giocatore(String nome) {
        super(nome, HP_INIZIALI, ATTACCO_INIZIALE);
        this.oro = ORO_INIZIALE;
        this.frammenti = 0;
        this.inventario = new ArrayList<>();
        this.livello = 1;
        this.esperienza = 0;
        this.esperienzaPerLivello = ESPERIENZA_BASE;
    }

    public String getStatistiche() {
        return getNome() +
                " | Lv " + livello +
                " | PV " + getPuntiVita() +
                "/" + getPuntiVitaMassimi() +
                " | Atk " + getAttacco() +
                " | Oro " + oro +
                " | Exp " + esperienza +
                "/" + esperienzaPerLivello +
                " | Frammenti " + frammenti +
                "/" + FRAMMENTI_NECESSARI +
                " | Pozioni " +inventario.size();


    }


    public void aggiungiEsperienza(int quantita){
        this.esperienza += quantita;
        controllaNuovoLivello();
    }

    private void controllaNuovoLivello(){
        if (esperienza >= esperienzaPerLivello){
            esperienza -= esperienzaPerLivello;
            nuovoLivello();
        }
    }

    private void nuovoLivello(){
        livello++;
        esperienzaPerLivello = livello * ESPERIENZA_BASE;
        aumentoAttacco(ATTACCO_PER_LIVELLO);
        aumentoPuntiVitaMassimi(PUNTIVITA_PER_LIVELLO);
        cura(getPuntiVitaMassimi());
    }

    public boolean eseguiAttaccoCritico() {
        return rand.nextDouble() < PROBABILITA_CRITICO;
    }

    public int getDannoCritico(){
        return getAttacco() * MOLTIPLICATORE_CRITICO;
    }

    public boolean usaPozione() {
        for (OggettoBase oggetto : inventario) {
            if (oggetto instanceof Pozione pozione) {
                cura(pozione.getQuantitaCura());
                inventario.remove(pozione);
                return true;
            }
        }
        return false;
    }

    public void aggiungiOggetto(OggettoBase oggetto) {
        inventario.add(oggetto);
    }

    public boolean spendiOro(int quantita) {
        if (oro < quantita) {
            return false;
        }
        else {
            oro -= quantita;
            return true;
        }
    }

    public void aggiungiOro(int quantita) {
        this.oro += quantita;
    }

    public void aggiungiFrammento() {
        if (frammenti < FRAMMENTI_NECESSARI) {
            frammenti++;
        }
    }

    public boolean haTuttiIFrammenti() {
        return frammenti >= FRAMMENTI_NECESSARI;
    }



    @Override
    public void cura(int quantita) {
        super.cura(quantita); // chiama il protected della superclasse
    }


    public void ripristinaVitaCompleta() {
        cura(getPuntiVitaMassimi());
    }

    public int getLivello() {
        return livello;
    }



}
