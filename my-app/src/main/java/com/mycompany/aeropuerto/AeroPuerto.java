package com.mycompany.aeropuerto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AeroPuerto implements Serializable{
    
    private String nombreAeroPuerto;
    private String ciudad;
    private String pais;

    public AeroPuerto(String nombreAeroPuerto, String ciudad, String pais){

        this.nombreAeroPuerto = nombreAeroPuerto;
        this.ciudad = ciudad;
        this.pais = pais;
    }

    public String getNombreAeroPuerto(){return this.nombreAeroPuerto;}

    public String getCiudad(){return this.ciudad;}

    public String getPais(){return this.pais;}

}
