package com.mycompany.archivos;


import java.io.*;
import java.util.*;

import com.mycompany.constantes.Constante;
import com.mycompany.persona.pasajero.Tarjeta;

public class ArchivoTarjeta {
    
    public static void agregarTarjeta(Tarjeta tarjeta){

        FileOutputStream file;
        try {
            file = new FileOutputStream(Constante.RUTA_TARJETAS+"/"+tarjeta.getCodigoCVC()+"_"+tarjeta.getNoTarjeta());
            ObjectOutputStream objeto = new ObjectOutputStream(file);
            objeto.writeObject(tarjeta);
            objeto.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static ArrayList<Tarjeta> leerTarjeta(){

        ArrayList<Tarjeta> tarjetas = new ArrayList<>();
        File[] archivos = Constante.RUTA_TARJETAS.listFiles();

        if(archivos.length == 0){
            return null;
        } else {
            for(int i = 0; i < archivos.length; i++){
                try {
                    ObjectInputStream lector = new ObjectInputStream(new FileInputStream(archivos[i]));
                    Tarjeta tarjeta = (Tarjeta)lector.readObject();
                    tarjetas.add(tarjeta);
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
            return tarjetas;
        }
    }
}
