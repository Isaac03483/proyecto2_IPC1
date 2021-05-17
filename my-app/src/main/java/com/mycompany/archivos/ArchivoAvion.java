package com.mycompany.archivos;

import java.io.*;
import java.util.*;

import com.mycompany.aeropuerto.avion.Avion;
import com.mycompany.constantes.Constante;

public class ArchivoAvion {
    

    /**
     * método estático que guarda el avión enviado en un archivo binario
     * @param avion
     */
    public static void agregarAvion(Avion avion){
        
        FileOutputStream archivo;
        try{
            
            archivo = new FileOutputStream(Constante.RUTA_AVIONES+"/"+avion.getCodigoAvion()+"_"+avion.getNombreAeroLinea());
            ObjectOutputStream escritor = new ObjectOutputStream(archivo);

            escritor.writeObject(avion);

            escritor.close();
        } catch(FileNotFoundException e){

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * método estático que guarda todos los archivos binarios de los aviones en un array y lo retorna
     * @return
     */
    public static ArrayList<Avion> leerAvion(){

        ArrayList<Avion> aviones = new ArrayList<>();
        File[] archivos = Constante.RUTA_AVIONES.listFiles();

        if(archivos.length == 0){
            return null;
        } else {
            for(int i = 0; i < archivos.length; i++){
                try {
                    ObjectInputStream lector = new ObjectInputStream(new FileInputStream(archivos[i]));
                    Avion avion = (Avion)lector.readObject();
                    aviones.add(avion);
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
            return aviones;
        }
    }
}
