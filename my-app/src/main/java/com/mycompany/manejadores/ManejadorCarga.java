package com.mycompany.manejadores;

import java.io.File;

import javax.swing.*;

import com.mycompany.cargaDatos.HiloCarga;
import com.mycompany.ventanas.Menu;
import com.mycompany.ventanas.VentanaCarga;

public class ManejadorCarga {

    private VentanaCarga carga;
    private String archivoALeer;
    private String nombreArchivo;
    private HiloCarga hilo;


    public ManejadorCarga(VentanaCarga carga){

        this.carga = carga;
    }

    public void accionAdministrar(){
        this.carga.getAdmin().menuAdministrador(this.carga);
    }  
    
    public void accionPrincipal(){
        Menu menu = new Menu();
        menu.setVisible(true);
        this.carga.dispose();
        
    }

    public void accionCargar(){
        JFileChooser buscarArchivo = new JFileChooser();

        int opcion = buscarArchivo.showOpenDialog(this.carga);

        if(opcion == JFileChooser.APPROVE_OPTION){

            String archivo = buscarArchivo.getSelectedFile().getAbsolutePath();
            String archivoUno = buscarArchivo.getSelectedFile().toString();

            this.archivoALeer = archivo;
            this.nombreArchivo = archivoUno;

            try{
                File archivoCargar = new File(archivoALeer);
                if(archivoCargar.exists()){
                    
                    hilo = new HiloCarga(archivoCargar, this.archivoALeer, this.carga);
                    hilo.start();
                }
            } catch(NullPointerException e){
                
            }

        }

    }
    
}
