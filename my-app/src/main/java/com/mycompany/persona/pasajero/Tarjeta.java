package com.mycompany.persona.pasajero;

import java.io.Serializable;
import com.mycompany.constantes.*;

public class Tarjeta implements Serializable{

    private int noTarjeta;
    private int noPasaporte;
    private double dineroActual;
    private int codicoCVC;
    
    /**
     * Constructor utilizado para la creación de tarjetas en el programa
     * @param noPasaporte
     * @param dineroActual
     */
    public Tarjeta(int noPasaporte, double dineroActual){

        this.noTarjeta=Integer.parseInt(com.mycompany.generadorCodigos.GenerarCodigo.generarCodigo(String.valueOf(this.noTarjeta), Constante.CARACTERES_NUMERO_TARJETA, false));
        this.noPasaporte=noPasaporte;
        this.dineroActual=dineroActual;
        this.codicoCVC = Integer.parseInt(com.mycompany.generadorCodigos.GenerarCodigo.generarCodigo(String.valueOf(this.codicoCVC), Constante.CARACTERES_CODIGO_CVC, false));
    }

    /**
     * Constructor utilizado para la carga de datos por medio de un archivo de texto
     * @param noTarjeta
     * @param noPasaporte
     * @param dineroActual
     * @param codigoCVC
     */
    
    public Tarjeta(int noTarjeta, int noPasaporte, double dineroActual, int codigoCVC){
        this.noTarjeta=noTarjeta;
        this.noPasaporte=noPasaporte;
        this.dineroActual=dineroActual;
        this.codicoCVC=codigoCVC;
    }

    public int getNoTarjeta(){return this.noTarjeta;}

    public int getNoPasaporte(){return this.noPasaporte;}

    public double getDinero(){return this.dineroActual;}

    public int getCodigoCVC(){return this.codicoCVC;}
}