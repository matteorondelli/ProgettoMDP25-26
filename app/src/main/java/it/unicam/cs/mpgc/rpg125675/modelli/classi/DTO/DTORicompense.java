package it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO;

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
    private final boolean livelloSalito;

    public DTORicompense(int oroGuadagnato, int esperienzaGuadagnata, boolean pozioneOttenuta,
                         boolean frammentoOttenuto, boolean livelloSalito) {
        this.oroGuadagnato = oroGuadagnato;
        this.esperienzaGuadagnata = esperienzaGuadagnata;
        this.pozioneOttenuta = pozioneOttenuta;
        this.frammentoOttenuto = frammentoOttenuto;
        this.livelloSalito = livelloSalito;
    }

    public int getOroGuadagnato(){
        return oroGuadagnato;
    }
    public int getEsperienzaGuadagnata(){
        return esperienzaGuadagnata;
    }
    public boolean isPozioneOttenuta(){
        return pozioneOttenuta;
    }
    public boolean isFrammentoOttenuto(){
        return frammentoOttenuto;
    }
    public boolean isLivelloSalito(){
        return livelloSalito;
    }
}