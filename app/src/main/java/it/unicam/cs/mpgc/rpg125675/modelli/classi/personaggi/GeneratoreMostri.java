package it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.util.CaricaJson;

import java.util.List;
import java.util.Random;

public class GeneratoreMostri {

    private static final Random rand = new Random();
    private static final double PROBABILITA_FRAMMENTO = 0.4;


    private final List<Mostro> mostri;

    public GeneratoreMostri() {
        this.mostri = CaricaJson.caricaMostri();
    }

    public Mostro generaMostro() {
        Mostro scelto = mostri.get(rand.nextInt(mostri.size()));
        boolean droppaFrammento = rand.nextDouble() < PROBABILITA_FRAMMENTO;
        return new Mostro(scelto.getNome(), scelto.getPuntiVitaMassimi(),
                scelto.getAttacco(), scelto.getRicompensaOro(),
                scelto.getRicompensaEsperienza(), droppaFrammento);
    }
}
