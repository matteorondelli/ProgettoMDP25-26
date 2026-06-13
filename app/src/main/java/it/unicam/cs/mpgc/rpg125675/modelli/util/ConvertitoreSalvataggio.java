package it.unicam.cs.mpgc.rpg125675.modelli.util;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOSalvataggio;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.*;
import it.unicam.cs.mpgc.rpg125675.modelli.logica.MotoreCombattimento;
import it.unicam.cs.mpgc.rpg125675.modelli.logica.StatoGioco;

public class ConvertitoreSalvataggio {

    private ConvertitoreSalvataggio() {}

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

    public static IStatoGioco aStato(DTOSalvataggio dto,
                                     ILocanda locanda,
                                     IGeneratoreMostri generatore,
                                     MotoreCombattimento motore,
                                     ICaricatoreMostri caricatore) {

        // 1. Creiamo lo stato usando il costruttore disaccoppiato
        IStatoGioco stato = new StatoGioco(dto.getNome(), locanda, generatore, motore, caricatore);

        // 2. Chiamiamo il tuo metodo pulito per ripristinare il giocatore in un colpo solo!
        // Nota: se la variabile del giocatore è privata, usiamo il getter dello stato
        stato.getGiocatore().ripristinaDaSalvataggio(dto);

        // 3. Ripristiniamo l'unico dato di competenza dello stato: il luogo attuale
        stato.impostaLuogo(dto.getLuogoAttuale());

        return stato;
    }
}
