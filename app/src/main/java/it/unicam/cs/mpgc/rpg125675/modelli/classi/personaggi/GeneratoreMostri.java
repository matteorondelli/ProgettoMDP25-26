package it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi;

import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.ICaricatoreMostri;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IGeneratoreMostri;
import it.unicam.cs.mpgc.rpg125675.modelli.util.CaricaDaJson;

import java.util.List;
import java.util.Random;

public class GeneratoreMostri implements IGeneratoreMostri {

    private static final Random rand = new Random();
    private static final double PROBABILITA_FRAMMENTO = 0.4;
    private static final double PROBABILITA_POZIONE = 0.4;
    private final List<Mostro> mostri;

    public GeneratoreMostri() {
        this(new CaricaDaJson());
    }

    public GeneratoreMostri(ICaricatoreMostri caricatoreMostri) {
        this.mostri = caricatoreMostri.caricaMostri();
    }

    @Override
    public Mostro generaMostro() {
        Mostro generato = mostri.get(rand.nextInt(mostri.size()));
        boolean droppaFrammento = rand.nextDouble() < PROBABILITA_FRAMMENTO;
        boolean droppaPozione = rand.nextDouble() < PROBABILITA_POZIONE;
        return new Mostro(generato.getNome(), generato.getPuntiVitaMassimi(),
                generato.getAttacco(), generato.getRicompensaOro(),
                generato.getRicompensaEsperienza(),droppaPozione,
                droppaFrammento);
    }
}
