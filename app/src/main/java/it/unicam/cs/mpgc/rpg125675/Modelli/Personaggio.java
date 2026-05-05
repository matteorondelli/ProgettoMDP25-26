package it.unicam.cs.mpgc.rpg125675.Modelli;



public class Personaggio extends Entità {

        public Personaggio(String nome, int puntiVita, int attacco) {
            super(nome,puntiVita,attacco);
        }

        public void cura(){
            this.setPuntiVita(Math.min(this.getPuntiVita() + 5, this.getPuntiVitaMassimi()));
        }







}
