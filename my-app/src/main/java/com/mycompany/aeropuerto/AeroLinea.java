package com.mycompany.aeropuerto;

import java.io.Serializable;

public class AeroLinea implements Serializable{
    
    private String nombreAeroPuerto;
    private String nombreAeroLinea;

    public AeroLinea(String nombreAeroPuerto, String nombreAeroLinea){

        this.nombreAeroPuerto=nombreAeroPuerto;
        this.nombreAeroLinea = nombreAeroLinea;
    }

    public String getNombreAeroLinea(){return this.nombreAeroLinea;}

    public String getNombreAeroPuerto(){return this.nombreAeroPuerto;}

    @Override
    public String toString(){return this.nombreAeroLinea;}
}
