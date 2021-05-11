package com.mycompany.cargaDatos;

import java.util.ArrayList;
import java.util.Date;

import com.mycompany.aeropuerto.*;
import com.mycompany.aeropuerto.avion.Avion;
import com.mycompany.aeropuerto.avion.objeto_avion.Asiento;
import com.mycompany.archivos.*;
import com.mycompany.enums.EstadoAsiento;
import com.mycompany.enums.EstadoVuelo;
import com.mycompany.persona.empleados.*;
import com.mycompany.persona.pasajero.*;

public class Verificaciones {
    
    public static boolean aeroPuertoExistente(String nombreAeroPuerto){

        ArrayList<AeroPuerto> aeroPuertos = ArchivoAeroPuerto.leerAeroPuertos();
        if(aeroPuertos != null){
            for(AeroPuerto aeroPuerto: aeroPuertos){
                
                if(aeroPuerto.getNombreAeroPuerto().equals(nombreAeroPuerto)){
                    return  true;
                }
                
            }
        }
        return false;
    }

    public static boolean aeroLineaExistente(String nombreAeroPuerto,String nombreAeroLinea){

        ArrayList<AeroLinea> aeroLineas = ArchivoAeroLinea.leerAeroLinea();

        if(aeroLineas != null){
            for(AeroLinea aeroLinea: aeroLineas){
                if(aeroLinea.getNombreAeroPuerto().equals(nombreAeroPuerto)){
                    if(aeroLinea.getNombreAeroLinea().equals(nombreAeroLinea)){
                        return true;
                    }
                }
            }
        }
        return false;
        
    }

    public static boolean GerenteConAerolinea(String nombreAeroLinea){

        ArrayList<Empleado> gerentes = ArchivoEmpleado.leerEmpleados();

        if(gerentes != null){
            for(Empleado gerente: gerentes){
                if(gerente instanceof Gerente){
                    if(((Gerente)gerente).getNombreAeroLinea().equals(nombreAeroLinea)){
                        return true;
                    }
                }
            }
        }

        return false;
        
    }

    public static boolean verificarAvion(int codigoAvion){

        ArrayList<Avion> aviones = ArchivoAvion.leerAvion();

        if(aviones != null){
            for(Avion avion: aviones){
                if(avion.getCodigoAvion() == codigoAvion){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean verificarDistancia(String nombreAeroPuertoOrigen, String nombreAeroPuertoDestino){

        ArrayList<Distancia> distancias = ArchivoDistancia.leerDistancias();

        if(distancias != null){
            for(Distancia distancia: distancias){

                if(distancia.getAeroPuertoOrigen().equals(nombreAeroPuertoOrigen)){

                    if(distancia.getAeroPuertoDestino().equals(nombreAeroPuertoDestino)){
                        return true;
                    }
                }
            }
        }
        return false;

    }

    public static boolean verificarGasolina(int codigoAvion, String nombreAeroPuertoOrigen, String nombreAeroPuertoDestino){

        ArrayList<Avion> aviones = ArchivoAvion.leerAvion();
        Avion avionEscogido=null;
        if(aviones != null){
            for(Avion avion: aviones){
                if(avion.getCodigoAvion() == codigoAvion && avion.getAeroPuertoActual().equals(nombreAeroPuertoOrigen)){
                    avionEscogido = avion;
                }
            }
        }

        ArrayList<Distancia> distancias = ArchivoDistancia.leerDistancias();
        Distancia distanciaVuelo=null;
        if(distancias != null){
            for(Distancia distancia: distancias){

                if(distancia.getAeroPuertoOrigen().equals(nombreAeroPuertoOrigen)){
                    if(distancia.getAeroPuertoDestino().equals(nombreAeroPuertoDestino)){                
                        distanciaVuelo = distancia;
                    }
                }
            }
        }

        if(avionEscogido != null && distanciaVuelo != null){

            if(distanciaVuelo.getCantidadMillas() <= avionEscogido.getCapacidadGasolina()/avionEscogido.getConsumoPorMilla()){
                return true;
            }
        }
        return false;
    }

    public static boolean verificarPasaporte(long noPasaporte){

        ArrayList<Pasaporte> pasaportes = ArchivoPasaporte.leerPasaporte();
        
        if(pasaportes != null){
            for(Pasaporte pasaporte: pasaportes){
                if(pasaporte.getNoPasaporte() == noPasaporte){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean verificarPasaporteVuelo(int noPasaporte){

        ArrayList<Reservacion> reservaciones = ArchivoReservacion.leerReservacion();
        ArrayList<Vuelo> vuelos = ArchivoVuelo.leerVuelos();
        int codigoVuelo =0;
        if(reservaciones != null){

            for(Reservacion reservacion: reservaciones){
                if(reservacion.getNoPasaporte() == noPasaporte){
                    codigoVuelo = reservacion.getCodigoVuelo();
                }
            }
        }

        if(codigoVuelo != 0){
            for(Vuelo vuelo: vuelos){
                if(vuelo.getCodigoVuelo() == codigoVuelo){
                    if(vuelo.getEstadoVuelo()== EstadoVuelo.ENESPERA){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static boolean verificarVigencia(int noPasaporte, Date fecha){

        ArrayList<Pasaporte> pasaportes = ArchivoPasaporte.leerPasaporte();
        
        if(pasaportes != null){
            for(Pasaporte pasaporte: pasaportes){
                if(pasaporte.getNoPasaporte() == noPasaporte){
                    if(fecha.before(pasaporte.fechaVencimiento()) || fecha.equals(pasaporte.fechaVencimiento())){

                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean verificarUbicacionPasaporte(int noPasaporte, String ciudadOrigen){

        ArrayList<Pasaporte> pasaportes = ArchivoPasaporte.leerPasaporte();
        ArrayList<AeroPuerto> aeroPuertos = ArchivoAeroPuerto.leerAeroPuertos();
        String pais = null;

        if(aeroPuertos != null){
            for(AeroPuerto aeropuerto: aeroPuertos){
                if(ciudadOrigen.equals(aeropuerto.getCiudad())){
                    pais = aeropuerto.getPais();
                }
            }
        }
        if(pais != null){
            if(pasaportes != null){
                for(Pasaporte pasaporte: pasaportes){
                    if(pasaporte.getNoPasaporte() == noPasaporte && pais.equals(pasaporte.getPaisActual())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean verificarAvionConVuelo(int codigoAvion){

        ArrayList<Vuelo> vuelos = ArchivoVuelo.leerVuelos();

        if(vuelos != null){

            for(Vuelo vuelo: vuelos){
                if(vuelo.getCodigoAvion() == codigoAvion){
                    if(vuelo.getEstadoVuelo() == EstadoVuelo.ENESPERA){
                        return true;
                    }
                }
            }
            
        }
        return false;
    }

    public static boolean verificarAsientoOcupado(Avion avion, String noAsiento){

        for(int i =0; i< avion.getObjetos().length; i++){
            for(int j =0; j < avion.getObjetos()[i].length; j++){

                if(avion.getObjetos()[i][j] instanceof Asiento){
                    if(noAsiento.equals(((Asiento)avion.getObjetos()[i][j]).getNoAsiento())){
                        if(((Asiento)avion.getObjetos()[i][j]).getEstado() == EstadoAsiento.OCUPADO){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
