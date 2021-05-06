package com.mycompany.manejadores;

import javax.swing.JOptionPane;

import com.mycompany.aeropuerto.*;
import com.mycompany.archivos.ArchivoDistancia;
import com.mycompany.cargaDatos.Verificaciones;
import com.mycompany.constantes.Constante;
import com.mycompany.ventanas.VentanaCrearDistancia;

public class ManejadorCrearDistancia {
    
    private VentanaCrearDistancia ventana;

    public ManejadorCrearDistancia(VentanaCrearDistancia ventana){
        this.ventana = ventana;
    }

    public void accionCrear(){
        AeroPuerto aeroPuertoOrigen = (AeroPuerto)this.ventana.getOrigen().getSelectedItem();
        AeroPuerto aeroPuertoDestino = (AeroPuerto)this.ventana.getDestino().getSelectedItem();
        String millasTotales = this.ventana.getTextoMillas().getText();

        if(!aeroPuertoOrigen.getNombreAeroPuerto().equals(aeroPuertoDestino.getNombreAeroPuerto())){

            if(!millasTotales.equals("")){

                try{
                    if(!Verificaciones.verificarDistancia(aeroPuertoOrigen.getNombreAeroPuerto(), aeroPuertoDestino.getNombreAeroPuerto())){

                        Distancia distancia = new Distancia(aeroPuertoOrigen.getNombreAeroPuerto(), aeroPuertoDestino.getNombreAeroPuerto(), Double.parseDouble(millasTotales));
                        ArchivoDistancia.agregarDistancia(distancia);
                        JOptionPane.showMessageDialog(null, "Distancia agregada con éxito.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
                        this.ventana.getVentanaVuelo().getComboOrigenDestino().addItem(distancia);
                        this.ventana.dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, "Esta Distancia ya ha sido registrada.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
                        this.ventana.getTextoMillas().setText("");
                    }
                } catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "El campo millas debe contener un valor numérico.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                
                JOptionPane.showMessageDialog(null, "Campo millas se encuentra vacío.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "El origen no puede ser igual al destino.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
