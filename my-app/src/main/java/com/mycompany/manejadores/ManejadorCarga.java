package com.mycompany.manejadores;

import java.io.File;

import javax.swing.*;

import com.mycompany.ventanas.VentanaCarga;

public class ManejadorCarga {

    private VentanaCarga carga;
    private String archivoALeer;
    private String nombreArchivo;

    public ManejadorCarga(VentanaCarga carga){

        this.carga = carga;
    }

    public void accionCargar(){
        JFileChooser buscarArchivo = new JFileChooser();

        int opcion = buscarArchivo.showOpenDialog(this.carga);

        if(opcion == JFileChooser.APPROVE_OPTION){

            String archivo = buscarArchivo.getSelectedFile().getAbsolutePath();
            String archivoUno = buscarArchivo.getSelectedFile().toString();

            this.archivoALeer = archivo;
            this.nombreArchivo = archivoUno;
        }

        try{
            File archivo = new File(archivoALeer);
            if(archivo.exists()){
                
            }
        } catch(NullPointerException e){
            
        }
    }
    
}
