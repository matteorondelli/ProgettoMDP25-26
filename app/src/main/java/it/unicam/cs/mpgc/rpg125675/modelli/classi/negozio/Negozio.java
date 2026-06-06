package it.unicam.cs.mpgc.rpg125675.modelli.classi.negozio;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti.OggettoBase;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.util.CaricaJson;

import java.util.List;

public class Negozio {

    private final List<OggettoBase> negozio;

    public Negozio() {
        this.negozio = CaricaJson.caricaNegozio();
    }

    public List<OggettoBase> getNegozio() {
        return negozio;
    }
}
