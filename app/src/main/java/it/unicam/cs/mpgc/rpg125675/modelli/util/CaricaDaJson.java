package it.unicam.cs.mpgc.rpg125675.modelli.util;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Boss;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Mostro;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.ICaricatoreMostri;
import com.google.gson.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Legge le definizioni dei mostri e del boss da un file JSON presente tra le risorse
 * dell'applicazione.
 *
 * Questa classe astrae il formato e la sorgente dei dati: per cambiare
 * sorgente è sufficiente fornire una diversa implementazione di {@link ICaricatoreMostri}, senza modificare
 * il resto della logica di gioco.
 */
public class CaricaDaJson implements ICaricatoreMostri {

    private static final String PERCORSO_MOSTRI = "/mostri/Mostri.json";

    /**
     * Contenitore interno delle statistiche di base comuni a mostri e boss
     *
     * @param nome nome dell'entità
     * @param puntiVita punti vita massimi
     * @param attacco valore di attacco
     * @param oro ricompensa in oro
     * @param esperienza ricompensa in esperienza
     */
    private record StatisticheBaseNemico(String nome, int puntiVita, int attacco, int oro, int esperienza) {}

    /**
     * Legge e analizza il file JSON delle risorse, restituendone la
     * struttura come {@link JsonObject}.
     *
     * @return l'oggetto JSON radice contenente le definizioni di mostri e boss
     * @throws IllegalStateException se il file delle risorse non viene trovato
     * @throws RuntimeException se si verifica un errore di I/O durante la lettura
     */
    private JsonObject leggiJson() {
        try (InputStream stream = CaricaDaJson.class.getResourceAsStream(PERCORSO_MOSTRI)) {
            if (stream == null) throw new IllegalStateException("File non trovato: " + PERCORSO_MOSTRI);
            InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException("Errore lettura file JSON", e);
        }
    }

    /**
     * Estrae le statistiche di base comuni da un oggetto JSON che descrive
     * un mostro o il boss.
     *
     * @param obj oggetto JSON
     * @return le statistiche di base estratte
     */
    private StatisticheBaseNemico estraiStatistiche(JsonObject obj) {
        return new StatisticheBaseNemico(
                obj.get("nome").getAsString(),
                obj.get("puntiVita").getAsInt(),
                obj.get("attacco").getAsInt(),
                obj.get("oro").getAsInt(),
                obj.get("esperienza").getAsInt()
        );
    }

    /**
     * {@inheritDoc}
     *
     * Legge l'array {@code "mostri"} dal file JSON e crea, per ciascun
     * elemento, un {@link Mostro} con i flag di drop di pozione e frammento
     * impostati entrambi a {@code false} (i template caricati da qui
     * rappresentano le statistiche base, mentre i drop effettivi vengono
     * decisi da {@code GeneratoreMostri}).
     */
    @Override
    public List<Mostro> caricaMostri() {
        JsonObject root = leggiJson();
        JsonArray arrayMostri = root.getAsJsonArray("mostri");

        List<Mostro> mostri = new ArrayList<>();
        for (JsonElement elemento : arrayMostri) {
            StatisticheBaseNemico stat = estraiStatistiche(elemento.getAsJsonObject());
            mostri.add(new Mostro(stat.nome(), stat.puntiVita(), stat.attacco(), stat.oro(), stat.esperienza(), false, false));
        }
        return mostri;
    }

    /**
     * {@inheritDoc}
     *
     * Legge l'oggetto {@code "boss"} dal file JSON e crea la corrispondente
     * istanza di {@link Boss}.
     */
    @Override
    public Boss caricaBoss() {
        JsonObject root = leggiJson();
        JsonObject objBoss = root.getAsJsonObject("boss");
        StatisticheBaseNemico stat = estraiStatistiche(objBoss);
        return new Boss(stat.nome(), stat.puntiVita(), stat.attacco(), stat.oro(), stat.esperienza());
    }
}