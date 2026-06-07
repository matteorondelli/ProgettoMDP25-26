package it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti.Arma;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti.OggettoBase;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti.Pozione;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Giocatore extends EntitaCombattente {

    private int livello;
    private int esperienza;
    private int esperienzaPerLivello;
    private int oro;
    private int frammenti;
    private Arma armaEquipaggiata;
    private final List<OggettoBase> inventario;

    private static final Random rand = new Random();
    private static final double PROBABILITA_CRITICO = 0.3;
    private static final int MOLTIPLICATORE_CRITICO = 2;
    private static final int FRAMMENTI_NECESSARI = 3;
    private static final int ESPERIENZA_BASE = 100;
    private static final int PUNTIVITA_PER_LIVELLO = 10;
    private static final int ATTACCO_PER_LIVELLO = 3;


    public Giocatore(String nome, int puntiVitaMassimi, int attacco, int oro) {
        super(nome, puntiVitaMassimi, attacco);
        this.oro = oro;
        this.frammenti = 0;
        this.armaEquipaggiata = null;
        this.inventario = new ArrayList<>();
        this.livello = 1;
        this.esperienza = 0;
        this.esperienzaPerLivello = ESPERIENZA_BASE;
    }

    public void aggiungiEsperienza(int quantita){
        this.esperienza += quantita;
        controllaNuovoLivello();
    }

    private void controllaNuovoLivello(){
        while(esperienza >= esperienzaPerLivello){
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

    public void equipaggiaArma(Arma arma) {
        this.armaEquipaggiata = arma;
    }

    public int getAttaccoTotale() {
        if (armaEquipaggiata != null) {
            return getAttacco() + armaEquipaggiata.getBonusAttacco();
        }
        return getAttacco();
    }

    public boolean eseguiAttaccoCritico() {
        return rand.nextDouble() < PROBABILITA_CRITICO;
    }

    public int getDannoCritico(){
        return getAttaccoTotale() * MOLTIPLICATORE_CRITICO;
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

    public void ripristinaVitaCompleta() {
        cura(getPuntiVitaMassimi());
    }

    public int getOro() {
        return oro;
    }

    public int getFrammenti() {
        return frammenti;
    }

    public int getLivello() {
        return livello;
    }

    public int getEsperienza() {
        return esperienza;
    }

    public int getEsperienzaPerLivello() {
        return esperienzaPerLivello;
    }

    public Arma getArmaEquipaggiata() {
        return armaEquipaggiata;
    }

    public List<OggettoBase> getInventario() {
        return new ArrayList<>(inventario);
    }
}
