package com.mycompany.manejadores;

import java.io.File;
import javax.swing.*;
import com.mycompany.cargaDatos.HiloCarga;
import com.mycompany.ventanas.Menu;
import com.mycompany.ventanas.VentanaCarga;

public class ManejadorCarga {

    private VentanaCarga carga;
    private String archivoALeer;
    private HiloCarga hilo;


    /**
     * Cosntructor para  el manejo de la ventanaCarga
     * @param carga
     */
    public ManejadorCarga(VentanaCarga carga){

        this.carga = carga;
    }

    /**
     * método que muestra el menú de acciones del administrador
     */
    public void accionAdministrar(){
        this.carga.getAdmin().menuAdministrador(this.carga);
    }  
    
    /**
     * método que regresa al menú principal
     */
    public void accionPrincipal(){
        Menu menu = new Menu();
        menu.setVisible(true);
        this.carga.dispose();
        
    }

    /**
     * método que le pide al usuario  ingresar el documento a cargar
     * e inicia el hilo de carga
     */
    public void accionCargar(){
        JFileChooser buscarArchivo = new JFileChooser();

        int opcion = buscarArchivo.showOpenDialog(this.carga);

        if(opcion == JFileChooser.APPROVE_OPTION){

            String archivo = buscarArchivo.getSelectedFile().getAbsolutePath();

            this.archivoALeer = archivo;

            try{
                File archivoCargar = new File(archivoALeer);
                if(archivoCargar.exists()){
                    
                    hilo = new HiloCarga(archivoCargar, this.carga);
                    hilo.start();
                }
            } catch(NullPointerException e){
                
            }

        }

    }
    
}
