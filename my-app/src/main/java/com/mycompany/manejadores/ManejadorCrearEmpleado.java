package com.mycompany.manejadores;

import com.mycompany.ventanas.*;
import com.mycompany.archivos.ArchivoEmpleado;
import com.mycompany.cargaDatos.Verificaciones;
import com.mycompany.constantes.Constante;
import com.mycompany.persona.empleados.*;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mycompany.aeropuerto.*;

public class ManejadorCrearEmpleado {
    
    private VentanaCrearEmpleado crear;
    private Empleado empleado;

    public ManejadorCrearEmpleado(VentanaCrearEmpleado crear){
        this.crear = crear;
        empleado = null;
    }

    public void accionCrear(){
        
        String nombre = this.crear.getTextoNombre().getText();
        String apellido = this.crear.getTextoApellido().getText();
        String nombreAeroLinea = ((AeroLinea)this.crear.getCombo().getSelectedItem()).getNombreAeroLinea();
        
        if(!nombre.equals("") && !apellido.equals("")){
            if(this.crear.getRadioAdmin().isSelected()){

                empleado = new Administrador(nombre, apellido);
            } else if (this.crear.getRadioGerente().isSelected()){
    
                if(Verificaciones.GerenteConAerolinea(nombreAeroLinea)){
                    JOptionPane.showMessageDialog(null, "Esta aeroLinea ya cuenta con un Gerente.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
                    empleado = null;
                } else {
                    empleado = new Gerente(nombre, apellido, nombreAeroLinea);
                    
                }
            } else if(this.crear.getRadioOperador().isSelected()){
                empleado = new Operador(nombre, apellido, nombreAeroLinea);
                
            }
    
            if(empleado != null){
                ArchivoEmpleado.guardarEmpleado(empleado);
                JOptionPane.showMessageDialog(null, "Empleado creado con éxito."
                +"\n\nNombre usuario: "+empleado.getUsuario()
                +"\nContraseña: "+empleado.getContrasena()
                +"\n\nGuarda estos datos y no los compartas a nadie.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
                empleado = null;
            }
        } else {
            
            JOptionPane.showMessageDialog(null, "Uno de los campos se encuentra vacío.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void accionAdministrar(){

        this.crear.getAdmin().menuAdministrador(this.crear);
    }

    public void accionPrincipal(){

        Menu menu = new Menu();
        menu.setVisible(true);

        this.crear.dispose();
    }

    public void accionAeroPuerto(){
        this.crear.getAdmin().seleccionarAeroPuerto(this.crear);
    }
}
