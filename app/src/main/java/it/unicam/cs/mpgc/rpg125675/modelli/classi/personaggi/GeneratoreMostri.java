package it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi;

import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.ICaricatoreMostri;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IGeneratoreMostri;
import it.unicam.cs.mpgc.rpg125675.modelli.util.CaricaDaJson;
import java.util.List;
import java.util.Random;

/**
 * Genera mostri casuali a partire da quelli estratti dalla sorgente,
 * a cui vengono aggiunti i flag di drop pozione e drop frammento.
 */
public class GeneratoreMostri implements IGeneratoreMostri {

    private static final Random rand = new Random();
    private static final double PROBABILITA_FRAMMENTO = 0.4;
    private static final double PROBABILITA_POZIONE = 0.4;
    private final List<Mostro> mostri;

    /**
     * Crea un nuovo generatore di mostri utilizzando {@link CaricaDaJson}
     * come caricatore di default per i template dei mostri.
     */
    public GeneratoreMostri() {
        this(new CaricaDaJson());
    }

    /**
     * Crea un nuovo generatore di mostri, caricando i template tramite il
     * caricatore specificato.
     *
     * @param caricatoreMostri caricatore da cui ottenere i template dei mostri
     */
    public GeneratoreMostri(ICaricatoreMostri caricatoreMostri) {
        this.mostri = caricatoreMostri.caricaMostri();
    }

    /**
     * {@inheritDoc}
     *
     * Seleziona casualmente un template tra quelli disponibili e crea una
     * nuova istanza alla quale vengono assegnati  i flag di drop di pozione e frammento.
     */
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
