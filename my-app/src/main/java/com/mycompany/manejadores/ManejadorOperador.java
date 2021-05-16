package com.mycompany.manejadores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import com.mycompany.aeropuerto.Vuelo;
import com.mycompany.aeropuerto.avion.Avion;
import com.mycompany.archivos.*;
import com.mycompany.constantes.Constante;
import com.mycompany.enums.EstadoVuelo;
import com.mycompany.persona.empleados.*;
import com.mycompany.ventanas.*;

public class ManejadorOperador {
    
    private VentanaOperador ventana;

    public ManejadorOperador(VentanaOperador ventana){
        this.ventana = ventana;
    }

    public void accionCancelar(){

        ArrayList<Vuelo> vuelosCompatibles = escogerVuelos();
        try{
            int seleccion = Integer.parseInt(JOptionPane.showInputDialog(null, "cancelar Vuelos:"
            +"\n0. Cancelar"
            +"\n"+listarVuelos(vuelosCompatibles), Constante.TITULO, JOptionPane.INFORMATION_MESSAGE));

            switch(seleccion){
                case 0: 
                break;
                default:
                vuelosCompatibles.get(seleccion-1).setEstadoVuelo(EstadoVuelo.CANCELADO);
                ArchivoVuelo.agregarVuelo(vuelosCompatibles.get(seleccion-1));
                JOptionPane.showMessageDialog(null, "El vuelo ha sido cancelado.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);

                break;
            }
        } catch(IndexOutOfBoundsException e){

            JOptionPane.showMessageDialog(null, "Opción incorrecta.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
        }catch(NumberFormatException e){
            
        }

    }

    public void accionPosponer(){
        ArrayList<Vuelo> vuelosCompatibles = escogerVuelos();
        try{
            int seleccion = Integer.parseInt(JOptionPane.showInputDialog(null, "posponer Vuelos:"
            +"\n0. Cancelar"
            +"\n"+listarVuelos(vuelosCompatibles), Constante.TITULO, JOptionPane.INFORMATION_MESSAGE));

            switch(seleccion){
                case 0: 
                break;
                default:
                String nuevaFecha = JOptionPane.showInputDialog(null, "Ingrese la nueva fecha:", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
                if(!new SimpleDateFormat("dd/mm/yyyy").parse(nuevaFecha).before(vuelosCompatibles.get(seleccion-1).getFechaSalida())){

                    vuelosCompatibles.get(seleccion-1).setFecha(new SimpleDateFormat("dd/mm/yyyy").parse(nuevaFecha));
                    ArchivoVuelo.agregarVuelo(vuelosCompatibles.get(seleccion-1));
                    JOptionPane.showMessageDialog(null, "Vuelo pospuesto.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(vuelosCompatibles.get(seleccion-1).getFechaSalida());

                } else {
                    JOptionPane.showMessageDialog(null, "La fecha no puede ser menor a la fecha de salida.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);

                }
                break;
            }
        } catch(IndexOutOfBoundsException e){

            JOptionPane.showMessageDialog(null, "Opción incorrecta.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "formato de fecha incorrecta.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void accionVolar(){
        ArrayList<Vuelo> vuelosCompatibles = escogerVuelos();
        try{
            int seleccion = Integer.parseInt(JOptionPane.showInputDialog(null, "iniciar Vuelos:"
            +"\n0. Cancelar"
            +"\n"+listarVuelos(vuelosCompatibles), Constante.TITULO, JOptionPane.INFORMATION_MESSAGE));

            switch(seleccion){
                case 0: 
                break;
                default:
                this.ventana.getEtiquetaOrigen().setText(vuelosCompatibles.get(seleccion-1).getAeroPuertoOrigen());
                this.ventana.getEtiquetaDestino().setText(vuelosCompatibles.get(seleccion-1).getAeroPuertoDestino());
                vuelosCompatibles.get(seleccion-1).asignarEtiqueta(this.ventana.getEtiquetaAvion());
                Thread vuelo = new Thread(vuelosCompatibles.get(seleccion-1));
                vuelo.start();
                break;
            }
        } catch(IndexOutOfBoundsException e){

            JOptionPane.showMessageDialog(null, "Opción incorrecta.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
        } catch(NumberFormatException e){

        }

    }

    public void accionPrincipal(){

        Menu menu = new Menu();
        menu.setVisible(true);
        this.ventana.dispose();
    }

    public void accionMenu(){

        if(this.ventana.getEmpleado() instanceof Administrador){
            ((Administrador)this.ventana.getEmpleado()).menuAdministrador(this.ventana);
        } else if(this.ventana.getEmpleado() instanceof Gerente){
            ((Gerente)this.ventana.getEmpleado()).menuGerente(this.ventana);

        }
    }

    public void accionVisualizar(){
        

        ArrayList<Vuelo> vuelosCompatibles = escogerVuelos();
        try{
            int seleccion = Integer.parseInt(JOptionPane.showInputDialog(null, "iniciar Vuelos:"
            +"\n0. Cancelar"
            +"\n"+listarVuelos(vuelosCompatibles), Constante.TITULO, JOptionPane.INFORMATION_MESSAGE));

            switch(seleccion){
                case 0: 
                break;
                default:
                VentanaVuelos vuelos = new VentanaVuelos(this.ventana, true, this.ventana.getEmpleado(), vuelosCompatibles.get(seleccion-1), 0);
                vuelos.setVisible(true);
                break;
            }
        } catch(IndexOutOfBoundsException e){

            JOptionPane.showMessageDialog(null, "Opción incorrecta.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
        }catch(NumberFormatException e){
            
        }
    }

    private ArrayList<Vuelo> escogerVuelos(){

        ArrayList<Vuelo> vuelos = ArchivoVuelo.leerVuelos();
        ArrayList<Avion> aviones = ArchivoAvion.leerAvion();
        ArrayList<Vuelo> vuelosCompatibles = new ArrayList<>();
        if(vuelos != null){

            if(this.ventana.getEmpleado() instanceof Gerente){

                for(Vuelo vuelo: vuelos){

                    if(vuelo.getEstadoVuelo() == EstadoVuelo.ENESPERA){
                        for(Avion avion: aviones){
                            if(avion.getCodigoAvion() == vuelo.getCodigoAvion()){
                                if(((Gerente)this.ventana.getEmpleado()).getNombreAeroLinea().equals(avion.getNombreAeroLinea())){

                                    vuelosCompatibles.add(vuelo);
                                    break;
                                }
                            }
                        }
                    }
                }
            } else if(this.ventana.getEmpleado() instanceof Operador){

                for(Vuelo vuelo: vuelos){
                    if(vuelo.getEstadoVuelo() == EstadoVuelo.ENESPERA){
                        for(Avion avion: aviones){
                            if(avion.getCodigoAvion() == vuelo.getCodigoAvion()){
                                if(((Operador)this.ventana.getEmpleado()).getNombreAeroLinea().equals(avion.getNombreAeroLinea())){

                                    vuelosCompatibles.add(vuelo);
                                    break;
                                }
                            }
                        }
                    }
                }
            }else {
                
                for(Vuelo vuelo: vuelos){
                
                    if(vuelo.getEstadoVuelo() == EstadoVuelo.ENESPERA){
                        if(((Administrador)this.ventana.getEmpleado()).getAeroPuertoActual().equals(vuelo.getAeroPuertoOrigen())){
                            vuelosCompatibles.add(vuelo);
                        }
                    }
                }
            }

            return vuelosCompatibles;
        }

        return null;
    }

    private String listarVuelos(ArrayList<Vuelo> vuelos){

        int posicion =0;
        String lista ="";
        if(vuelos != null){

            for(Vuelo vuelo: vuelos){
                posicion++;
                lista += posicion+". "+vuelo.getCodigoVuelo()+"\n";
            }
        }
        return lista;
    }
}
