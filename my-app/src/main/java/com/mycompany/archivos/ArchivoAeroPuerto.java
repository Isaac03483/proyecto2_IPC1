package com.mycompany.archivos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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

    public static void leerAeroPuerto(){

        ArrayList<AeroPuerto> aeropuertos = new ArrayList<>();
    }
}
