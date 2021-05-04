package com.mycompany.archivos;

import java.io.*;
import java.util.*;

import com.mycompany.aeropuerto.Distancia;
import com.mycompany.constantes.Constante;

public class ArchivoDistancia {
    
    public static void agregarDistancia(Distancia distancia){

        FileOutputStream file;
        try {
            file = new FileOutputStream(Constante.RUTA_DISTANCIAS+"/"+distancia.getAeroPuertoOrigen()+"_"+distancia.getAeroPuertoDestino());
            ObjectOutputStream objeto = new ObjectOutputStream(file);
            objeto.writeObject(distancia);
            objeto.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static ArrayList<Distancia> leerDistancias(){

        ArrayList<Distancia> distancias = new ArrayList<>();
        File[] archivos = Constante.RUTA_DISTANCIAS.listFiles();

        if(archivos.length == 0){
            return null;
        } else {
            for(int i = 0; i < archivos.length; i++){
                try {
                    ObjectInputStream lector = new ObjectInputStream(new FileInputStream(archivos[i]));
                    Distancia distancia = (Distancia)lector.readObject();
                    distancias.add(distancia);
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
            return distancias;
        }
    }
}
