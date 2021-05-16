package com.mycompany.aeropuerto;

import java.io.Serializable;

public class AeroPuerto implements Serializable{
    
    private String nombreAeroPuerto;
    private String ciudad;
    private String pais;
    private int ganancias;

    public AeroPuerto(String nombreAeroPuerto, String ciudad, String pais){

        this.nombreAeroPuerto = nombreAeroPuerto;
        this.ciudad = ciudad;
        this.pais = pais;
        this.ganancias =0;
    }

    public String getNombreAeroPuerto(){return this.nombreAeroPuerto;}

    public String getCiudad(){return this.ciudad;}

    public String getPais(){return this.pais;}

    public int getGanancias(){return this.ganancias;}

    public void setGanancias(int ganancias){this.ganancias+=ganancias;}

    @Override
    public String toString(){return this.nombreAeroPuerto+"-"+this.ciudad;}

}
