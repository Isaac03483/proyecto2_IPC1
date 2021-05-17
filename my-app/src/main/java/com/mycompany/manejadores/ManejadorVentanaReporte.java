package com.mycompany.manejadores;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import com.mycompany.aeropuerto.Vuelo;
import com.mycompany.aeropuerto.avion.Avion;
import com.mycompany.archivos.*;
import com.mycompany.constantes.Constante;
import com.mycompany.enums.*;
import com.mycompany.persona.pasajero.Pasaporte;
import com.mycompany.persona.pasajero.Reservacion;
import com.mycompany.ventanas.*;

public class ManejadorVentanaReporte {
    
    private VentanaReporte ventana;

    public ManejadorVentanaReporte(VentanaReporte ventana){
        this.ventana = ventana;
    }

    public void accionPrincipal(){

        Menu menu = new Menu();
        menu.setVisible(true);
        this.ventana.dispose();
    }

    public void accionAdministrar(){

        this.ventana.getAdministrador().menuAdministrador(this.ventana);
    }

    public void accionSexo(){

        this.ventana.getModelo().setRowCount(0);
        this.ventana.getModelo().setColumnCount(0);
        this.ventana.getModelo().addColumn("SEXO");
        this.ventana.getModelo().addColumn("NO VIAJEROS");
        ArrayList<Reservacion> reservaciones = ArchivoReservacion.leerReservacion();
        ArrayList<Vuelo> vuelos = ArchivoVuelo.leerVuelos();
        ArrayList<Pasaporte> pasaportes = ArchivoPasaporte.leerPasaporte();
        int[] contadores = new int[3];
        //hombre        mujer           sindefinir
        contadores[0] = contadores[1] = contadores[2] = 0;
        
        if(reservaciones != null && vuelos != null && pasaportes != null){
            for(Reservacion reservacion: reservaciones){
                for(Vuelo vuelo: vuelos){
                    if(reservacion.getCodigoVuelo() == vuelo.getCodigoVuelo() && vuelo.getEstadoVuelo() == EstadoVuelo.COMPLETADO){
                        for(Pasaporte pasaporte: pasaportes){
                            if(reservacion.getNoPasaporte() == pasaporte.getNoPasaporte()){
                                if(pasaporte.getSexo() == Sexo.MASCULINO){
                                    contadores[0]++;
                                } else if(pasaporte.getSexo() == Sexo.FEMENINO) {
                                    contadores[1]++;
                                } else {
                                    contadores[2]++;
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
        String[] filaHombre = {Sexo.MASCULINO.name(), Integer.toString(contadores[0])};
        String[] filaMujer = {Sexo.FEMENINO.name(), Integer.toString(contadores[1])};
        String[] filaOtro = {Sexo.SINDEFINIR.name(), Integer.toString(contadores[2])};

        this.ventana.getModelo().addRow(filaHombre);
        this.ventana.getModelo().addRow(filaMujer);
        this.ventana.getModelo().addRow(filaOtro);
    }

    public void accionEstado(){

        this.ventana.getModelo().setRowCount(0);
        this.ventana.getModelo().setColumnCount(0);
        this.ventana.getModelo().addColumn("ESTADO CIVIL");
        this.ventana.getModelo().addColumn("NO VIAJEROS");
        ArrayList<Reservacion> reservaciones = ArchivoReservacion.leerReservacion();
        ArrayList<Vuelo> vuelos = ArchivoVuelo.leerVuelos();
        ArrayList<Pasaporte> pasaportes = ArchivoPasaporte.leerPasaporte();
        int[] contadores = new int[3];
        //Soltero       casado           divorciado
        contadores[0] = contadores[1] = contadores[2] = 0;
        
        if(reservaciones != null && vuelos != null && pasaportes != null){
            for(Reservacion reservacion: reservaciones){
                for(Vuelo vuelo: vuelos){
                    if(reservacion.getCodigoVuelo() == vuelo.getCodigoVuelo() && vuelo.getEstadoVuelo() == EstadoVuelo.COMPLETADO){
                        for(Pasaporte pasaporte: pasaportes){
                            if(reservacion.getNoPasaporte() == pasaporte.getNoPasaporte()){
                                if(pasaporte.getEstadoCivil() == EstadoCivil.SOLTERO){
                                    contadores[0]++;
                                } else if(pasaporte.getEstadoCivil() == EstadoCivil.CASADO) {
                                    contadores[1]++;
                                } else {
                                    contadores[2]++;
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
        String[] filaCasado = {EstadoCivil.CASADO.name(), Integer.toString(contadores[1])};
        String[] filaSoltero = {EstadoCivil.SOLTERO.name(), Integer.toString(contadores[0])};
        String[] filaDivorciado= {EstadoCivil.DIVORCIADO.name(), Integer.toString(contadores[2])};

        this.ventana.getModelo().addRow(filaCasado);
        this.ventana.getModelo().addRow(filaSoltero);
        this.ventana.getModelo().addRow(filaDivorciado);
    }

    public void accionVuelo(){

        this.ventana.getModelo().setRowCount(0);
        this.ventana.getModelo().setColumnCount(0);
        this.ventana.getModelo().addColumn("pasaporte");
        this.ventana.getModelo().addColumn("nombre");
        this.ventana.getModelo().addColumn("nacionalidad");
        this.ventana.getModelo().addColumn("pais actual");
        ArrayList<Vuelo> vuelos = ArchivoVuelo.leerVuelos();
        ArrayList<Vuelo> completados = vuelosCompletados(vuelos);
        try{
            int opcionMenu = Integer.parseInt(JOptionPane.showInputDialog(null, "SELECCIONE UN VUELO:"
            +"\n 0. Cancelar."
            +"\n"+listarVuelos(completados), Constante.TITULO,JOptionPane.INFORMATION_MESSAGE));
            switch(opcionMenu){
                case 0: 
                break;
                default:
                Vuelo vueloSeleccionado = completados.get(opcionMenu-1);
                agregarFilas(vueloSeleccionado);

                break;
            }
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Debe ingresar un dato de tipo numérico.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
        } catch(IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Vuelo no encontrado.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void agregarFilas(Vuelo vuelo){

        ArrayList<Reservacion> reservaciones = ArchivoReservacion.leerReservacion();
        ArrayList<Pasaporte> pasaportes = ArchivoPasaporte.leerPasaporte();

        if(reservaciones != null & pasaportes != null){
            for(Reservacion reservacion: reservaciones){
                if(reservacion.getCodigoVuelo() == vuelo.getCodigoVuelo()){
                    for(Pasaporte pasaporte: pasaportes){
                        if(pasaporte.getNoPasaporte() == reservacion.getNoPasaporte()){
                            String[] fila = {Integer.toString(pasaporte.getNoPasaporte()), pasaporte.getNombre(), pasaporte.getNacionalidad(), pasaporte.getPaisActual()};
                            this.ventana.getModelo().addRow(fila);
                            break;
                        }
                    }
                }
            }
        }
    }

    private String listarVuelos(ArrayList<Vuelo> completados){

        String listado ="";

        if(completados != null){
            int contador = 1;
            for(Vuelo vuelo: completados){
                listado+=contador+". "+vuelo.getCodigoVuelo()+".\n";
                contador++;
            }
        }
        return listado;
    }

    private ArrayList<Vuelo> vuelosCompletados(ArrayList<Vuelo> vuelos){
        ArrayList<Vuelo> vuelosCompletados = new ArrayList<>();

        if(vuelos != null){
            for(Vuelo vuelo: vuelos){
                if(vuelo.getEstadoVuelo() == EstadoVuelo.COMPLETADO){
                    vuelosCompletados.add(vuelo);
                }
            }
            return vuelosCompletados;
        }
        return null;
    }

    public void accionAvion(){

        this.ventana.getModelo().setRowCount(0);
        this.ventana.getModelo().setColumnCount(0);
        this.ventana.getModelo().addColumn("código avión");
        this.ventana.getModelo().addColumn("aerolinea");
        this.ventana.getModelo().addColumn("gasolina consumida");

        ArrayList<Avion> aviones = ArchivoAvion.leerAvion();
        
        if(aviones != null){
            for(Avion avion: aviones){
                String[] fila = {Integer.toString(avion.getCodigoAvion()), avion.getNombreAeroLinea(), Double.toString(avion.getGasolinaConsumida())};
                this.ventana.getModelo().addRow(fila);
            }
        }
    }
}
