package com.mycompany.cargaDatos;

import java.util.ArrayList;
import java.util.Date;

import com.mycompany.aeropuerto.*;
import com.mycompany.aeropuerto.avion.Avion;
import com.mycompany.aeropuerto.avion.objeto_avion.Asiento;
import com.mycompany.archivos.*;
import com.mycompany.enums.*;
import com.mycompany.persona.empleados.*;
import com.mycompany.persona.pasajero.*;

public class Verificaciones {
    
    /**
     * retorna true en caso de encontrar un aeropuerto con el mismo nombre
     * @param nombreAeroPuerto
     * @return
     */
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

    /**
     * retorna true en caso de encontrar una aerolinea con el mismo nombre en el aeropuerto seleccionado
     * @param nombreAeroPuerto
     * @param nombreAeroLinea
     * @return
     */
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

    /**
     * retorna true al encontrar un vuelo con el mismo código y se encuentre en espera
     * @param codigoVuelo
     * @return
     */
    public static boolean verificarVuelo(int codigoVuelo){
        ArrayList<Vuelo> vuelos = ArchivoVuelo.leerVuelos();

        if(vuelos != null){

            for(Vuelo vuelo: vuelos){
                if(vuelo.getCodigoVuelo() == codigoVuelo){
                    if(vuelo.getEstadoVuelo() == EstadoVuelo.ENESPERA){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * retorna true en caso de encontrar la tarjeta solicitada
     * @param noTarjeta
     * @return
     */
    public static boolean verificarTarjeta(long noTarjeta){

        ArrayList<Tarjeta> tarjetas = ArchivoTarjeta.leerTarjeta();

        if(tarjetas != null){

            for(Tarjeta tarjeta: tarjetas){
                if(tarjeta.getNoTarjeta() == noTarjeta){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * retorna true al encontrar a un gerente con una aerolinea asignada
     * @param nombreAeroLinea
     * @return
     */
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

    /**
     * retorna true al encontrar un avión con el mismo código
     * @param codigoAvion
     * @return
     */
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

    /**
     * retorna true al encontrar una distancia con el mismo origen-destino
     * @param nombreAeroPuertoOrigen
     * @param nombreAeroPuertoDestino
     * @return
     */
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

    /**
     * retorna true al verificar que el avión posea la gasolina suficiente para recorrer toda la distancia
     * @param codigoAvion
     * @param nombreAeroPuertoOrigen
     * @param nombreAeroPuertoDestino
     * @return
     */
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

    /**
     * retorna  ture  al encontrar el mismo número de pasaporte
     */
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

    /**
     * retorna true al encontrar a un pasaporte con un vuelo en espera
     * @param noPasaporte
     * @return
     */
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

    /**
     * retorna true al verificar que el pasaporte se encuentra vigente
     * @param noPasaporte
     * @param fecha
     * @return
     */
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

    /**
     * retorna true al verificar que el pais actual del pasaporte es igual al enviado
     * @param noPasaporte
     * @param ciudadOrigen
     * @return
     */
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

    /**
     * retorna true al encontrar al avión con un vuelo en espera
     * @param codigoAvion
     * @return
     */
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

    /**
     * retorna true en caso de encontrar un asiento ocupado
     * @param avion
     * @param noAsiento
     * @return
     */
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

    /**
     * retorna true en caso de que el numero de pasaporte y la contraseña sean correctas
     * @param noPasaporte
     * @param contrasena
     * @return
     */
    public static boolean verificarConstrasena(int noPasaporte, String contrasena){
        ArrayList<Pasaporte> pasaportes = ArchivoPasaporte.leerPasaporte();

        if(pasaportes != null){
            for(Pasaporte pasaporte: pasaportes){
                if(pasaporte.getNoPasaporte() == noPasaporte && pasaporte.getContrasena().equals(contrasena)){
                    return true;
                }
            }
        }
        return false;
    }
}
