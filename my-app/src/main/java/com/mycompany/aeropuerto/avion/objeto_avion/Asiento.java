package com.mycompany.aeropuerto.avion.objeto_avion;

import com.mycompany.enums.EstadoAsiento;

public class Asiento implements Objeto{
    
    EstadoAsiento estado;
    String noAsiento;

    /**
     * Constructor que inicializa un asiento
     * @param noAsiento recibe parámetro de tipo String
     */
    public Asiento(String noAsiento){
        this.noAsiento = noAsiento;
        estado = EstadoAsiento.DISPONIBLE;
    }

    /**
     * cambia el estado del asiento
     * @param estado enum EstadoAsiento
     */
    public void setEstado(EstadoAsiento estado){this.estado = estado;}

    /**
     * retorna el estado del asiento en ese momento
     * @return retorna un dato de tipo EstadoAsiento
     */
    public EstadoAsiento getEstado(){return this.estado;}

    /**
     * Retorna el número de asiento
     * @return retorna un dato de tipo String
     */
    public String getNoAsiento(){return this.noAsiento;}
}
