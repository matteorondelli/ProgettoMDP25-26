package it.unicam.cs.mpgc.rpg125675.modelli.util;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Mostro;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;

public class CaricaDaJson {

    private static final String PERCORSO_MOSTRI= "/mostri/Mostri.json";


    private CaricaDaJson() {}


    private static JsonArray leggiJson(String percorsoFile, String tipo) {
        try (InputStream stream = CaricaDaJson.class.getResourceAsStream(percorsoFile)) {
            if (stream == null) {
                throw new IllegalStateException("File non trovato: " + percorsoFile);
            }
            InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            return JsonParser.parseReader(reader).getAsJsonObject().getAsJsonArray(tipo);
        } catch (IOException e) {
            throw new RuntimeException("Errore lettura file: " + percorsoFile, e);
        }
    }


    public static List<Mostro> caricaMostri() {
        JsonArray array = leggiJson(PERCORSO_MOSTRI, "mostri");
        List<Mostro> mostri = new ArrayList<>();
        for (JsonElement elemento : array) {
            mostri.add(creaMostro(elemento.getAsJsonObject()));
        }
        return mostri;
    }

    private static Mostro creaMostro(JsonObject obj) {
        String nome    = obj.get("nome").getAsString();
        int puntiVita  = obj.get("puntiVita").getAsInt();
        int attacco    = obj.get("attacco").getAsInt();
        int oro        = obj.get("oro").getAsInt();
        int esperienza = obj.get("esperienza").getAsInt();
        return new Mostro(nome, puntiVita, attacco, oro, esperienza, false, false);
       
    }
}
