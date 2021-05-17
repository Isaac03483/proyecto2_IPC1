package com.mycompany.manejadores;

import com.mycompany.ventanas.*;

public class ManejadorMenu {
    
    private Menu menu;

    /**
     * Constructor de la ventana principal
     * @param menu
     */
    public ManejadorMenu(Menu menu){

        this.menu = menu;
    }


    /**
     * método que muestra la ventana del módulo de clientes
     */
    public void accionCliente(){
        
        VentanaCliente cliente = new VentanaCliente();
        cliente.setVisible(true);
        this.menu.dispose();
    }

    /**
     * método que muestra la ventana de login para el módulo de empleados
     */
    public void accionEmpresa(){
        VentanaLog login = new VentanaLog(this.menu, true);
        login.setVisible(true);
    }

}
