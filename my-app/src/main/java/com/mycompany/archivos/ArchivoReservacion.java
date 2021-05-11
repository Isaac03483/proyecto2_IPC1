package com.mycompany.archivos;

import java.io.*;
import java.util.*;

import com.mycompany.aeropuerto.Vuelo;
import com.mycompany.aeropuerto.avion.Avion;
import com.mycompany.aeropuerto.avion.objeto_avion.Asiento;
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
            
            ArrayList<Vuelo> vuelos = ArchivoVuelo.leerVuelos();
            ArrayList<Avion> aviones = ArchivoAvion.leerAvion();
            Avion avionSelecciando=null;
            int codigoAvion=0;
            if(vuelos != null){
                for(Vuelo vuelo: vuelos){
                    if(vuelo.getCodigoVuelo() == reservacion.getCodigoVuelo()){
                        codigoAvion = vuelo.getCodigoAvion();
                        break;
                    }
                }
            }
            if(codigoAvion != 0 && aviones != null){
                for(Avion avion: aviones){
                    if(avion.getCodigoAvion() == codigoAvion){
                        avionSelecciando = avion;
                        break;
                    }
                }
            }

            if(avionSelecciando != null){
                for(int i = 0; i < avionSelecciando.getObjetos().length; i++){
                    for(int j = 0; j < avionSelecciando.getObjetos()[i].length; j++){
                        if(avionSelecciando.getObjetos()[i][j] instanceof Asiento){
                            if(((Asiento)avionSelecciando.getObjetos()[i][j]).getNoAsiento().equals(reservacion.getNoAsiento())){
                                ((Asiento)avionSelecciando.getObjetos()[i][j]).setEstado();
                                ArchivoAvion.agregarAvion(avionSelecciando);
                                return;
                            }
                        }
                    }
                }
            }
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
