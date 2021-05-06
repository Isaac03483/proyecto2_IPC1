package com.mycompany.aeropuerto.avion;

import java.io.Serializable;

import com.mycompany.aeropuerto.avion.objeto_avion.*;
import com.mycompany.constantes.*;

public class Avion implements Serializable{

    private String nombreAeroLinea;
    private String aeroPuertoActual;
    private int codigoAvion;
    private int capacidadPasajeros;
    private double capacidadGasolina;
    private double consumoPorMilla;
    private Objeto[][] objetos;

    /**
     * Constructor creado para crear más aviones desde el programa
     * @param nombreAeroLinea
     * @param aeroPuertoActual
     * @param codigoAvion
     * @param capacidadPasajeros
     * @param capacidadGasolina
     * @param consumoPorMilla
     */
    public Avion(String nombreAeroLinea, String aeroPuertoActual, double capacidadGasolina, double consumoPorMilla){

        this.nombreAeroLinea = nombreAeroLinea;
        this.aeroPuertoActual=aeroPuertoActual;
        this.capacidadGasolina = capacidadGasolina;
        this.consumoPorMilla = consumoPorMilla;
        this.codigoAvion = Integer.parseInt(com.mycompany.generadorCodigos.GenerarCodigo.generarCodigo(Integer.toString(this.codigoAvion), Constante.CARACTERES_CODIGO_AVION, false));
        
    }

    public void generarMatiz(int noFilas, int noColumnas, int columnaPasillo){

        this.capacidadPasajeros = (noFilas)*(noColumnas-1);
        this.objetos = new Objeto[noFilas][noColumnas];
        int noAsiento = 1;
        for(int i = 0; i < noFilas; i++){
            for(int j=0; j< noColumnas; j++){
                if(j != columnaPasillo){
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


    public void cambiarAeroPuertoActual(String aeroPuertoActual){this.aeroPuertoActual = aeroPuertoActual;}
    
    public String getNombreAeroLinea(){return this.nombreAeroLinea;}

    public String getAeroPuertoActual(){return this.aeroPuertoActual;}

    public int getCodigoAvion(){return this.codigoAvion;}

    public int getCapacidadPasajeros(){return this.capacidadPasajeros;}

    public double getCapacidadGasolina(){return this.capacidadGasolina;}

    public double getConsumoPorMilla() {return this.consumoPorMilla;}

    @Override
    public String toString(){return String.valueOf(this.codigoAvion);}
}
