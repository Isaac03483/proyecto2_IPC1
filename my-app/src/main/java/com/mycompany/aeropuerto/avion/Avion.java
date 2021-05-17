package com.mycompany.aeropuerto.avion;

import java.io.Serializable;

import com.mycompany.aeropuerto.avion.objeto_avion.*;
import com.mycompany.constantes.*;
import com.mycompany.enums.EstadoAsiento;

public class Avion implements Serializable{

    private String nombreAeroLinea;
    private String aeroPuertoActual;
    private int codigoAvion;
    private double gasolinaConsumida;
    private int capacidadPasajeros;
    private double capacidadGasolina;
    private double consumoPorMilla;
    private Objeto[][] objetos;

    /**
     * método oculto
     */
    {
        this.gasolinaConsumida =0;
    }

    /**
     * Constructor creado para crear más aviones desde el programa
     * @param nombreAeroLinea nombre de la aerolinea
     * @param aeroPuertoActual nombre del aeropuerto en el que se encuentra
     * @param codigoAvion codigo único del avion
     * @param capacidadPasajeros capacidad única del avión
     * @param capacidadGasolina capacidad única de gasolina
     * @param consumoPorMilla consumo de gasolina por milla
     */
    public Avion(String nombreAeroLinea, String aeroPuertoActual, double capacidadGasolina, double consumoPorMilla){

        this.nombreAeroLinea = nombreAeroLinea;
        this.aeroPuertoActual=aeroPuertoActual;
        this.capacidadGasolina = capacidadGasolina;
        this.consumoPorMilla = consumoPorMilla;
        this.codigoAvion = Integer.parseInt(com.mycompany.generadorCodigos.GenerarCodigo.generarCodigo("", Constante.CARACTERES_CODIGO_AVION, false));
        
    }

    /**
     * método que genera la matriz de asientos y pasillos del avión
     * @param noFilas recibe el número de filas totales
     * @param noColumnas recibe el número de columnas totales
     * @param columnaPasillo recube el número de columna que será asignada al pasillo
     */
    public void generarMatiz(int noFilas, int noColumnas, int columnaPasillo){

        this.capacidadPasajeros = (noFilas)*(noColumnas-1);
        this.objetos = new Objeto[noFilas][noColumnas];
        int noAsiento = 1;
        for(int i = 0; i < noFilas; i++){
            for(int j=0; j< noColumnas; j++){
                if(j != (columnaPasillo-1)){
                    objetos[i][j] = new Asiento("A"+noAsiento);
                    noAsiento++;
                } else {
                    objetos[i][j] = new Pasillo();
                }
            }
        }
    }

    /**
     * Constructor creado para la carga de datos por medio de un archivo de texto
     * @param nombreAeroLinea
     * @param aeroPuertoActual
     * @param codigoAvion
     * @param capacidadPasajeros
     * @param capacidadGasolina
     * @param consumoPorMilla
     */
    public Avion(String nombreAeroLinea, String aeroPuertoActual, int codigoAvion, int capacidadPasajeros, double capacidadGasolina, double consumoPorMilla){

        this.nombreAeroLinea = nombreAeroLinea;
        this.aeroPuertoActual=aeroPuertoActual;
        this.codigoAvion = codigoAvion;
        this.capacidadPasajeros = capacidadPasajeros;
        this.capacidadGasolina = capacidadGasolina;
        this.consumoPorMilla = consumoPorMilla;

        generarMatriz();
    }

    /**
     * método utilizado para crear las filas y columnas del avión mediante la carga de archivos de texto
     */
    public void generarMatriz(){

        int noFilas = this.capacidadPasajeros/4;
        if(noFilas <= 50){
            int noAsiento =1;
            objetos = new Objeto[noFilas][5];

            for(int i = 0; i < noFilas; i++){
                for(int j=0; j< 5; j++){
                    if( j != 2){
                        objetos[i][j] = new Asiento("A"+noAsiento);
                        noAsiento++;
                    } else {
                        objetos[i][j] = new Pasillo();
                    }
                }
            }
        }
    }


    /**
     * cambia el aeropuerto en el que se encuentra ahora el avión
     * @param aeroPuertoActual recibe el nombre del nuevo aeropuerto
     */
    public void cambiarAeroPuertoActual(String aeroPuertoActual){this.aeroPuertoActual = aeroPuertoActual;}
    
    /**
     * Retorna el nombre de la aerolinea asingada
     * @return retorna un dato de tipo string
     */
    public String getNombreAeroLinea(){return this.nombreAeroLinea;}

    /**
     * retorna el nombre del aeropuerto actual
     * @return
     */
    public String getAeroPuertoActual(){return this.aeroPuertoActual;}

    /**
     * retorna el código del avión
     * @return
     */
    public int getCodigoAvion(){return this.codigoAvion;}

    /**
     * retorna el total de gasolina consumida
     * @return
     */
    public double getGasolinaConsumida(){return this.gasolinaConsumida;}

    /**
     * retorna la matriz de asientos y pasillos
     * @return
     */
    public Objeto[][] getObjetos(){return this.objetos;}

    /**
     * retorna el valor total de la gasolina consumida
     * @param gasolina
     */
    public void setGasolinaConsumida(double gasolina){this.gasolinaConsumida+=gasolina;}

    /**
     * vende un asiento y cambia el estado del mismo
     * @param i el número de fila en la que se encuentra el asiento
     * @param j el número de columna en la que se encuentra el asiento
     * @param estado el nuevo estado del asiento
     */
    public void venderAsiento(int i, int j, EstadoAsiento estado){((Asiento)this.objetos[i][j]).setEstado(estado);}

    /**
     * retorna la capacidad total de pasajeros del avion
     * @return
     */
    public int getCapacidadPasajeros(){return this.capacidadPasajeros;}

    /**
     * retorna la capacidad total de gasolina que posee el avión
     * @return
     */
    public double getCapacidadGasolina(){return this.capacidadGasolina;}

    /**
     * retorna el consumo de gasolina por milla
     * @return
     */
    public double getConsumoPorMilla() {return this.consumoPorMilla;}

    /**
     * método sobreescrito de la clase object
     */
    @Override
    public String toString(){return String.valueOf(this.codigoAvion);}
}
