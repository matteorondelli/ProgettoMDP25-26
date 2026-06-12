package it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO;

import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.Luoghi;

import java.util.List;
import java.util.Objects;

/**
 * DTO immutabile che trasporta i dati di salvataggio del giocatore,
 * senza esporre logica di dominio ai livelli superiori.
 */
public class DTOSalvataggio {

    private final String nome;
    private final int livello;
    private final int esperienza;
    private final int esperienzaPerLivello;
    private final int puntiVita;
    private final int puntiVitaMassimi;
    private final int attacco;
    private final int oro;
    private final int frammenti;
    private final Luoghi luogoAttuale;
    private final List<Integer> pozioniCura;

    public DTOSalvataggio(String nome, int livello, int esperienza, int esperienzaPerLivello,
                          int puntiVita, int puntiVitaMassimi, int attacco, int oro,
                          int frammenti, Luoghi luogoAttuale, List<Integer> pozioniCura) {
        this.nome = nome;
        this.livello = livello;
        this.esperienza = esperienza;
        this.esperienzaPerLivello = esperienzaPerLivello;
        this.puntiVita = puntiVita;
        this.puntiVitaMassimi = puntiVitaMassimi;
        this.attacco = attacco;
        this.oro = oro;
        this.frammenti = frammenti;
        this.luogoAttuale = luogoAttuale;
        this.pozioniCura = pozioniCura;
    }

    public String getNome() {
        return nome;
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

    public int getPuntiVita() {
        return puntiVita;
    }

    public int getPuntiVitaMassimi() {
        return puntiVitaMassimi;
    }

    public int getAttacco() {
        return attacco;
    }

    public int getOro() {
        return oro;
    }

    public int getFrammenti() {
        return frammenti;
    }

    public Luoghi getLuogoAttuale() {
        return luogoAttuale;
    }

    public List<Integer> getPozioniCura() {
        return pozioniCura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DTOSalvataggio that)) return false;
        return livello == that.livello &&
                esperienza == that.esperienza &&
                esperienzaPerLivello == that.esperienzaPerLivello &&
                puntiVita == that.puntiVita &&
                puntiVitaMassimi == that.puntiVitaMassimi &&
                attacco == that.attacco &&
                oro == that.oro &&
                frammenti == that.frammenti &&
                Objects.equals(nome, that.nome) &&
                luogoAttuale == that.luogoAttuale &&
                Objects.equals(pozioniCura, that.pozioniCura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, livello, esperienza, esperienzaPerLivello,
                puntiVita, puntiVitaMassimi, attacco, oro, frammenti,
                luogoAttuale, pozioniCura);
    }

    @Override
    public String toString() {
        return "DTOSalvataggio{" +
                "nome='" + nome + '\'' +
                ", livello=" + livello +
                ", esperienza=" + esperienza +
                ", esperienzaPerLivello=" + esperienzaPerLivello +
                ", puntiVita=" + puntiVita +
                ", puntiVitaMassimi=" + puntiVitaMassimi +
                ", attacco=" + attacco +
                ", oro=" + oro +
                ", frammenti=" + frammenti +
                ", luogoAttuale=" + luogoAttuale +
                ", pozioniCura=" + pozioniCura +
                '}';
    }
}
