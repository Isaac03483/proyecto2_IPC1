package com.mycompany.persona.empleados;

import javax.swing.JOptionPane;

import com.mycompany.ventanas.VentanaGerente;
import com.mycompany.ventanas.VentanaOperador;

public class Gerente extends Empleado{

    private String nombreAeroLinea;

    public Gerente(String nombre, String apellido,String nombreAeroLinea) {
        super(nombre, apellido);
        this.nombreAeroLinea=nombreAeroLinea;
    }

    public String getNombreAeroLinea(){return this.nombreAeroLinea;}
    
    public void menuGerente(){
        int opcionMenu;

        try{
            do{
                opcionMenu = Integer.parseInt(JOptionPane.showInputDialog(null, ".:Menu Gerente:."
                +"\n0. Volver."
                +"\n1. Ver reportes."
                +"\n2. Operar Vuelo.", "AeroBalamDevs", JOptionPane.INFORMATION_MESSAGE));
                switch(opcionMenu){
                    case 0:
                    break;
                    case 1:
                    VentanaGerente ventanaGerente = new VentanaGerente(this);
                    ventanaGerente.setVisible(true);
                    break;
                    case 2:
                    VentanaOperador ventanaEmpleado = new VentanaOperador();
                    ventanaEmpleado.setVisible(true);
                }
            } while(opcionMenu <0 || opcionMenu > 2);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Valor incorrecto.", "AeroBalamDevs", JOptionPane.ERROR_MESSAGE);
        }
    }
}
