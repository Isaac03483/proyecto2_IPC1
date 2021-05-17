package com.mycompany.manejadores;

import java.util.*;

import javax.swing.JOptionPane;

import com.mycompany.aeropuerto.*;
import com.mycompany.aeropuerto.avion.Avion;
import com.mycompany.archivos.ArchivoAeroLinea;
import com.mycompany.archivos.ArchivoAvion;
import com.mycompany.cargaDatos.Verificaciones;
import com.mycompany.constantes.Constante;
import com.mycompany.ventanas.*;

public class ManejadorCrearAvion {
    
    private VentanaCrearAvion ventana;

    /**
     * Constructor para el manejo de la ventanaCrearAvion
     * @param ventana
     */
    public ManejadorCrearAvion(VentanaCrearAvion ventana){
        this.ventana = ventana;
    }

    /**
     * método para cambiar el aeropuerto en el que se encuentra el administrador
     */
    public void accionAeroPuerto(){
        this.ventana.getAdministrador().seleccionarAeroPuerto(this.ventana);
    }

    /**
     * método que muestra las distintas acciones que puede realizar el administrador
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

    /**
     * método que crea aviones con toda la información pedida en la ventana
     */
    public void accionCrear(){

        if(!this.ventana.getTextoGasolina().getText().equals("") && !this.ventana.getTextoMillas().getText().equals("")){

            try{
                int capacidadGasolina = Integer.parseInt(this.ventana.getTextoGasolina().getText());
                int consumoPorMilla = Integer.parseInt(this.ventana.getTextoMillas().getText());

                String aeroPuertoActual = ((AeroPuerto)this.ventana.getComboAeroPuerto().getSelectedItem()).getNombreAeroPuerto();
                String nombreAeroLinea = ((AeroLinea)this.ventana.getComboAeroLinea().getSelectedItem()).getNombreAeroLinea();

                int noFilas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de filas que tendrá el avión:", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE));
                int noColumnas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de columnas que tendrá el avión:", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE));
                int columnaPasillo = 0;

                do{

                    columnaPasillo = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la columna que contendrá el pasillo del avión:"
                    +"\nNOTA: no puede seleccionar una columna menor a 1 o una columna mayor a: "+noColumnas, Constante.TITULO, JOptionPane.INFORMATION_MESSAGE));
                } while(columnaPasillo < 1 || columnaPasillo > noColumnas);

                if(columnaPasillo > 0 && columnaPasillo< noColumnas){
                    Avion avion = null;
                    do{
                        avion = new Avion(nombreAeroLinea, aeroPuertoActual, capacidadGasolina, consumoPorMilla);
                    } while(Verificaciones.verificarAvion(avion.getCodigoAvion()) == true);
                    if(avion != null){
                        avion.generarMatiz(noFilas, noColumnas, columnaPasillo);
                        ArchivoAvion.agregarAvion(avion);
                        JOptionPane.showMessageDialog(null, "Avión creado con éxito su código es: "+avion.getCodigoAvion(), Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Las campos capacidad Gasolina y consumo por milla necesitan datos numéricos.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
            } catch(NullPointerException e){

            }
        } else {
            JOptionPane.showMessageDialog(null, "Uno de los campos se encuentra vacío.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * método que agrega aerolineas al comboBox
     */
    public void seleccionarAeroLinea(){
        
        this.ventana.getComboAeroLinea().removeAllItems();
        AeroPuerto aeroPuerto = (AeroPuerto)this.ventana.getComboAeroPuerto().getSelectedItem();

        ArrayList<AeroLinea> aeroLineas = ArchivoAeroLinea.leerAeroLinea();

        for(AeroLinea aeroLinea: aeroLineas){
            if(aeroPuerto.getNombreAeroPuerto().equals(aeroLinea.getNombreAeroPuerto())){
                this.ventana.getComboAeroLinea().addItem(aeroLinea);
            }
        }
    }
}
