package com.mycompany.archivos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import com.mycompany.constantes.Constante;
import com.mycompany.persona.empleados.Administrador;
import com.mycompany.persona.empleados.Empleado;

public class ArchivoEmpleado {
    
    public static void guardarEmpleado(Empleado empleado){

        FileOutputStream archivo;

        try {
            archivo = new FileOutputStream(Constante.RUTA_EMPLEADOS+"/"+empleado.getUsuario());
            ObjectOutputStream escritor = new ObjectOutputStream(archivo);

            escritor.writeObject(empleado);
            escritor.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        
            e.printStackTrace();
        }
    }

    public static Empleado leerEmpleado(String usuario){

        FileInputStream archivo;
        try {
            archivo = new FileInputStream(Constante.RUTA_EMPLEADOS+"/"+usuario);
            ObjectInputStream lector;

            lector = new ObjectInputStream(archivo);

            Empleado empleado = (Empleado)lector.readObject();
            lector.close();

            return empleado;
            
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Este usuario no existe.", "AeroBalamDevs", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            
            e.printStackTrace();
        }
        return null;
    }
}
