package com.mycompany.archivos;

import java.io.*;
import java.util.ArrayList;
import com.mycompany.constantes.*;
import com.mycompany.aeropuerto.AeroLinea;

public class ArchivoAeroLinea {
    

    /**
     * método estático que carga el aeropuerto a un archivo binario
     * @param aerolinea
     */
    public static void guardarAeroLinea(AeroLinea aerolinea){

        try {
                  
            ObjectOutputStream objeto = new ObjectOutputStream(new FileOutputStream(Constante.RUTA_AEROLINEAS+"/"+aerolinea.getNombreAeroLinea()+"_"+aerolinea.getNombreAeroPuerto()));
            objeto.writeObject(aerolinea);
            objeto.close();                
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    /**
     * método estático que obtiene todas las aerolieneas guardadas
     * en archivos binarios y las retorna
     * @return
     */
    public static ArrayList<AeroLinea> leerAeroLinea(){

        ArrayList<AeroLinea> aeroLineas = new ArrayList<>();
        String[] archivos = Constante.RUTA_AEROLINEAS.list();
        ObjectInputStream lector;

        if(archivos.length == 0){
            return null;

        } else {
            for(int i = 0; i < archivos.length; i++){

                String archivo = archivos[i];
                try {
                    lector = new ObjectInputStream(new FileInputStream(Constante.RUTA_AEROLINEAS+"/"+archivo));
                    AeroLinea aeroLinea = (AeroLinea)lector.readObject();
                    aeroLineas.add(aeroLinea);
                    lector.close();
                } catch (FileNotFoundException e) {
                    
                    e.printStackTrace();
                } catch (IOException e) {
                    
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    
                    e.printStackTrace();
                }
            }
            return aeroLineas;
        }

    }
}
