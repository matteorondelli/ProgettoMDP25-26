package it.unicam.cs.mpgc.rpg125675.modelli.classi.util;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti.Arma;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti.OggettoBase;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.oggetti.Pozione;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Mostro;
import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.TipiOggetti;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;

public class CaricaJson {

    private static final String PERCORSO_NEGOZIO = "/negozio/Oggetti.json";
    private static final String PERCORSO_MOSTRI= "/mostri/Mostri.json";


    private CaricaJson() {}


    private static JsonArray leggiJson(String PERCORSO_FILE, String tipo) {
        try (InputStream stream = CaricaJson.class.getResourceAsStream(PERCORSO_FILE)) {
            if (stream == null) {
                throw new IllegalStateException("File non trovato: " + PERCORSO_FILE);
            }
            InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            return JsonParser.parseReader(reader).getAsJsonObject().getAsJsonArray(tipo);
        } catch (IOException e) {
            throw new RuntimeException("Errore lettura file: " + PERCORSO_FILE, e);
        }
    }


    public static List<OggettoBase> caricaNegozio() {
        JsonArray array = leggiJson(PERCORSO_NEGOZIO, "oggetti");
        List<OggettoBase> oggetti = new ArrayList<>();
        for (JsonElement elemento : array) {
            oggetti.add(creaOggetto(elemento.getAsJsonObject()));
        }
        return oggetti;
    }

    private static OggettoBase creaOggetto(JsonObject obj) {
        String nome = obj.get("nome").getAsString();
        int prezzo  = obj.get("prezzo").getAsInt();
        String tipo = obj.get("tipo").getAsString();
        int valore  = obj.get("valore").getAsInt();

        if (tipo.equals("ARMA"))    return new Arma(nome, prezzo, TipiOggetti.ARMA, valore);
        if (tipo.equals("POZIONE")) return new Pozione(nome, prezzo, TipiOggetti.POZIONE, valore);
        throw new IllegalArgumentException("Tipo oggetto sconosciuto: " + tipo);
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
        return new Mostro(nome, puntiVita, attacco, oro, esperienza, false);
       
    }
}
