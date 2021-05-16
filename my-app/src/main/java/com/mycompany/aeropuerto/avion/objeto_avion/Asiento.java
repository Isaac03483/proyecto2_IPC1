package com.mycompany.aeropuerto.avion.objeto_avion;

import com.mycompany.enums.EstadoAsiento;

public class Asiento implements Objeto{
    
    EstadoAsiento estado;
    String noAsiento;

    public Asiento(String noAsiento){
        this.noAsiento = noAsiento;
        estado = EstadoAsiento.DISPONIBLE;
    }

    public void setEstado(EstadoAsiento estado){this.estado = estado;}

    public EstadoAsiento getEstado(){return this.estado;}

    public String getNoAsiento(){return this.noAsiento;}
}
