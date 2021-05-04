package com.mycompany.archivos;

import java.io.*;
import java.util.*;

import com.mycompany.aeropuerto.Vuelo;
import com.mycompany.constantes.Constante;

public class ArchivoVuelo {
    
    public static void agregarVuelo(Vuelo vuelo){

        FileOutputStream file;
        try {
            file = new FileOutputStream(Constante.RUTA_VUELOS+"/"+vuelo.getCodigoVuelo()+"_"+vuelo.getAeroPuertoOrigen());
            ObjectOutputStream objeto = new ObjectOutputStream(file);
            objeto.writeObject(vuelo);
            objeto.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static ArrayList<Vuelo> leerVuelos(){

        ArrayList<Vuelo> vuelos = new ArrayList<>();
        File[] archivos = Constante.RUTA_VUELOS.listFiles();

        if(archivos.length == 0){
            return null;
        } else {
            for(int i = 0; i < archivos.length; i++){
                try {
                    ObjectInputStream lector = new ObjectInputStream(new FileInputStream(archivos[i]));
                    Vuelo vuelo = (Vuelo)lector.readObject();
                    vuelos.add(vuelo);
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
            return vuelos;
        }
    }
}
