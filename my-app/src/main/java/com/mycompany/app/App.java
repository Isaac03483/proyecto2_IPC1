package com.mycompany.app;

import com.mycompany.ventanas.*;
import com.mycompany.aeropuerto.*;
import com.mycompany.archivos.*;
import com.mycompany.persona.empleados.*;
public class App {

    
    public static void main( String[] args ){

        Menu menu = new Menu("AeroBalamDevs");

        agregarConstantes();
    }

    private static void agregarConstantes(){
        AeroPuerto aeropuerto = new AeroPuerto("AuroraDos", "Guatemala", "Guatemala");
        ArchivoAeroPuerto.guardarAeroPuerto(aeropuerto);
        AeroLinea aerolinea = new AeroLinea("AuroraTres", "aeroLineaDos");
        ArchivoAeroLinea.guardarAeroLinea(aerolinea);
        Administrador admin = new Administrador("Pablo", "Fernández");
        ArchivoEmpleado.guardarEmpleado(admin);
        System.out.println(admin.getUsuario());
        System.out.println(admin.getContrasena());
        Operador opera = new Operador("Samuel", "Rodriguez");
        ArchivoEmpleado.guardarEmpleado(opera);
        System.out.println(opera.getUsuario());
        System.out.println(opera.getContrasena());
    }
}
