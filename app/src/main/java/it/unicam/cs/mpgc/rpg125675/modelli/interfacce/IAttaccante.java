package it.unicam.cs.mpgc.rpg125675.modelli.interfacce;

public interface IAttaccante {
    int getAttacco();
    boolean eseguiAttaccoCritico();
    int getDannoCritico();
    void prendiDanno(int danno);
    boolean isVivo();
}