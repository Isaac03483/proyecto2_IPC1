package com.mycompany.persona;

import java.io.Serializable;

public class Persona implements Serializable{

    protected String nombre;
    protected String apellido;
    
    /**
     * Constructor para la creación de personas
     * @param nombre
     * @param apellido
     */
    public Persona(final String nombre, final String apellido){
        this.nombre = nombre;
        this.apellido=apellido;
    }

    /**
     * retorna el nombre de la persona
     * @return
     */
    public String getNombre(){return this.nombre;}

    /**
     * retorna el apellido de la persona
     * @return
     */
    public String getApellido(){return this.apellido;}
}