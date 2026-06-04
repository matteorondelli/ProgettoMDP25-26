package it.unicam.cs.mpgc.rpg125675.modelli.interfacce;

public interface Attaccabile {

    void prendiDanno(int danno);
    boolean vivo();
    int getPuntiVita();
    int getPuntiVitaMassimi();
}
