package it.unicam.cs.mpgc.rpg125675.modelli.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOSalvataggio;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IGestoreSalvataggio;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class GestoreSalvataggio implements IGestoreSalvataggio {

    private static final String PERCORSO_SALVATAGGIO = "salvataggio.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void salva(DTOSalvataggio dto) {
        Path percorso = Path.of(PERCORSO_SALVATAGGIO);
        try (Writer writer = Files.newBufferedWriter(percorso, StandardCharsets.UTF_8)) {
            gson.toJson(dto, writer);
        } catch (IOException e) {
            throw new RuntimeException("Errore durante il salvataggio", e);
        }
    }

    @Override
    public DTOSalvataggio carica() {
        Path percorso = Path.of(PERCORSO_SALVATAGGIO);
        if (!Files.exists(percorso)) {
            return null;
        }
        try (Reader reader = Files.newBufferedReader(percorso, StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, DTOSalvataggio.class);
        } catch (IOException e) {
            throw new RuntimeException("Errore durante il caricamento", e);
        }
    }

    @Override
    public boolean esisteSalvataggio() {
        return Files.exists(Path.of(PERCORSO_SALVATAGGIO));
    }

    @Override
    public void elimina() {
        try {
            Files.deleteIfExists(Path.of(PERCORSO_SALVATAGGIO));
        } catch (IOException e) {
            throw new RuntimeException("Errore durante l'eliminazione del salvataggio", e);
        }
    }
}