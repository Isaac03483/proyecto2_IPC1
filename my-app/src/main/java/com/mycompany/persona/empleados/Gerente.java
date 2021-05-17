package com.mycompany.persona.empleados;

import javax.swing.*;

import com.mycompany.constantes.Constante;
import com.mycompany.ventanas.VentanaOperador;

public class Gerente extends Empleado{

    private String nombreAeroLinea;
    private JFrame ventana;

    /**
     * Constructor para la creación de gerentes
     * @param nombre
     * @param apellido
     * @param nombreAeroLinea
     */
    public Gerente(String nombre, String apellido,String nombreAeroLinea) {
        super(nombre, apellido);
        this.nombreAeroLinea=nombreAeroLinea;
    }

    /**
     * método que retorna la aerolinea del gerente
     * @return
     */
    public String getNombreAeroLinea(){return this.nombreAeroLinea;}
    
    /**
     * menú con las distintas acciones del gerente
     * @param ventana
     */
    public void menuGerente(JFrame ventana){
        this.ventana = ventana;
        int opcionMenu;

        try{
            do{
                opcionMenu = Integer.parseInt(JOptionPane.showInputDialog(null, ".:MENÚ GERENTE:."
                +"\n0. Volver."
                +"\n1. Ver reportes."
                +"\n2. Operar Vuelo.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE));
                switch(opcionMenu){
                    case 0:
                    break;
                    case 1:
                    /*VentanaGerente ventanaGerente = new VentanaGerente(this);
                    ventanaGerente.setVisible(true);*/
                    this.ventana.dispose();
                    break;
                    case 2:
                    VentanaOperador ventanaEmpleado = new VentanaOperador(this);
                    ventanaEmpleado.setVisible(true);
                    this.ventana.dispose();
                    break;
                    default:
                    JOptionPane.showMessageDialog(null, "Valor incorrecto.", Constante.TITULO, JOptionPane.ERROR_MESSAGE);
                    break;
                }
            } while(opcionMenu <0 || opcionMenu > 2);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Valor incorrecto.", Constante.TITULO, JOptionPane.ERROR_MESSAGE);
        }
    }
}
