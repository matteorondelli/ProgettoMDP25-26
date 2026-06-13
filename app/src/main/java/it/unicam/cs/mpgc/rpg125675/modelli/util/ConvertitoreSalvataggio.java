package it.unicam.cs.mpgc.rpg125675.modelli.util;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOSalvataggio;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.*;
import it.unicam.cs.mpgc.rpg125675.modelli.logica.MotoreCombattimento;
import it.unicam.cs.mpgc.rpg125675.modelli.logica.StatoGioco;

/**
 * Classe di utilità responsabile della conversione tra
 * {@link IStatoGioco} e {@link DTOSalvataggio}, nelle due direzioni.
 *
 * Centralizza la logica di mappatura tra il modello di dominio e la
 * rappresentazione JSON dello stato di gioco, mantenendo
 * {@link StatoGioco} indipendente dai dettagli di serializzazione.
 */
public class ConvertitoreSalvataggio {

    /**
     * Costruttore privato per impedire l'istanziazione di questa classe di utilità.
     */
    private ConvertitoreSalvataggio() {}

    /**
     * Crea un {@link DTOSalvataggio} a partire dallo stato di gioco corrente,
     * estraendo tutti i dati del giocatore e il luogo in cui si trova.
     *
     * @param statoGioco lo stato di gioco da cui estrarre i dati da salvare
     * @return un DTO immutabile pronto per essere serializzato
     */
    public static DTOSalvataggio daStato(IStatoGioco statoGioco) {
        IPersonaggioGiocante g = statoGioco.getGiocatore();
        return new DTOSalvataggio(
                g.getNome(),
                g.getLivello(),
                g.getEsperienza(),
                g.getEsperienzaPerLivello(),
                g.getPuntiVita(),
                g.getPuntiVitaMassimi(),
                g.getAttacco(),
                g.getOro(),
                g.getFrammenti(),
                statoGioco.getLuogoAttuale(),
                g.getInventarioPozioni()
        );
    }

    /**
     * Ricostruisce un {@link IStatoGioco} a partire da un {@link DTOSalvataggio}
     * precedentemente caricato, iniettando le dipendenze concrete necessarie.
     *
     * @param dto i dati di salvataggio da cui ripristinare lo stato
     * @param locanda implementazione della locanda da utilizzare nel nuovo stato
     * @param generatore generatore di mostri da utilizzare nel nuovo stato
     * @param motore motore di combattimento da utilizzare nel nuovo stato
     * @param caricatore caricatore di mostri/boss da utilizzare nel nuovo stato
     * @return un nuovo {@link IStatoGioco} con lo stato ripristinato dal salvataggio
     */
    public static IStatoGioco aStato(DTOSalvataggio dto, ILocanda locanda, IGeneratoreMostri generatore,
                                     MotoreCombattimento motore, ICaricatoreMostri caricatore) {

        IStatoGioco stato = new StatoGioco(dto.getNome(), locanda, generatore, motore, caricatore);
        stato.getGiocatore().ripristinaDaSalvataggio(dto);
        stato.impostaLuogo(dto.getLuogoAttuale());
        return stato;
    }
}
