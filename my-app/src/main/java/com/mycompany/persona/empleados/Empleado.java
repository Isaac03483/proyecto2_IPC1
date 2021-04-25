package com.mycompany.persona.empleados;

import com.mycompany.constantes.Constante;
import com.mycompany.persona.Persona;

public class Empleado extends Persona{

    protected String usuario;
    protected String contrasena;

    {
        this.contrasena="";
    }
    public Empleado(String nombre, String apellido) {
        super(nombre, apellido);
        this.usuario = com.mycompany.generadorCodigos.GenerarCodigo.generarCodigo((this.nombre+this.apellido), (this.nombre.length()+ this.apellido.length() + 3), false);
        this.contrasena = com.mycompany.generadorCodigos.GenerarCodigo.generarCodigo(this.contrasena, Constante.CARACTERES_CONTRASENA, true);
    }

    public String getUsuario(){return this.usuario;}

    public String getContrasena(){return this.contrasena;}
    
}
