package com.mycompany.manejadores;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mycompany.archivos.*;
import com.mycompany.cargaDatos.Verificaciones;
import com.mycompany.constantes.Constante;
import com.mycompany.persona.pasajero.*;
import com.mycompany.ventanas.VentanaVuelos;

public class ManejadorVuelos {
    
    private VentanaVuelos ventana;

    public ManejadorVuelos(VentanaVuelos ventana){
        this.ventana = ventana;

    }

    public void accionComprar(){

        Tarjeta tarjetaUsuario = obtenerTarjeta(this.ventana.getNoPasaporte());
    
        if(tarjetaUsuario != null){
            if(tarjetaUsuario.getDinero() >= this.ventana.getVuelo().getPrecioBoleto()){
    
                String noAsiento = this.ventana.getTexto().getText();
                if(!noAsiento.equals("")){
                    if(!Verificaciones.verificarAsientoOcupado(this.ventana.getAvion(), noAsiento)){
                        tarjetaUsuario.setDinero(this.ventana.getVuelo().getPrecioBoleto());
                        ArchivoReservacion.agregarReservacion(new Reservacion(this.ventana.getNoPasaporte(), this.ventana.getVuelo().getCodigoVuelo(), tarjetaUsuario.getNoTarjeta(), noAsiento));
                        System.out.println(noAsiento);
                                
                        JOptionPane.showMessageDialog(null, "Boleto comprado con éxito.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
                        this.ventana.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Asiento ocupado.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            } else {
                System.err.println("No tiene el dinero suficiente.");
            }
        } else {
            System.err.println("No se ha encontrado la tarjeta.");
    
        }
    }

    private Tarjeta obtenerTarjeta(int noPasaporte){

        ArrayList<Tarjeta> tarjetas= ArchivoTarjeta.leerTarjeta();

        if(tarjetas != null){
            for(Tarjeta tarjeta: tarjetas){
                if(tarjeta.getNoPasaporte() == noPasaporte){
                    return tarjeta;
                }
            }
        }

        return null;
    }
}
