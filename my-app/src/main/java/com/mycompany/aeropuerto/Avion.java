package com.mycompany.aeropuerto;

import javax.swing.JLabel;
import com.mycompany.constantes.*;
public class Avion extends JLabel implements Runnable{

    private String nombreAeroLinea;
    private String aeroPuertoActual;
    private int codigoAvion;
    private int capacidadPasajeros;
    private double capacidadGasolina;
    private double consumoPorMilla;


    /**
     * Constructor creado para crear más aviones desde el programa
     * @param nombreAeroLinea
     * @param aeroPuertoActual
     * @param codigoAvion
     * @param capacidadPasajeros
     * @param capacidadGasolina
     * @param consumoPorMilla
     */
    public Avion(String nombreAeroLinea, String aeroPuertoActual, int capacidadPasajeros, double capacidadGasolina, double consumoPorMilla){

        this.nombreAeroLinea = nombreAeroLinea;
        this.aeroPuertoActual=aeroPuertoActual;
        this.capacidadPasajeros = capacidadPasajeros;
        this.capacidadGasolina = capacidadGasolina;
        this.consumoPorMilla = consumoPorMilla;
        this.codigoAvion = Integer.parseInt(com.mycompany.generadorCodigos.GenerarCodigo.generarCodigo(Integer.toString(this.codigoAvion), Constante.CARACTERES_CODIGO_AVION, false));
    
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
    }


    public void cambiarAeroPuertoActual(String aeroPuertoActual){this.aeroPuertoActual = aeroPuertoActual;}
    
    public String getNombreAeroLinea(){return this.nombreAeroLinea;}

    public String getAeroPuertoActual(){return this.aeroPuertoActual;}

    public int getCodigoAvion(){return this.codigoAvion;}

    public int getCapacidadPasajeros(){return this.capacidadPasajeros;}

    public double getCapacidadGasolina(){return this.capacidadGasolina;}

    public double getConsumoPorMilla() {return this.consumoPorMilla;}
    @Override
    public void run(){
        
    }
}
