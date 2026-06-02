package it.unicam.cs.mpgc.rpg125675;


import it.unicam.cs.mpgc.rpg125675.modelli.Giocatore;
import it.unicam.cs.mpgc.rpg125675.modelli.Nemico;

public class Main {
    public static void main(String[] args) {
        Giocatore giocatore = new Giocatore("aldo", 150, 10, 2);
        Nemico nemico = new Nemico("aldo", 150, 10);
        giocatore.subisciDanno(50);
        giocatore.cura();
        System.out.println(giocatore.getPuntiVita());
        nemico.subisciDanno(50);
        System.out.println(nemico.getPuntiVita());
    }


}
