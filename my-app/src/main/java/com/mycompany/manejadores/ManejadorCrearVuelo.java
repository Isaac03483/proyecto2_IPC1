package com.mycompany.manejadores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import com.mycompany.aeropuerto.*;
import com.mycompany.aeropuerto.avion.*;
import com.mycompany.archivos.ArchivoVuelo;
import com.mycompany.cargaDatos.Verificaciones;
import com.mycompany.constantes.Constante;
import com.mycompany.ventanas.*;

public class ManejadorCrearVuelo {
    
    private VentanaCrearVuelo ventana;
    private SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");

    /**
     * Constructor para la ventanaCrearVuelo
     * @param ventana
     */
    public ManejadorCrearVuelo(VentanaCrearVuelo ventana){
        this.ventana = ventana;
    }

    /**
     * Método que verifica toda la información necesaria para la creación de vuelos
     */
    public void accionCrearVuelo(){
        
        String fecha = this.ventana.getTextoFecha().getText();
        String precio = this.ventana.getTextoPrecio().getText();
        Distancia distancia = (Distancia)this.ventana.getComboOrigenDestino().getSelectedItem();
        if(distancia != null){
            if(!fecha.equals("") && !precio.equals("")){

                try{
                    Date fechaSalida = formato.parse(fecha);
                    String nombreAeroPuertoOrigen = distancia.getAeroPuertoOrigen();
                    String nombreAeroPuertoDestino = distancia.getAeroPuertoDestino();
                    if(this.ventana.getComboCodigoAvion().getSelectedItem() != null){
                        int codigoAvion = ((Avion)this.ventana.getComboCodigoAvion().getSelectedItem()).getCodigoAvion();
                        if(Verificaciones.aeroLineaExistente(nombreAeroPuertoOrigen, ((Avion)this.ventana.getComboCodigoAvion().getSelectedItem()).getNombreAeroLinea()) && Verificaciones.aeroLineaExistente(nombreAeroPuertoDestino, ((Avion)this.ventana.getComboCodigoAvion().getSelectedItem()).getNombreAeroLinea())){

                            ArchivoVuelo.agregarVuelo(new Vuelo(codigoAvion, nombreAeroPuertoOrigen, nombreAeroPuertoDestino, Double.parseDouble(precio), fechaSalida));
                        } else {
                            JOptionPane.showMessageDialog(null, "El aeropuerto de destino no cuenta con la aerolinea asociada al avión.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "parece que no hay ningún avión en este aeropuerto.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);

                    }
                    
                } catch(ParseException e){
                    JOptionPane.showMessageDialog(null, "La fecha no cumple con el formato: dd/mm/yyyy", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
    
                } catch(NumberFormatException e){

                    JOptionPane.showMessageDialog(null, "Campo precio necesita un valor numérico.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Uno de los campos se encuentra vacío.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * método que inicia la ventana para la creación de distancias
     */
    public void accionCrearDistancia(){
        VentanaCrearDistancia distancia = new VentanaCrearDistancia(this.ventana, true);
        distancia.setVisible(true);
    }

    /**
     * método que muestra la opción de cambiar el aeropuerto en el que se encuentra el administrador
     */
    public void accionAeroPuerto(){
        this.ventana.getAdministrador().seleccionarAeroPuerto(this.ventana);
    }

    /**
     * método que muestra el menú de opciones del administrador
     */
    public void accionAdministrar(){
        this.ventana.getAdministrador().menuAdministrador(this.ventana);
    }

    /**
     * método que regresa el menú principal
     */
    public void accionPrincipal(){
        Menu menu = new Menu();
        menu.setVisible(true);
        this.ventana.dispose();
    }
}


