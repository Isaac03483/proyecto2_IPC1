package com.mycompany.manejadores;

import com.mycompany.ventanas.*;

public class ManejadorMenu {
    
    private Menu menu;

    public ManejadorMenu(Menu menu){

        this.menu = menu;
    }


    public void accionCliente(){
        
        VentanaCliente cliente = new VentanaCliente();
        cliente.setVisible(true);
        this.menu.dispose();
    }

    public void accionEmpresa(){
        VentanaLog login = new VentanaLog(this.menu, true);
        login.setVisible(true);
    }

}
