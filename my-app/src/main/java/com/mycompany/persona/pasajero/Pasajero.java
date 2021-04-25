package com.mycompany.persona.pasajero;

import java.io.Serializable;

public class Pasajero extends com.mycompany.persona.Persona implements Serializable{

    private Tarjeta tarjeta;
    private Pasaporte pasaporte;   

    public Pasajero(String nombre, String apellido, Tarjeta tarjeta, Pasaporte pasaporte) {
        super(nombre, apellido);
        this.tarjeta=tarjeta;
        this.pasaporte=pasaporte;
    
    }

    public Tarjeta getTarjeta(){return this.tarjeta;}

    public Pasaporte getPasaporte(){return this.pasaporte;}
    
}
