package com.mycompany.archivos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mycompany.constantes.Constante;
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

    public static ArrayList<Empleado> leerEmpleados(){

        ArrayList<Empleado> empleados = new ArrayList<>();
        String [] archivos = Constante.RUTA_EMPLEADOS.list();
        ObjectInputStream lector;
        if(archivos.length == 0){
            return null;

        } else {
            for(int i = 0; i < archivos.length; i++){

                String archivo = archivos[i];
                try {
                    lector = new ObjectInputStream(new FileInputStream(Constante.RUTA_EMPLEADOS+"/"+archivo));
                    Empleado empleado = (Empleado)lector.readObject();
                    empleados.add(empleado);
                    lector.close();
                } catch (FileNotFoundException e) {
                    
                    e.printStackTrace();
                } catch (IOException e) {
                    
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    
                    e.printStackTrace();
                }
            }
        }

        return empleados;
    }
}
