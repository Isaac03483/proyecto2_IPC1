package com.mycompany.manejadores;

import java.util.ArrayList;
import com.mycompany.aeropuerto.AeroLinea;
import com.mycompany.archivos.ArchivoAeroLinea;
import com.mycompany.ventanas.VentanaCliente;

public class ManejadorCliente {
    
    private VentanaCliente cliente;

    public ManejadorCliente(VentanaCliente cliente){
        this.cliente = cliente;
    }


    public void agregarAeroLineas(){

        ArrayList<AeroLinea> aeroLineas= ArchivoAeroLinea.leerAeroLinea();

        if(aeroLineas != null){
            for(AeroLinea aero: aeroLineas){
                this.cliente.getCombo().addItem(aero);
            }
        }
    }
}
