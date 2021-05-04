package com.mycompany.persona.empleados;

public class Operador extends Empleado {
    
    private String nombreAeroLinea;
    public Operador(String nombre, String apellido, String nombreAerolinea) {
        super(nombre, apellido);
        this.nombreAeroLinea=nombreAerolinea;
    }

    public String getNombreAeroLinea(){return this.nombreAeroLinea;}
    
}
