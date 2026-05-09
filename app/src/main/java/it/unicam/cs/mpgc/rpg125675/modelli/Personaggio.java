package it.unicam.cs.mpgc.rpg125675.modelli;



public class Personaggio extends Entita {

        public Personaggio(String nome, int puntiVita, int attacco) {
            super(nome,puntiVita,attacco);
        }

        public void cura(){
            this.setPuntiVita(Math.min(this.getPuntiVita() + 5, this.getPuntiVitaMassimi()));
        }







}
