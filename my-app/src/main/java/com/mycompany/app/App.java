package com.mycompany.app;

import com.mycompany.ventanas.*;
import com.mycompany.archivos.*;

import com.mycompany.persona.empleados.*;

public class App {

    /**
     * método main
     * @param args
     */
    public static void main( String[] args ){

        Menu menu = new Menu();
        menu.setVisible(true);
        
        agregarConstantes();
    }

    /**
     * agrega un administrador por defecto
     */
    private static void agregarConstantes(){
        Administrador admin = new Administrador("Pablo", "Fernández");
        ArchivoEmpleado.guardarEmpleado(admin);
        System.out.println(admin.getUsuario());
        System.out.println(admin.getContrasena());
    }
}
