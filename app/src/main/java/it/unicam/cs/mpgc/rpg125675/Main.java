package it.unicam.cs.mpgc.rpg125675;


import it.unicam.cs.mpgc.rpg125675.Gestione.GestioneCombattimento;
import it.unicam.cs.mpgc.rpg125675.Modelli.Nemico;
import it.unicam.cs.mpgc.rpg125675.Modelli.Personaggio;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Personaggio giocatore = new Personaggio("Aldo", 100, 20);
        Nemico nemico = new Nemico("Pdor", 100, 10);
        Scanner scanner = new Scanner(System.in);

        GestioneCombattimento gestioneCombattimento = new GestioneCombattimento(giocatore, nemico, scanner);
        gestioneCombattimento.avvia();
    }


}
