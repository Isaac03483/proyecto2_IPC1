package com.mycompany.archivos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.mycompany.constantes.Constante;
import com.mycompany.persona.pasajero.Pasaporte;

public class ArchivoPasaporte {
    
    public static void agregarPasaporte(Pasaporte pasaporte){
        
        FileOutputStream archivo;

        try {
            
            archivo = new FileOutputStream(Constante.RUTA_PASAPORTES+"/"+pasaporte.getNoPasaporte());
            ObjectOutputStream escritor = new ObjectOutputStream(archivo);

            escritor.writeObject(pasaporte);
            escritor.close();
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
