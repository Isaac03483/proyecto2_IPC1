package com.mycompany.persona.empleados;

import javax.swing.*;

import com.mycompany.constantes.Constante;
import com.mycompany.ventanas.*;

public class Operador extends Empleado {
    
    private String nombreAeroLinea;
    private JFrame ventana;
    
    public Operador(String nombre, String apellido, String nombreAerolinea) {
        super(nombre, apellido);
        this.nombreAeroLinea=nombreAerolinea;
    }

    public String getNombreAeroLinea(){return this.nombreAeroLinea;}
}
