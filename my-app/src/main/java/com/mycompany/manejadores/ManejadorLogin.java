package com.mycompany.manejadores;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mycompany.archivos.ArchivoEmpleado;
import com.mycompany.constantes.Constante;
import com.mycompany.persona.empleados.*;
import com.mycompany.ventanas.*;

public class ManejadorLogin {
    
    private VentanaLog ventana;

    /**
     * constructor para ventana de log
     * @param ventana
     */
    public ManejadorLogin(VentanaLog ventana){
        this.ventana = ventana;

    }

    /**
     * métdo que verifica los datos ingresados y busca al empleado
     * @param menu
     */
    public void verificarUsuario(Menu menu){

        ArrayList<Empleado> empleados = ArchivoEmpleado.leerEmpleados();
        
        if(empleados != null){

            Empleado empleado =verificarExistencia(empleados, this.ventana.getContrasena(), this.ventana.getUsuario());
            if(empleado != null){

                if(empleado instanceof Administrador){
                    ((Administrador)empleado).seleccionarAeroPuerto(menu);
                } else if (empleado instanceof Gerente){
                    ((Gerente)empleado).menuGerente(menu);
                } else if (empleado instanceof Operador){
                    VentanaOperador operador = new VentanaOperador(empleado);
                    operador.setVisible(true);
                    menu.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario incorrecto.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * método que verifica que exista el empleado
     * @param empleados
     * @param contrasena
     * @param usuario
     * @return
     */
    private Empleado verificarExistencia(ArrayList<Empleado> empleados, String contrasena, String usuario){

        if(empleados != null){
            
            for(Empleado emp: empleados){

                if(emp.getUsuario().equals(usuario)){
                    if(emp.getContrasena().equals(contrasena)){
                        return emp;
                    }
                }
            }
        }

        return null;

    }
}
