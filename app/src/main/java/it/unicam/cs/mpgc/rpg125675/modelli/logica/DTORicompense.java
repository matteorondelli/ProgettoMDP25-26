package it.unicam.cs.mpgc.rpg125675.modelli.logica;

/**
 * DTO immutabile che trasporta i risultati delle ricompense
 * dopo la sconfitta di un mostro, senza esporre logica di dominio
 * ai livelli superiori (controlli, UI).
 */
public class DTORicompense {

    private final int oroGuadagnato;
    private final int esperienzaGuadagnata;
    private final boolean pozioneOttenuta;
    private final boolean frammentoOttenuto;

    public DTORicompense(int oroGuadagnato,
                         int esperienzaGuadagnata,
                         boolean pozioneOttenuta,
                         boolean frammentoOttenuto) {
        this.oroGuadagnato = oroGuadagnato;
        this.esperienzaGuadagnata = esperienzaGuadagnata;
        this.pozioneOttenuta = pozioneOttenuta;
        this.frammentoOttenuto = frammentoOttenuto;
    }

    public int getOroGuadagnato(){ return oroGuadagnato; }
    public int getEsperienzaGuadagnata(){ return esperienzaGuadagnata; }
    public boolean isPozioneOttenuta(){ return pozioneOttenuta; }
    public boolean isFrammentoOttenuto(){ return frammentoOttenuto; }
}