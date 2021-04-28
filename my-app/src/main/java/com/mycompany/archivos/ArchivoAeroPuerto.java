package com.mycompany.archivos;

import java.io.*;
import java.util.ArrayList;

import com.mycompany.aeropuerto.AeroPuerto;
import com.mycompany.constantes.Constante;

public class ArchivoAeroPuerto {
    

    public static void guardarAeroPuerto(AeroPuerto aeropuerto){

        FileOutputStream file;
        try {
            file = new FileOutputStream(Constante.RUTA_AEROPUERTOS+"/"+aeropuerto.getNombreAeroPuerto());
            ObjectOutputStream objeto = new ObjectOutputStream(file);
            objeto.writeObject(aeropuerto);
            objeto.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public static ArrayList<AeroPuerto> leerAeroPuertos(){

        ArrayList<AeroPuerto> aeroPuertos = new ArrayList<>();
        String[] archivos = Constante.RUTA_AEROPUERTOS.list();
        ObjectInputStream lector;

        if(archivos.length == 0){
            return null;

        } else {
            for(int i = 0; i < archivos.length; i++){

                String archivo = archivos[i];
                try {
                    lector = new ObjectInputStream(new FileInputStream(Constante.RUTA_AEROPUERTOS+"/"+archivo));
                    AeroPuerto aeroPuerto= (AeroPuerto)lector.readObject();
                    aeroPuertos.add(aeroPuerto);
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

        return aeroPuertos;
    }
}
