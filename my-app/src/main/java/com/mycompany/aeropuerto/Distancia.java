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

    /**
     * retorna el nombre  del aeropuerto de origen (de inicio)
     * @return
     */
    public String getAeroPuertoOrigen(){return this.nombreAeroPuertoOrigen;}

    /**
     * 
     retorna el nombre del aeropuerto de destino (de llegada)
     * @return
     */
    public String getAeroPuertoDestino(){return this.nombreAeroPuertoDestino;}

    /**
     * retorna la cantidad de millas que hay de distancia
     * @return
     */
    public double getCantidadMillas(){return this.cantidadMillas;}

    /**
     * método sobreescrito de la clase object
     */
    @Override
    public String toString(){return this.nombreAeroPuertoOrigen+" -> "+this.nombreAeroPuertoDestino;}
    
}
