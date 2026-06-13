package it.unicam.cs.mpgc.rpg125675.modelli.interfacce;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.EntitaCombattente;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Mostro;
import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.Luoghi;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOCombattimento;
import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTORicompense;

/**
 * Permette operazioni di lettura e modifica dello stato della partita.
 * Operazioni di lettura prese da({@link IStatoGiocoLettura})
 */
public interface IStatoGioco extends IStatoGiocoLettura {

    /**
     * Sposta il giocatore in un nuovo luogo, se l'accesso è consentito.
     *
     * @param destinazione luogo verso cui spostarsi
     * @return {@code true} se lo spostamento è avvenuto, {@code false} se
     *         la destinazione non è accessibile (es. portale senza frammenti)
     */
    boolean spostati(Luoghi destinazione);

    /**
     * Verifica se il portale è accessibile al giocatore.
     *
     * @return {@code true} se il giocatore possiede tutti i frammenti necessari, {@code false} altrimenti
     */
    boolean portaleAccessibile();

    /**
     * Esegue un turno di combattimento tra il giocatore e il nemico specificato.
     *
     * @param nemico il nemico coinvolto nel combattimento
     * @return un DTO con l'esito del turno
     */
    DTOCombattimento eseguiTurno(EntitaCombattente nemico);

    /**
     * Elabora le ricompense dopo la sconfitta di un'entità nemica,
     * assegnandole al giocatore.
     *
     * @param mostro l'entità sconfitta da cui ottenere le ricompense
     * @return un DTO con le ricompense assegnate
     */
    DTORicompense elaboraRicompense(IRicompensante mostro);

    /**
     * Riposare il giocatore alla locanda, se possibile.
     *
     * @return {@code true} se il riposo è avvenuto con successo,
     *         {@code false} se il giocatore non ha oro sufficiente
     */
    boolean riposati();

    /**
     * Fa utilizzare al giocatore una pozione dal proprio inventario, se disponibile.
     *
     * @return {@code true} se una pozione è stata usata,
     *         {@code false} se l'inventario non contiene pozioni
     */
    boolean usaPozione();

    /**
     * Genera un nuovo mostro da affrontare in combattimento.
     *
     * @return il mostro generato
     */
    Mostro generaMostro();

    /**
     * Termina la partita corrente, registrandone l'esito.
     *
     * @param vittoria {@code true} se il giocatore ha vinto, {@code false} se ha perso
     */
    void terminaPartita(boolean vittoria);

    /**
     * Imposta il mostro da affrontare nel combattimento.
     *
     * @param m il mostro da impostare come avversario
     */
    void impostaMostro(Mostro m);

    /**
     * Imposta il luogo in cui si trova il giocatore.
     *
     * @param luogo il luogo da impostare come corrente
     */
    void impostaLuogo(Luoghi luogo);

    /**
     * Restituisce il personaggio controllato dal giocatore.
     *
     * @return il giocatore della partita corrente
     */
    IPersonaggioGiocante getGiocatore();
}
