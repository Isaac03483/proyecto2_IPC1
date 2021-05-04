package com.mycompany.persona.pasajero;

import java.io.*;

public class Reservacion implements Serializable{
    
    private int noPasaporte;
    private int codigoVuelo;
    private long noTarjeta;
    private String noAsiento;

    public Reservacion(int noPasaporte, int codigoVuelo, long noTarjeta, String noAsiento){
        this.noPasaporte = noPasaporte;
        this.codigoVuelo = codigoVuelo;
        this.noTarjeta = noTarjeta;
        this.noAsiento = noAsiento;
    }

    public int getNoPasaporte(){return this.noPasaporte;}

    public int getCodigoVuelo(){return this.codigoVuelo;}

    public long getNoTarjeta(){return this.noTarjeta;}

    public String getNoAsiento(){return this.noAsiento;}
}
