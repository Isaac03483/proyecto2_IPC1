package com.mycompany.aeropuerto;

import java.io.Serializable;

public class AeroPuerto implements Serializable{
    
    private String nombreAeroPuerto;
    private String ciudad;
    private String pais;
    private int ganancias;

    /**
     * Constructor para inicializar un aeropuerto
     * @param nombreAeroPuerto
     * @param ciudad
     * @param pais
     */
    public AeroPuerto(String nombreAeroPuerto, String ciudad, String pais){

        this.nombreAeroPuerto = nombreAeroPuerto;
        this.ciudad = ciudad;
        this.pais = pais;
        this.ganancias =0;
    }

    /**
     * retorna el nombre del aeropuerto
     * @return
     */
    public String getNombreAeroPuerto(){return this.nombreAeroPuerto;}

    /**
     * retorna el nombre de la ciudad en la que se encuentra el aeropuerto
     * @return
     */
    public String getCiudad(){return this.ciudad;}

    /**
     * retorna el nombre del país en el que se encuentra el aeropuerto
     * @return
     */
    public String getPais(){return this.pais;}

    /**
     * retorna las ganancias del aeropuerto
     * @return
     */
    public int getGanancias(){return this.ganancias;}

    /**
     * aumenta el valor de las ganancias del aeropuerto
     * @param ganancias
     */
    public void setGanancias(int ganancias){this.ganancias+=ganancias;}

    /**
     * método sobreescrito de la clase object
     */
    @Override
    public String toString(){return this.nombreAeroPuerto+"-"+this.ciudad;}

}
