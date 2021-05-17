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

    /**
     * Constructor para manejo de la ventanaVuelos
     * @param ventana
     */
    public ManejadorVuelos(VentanaVuelos ventana){
        this.ventana = ventana;

    }

    /**
     * Método que revisa toda la información necesaria y las verificaciones
     * para poder comprar y generar una reservación del vuelo
     */
    public void accionComprar(){

        Tarjeta tarjetaUsuario = obtenerTarjeta(this.ventana.getNoPasaporte());
    
        if(tarjetaUsuario != null){
            if(tarjetaUsuario.getDinero() >= this.ventana.getVuelo().getPrecioBoleto()){
    
                String noAsiento = this.ventana.getTexto().getText();
                if(!noAsiento.equals("")){
                    if(!Verificaciones.verificarAsientoOcupado(this.ventana.getAvion(), noAsiento)){
                        Pasaporte pasaporteUsuario = obtenerPasaporte(this.ventana.getNoPasaporte());
                        if(pasaporteUsuario != null){

                            tarjetaUsuario.setDinero(this.ventana.getVuelo().getPrecioBoleto());
                            ArchivoTarjeta.agregarTarjeta(tarjetaUsuario);
                            ArchivoReservacion.agregarReservacion(new Reservacion(this.ventana.getNoPasaporte(), this.ventana.getVuelo().getCodigoVuelo(), tarjetaUsuario.getNoTarjeta(), noAsiento));
                            System.out.println(noAsiento);

                            JOptionPane.showMessageDialog(null, "Boleto comprado con éxito.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
                            if(pasaporteUsuario.getContrasena().equals("")){
                                pasaporteUsuario.generarContrasena();
                                JOptionPane.showMessageDialog(null, "Tu contraseña por primera compra es: \n"+pasaporteUsuario.getContrasena());
                            }
                            pasaporteUsuario.setBoletosComprados();
                            ArchivoPasaporte.agregarPasaporte(pasaporteUsuario);
                            this.ventana.dispose();
                        }
                                
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

    /**
     * método privado que retorna el pasaporte del usuario 
     * @param noPasaporte
     * @return
     */
    private Pasaporte obtenerPasaporte(int noPasaporte){

        ArrayList<Pasaporte> pasaportes = ArchivoPasaporte.leerPasaporte();
        if(pasaportes != null){

            for(Pasaporte pasaporte: pasaportes){
                if(pasaporte.getNoPasaporte() == noPasaporte){
                    return pasaporte;
                }
            }
        }
        return null;
    }

    /**
     * método privado que retorna la tarjeta asociada al pasaporte del usuario
     * @param noPasaporte
     * @return
     */
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
