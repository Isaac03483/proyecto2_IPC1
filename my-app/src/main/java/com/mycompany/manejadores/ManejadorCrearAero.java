package com.mycompany.manejadores;

import java.util.*;
import javax.swing.JOptionPane;
import com.mycompany.aeropuerto.*;
import com.mycompany.archivos.*;
import com.mycompany.cargaDatos.Verificaciones;
import com.mycompany.constantes.Constante;
import com.mycompany.ventanas.*;

public class ManejadorCrearAero {
    
    VentanaCrearAero ventana;

    /**
     * Constructor para el manejo de la ventanaCrearAero
     * @param ventana
     */
    public ManejadorCrearAero(VentanaCrearAero ventana){
        this.ventana = ventana;
    }

    /**
     * método que cambia el aeropuerto en el que se encuentra el administrador
     */
    public void accionAeroPuerto(){
        this.ventana.getAdministrador().seleccionarAeroPuerto(this.ventana);
    }

    /**
     * muestra todos los campos necesarios para la creación de aeropuertos  y aerolineas y oculta los demás
     */
    public void accionEleccionPuerto(){
        if(this.ventana.getRadioAeroPuerto().isSelected()){

            this.ventana.getTextoNombreAeroPuerto().setVisible(true);
            this.ventana.getTextoCiudad().setVisible(true);
            this.ventana.getTextoPais().setVisible(true);
            this.ventana.getTextoNombreAeroLinea().setVisible(false);
            this.ventana.getEtiquetaNombreAeroLinea().setVisible(false);
            this.ventana.getComboBox().setVisible(false);
            this.ventana.getEtiquetaNombreAeroPuerto().setVisible(true);
            this.ventana.getEtiquetaCiudad().setVisible(true);
            this.ventana.getEtiquetaPais().setVisible(true);
        } else {
            
            this.ventana.getTextoNombreAeroPuerto().setVisible(false);
            this.ventana.getTextoCiudad().setVisible(false);
            this.ventana.getTextoPais().setVisible(false);
            this.ventana.getTextoNombreAeroLinea().setVisible(true);
            this.ventana.getEtiquetaNombreAeroLinea().setVisible(true);
            this.ventana.getComboBox().setVisible(true);
            this.ventana.getEtiquetaNombreAeroPuerto().setVisible(true);
            this.ventana.getEtiquetaCiudad().setVisible(false);
            this.ventana.getEtiquetaPais().setVisible(false);

            agregarAeroPuertos();
        }
    }

    /**
     * método que agrega aeropuertos al comboBox para la creación de aerolineas
     */
    private void agregarAeroPuertos(){
        this.ventana.getComboBox().removeAllItems();
        ArrayList<AeroPuerto> aeroPuertos = ArchivoAeroPuerto.leerAeroPuertos();

        if(aeroPuertos!= null){

            for(AeroPuerto aeroPuerto: aeroPuertos){
                this.ventana.getComboBox().addItem(aeroPuerto);
            }
        }
    }

    /**
     * crea aeropuertos/aerolineas dependiendo del caso con toda la información proporcionada
     * en la ventana
     */
    public void accionCrear(){

        if(this.ventana.getRadioAeroPuerto().isSelected()){

            String nombreAeroPuerto = this.ventana.getTextoNombreAeroPuerto().getText();
            String ciudad = this.ventana.getTextoCiudad().getText();
            if(!nombreAeroPuerto.equals("") && !ciudad.equals("")){
                if(!Verificaciones.aeroPuertoExistente(nombreAeroPuerto)){
                    String pais = this.ventana.getTextoPais().getText();
                    ArchivoAeroPuerto.guardarAeroPuerto(new AeroPuerto(nombreAeroPuerto, ciudad, pais));
                    JOptionPane.showMessageDialog(null, "Aeropuerto creado con éxito.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Ya existe un aeropuerto con ese nombre.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Uno de los campos se encuentra vacío.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
            }
            
        } else if(this.ventana.getRadioAeroLinea().isSelected()){

            String nombreAeroLinea = this.ventana.getTextoNombreAeroLinea().getText();
            AeroPuerto aeroPuerto = (AeroPuerto)this.ventana.getComboBox().getSelectedItem();
            if(!nombreAeroLinea.equals("")){
                if(!Verificaciones.aeroLineaExistente(aeroPuerto.getNombreAeroPuerto(), nombreAeroLinea)){
                    ArchivoAeroLinea.guardarAeroLinea(new AeroLinea(aeroPuerto.getNombreAeroPuerto(), nombreAeroLinea));
                    JOptionPane.showMessageDialog(null, "Aerolinea creado con éxito.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Este aeropuerto ya cuenta con una aerolinea con ese nombre.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El campo nombre de aerolinea se encuentra vacío.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
            }
            
        }
        this.ventana.limpiarTexto();
    }

    /**
     * muestra las distintas acciones que puede realizar el administrador
     */
    public void accionAdministrar(){

        this.ventana.getAdministrador().menuAdministrador(this.ventana);
    }

    /**
     * método que regresa al menú principal
     */
    public void accionPrincipal(){
        Menu menu = new Menu();
        menu.setVisible(true);
        this.ventana.dispose();
    }

}
