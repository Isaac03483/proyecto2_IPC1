package com.mycompany.archivos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.mycompany.aeropuerto.Avion;
import com.mycompany.constantes.Constante;

public class ArchivoAvion {
    

    public static void agregarAvion(Avion avion){
        
        FileOutputStream archivo;
        try{
            
            archivo = new FileOutputStream(Constante.RUTA_AVIONES+"/"+avion.getCodigoAvion());
            ObjectOutputStream escritor = new ObjectOutputStream(archivo);

            escritor.writeObject(avion);

            escritor.close();
        } catch(FileNotFoundException e){

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
