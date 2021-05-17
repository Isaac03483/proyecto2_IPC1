package com.mycompany.persona.empleados;

public class Operador extends Empleado {
    
    private String nombreAeroLinea;
    
    /**
     * Constructor para la creación de operadores
     * @param nombre
     * @param apellido
     * @param nombreAerolinea
     */
    public Operador(String nombre, String apellido, String nombreAerolinea) {
        super(nombre, apellido);
        this.nombreAeroLinea=nombreAerolinea;
    }

    /**
     * método que retorna la aerolinea del operador
     * @return
     */
    public String getNombreAeroLinea(){return this.nombreAeroLinea;}
}
