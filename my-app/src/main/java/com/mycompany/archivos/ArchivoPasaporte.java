package com.mycompany.archivos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

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

    public static ArrayList<Pasaporte> leerPasaporte(){

        ArrayList<Pasaporte> pasaportes = new ArrayList<>();
        String[] archivos = Constante.RUTA_PASAPORTES.list();
        ObjectInputStream lector;

        if(archivos.length == 0){
            return null;

        } else {
            for(int i = 0; i < archivos.length; i++){

                String archivo = archivos[i];
                try {
                    lector = new ObjectInputStream(new FileInputStream(Constante.RUTA_PASAPORTES+"/"+archivo));
                    Pasaporte pasaporte = (Pasaporte)lector.readObject();
                    pasaportes.add(pasaporte);
                    lector.close();
                } catch (FileNotFoundException e) {
                    
                    e.printStackTrace();
                } catch (IOException e) {
                    
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    
                    e.printStackTrace();
                }
            }

            return pasaportes;
        }
    }

    public static void renovacionPasaporte(int noPasaporte, Date fechaRenovacion){
        
        ArrayList<Pasaporte> pasaportes = leerPasaporte();

        if(pasaportes != null){

            for(Pasaporte pasaporte: pasaportes){
                if(pasaporte.getNoPasaporte() == noPasaporte){
                    pasaporte.setFechaVencimiento(fechaRenovacion);
                    agregarPasaporte(pasaporte);
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe dicho pasaporte.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
