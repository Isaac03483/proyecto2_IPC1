package com.mycompany.app;

import com.mycompany.ventanas.*;
import com.mycompany.archivos.*;

import com.mycompany.persona.empleados.*;

public class App {

    
    public static void main( String[] args ){

        Menu menu = new Menu();
        menu.setVisible(true);
        
        agregarConstantes();
    }

    private static void agregarConstantes(){
        Administrador admin = new Administrador("Pablo", "Fernández");
        ArchivoEmpleado.guardarEmpleado(admin);
        System.out.println(admin.getUsuario());
        System.out.println(admin.getContrasena());
    }
}
