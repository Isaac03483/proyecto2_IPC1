package com.mycompany.archivos;

import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mycompany.constantes.*;
import com.mycompany.aeropuerto.AeroLinea;

public class ArchivoAeroLinea {
    

    public static void guardarAeroLinea(AeroLinea aerolinea){

        try {
            File archivo = new File(Constante.RUTA_AEROPUERTOS+"/"+aerolinea.getNombreAeroPuerto());
            if(archivo.exists()){
                File aero = new File(Constante.RUTA_AEROLINEAS+"/"+aerolinea.getNombreAeroLinea());
                if(aero.exists()){
                    JOptionPane.showMessageDialog(null, "Ya existe una Aerolinea con ese nombre.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ObjectOutputStream objeto = new ObjectOutputStream(new FileOutputStream(Constante.RUTA_AEROLINEAS+"/"+aerolinea.getNombreAeroLinea()));
                    objeto.writeObject(aerolinea);
                    objeto.close();
                }
            } else {
                JOptionPane.showMessageDialog(null, "No existe un aeropuerto con ese nombre.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

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
        }

        return aeroLineas;
    }
}
