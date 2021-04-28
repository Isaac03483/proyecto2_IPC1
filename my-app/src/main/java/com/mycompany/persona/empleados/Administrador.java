package com.mycompany.persona.empleados;

import javax.swing.JOptionPane;
import com.mycompany.ventanas.*;

public class Administrador extends Gerente{

    private Menu menu;
    public Administrador(String nombre, String apellido) {
        super(nombre, apellido);
        
    }

    public void menuAdministrador(Menu menu){
        this.menu = menu;
        int opcionMenu;
        try{
            do{
                opcionMenu = Integer.parseInt(JOptionPane.showInputDialog(null, ".:Menú Administrador:."
                +"\n0. Volver."
                +"\n1. Cargar Datos."
                +"\n2. Añadir empleado."
                +"\n3. Ver reportes."
                +"\n4. Operar vuelos.", "AeroBalamDevs", JOptionPane.INFORMATION_MESSAGE));
                switch(opcionMenu){
                    case 0:
                    break;
                    case 1: 
                    VentanaCarga carga = new VentanaCarga();
                    carga.setVisible(true);
                    this.menu.dispose();
                    break;
                    case 2:
                    VentanaCrearEmpleado crear = new VentanaCrearEmpleado();
                    crear.setVisible(true);
                    this.menu.dispose();
                    break;
                    case 3:
                    break;
                    case 4:
                    break;
                    default:
                    JOptionPane.showMessageDialog(null, "La opción seleccionada es incorrecta.", "AeroBalamDevs", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }while(opcionMenu < 0 || opcionMenu > 3);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Solo puede ingresar números.", "AeroBalamDevs", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
}
