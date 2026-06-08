package it.unicam.cs.mpgc.rpg125675.modelli.classi.negozio;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti.Arma;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti.OggettoBase;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Giocatore;
import it.unicam.cs.mpgc.rpg125675.modelli.util.CaricaDaJson;

import java.util.ArrayList;
import java.util.List;

public class Negozio {

    private final List<OggettoBase> negozio;

    public Negozio() {
        this.negozio = CaricaDaJson.caricaNegozio();
    }

    public boolean acquista(Giocatore giocatore, OggettoBase oggetto) {
        if (!negozio.contains(oggetto)) return false;
        if (!giocatore.spendiOro(oggetto.getPrezzo())) return false;
        giocatore.aggiungiOggetto(oggetto);
        if (oggetto instanceof Arma arma) {
            giocatore.equipaggiaArma(arma);
        }
        return true;
    }

    public List<OggettoBase> getNegozio() {
        return new ArrayList<>(negozio);
    }
}
