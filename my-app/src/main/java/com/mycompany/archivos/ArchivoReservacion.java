package com.mycompany.archivos;

import java.io.*;
import java.util.*;

import com.mycompany.constantes.Constante;
import com.mycompany.persona.pasajero.Reservacion;

public class ArchivoReservacion {
    
    public static void agregarReservacion(Reservacion reservacion){

        FileOutputStream file;
        try {
            file = new FileOutputStream(Constante.RUTA_RESERVACIONES+"/"+reservacion.getCodigoVuelo()+"_"+reservacion.getNoPasaporte());
            ObjectOutputStream objeto = new ObjectOutputStream(file);
            objeto.writeObject(reservacion);
            objeto.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static ArrayList<Reservacion> leerReservacion(){

        ArrayList<Reservacion> reservaciones = new ArrayList<>();
        File[] archivos = Constante.RUTA_RESERVACIONES.listFiles();

        if(archivos.length == 0){
            return null;
        } else {
            for(int i = 0; i < archivos.length; i++){
                try {
                    ObjectInputStream lector = new ObjectInputStream(new FileInputStream(archivos[i]));
                    Reservacion reservacion = (Reservacion)lector.readObject();
                    reservaciones.add(reservacion);
                    lector.close();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
            return reservaciones;
        }
    }
}
