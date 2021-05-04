package com.mycompany.ventanas;

import javax.swing.*;

import com.mycompany.persona.empleados.Empleado;

public class VentanaGerente extends JFrame{
    
    private Empleado empleado;

    public VentanaGerente(Empleado empleado){
        this.empleado = empleado;
    }
}
