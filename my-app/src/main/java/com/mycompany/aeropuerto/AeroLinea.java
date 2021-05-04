package com.mycompany.aeropuerto;

import java.io.Serializable;

public class AeroLinea implements Serializable{
    
    private String nombreAeroPuerto;
    private String nombreAeroLinea;
    private double ganancias;
    
    {
        this.ganancias =0;
    }

    public AeroLinea(String nombreAeroPuerto, String nombreAeroLinea){

        this.nombreAeroPuerto=nombreAeroPuerto;
        this.nombreAeroLinea = nombreAeroLinea;
    }


    public String getNombreAeroLinea(){return this.nombreAeroLinea;}

    public String getNombreAeroPuerto(){return this.nombreAeroPuerto;}

    public void setGanancias(double ganancias){this.ganancias+=ganancias;}

    public double getGanancias(){return this.ganancias;}

    @Override
    public String toString(){return this.nombreAeroLinea;}
}
