package com.mycompany.persona.empleados;

import javax.swing.JOptionPane;
import com.mycompany.ventanas.*;

public class Administrador extends Gerente{

    public Administrador(String nombre, String apellido) {
        super(nombre, apellido);
        
    }

    public void menuAdministrador(){
        int opcionMenu;
        do{
            opcionMenu = Integer.parseInt(JOptionPane.showInputDialog(null, ".:Menú Administrador:."
            +"\n0. Volver."
            +"\n1.Añadir empleado."
            +"\n2.Ver reportes."
            +"\n3.Operar vuelos.", "AeroBalamDevs", JOptionPane.INFORMATION_MESSAGE));
            switch(opcionMenu){
                case 0:
                break;
                case 1: 
                VentanaCrearEmpleado crear = new VentanaCrearEmpleado();
                crear.setVisible(true);
                break;
                case 2:
                break;
                case 3:
                break;
                default:
                break;
            }
        }while(opcionMenu != 0);
    }
    
    
}
