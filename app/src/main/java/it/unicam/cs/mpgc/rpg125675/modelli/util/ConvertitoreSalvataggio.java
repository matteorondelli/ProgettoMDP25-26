package it.unicam.cs.mpgc.rpg125675.modelli.util;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOSalvataggio;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Giocatore;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IPersonaggioGiocante;
import it.unicam.cs.mpgc.rpg125675.modelli.interfacce.IStatoGioco;
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

    public static StatoGioco aStato(DTOSalvataggio dto) {
        StatoGioco statoGioco = new StatoGioco(dto.getNome());
        IPersonaggioGiocante giocatore = statoGioco.getGiocatore();
        if (giocatore instanceof Giocatore g) {
            g.ripristinaDaSalvataggio(dto);
        }
        statoGioco.impostaLuogo(dto.getLuogoAttuale());
        return statoGioco;
    }
}
