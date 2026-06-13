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

/**
 * Implementazione di {@link IGestoreSalvataggio} basata su un file JSON
 * locale, gestito tramite la libreria Gson.
 *
 * Il salvataggio viene scritto e letto da un singolo file
 * ({@code salvataggio.json}) nella directory di lavoro dell'applicazione.
 */
public class GestoreSalvataggio implements IGestoreSalvataggio {

    private static final String PERCORSO_SALVATAGGIO = "salvataggio.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * {@inheritDoc}
     *
     * Serializza il DTO in formato JSON con indentazione leggibile e lo
     * scrive nel file di salvataggio.
     *
     * @throws RuntimeException se si verifica un errore di I/O durante la scrittura
     */
    @Override
    public void salva(DTOSalvataggio dto) {
        Path percorso = Path.of(PERCORSO_SALVATAGGIO);
        try (Writer writer = Files.newBufferedWriter(percorso, StandardCharsets.UTF_8)) {
            gson.toJson(dto, writer);
        } catch (IOException e) {
            throw new RuntimeException("Errore durante il salvataggio", e);
        }
    }

    /**
     * {@inheritDoc}
     *
     * Se il file di salvataggio non esiste, restituisce {@code null}.
     * Altrimenti, legge e deserializza il contenuto del file in un
     * {@link DTOSalvataggio}.
     *
     * @throws RuntimeException se si verifica un errore di I/O durante la lettura
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean esisteSalvataggio() {
        return Files.exists(Path.of(PERCORSO_SALVATAGGIO));
    }

    /**
     * {@inheritDoc}
     *
     * @throws RuntimeException se si verifica un errore di I/O durante l'eliminazione
     */
    @Override
    public void elimina() {
        try {
            Files.deleteIfExists(Path.of(PERCORSO_SALVATAGGIO));
        } catch (IOException e) {
            throw new RuntimeException("Errore durante l'eliminazione del salvataggio", e);
        }
    }
}