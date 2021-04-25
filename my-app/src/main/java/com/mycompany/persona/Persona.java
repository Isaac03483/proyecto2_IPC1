package com.mycompany.persona;

import java.io.Serializable;

public class Persona implements Serializable{

    protected String nombre;
    protected String apellido;
    
    public Persona(final String nombre, final String apellido){
        this.nombre = nombre;
        this.apellido=apellido;
    }

    public String getNombre(){return this.nombre;}

    public String getApellido(){return this.apellido;}
}