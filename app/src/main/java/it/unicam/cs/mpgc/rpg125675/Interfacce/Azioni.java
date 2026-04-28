package it.unicam.cs.mpgc.rpg125675.Interfacce;

import it.unicam.cs.mpgc.rpg125675.Modelli.Entità;

public interface Azioni {

        void attacca(Entità entità);

        void cura(Entità entità);

        int getHp();

        void setHp(int vita);

        int getMaxHp();

        int getDmg();
}
