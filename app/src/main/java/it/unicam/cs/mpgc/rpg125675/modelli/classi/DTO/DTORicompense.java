package it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO;

/**
 * DTO che trasporta i risultati delle ricompense
 * dopo la sconfitta di un mostro, senza esporre logica di dominio
 * ai livelli superiori (controlli, UI).
 */
public class DTORicompense {

    private final int oroGuadagnato;
    private final int esperienzaGuadagnata;
    private final boolean pozioneOttenuta;
    private final boolean frammentoOttenuto;
    private final boolean livelloSalito;

    /**
     * Crea un nuovo DTO con le ricompense assegnate al giocatore.
     *
     * @param oroGuadagnato oro ottenuto
     * @param esperienzaGuadagnata esperienza ottenuta
     * @param pozioneOttenuta {@code true} se è stata ottenuta una pozione
     * @param frammentoOttenuto {@code true} se è stato ottenuto un frammento del portale
     * @param livelloSalito {@code true} se il giocatore è salito di livello
     */
    public DTORicompense(int oroGuadagnato, int esperienzaGuadagnata, boolean pozioneOttenuta,
                         boolean frammentoOttenuto, boolean livelloSalito) {
        this.oroGuadagnato = oroGuadagnato;
        this.esperienzaGuadagnata = esperienzaGuadagnata;
        this.pozioneOttenuta = pozioneOttenuta;
        this.frammentoOttenuto = frammentoOttenuto;
        this.livelloSalito = livelloSalito;
    }

    /**
     * Restituisce l'oro guadagnato.
     *
     * @return oro guadagnato
     */
    public int getOroGuadagnato(){
        return oroGuadagnato;
    }

    /**
     * Restituisce l'esperienza guadagnata.
    *
    * @return esperienza guadagnata
    */
    public int getEsperienzaGuadagnata(){
        return esperienzaGuadagnata;
    }

    /**
     * Indica se è stata ottenuta una pozione come ricompensa.
     *
     * @return {@code true} se è stata ottenuta una pozione
     */
    public boolean isPozioneOttenuta(){
        return pozioneOttenuta;
    }

    /**
     * Indica se è stato ottenuto un frammento del portale come ricompensa.
     *
     * @return {@code true} se è stato ottenuto un frammento
     */
    public boolean isFrammentoOttenuto(){
        return frammentoOttenuto;
    }

    /**
     * Indica se il giocatore è salito di livello dopo aver ottenuto dell'esperienza.
     *
     * @return {@code true} se il giocatore è salito di livello
     */
    public boolean isLivelloSalito(){
        return livelloSalito;
    }
}