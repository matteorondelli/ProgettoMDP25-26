package it.unicam.cs.mpgc.rpg125675;


import it.unicam.cs.mpgc.rpg125675.gestione.GestioneCombattimento;
import it.unicam.cs.mpgc.rpg125675.interfaccia.InterfacciaCombattimento;
import it.unicam.cs.mpgc.rpg125675.interfaccia.InterfacciaConsole;
import it.unicam.cs.mpgc.rpg125675.modelli.Nemico;
import it.unicam.cs.mpgc.rpg125675.modelli.Personaggio;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Personaggio giocatore = new Personaggio("Aldo", 100, 20);
        Nemico nemico = new Nemico("Pdor", 100, 10);
        Scanner scanner = new Scanner(System.in);
        InterfacciaCombattimento interfaccia = new InterfacciaConsole(scanner);

        GestioneCombattimento gestioneCombattimento = new GestioneCombattimento(giocatore, nemico, interfaccia);


        gestioneCombattimento.avvia();
    }


}
