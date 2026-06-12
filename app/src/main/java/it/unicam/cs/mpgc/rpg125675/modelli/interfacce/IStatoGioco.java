package it.unicam.cs.mpgc.rpg125675.modelli.interfacce;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.EntitaCombattente;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Mostro;
import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.Luoghi;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOCombattimento;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTORicompense;

/**
 * Vista completa dello stato di gioco, comprendente sia le operazioni di
 * lettura ({@link IStatoGiocoLettura}) sia quelle che modificano lo stato
 * della partita (combattimento, spostamenti, salvataggio/caricamento, ecc.).
 */
public interface IStatoGioco extends IStatoGiocoLettura {
    boolean spostati(Luoghi destinazione);
    boolean portaleAccessibile();
    DTOCombattimento eseguiTurno(EntitaCombattente nemico);
    DTORicompense elaboraRicompense(Mostro mostro);
    boolean riposati();
    boolean usaPozione();
    Mostro generaMostro();
    void terminaPartita(boolean vittoria);
    void impostaMostro(Mostro m);
    void impostaLuogo(Luoghi luogo);
    IPersonaggioGiocante getGiocatore();
}
