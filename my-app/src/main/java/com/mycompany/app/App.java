package com.mycompany.app;

import com.mycompany.ventanas.*;
import com.mycompany.aeropuerto.*;
import com.mycompany.archivos.*;
import com.mycompany.constantes.Constante;
import com.mycompany.persona.empleados.*;

public class App {

    
    public static void main( String[] args ){

        Menu menu = new Menu(Constante.TITULO);

        agregarConstantes();
    }

    private static void agregarConstantes(){
        AeroPuerto aeropuerto = new AeroPuerto("AuroraDos", "Guatemala", "Guatemala");
        ArchivoAeroPuerto.guardarAeroPuerto(aeropuerto);
        AeroLinea aerolinea = new AeroLinea("AuroraDos", "aeroLineaUno");
        ArchivoAeroLinea.guardarAeroLinea(aerolinea);
        Administrador admin = new Administrador("Pablo", "Fernández");
        ArchivoEmpleado.guardarEmpleado(admin);
        System.out.println(admin.getUsuario());
        System.out.println(admin.getContrasena());
        Gerente gerente = new Gerente("Samuel", "Rodriguez");
        ArchivoEmpleado.guardarEmpleado(gerente);
        System.out.println(gerente.getUsuario());
        System.out.println(gerente.getContrasena());
    }
}
