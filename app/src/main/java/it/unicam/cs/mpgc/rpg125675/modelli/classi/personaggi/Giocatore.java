package it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti.OggettoBase;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti.Pozione;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IAttaccante;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IPersonaggioGiocante;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOSalvataggio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Rappresenta il personaggio controllato dal giocatore.
 */
public class Giocatore extends EntitaCombattente implements IAttaccante, IPersonaggioGiocante {

    private int livello;
    private int esperienza;
    private int esperienzaPerLivello;
    private int oro;
    private int frammenti;
    private final List<OggettoBase> inventario;

    private static final Random rand = new Random();
    private static final int HP_INIZIALI = 100;
    private static final int ATTACCO_INIZIALE = 10;
    private static final int ORO_INIZIALE = 50;
    private static final double PROBABILITA_CRITICO = 0.3;
    private static final int MOLTIPLICATORE_CRITICO = 2;
    private static final int FRAMMENTI_NECESSARI = 3;
    private static final int ESPERIENZA_BASE = 50;
    private static final int PUNTIVITA_PER_LIVELLO = 10;
    private static final int ATTACCO_PER_LIVELLO = 5;

    /**
     * Crea un nuovo giocatore con il nome specificato e le statistiche
     * iniziali di default.
     *
     * @param nome nome del personaggio
     */
    public Giocatore(String nome) {
        super(nome, HP_INIZIALI, ATTACCO_INIZIALE);
        this.oro = ORO_INIZIALE;
        this.frammenti = 0;
        this.inventario = new ArrayList<>();
        this.livello = 1;
        this.esperienza = 0;
        this.esperienzaPerLivello = ESPERIENZA_BASE;
    }

    /**
     * Restituisce una stringa delle statistiche del giocatore.
     *
     * @return statistiche del giocatore formattate come stringa
     */
    public String getStatistiche() {
        return " " + getNome() +
                " | Lv " + livello +
                " | PV " + getPuntiVita() +
                "/" + getPuntiVitaMassimi() +
                " | Atk " + getAttacco() +
                " | Oro " + oro +
                " | Exp " + esperienza +
                "/" + esperienzaPerLivello +
                " | Frammenti " + frammenti +
                "/" + FRAMMENTI_NECESSARI +
                " | Pozioni " + inventario.size();
    }

    /**
     * {@inheritDoc}
     *
     * Aggiunge all'inventario una pozione standard con quantità di cura fissa.
     */
    @Override
    public void aggiungiPozione() {
        inventario.add(new Pozione("Pozione", 0, 30));
    }

    /**
     * {@inheritDoc}
     *
     * Aggiunge esperienza e controlla se il giocatore sale di livello
     */
    @Override
    public void aggiungiEsperienza(int quantita) {
        this.esperienza += quantita;
        controllaNuovoLivello();
    }

    /**
     * Verifica se con l'esperienza guadagnata il giocatore sale di livello
     */
    private void controllaNuovoLivello() {
        if (esperienza >= esperienzaPerLivello) {
            esperienza -= esperienzaPerLivello;
            nuovoLivello();
        }
    }

    /**
     * Avanza di livello il giocatore. Aumenta la soglia di esperienza per il prossimo livello,
     * i punti vita massimi, l'attacco e ripristina la vita del giocatore al massimo
     */
    private void nuovoLivello() {
        livello++;
        esperienzaPerLivello = livello * ESPERIENZA_BASE;
        aumentoAttacco(ATTACCO_PER_LIVELLO);
        aumentoPuntiVitaMassimi(PUNTIVITA_PER_LIVELLO);
        cura(getPuntiVitaMassimi());
    }

    /**
     * {@inheritDoc}
     *
     * Il colpo critico ha una probabilità fissa già stabilita.
     */
    @Override
    public boolean eseguiAttaccoCritico() {
        return rand.nextDouble() < PROBABILITA_CRITICO;
    }

    /**
     * {@inheritDoc}
     *
     * Il danno critico è pari all'attacco corrente moltiplicato per
     * {@link #MOLTIPLICATORE_CRITICO}.
     */
    @Override
    public int getDannoCritico() {
        return getAttacco() * MOLTIPLICATORE_CRITICO;
    }

    /**
     * Utilizza la prima pozione disponibile nell'inventario, curando il
     * giocatore della quantità corrispondente e rimuovendo la pozione
     * dall'inventario.
     *
     * @return {@code true} se una pozione è stata trovata e utilizzata,
     *         {@code false} se l'inventario non contiene pozioni
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void aggiungiOggetto(OggettoBase oggetto) {
        inventario.add(oggetto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean spendiOro(int quantita) {
        if (oro < quantita) return false;
        oro -= quantita;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void aggiungiOro(int quantita) {
        this.oro += quantita;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Il numero di frammenti non supera mai {@link #FRAMMENTI_NECESSARI}.
     */
    @Override
    public void aggiungiFrammento() {
        if (frammenti < FRAMMENTI_NECESSARI) frammenti++;
    }

    /**
     * Verifica se il giocatore possiede tutti i frammenti necessari per
     * accedere al portale.
     *
     * @return {@code true} se il numero di frammenti posseduti è sufficiente, {@code false} altrimenti
     */
    public boolean haTuttiIFrammenti() {
        return frammenti >= FRAMMENTI_NECESSARI;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ripristinaVitaCompleta() {
        cura(getPuntiVitaMassimi());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLivello() {
        return livello;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEsperienza() {
        return esperienza;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEsperienzaPerLivello() {
        return esperienzaPerLivello;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOro() {
        return oro;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFrammenti() {
        return frammenti;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getInventarioPozioni() {
        List<Integer> cure = new ArrayList<>();
        for (OggettoBase oggetto : inventario) {
            if (oggetto instanceof Pozione pozione) {
                cure.add(pozione.getQuantitaCura());
            }
        }
        return cure;
    }

    /**
     * {@inheritDoc}
     *
     * Ripristina il giocatore a partire dai dati contenuti nel DTO di salvataggio.
     */
    @Override
    public void ripristinaDaSalvataggio(DTOSalvataggio dto) {
        this.livello = dto.getLivello();
        this.esperienza = dto.getEsperienza();
        this.esperienzaPerLivello = dto.getEsperienzaPerLivello();
        this.oro = dto.getOro();
        this.frammenti = dto.getFrammenti();

        impostaPuntiVita(dto.getPuntiVitaMassimi(), dto.getPuntiVita());
        impostaAttacco(dto.getAttacco());
        this.inventario.clear();
        for (int cura : dto.getPozioniCura()) {
            this.inventario.add(new Pozione("Pozione", 0, cura));
        }
    }
}
