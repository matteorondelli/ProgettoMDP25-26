package it.unicam.cs.mpgc.rpg125675.Gestione;


import it.unicam.cs.mpgc.rpg125675.Modelli.Nemico;
import it.unicam.cs.mpgc.rpg125675.Modelli.Personaggio;

import java.util.Scanner;

public class GestioneCombattimento {

    private final Personaggio giocatore;
    private final Nemico nemico;
    private final Scanner scanner;

    public GestioneCombattimento(Personaggio giocatore, Nemico nemico, Scanner scanner) {
        this.giocatore = giocatore;
        this.nemico = nemico;
        this.scanner = scanner;
    }

    public void avvia() {
        while (giocatore.vivo() && nemico.vivo()) {
            statoCombattimento();
            turnoGiocatore();
            turnoNemico();
        }

        esitoCombattimento();
    }

    private void statoCombattimento() {
        System.out.println("Giocatore: " + giocatore.getNome());
        System.out.println("Vita: " + giocatore.getPuntiVita());
        System.out.println("Danni: " + giocatore.getAttacco() + "\n");
        System.out.println("Nemico: " + nemico.getNome());
        System.out.println("Vita: " + nemico.getPuntiVita());
        System.out.println("Danni: " + nemico.getAttacco() + "\n");
        System.out.println("1. Attacca (infliggi " + giocatore.getAttacco() + " danni)");
        System.out.println("2. Curati 5 punti vita");
    }

    private void turnoGiocatore() {
        int scelta = scanner.nextInt();

        if (scelta == 1) {
            giocatore.attacca(nemico);
        } else {
            giocatore.cura();
        }
    }

    private void turnoNemico() {
        if (nemico.vivo()) {
            nemico.attacca(giocatore);
        }
    }

    private void esitoCombattimento() {
        if (giocatore.vivo()) {
            System.out.println("Hai vinto!");
        } else {
            System.out.println("Game Over!");
        }
    }
}
