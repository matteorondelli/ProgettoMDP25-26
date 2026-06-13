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

public class CaricaDaJson implements ICaricatoreMostri {

    private static final String PERCORSO_MOSTRI = "/mostri/Mostri.json";

    // 1. Il contenitore dei dati in comune (punti vita, attacco, premi in oro ed exp)
    private record StatisticheBaseNemico(String nome, int puntiVita, int attacco, int oro, int esperienza) {}

    private JsonObject leggiJson() {
        try (InputStream stream = CaricaDaJson.class.getResourceAsStream(PERCORSO_MOSTRI)) {
            if (stream == null) throw new IllegalStateException("File non trovato: " + PERCORSO_MOSTRI);
            InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException("Errore lettura file JSON", e);
        }
    }

    // 2. L'UNICO METODO che legge le chiavi dal JSON (Zero duplicazione di stringhe e metodi get!)
    private StatisticheBaseNemico estraiStatistiche(JsonObject obj) {
        return new StatisticheBaseNemico(
                obj.get("nome").getAsString(),
                obj.get("puntiVita").getAsInt(),
                obj.get("attacco").getAsInt(),
                obj.get("oro").getAsInt(),
                obj.get("esperienza").getAsInt()
        );
    }

    @Override
    public List<Mostro> caricaMostri() {
        JsonObject root = leggiJson();
        JsonArray arrayMostri = root.getAsJsonArray("mostri");

        List<Mostro> mostri = new ArrayList<>();
        for (JsonElement elemento : arrayMostri) {
            // Estrae le statistiche usando il metodo comune
            StatisticheBaseNemico stat = estraiStatistiche(elemento.getAsJsonObject());

            // Fabbrica un Mostro aggiungendo i flag di drop (false, false)
            mostri.add(new Mostro(stat.nome(), stat.puntiVita(), stat.attacco(), stat.oro(), stat.esperienza(), false, false));
        }
        return mostri;
    }

    @Override
    public Boss caricaBoss() {
        JsonObject root = leggiJson();
        JsonObject objBoss = root.getAsJsonObject("boss");

        // Estrae le statistiche usando lo stesso identico metodo comune!
        StatisticheBaseNemico stat = estraiStatistiche(objBoss);

        // Fabbrica il Boss passandogli le statistiche pulite dal JSON!
        return new Boss(stat.nome(), stat.puntiVita(), stat.attacco(), stat.oro(), stat.esperienza());
    }
}