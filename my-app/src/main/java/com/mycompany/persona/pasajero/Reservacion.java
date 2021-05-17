package com.mycompany.persona.pasajero;

import java.io.*;

public class Reservacion implements Serializable{
    
    private int noPasaporte;
    private int codigoVuelo;
    private long noTarjeta;
    private String noAsiento;

    /**
     * Constructor para la creación de reservaciones
     * @param noPasaporte
     * @param codigoVuelo
     * @param noTarjeta
     * @param noAsiento
     */
    public Reservacion(int noPasaporte, int codigoVuelo, long noTarjeta, String noAsiento){
        this.noPasaporte = noPasaporte;
        this.codigoVuelo = codigoVuelo;
        this.noTarjeta = noTarjeta;
        this.noAsiento = noAsiento;
    }

    /**
     * retorna el número de pasaporte de la reservación
     * @return
     */
    public int getNoPasaporte(){return this.noPasaporte;}

    /**
     * método que retorna el código de  vuelo asociado a la reservación
     * @return
     */
    public int getCodigoVuelo(){return this.codigoVuelo;}

    /**
     * método que retorna el número de tarjeta de la reservación
     * @return
     */
    public long getNoTarjeta(){return this.noTarjeta;}

    /**
     * método que retorna el número de asiento reservado
     * @return
     */
    public String getNoAsiento(){return this.noAsiento;}
}
