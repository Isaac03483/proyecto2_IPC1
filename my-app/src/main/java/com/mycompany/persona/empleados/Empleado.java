package com.mycompany.persona.empleados;

import com.mycompany.constantes.Constante;
import com.mycompany.persona.Persona;

public class Empleado extends Persona{

    protected String usuario;
    protected String contrasena;

    {
        this.contrasena="";
    }
    
    /**
     * Constructor para la creación de empleados
     * @param nombre
     * @param apellido
     */
    public Empleado(String nombre, String apellido) {
        super(nombre, apellido);
        this.usuario = com.mycompany.generadorCodigos.GenerarCodigo.generarCodigo((this.nombre+this.apellido), (this.nombre.length()+ this.apellido.length() + 3), false);
        this.contrasena = com.mycompany.generadorCodigos.GenerarCodigo.generarCodigo(this.contrasena, Constante.CARACTERES_CONTRASENA, true);
    }

    /**
     * método que retorna el nombre de usuario del empleado
     * @return
     */
    public String getUsuario(){return this.usuario;}

    /**
     * método que retorna la contraseña del empleado
     * @return
     */
    public String getContrasena(){return this.contrasena;}
    
}
