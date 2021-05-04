package com.mycompany.aeropuerto;

import java.io.Serializable;

public class Distancia implements Serializable{
    
    private String nombreAeroPuertoOrigen;
    private String nombreAeroPuertoDestino;
    private double cantidadMillas;

    /**
     * Constructor para la carga mediante archivos de texto y la creación de distancias
     * @param nombreAeroPuertoOrigen
     * @param nombreAeropuertoDestino
     * @param cantidadMillas
     */
    public Distancia(String nombreAeroPuertoOrigen, String nombreAeropuertoDestino, double cantidadMillas){

        this.nombreAeroPuertoOrigen = nombreAeroPuertoOrigen;
        this.nombreAeroPuertoDestino = nombreAeropuertoDestino;
        this.cantidadMillas = cantidadMillas;

    }

    public String getAeroPuertoOrigen(){return this.nombreAeroPuertoOrigen;}

    public String getAeroPuertoDestino(){return this.nombreAeroPuertoDestino;}

    public double getCantidadMillas(){return this.cantidadMillas;}

    
}
