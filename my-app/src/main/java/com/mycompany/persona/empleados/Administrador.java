package com.mycompany.persona.empleados;

import java.util.ArrayList;

import javax.swing.*;

import com.mycompany.aeropuerto.AeroPuerto;
import com.mycompany.archivos.ArchivoAeroPuerto;
import com.mycompany.constantes.Constante;
import com.mycompany.ventanas.*;

public class Administrador extends Empleado{

    private JFrame menu;
    private String nombreAeroPuertoActual;
    private AeroPuerto[] aeroPuertos;

    public Administrador(String nombre, String apellido) {
        super(nombre, apellido);
        
    }

    public String getAeroPuertoActual(){return this.nombreAeroPuertoActual;}

    public void seleccionarAeroPuerto(JFrame menu){

        int opcionMenu;
        try{
            do{
                opcionMenu = Integer.parseInt(JOptionPane.showInputDialog(null,".:AeroPuertos:."
                +"\n0. Volver."
                +"\n"+listarAeroPuertos(),Constante.TITULO,JOptionPane.INFORMATION_MESSAGE));
                switch(opcionMenu){
                    case 0:
                    break;
                    default:
                    this.nombreAeroPuertoActual = this.aeroPuertos[opcionMenu-1].getNombreAeroPuerto();
                    menuAdministrador(menu);
                    break;
                }
            }while(opcionMenu < 0 || opcionMenu > this.aeroPuertos.length);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Solo puede ingresar números.", "AeroBalamDevs", JOptionPane.ERROR_MESSAGE);
        }catch(ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "No existe ese aeropuerto.", "AeroBalamDevs", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void menuAdministrador(JFrame menu){
        this.menu = menu;
        int opcionMenu;
        try{
            do{
                opcionMenu = Integer.parseInt(JOptionPane.showInputDialog(null, ".:Menú Administrador:."
                +"\n0. Volver."
                +"\n1. Cargar Datos."
                +"\n2. Añadir empleado."
                +"\n3. Añadir Aerolinea/Aeropuerto."
                +"\n4. Ver reportes."
                +"\n5. Operar vuelos."
                +"\n6. Crear vuelo.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE));
                switch(opcionMenu){
                    case 0:
                    break;
                    case 1: 
                    VentanaCarga carga = new VentanaCarga(this);
                    carga.setVisible(true);
                    this.menu.dispose();
                    break;
                    case 2:
                    VentanaCrearEmpleado crear = new VentanaCrearEmpleado(this);
                    crear.setVisible(true);
                    this.menu.dispose();
                    break;
                    case 3:
                    VentanaCrearAero aero = new VentanaCrearAero(this);
                    aero.setVisible(true);
                    this.menu.dispose();
                    break;
                    case 4:
                    break;
                    case 5:
                    break;
                    case 6:
                    VentanaCrearVuelo vuelo = new VentanaCrearVuelo(this);
                    vuelo.setVisible(true);
                    this.menu.dispose();
                    break;
                    default:
                    JOptionPane.showMessageDialog(null, "La opción seleccionada es incorrecta.", "AeroBalamDevs", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }while(opcionMenu < 0 || opcionMenu > 6);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Solo puede ingresar números.", "AeroBalamDevs", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String listarAeroPuertos(){

        ArrayList<AeroPuerto> aeros = ArchivoAeroPuerto.leerAeroPuertos();
        this.aeroPuertos = new AeroPuerto[aeros.size()];
        String listaAeroPuertos="";
        int i =0;

        if(aeroPuertos.length != 0){
            for(AeroPuerto aeroPuerto: aeros){
                this.aeroPuertos[i] = aeroPuerto;
                listaAeroPuertos+=(i+1)+" "+this.aeroPuertos[i]+"\n";
                i++;
            }
            return listaAeroPuertos;
        }
        
        return null;
    }
    
    
}
