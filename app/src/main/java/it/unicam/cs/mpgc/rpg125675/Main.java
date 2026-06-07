package it.unicam.cs.mpgc.rpg125675;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.personaggi.Mostro;
import it.unicam.cs.mpgc.rpg125675.modelli.enumerazioni.Luoghi;
import it.unicam.cs.mpgc.rpg125675.modelli.logica.MotoreCombattimento;
import it.unicam.cs.mpgc.rpg125675.modelli.logica.RisultatoCombattimento;
import it.unicam.cs.mpgc.rpg125675.modelli.logica.StatoGioco;

public class Main {
    public static void main(String[] args) {

        StatoGioco gioco = new StatoGioco("Eroe");

        // ── STATO INIZIALE ────────────────────────────────────────
        System.out.println("=== STATO INIZIALE ===");
        stampaGiocatore(gioco);

        // ── NEGOZIO ──────────────────────────────────────────────
        System.out.println("\n=== NEGOZIO ===");
        gioco.getNegozio().getNegozio().forEach(o ->
                System.out.println("- " + o.getNome() + " | prezzo: " + o.getPrezzo()));

        // acquisto primo oggetto (arma)
        var arma = gioco.getNegozio().getNegozio().get(0);
        boolean acquistato = gioco.getNegozio().acquista(gioco.getGiocatore(), arma);
        System.out.println("Acquisto " + arma.getNome() + ": " + (acquistato ? "OK" : "FALLITO")
                + " | oro rimasto: " + gioco.getGiocatore().getOro()
                + " | attacco totale: " + gioco.getGiocatore().getAttaccoTotale());

        // acquisto pozione
        var pozione = gioco.getNegozio().getNegozio().get(2);
        gioco.getNegozio().acquista(gioco.getGiocatore(), pozione);
        System.out.println("Acquisto " + pozione.getNome() + " | oro rimasto: " + gioco.getGiocatore().getOro());

        // ── LOCANDA ──────────────────────────────────────────────
        System.out.println("\n=== LOCANDA ===");
        gioco.getGiocatore().prendiDanno(40);
        System.out.println("Dopo 40 danno → HP: " + gioco.getGiocatore().getPuntiVita());
        boolean riposato = gioco.getLocanda().riposaGiocatore(gioco.getGiocatore());
        System.out.println("Riposo: " + (riposato ? "OK" : "FALLITO")
                + " | HP: " + gioco.getGiocatore().getPuntiVita()
                + " | oro: " + gioco.getGiocatore().getOro());

        // ── SPOSTAMENTO ──────────────────────────────────────────
        System.out.println("\n=== SPOSTAMENTO ===");
        System.out.println("Portale accessibile senza frammenti: " + gioco.spostati(Luoghi.PORTALE));
        System.out.println("Spostamento foresta: " + gioco.spostati(Luoghi.FORESTA)
                + " | luogo: " + gioco.getLuogoAttuale());

        // ── COMBATTIMENTO MOSTRO ──────────────────────────────────
        System.out.println("\n=== COMBATTIMENTO vs MOSTRO ===");
        MotoreCombattimento motore = gioco.getMotoreCombattimento();
        Mostro mostro = gioco.getGeneratoreMostri().generaMostro();
        System.out.println("Nemico: " + mostro.getNome()
                + " | HP: " + mostro.getPuntiVita()
                + " | ATT: " + mostro.getAttacco());

        int turno = 1;
        while (gioco.getGiocatore().isVivo() && mostro.isVivo()) {
            RisultatoCombattimento r = motore.eseguiTurno(gioco.getGiocatore(), mostro);
            System.out.println("Turno " + turno++
                    + " | causato: " + r.getDannoCausato()
                    + " | ricevuto: " + r.getDannoRicevuto()
                    + " | giocatore HP: " + gioco.getGiocatore().getPuntiVita()
                    + " | mostro HP: " + mostro.getPuntiVita());
        }

        if (gioco.getGiocatore().isVivo()) {
            gioco.getGiocatore().aggiungiOro(mostro.getRicompensaOro());
            gioco.getGiocatore().aggiungiEsperienza(mostro.getRicompensaEsperienza());
            if (mostro.getRicompensaFrammento()) gioco.getGiocatore().aggiungiFrammento();
            System.out.println("→ Vittoria! oro: +" + mostro.getRicompensaOro()
                    + " | exp: +" + mostro.getRicompensaEsperienza()
                    + " | frammento: " + mostro.getRicompensaFrammento());
        } else {
            System.out.println("→ Sconfitto!");
            gioco.terminaPartita(false);
        }

        stampaGiocatore(gioco);

        // ── FRAMMENTI E PORTALE ───────────────────────────────────
        System.out.println("\n=== FRAMMENTI ===");
        gioco.getGiocatore().aggiungiFrammento();
        gioco.getGiocatore().aggiungiFrammento();
        gioco.getGiocatore().aggiungiFrammento();
        System.out.println("Frammenti: " + gioco.getGiocatore().getFrammenti()
                + " | portale accessibile: " + gioco.portaleAccessibile());
        System.out.println("Spostamento portale: " + gioco.spostati(Luoghi.PORTALE)
                + " | luogo: " + gioco.getLuogoAttuale());

        // ── COMBATTIMENTO BOSS ────────────────────────────────────
        System.out.println("\n=== COMBATTIMENTO vs BOSS ===");
        gioco.getGiocatore().ripristinaVitaCompleta();
        System.out.println("HP ripristinati: " + gioco.getGiocatore().getPuntiVita());
        System.out.println("Boss: " + gioco.getBoss().getNome()
                + " | HP: " + gioco.getBoss().getPuntiVita()
                + " | ATT: " + gioco.getBoss().getAttacco()
                + " | danno speciale: " + gioco.getBoss().getDannoAttaccoSpeciale());

        turno = 1;
        while (gioco.getGiocatore().isVivo() && gioco.getBoss().isVivo()) {
            RisultatoCombattimento r = motore.eseguiTurno(gioco.getGiocatore(), gioco.getBoss());
            System.out.println("Turno " + turno++
                    + " | causato: " + r.getDannoCausato()
                    + (r.isAttaccoCriticoGiocatore() ? " [CRITICO!]" : "")
                    + " | ricevuto: " + r.getDannoRicevuto()
                    + (r.isAttaccoSpecialeBoss() ? " [SPECIALE!]" : "")
                    + " | giocatore HP: " + gioco.getGiocatore().getPuntiVita()
                    + " | boss HP: " + gioco.getBoss().getPuntiVita());
        }

        if (gioco.getGiocatore().isVivo()) {
            System.out.println("→ Boss sconfitto! HAI VINTO!");
            gioco.terminaPartita(true);
        } else {
            System.out.println("→ Sconfitto dal boss!");
            gioco.terminaPartita(false);
        }

        System.out.println("Fine partita: " + gioco.isFinePartita()
                + " | vittoria: " + gioco.isGiocatoreHaVinto());
    }

    private static void stampaGiocatore(StatoGioco gioco) {
        var g = gioco.getGiocatore();
        System.out.println("Giocatore: " + g.getNome()
                + " | HP: " + g.getPuntiVita() + "/" + g.getPuntiVitaMassimi()
                + " | ATT: " + g.getAttaccoTotale()
                + " | ORO: " + g.getOro()
                + " | LV: " + g.getLivello()
                + " | EXP: " + g.getEsperienza() + "/" + g.getEsperienzaPerLivello()
                + " | frammenti: " + g.getFrammenti());
    }
}
