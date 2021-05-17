package com.mycompany.aeropuerto;

import java.io.Serializable;

public class AeroLinea implements Serializable{
    
    private String nombreAeroPuerto;
    private String nombreAeroLinea;
    private double ganancias;
    
    /**
     * método oculto
     */
    {
        this.ganancias =0;
    }

    /**
     * Constructor para la carga de datos y creación mediante la interfaz gráfica
     * @param nombreAeroPuerto
     * @param nombreAeroLinea
     */
    public AeroLinea(String nombreAeroPuerto, String nombreAeroLinea){

        this.nombreAeroPuerto=nombreAeroPuerto;
        this.nombreAeroLinea = nombreAeroLinea;
    }

    /**
     * retorna el nombre de la aerolinea
     * @return
     */
    public String getNombreAeroLinea(){return this.nombreAeroLinea;}

    /**
     * retorna el nombre del aeropuerto asociado a la aerolinea
     * @return
     */
    public String getNombreAeroPuerto(){return this.nombreAeroPuerto;}

    /**
     * cambia el valor de las ganancias de la aerolinea
     * @param ganancias
     */
    public void setGanancias(double ganancias){this.ganancias+=ganancias;}

    /**
     * retorna el valor de las ganancias de la aerolinea
     * @return
     */
    public double getGanancias(){return this.ganancias;}

    /**
     * método sobreescrito de la clase object
     */
    @Override
    public String toString(){return this.nombreAeroLinea+" - "+this.nombreAeroPuerto;}
}
