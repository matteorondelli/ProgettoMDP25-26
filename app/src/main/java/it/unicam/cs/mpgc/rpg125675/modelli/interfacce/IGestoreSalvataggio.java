package it.unicam.cs.mpgc.rpg125675.modelli.interfacce;

import it.unicam.cs.mpgc.rpg125675.modelli.classi.DTO.DTOSalvataggio;

public interface IGestoreSalvataggio {
    void salva(DTOSalvataggio dto);
    DTOSalvataggio carica();
    boolean esisteSalvataggio();
    void elimina();
}
