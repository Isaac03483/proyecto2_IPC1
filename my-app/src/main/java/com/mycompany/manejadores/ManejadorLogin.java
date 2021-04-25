package com.mycompany.manejadores;

import javax.swing.JOptionPane;

import com.mycompany.archivos.ArchivoEmpleado;
import com.mycompany.persona.empleados.*;
import com.mycompany.ventanas.VentanaLog;

public class ManejadorLogin {
    
    private VentanaLog ventana;

    public ManejadorLogin(VentanaLog ventana){
        this.ventana = ventana;

    }

    public void verificarUsuario(){

        Empleado empleado = ArchivoEmpleado.leerEmpleado(this.ventana.getUsuario());
        if(empleado != null){
            
            if(empleado.getContrasena().equals(this.ventana.getContrasena())){
                if(empleado instanceof Administrador){
                    JOptionPane.showMessageDialog(null, "Administrador", "AeroValamDevs", JOptionPane.INFORMATION_MESSAGE);
                } else if(empleado instanceof Operador){
                    JOptionPane.showMessageDialog(null, "Operador", "AeroValamDevs", JOptionPane.INFORMATION_MESSAGE);
                } else if(empleado instanceof Gerente){
                    JOptionPane.showMessageDialog(null, "Gerente", "AeroValamDevs", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario incorrecto", "AeroValamDevs", JOptionPane.INFORMATION_MESSAGE);
                
            }
        }
    }
}
