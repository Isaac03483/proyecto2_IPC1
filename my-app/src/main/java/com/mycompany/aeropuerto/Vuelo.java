package com.mycompany.aeropuerto;

import java.io.Serializable;
import java.util.Date;

import com.mycompany.constantes.Constante;
import com.mycompany.enums.EstadoVuelo;

public class Vuelo implements Serializable{
    
    private int codigoVuelo;
    private int codigoAvion;
    private String nombreAeroPuertoOrigen;
    private String nombreAeroPuertoDestino;
    private double precioBoleto;
    private Date fechaSalida;
    private EstadoVuelo estado;
    private Distancia distancia;

    /**
     * Constructor creado para generar más vuelos mediante la interfaz gráfica
     * @param codigoAvion
     * @param nombreAeroPuertoOrigen
     * @param nombreAeroPuertoDestino
     * @param precioBoleto
     * @param fechaSalida
     */
    public Vuelo(int codigoAvion, String nombreAeroPuertoOrigen, String nombreAeroPuertoDestino, double precioBoleto, Date fechaSalida, Distancia distancia){

        this.codigoVuelo = Integer.parseInt(com.mycompany.generadorCodigos.GenerarCodigo.generarCodigo(Integer.toString(codigoVuelo), Constante.CARACTERES_CODIGO_VUELO, false));
        this.codigoAvion = codigoAvion;
        this.nombreAeroPuertoOrigen=nombreAeroPuertoOrigen;
        this.nombreAeroPuertoDestino = nombreAeroPuertoDestino;
        this.precioBoleto = precioBoleto;
        this.fechaSalida = fechaSalida;
        this.estado = EstadoVuelo.ENESPERA;
        this.distancia = distancia;
    }

    /**
     * Constructor  creado para la asignación de vuelos mediante la carga de archivos
     * @param codigoVuelo
     * @param codigoAvion
     * @param nombreAeroPuertoOrigen
     * @param nombreAeroPuertoDestino
     * @param precioBoleto
     * @param fechaSalida
     */
    public Vuelo(int codigoVuelo, int codigoAvion, String nombreAeroPuertoOrigen, String nombreAeroPuertoDestino, double precioBoleto, Date fechaSalida){

        this.codigoVuelo= codigoVuelo;
        this.codigoAvion = codigoAvion;
        this.nombreAeroPuertoOrigen=nombreAeroPuertoOrigen;
        this.nombreAeroPuertoDestino = nombreAeroPuertoDestino;
        this.precioBoleto = precioBoleto;
        this.fechaSalida = fechaSalida;
        this.estado = EstadoVuelo.COMPLETADO;
    }


}
