package com.mycompany.cargaDatos;

import java.util.ArrayList;

import com.mycompany.aeropuerto.*;
import com.mycompany.archivos.*;
import com.mycompany.persona.empleados.*;
import com.mycompany.persona.pasajero.*;

public class Verificaciones {
    
    public static boolean aeroPuertoExistente(String nombreAeroPuerto, String ciudad){

        ArrayList<AeroPuerto> aeroPuertos = ArchivoAeroPuerto.leerAeroPuertos();
        if(aeroPuertos != null){
            for(AeroPuerto aeroPuerto: aeroPuertos){
                if(!ciudad.equals("")){
                    if(aeroPuerto.getNombreAeroPuerto().equals(nombreAeroPuerto)){
                        if(aeroPuerto.getCiudad().equals(ciudad)){
                            return true;
                        }
                    }
                } else {
                    if(aeroPuerto.getNombreAeroPuerto().equals(nombreAeroPuerto)){
                        return  true;
                    }
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

    public static boolean verificarPasaporte(int noPasaporte){

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
}
