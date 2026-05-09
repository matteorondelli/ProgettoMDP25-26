package it.unicam.cs.mpgc.rpg125675.interfaccia;

import it.unicam.cs.mpgc.rpg125675.modelli.Nemico;
import it.unicam.cs.mpgc.rpg125675.modelli.Personaggio;

import java.util.Scanner;

public class InterfacciaConsole implements InterfacciaCombattimento{
    private final Scanner scanner;

    public InterfacciaConsole(Scanner scanner){
        this.scanner = scanner;
    }

    @Override
    public void mostraStato(Personaggio giocatore, Nemico nemico) {
        System.out.println("Giocatore: " + giocatore.getNome());
        System.out.println("Vita: " + giocatore.getPuntiVita());
        System.out.println("Danni: " + giocatore.getAttacco() + "\n");

        System.out.println("Nemico: " + nemico.getNome());
        System.out.println("Vita: " + nemico.getPuntiVita());
        System.out.println("Danni: " + nemico.getAttacco() + "\n");

        System.out.println("1. Attacca (infliggi " + giocatore.getAttacco() + " danni)");
        System.out.println("2. Curati 5 punti vita");
    }

    @Override
    public int leggiScelta() {
        return scanner.nextInt();
    }

    @Override
    public void mostraMessaggio(String messaggio) {
        System.out.println(messaggio);
    }
}
