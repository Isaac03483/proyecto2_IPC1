package com.mycompany.manejadores;

import java.text.*;
import java.util.*;
import javax.swing.JOptionPane;

import com.mycompany.aeropuerto.*;
import com.mycompany.aeropuerto.avion.Avion;
import com.mycompany.archivos.*;
import com.mycompany.cargaDatos.Verificaciones;
import com.mycompany.constantes.Constante;
import com.mycompany.ventanas.*;

public class ManejadorCliente {
    
    private VentanaCliente cliente;

    public ManejadorCliente(VentanaCliente cliente){
        this.cliente = cliente;
    }

    public void accionComprar(){
        String ciudadOrigen = this.cliente.getTextoOrigen().getText();
        String ciudadDestino = this.cliente.getTextoDestino().getText();
        String aeroLinea = ((AeroLinea)this.cliente.getComboAeroLinea().getSelectedItem()).getNombreAeroLinea();
        Date fecha = null;
        int noPasaporte =0;
        try{

            noPasaporte = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese su número de pasaporte.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE));
            fecha = new SimpleDateFormat("dd/mm/yyyy").parse(this.cliente.getTextoFecha().getText());
        } catch(ParseException e){

            JOptionPane.showMessageDialog(null, "El campo fecha no cuenta con el siguiente formato: dd/mm/yyyy.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "debe ingresar un dato de tipo numérico.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
            noPasaporte =0;
        }

        if(fecha != null && noPasaporte != 0){
            if(Verificaciones.verificarPasaporte(noPasaporte)){

                if(!Verificaciones.verificarPasaporteVuelo(noPasaporte)){
                    if(Verificaciones.verificarUbicacionPasaporte(noPasaporte, ciudadOrigen)){
                        if(Verificaciones.verificarVigencia(noPasaporte, fecha)){
                            String aeroPuertoOrigen = elegirAeroPuerto(ciudadOrigen);
                            String aeroPuertoDestino = elegirAeroPuerto(ciudadDestino);
    
                            if(aeroPuertoOrigen != null && aeroPuertoDestino != null){
    
                                if(Verificaciones.aeroLineaExistente(aeroPuertoOrigen, aeroLinea) && Verificaciones.aeroLineaExistente(aeroPuertoDestino, aeroLinea)){
    
                                    ArrayList<Vuelo> listaVuelos = buscarVuelos(aeroPuertoOrigen, aeroPuertoDestino, aeroLinea);
    
                                    String listarVuelos = listarVuelos(listaVuelos);
    
                                    int opcionVuelo = Integer.parseInt(JOptionPane.showInputDialog(null, "Lista de vuelos compatibles:"
                                    +"\n0. Cancelar"
                                    +"\n"+listarVuelos, Constante.TITULO, JOptionPane.INFORMATION_MESSAGE));
                                    try{
    
                                        if(opcionVuelo > 0){
                                            opcionVuelo--;
                                            Vuelo vuelo = listaVuelos.get(opcionVuelo);
                                            System.out.println(listaVuelos.get(opcionVuelo)+"    "+opcionVuelo);
                                            VentanaVuelos ventanaVuelo = new VentanaVuelos(this.cliente, true, null, vuelo, noPasaporte);
                                            ventanaVuelo.setVisible(true);
                                        }
                                    }catch(ArrayIndexOutOfBoundsException e){
                                        System.err.println(e.getMessage());
                                        JOptionPane.showMessageDialog(null, "El vuelo seleccionado no está disponible.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
                                    } catch(NumberFormatException e){
                                        JOptionPane.showMessageDialog(null, "Debe ingresar un valor numérico.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
    
                                    }
                                }
                            }
                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "El pasaporte actualmente se encuentra en otro país.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    System.out.println("Este pasaporte tiene un vuelo en proceso.");
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Pasaporte no encontrado.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
    }

    private String listarVuelos(ArrayList<Vuelo> vuelos){
        
        String lista ="";
        if(vuelos != null){

            int orden = 1;
            for(Vuelo vuelo: vuelos){

                lista +=orden+". "+vuelo.getCodigoVuelo()+"\n";
                orden++;
            }
        }
        return lista;
    }

    private ArrayList<Vuelo> buscarVuelos(String origen, String destino, String aeroLinea){

        ArrayList<Vuelo> vuelos = ArchivoVuelo.leerVuelos();
        ArrayList<Avion> aviones = ArchivoAvion.leerAvion();
        ArrayList<Vuelo> vuelosCompatibles= new ArrayList<>();
        ArrayList<Avion> avionesSeleccionados = new ArrayList<>();

        if(aviones != null){
            for(Avion  avion: aviones){
                if(aeroLinea.equals(avion.getNombreAeroLinea())){
                    avionesSeleccionados.add(avion);
                }
            }
        }
        if(vuelos != null && avionesSeleccionados != null){

            for(Vuelo vuelo: vuelos){
                for(Avion avion: avionesSeleccionados){
                    if(vuelo.getCodigoAvion() == avion.getCodigoAvion()){
                        vuelosCompatibles.add(vuelo);
                        break;
                    }
                }
            }
            return vuelosCompatibles;
        }
        return null;
    }

    private String elegirAeroPuerto(String ciudad){

        ArrayList<AeroPuerto> aeroPuertos = ArchivoAeroPuerto.leerAeroPuertos();

        if(aeroPuertos != null){

            for(AeroPuerto aeroPuerto: aeroPuertos){

                if(ciudad.equals(aeroPuerto.getCiudad())){
                    return aeroPuerto.getNombreAeroPuerto();
                }
            }
        }

        return null;
    }

    public void accionInformacion(){

    }

    public void accionVolver(){
        Menu menu = new Menu();
        menu.setVisible(true);
        this.cliente.dispose();
    }

    public void accionCrearPasaporte(){

        VentanaCrearPasaporte pasaporte = new VentanaCrearPasaporte(this.cliente, true);
        pasaporte.setVisible(true);
    }

    public void accionRenovarPasaporte(){

        try{

            int noPasaporte = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese su número de pasaporte:", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE));

            if(Verificaciones.verificarPasaporte(noPasaporte)){
                String fechaNueva = JOptionPane.showInputDialog(null,"Ingrese la nueva fecha (formato: dd/mm/yyy)",Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);

                ArchivoPasaporte.renovacionPasaporte(noPasaporte, new SimpleDateFormat("dd/mm/yyy").parse(fechaNueva));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el pasaporte.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);

            }

        }catch(ParseException e){

            JOptionPane.showMessageDialog(null, "No posee el formato.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
        } catch(NumberFormatException e){}
    }
}
