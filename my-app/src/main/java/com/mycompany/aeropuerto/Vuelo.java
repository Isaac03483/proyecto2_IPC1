package com.mycompany.aeropuerto;

import java.awt.Image;
import java.io.Serializable;
import java.util.*;
import javax.swing.*;

import com.mycompany.aeropuerto.avion.Avion;
import com.mycompany.aeropuerto.avion.objeto_avion.Asiento;
import com.mycompany.archivos.*;
import com.mycompany.constantes.Constante;
import com.mycompany.enums.EstadoAsiento;
import com.mycompany.enums.EstadoVuelo;
import com.mycompany.persona.pasajero.*;

public class Vuelo implements Serializable, Runnable{
    
    private int codigoVuelo;
    private int codigoAvion;
    private int noPasajeros;
    private String nombreAeroPuertoOrigen;
    private String nombreAeroPuertoDestino;
    private double precioBoleto;
    private Date fechaSalida;
    private EstadoVuelo estado;
    private JLabel etiquetaAvion;

    /**
     * método oculto
     */
    {
        this.noPasajeros =0;
    }
    /**
     * Constructor creado para generar más vuelos mediante la interfaz gráfica
     * @param codigoAvion
     * @param nombreAeroPuertoOrigen
     * @param nombreAeroPuertoDestino
     * @param precioBoleto
     * @param fechaSalida
     */
    public Vuelo(int codigoAvion, String nombreAeroPuertoOrigen, String nombreAeroPuertoDestino, double precioBoleto, Date fechaSalida){

        this.codigoVuelo = Integer.parseInt(com.mycompany.generadorCodigos.GenerarCodigo.generarCodigo(Integer.toString(codigoVuelo), Constante.CARACTERES_CODIGO_VUELO, false));
        this.codigoAvion = codigoAvion;
        this.nombreAeroPuertoOrigen=nombreAeroPuertoOrigen;
        this.nombreAeroPuertoDestino = nombreAeroPuertoDestino;
        this.precioBoleto = precioBoleto;
        this.fechaSalida = fechaSalida;
        this.estado = EstadoVuelo.ENESPERA;
    }

    /**
     * Constructor  creado para la asignación de vuelos mediante la carga de archivos
     * @param codigoVuelo
     * @param codigoAvion
     * @param nombreAeroPuertoOrigen
     * @param nombreAeroPuertoDestino
     * @param precioBoleto
     * @param fechaSalida
     */
    public Vuelo(int codigoVuelo, int codigoAvion, String nombreAeroPuertoOrigen, String nombreAeroPuertoDestino, double precioBoleto, Date fechaSalida){

        this.codigoVuelo= codigoVuelo;
        this.codigoAvion = codigoAvion;
        this.nombreAeroPuertoOrigen=nombreAeroPuertoOrigen;
        this.nombreAeroPuertoDestino = nombreAeroPuertoDestino;
        this.precioBoleto = precioBoleto;
        this.fechaSalida = fechaSalida;
        this.estado = EstadoVuelo.ENESPERA;
    }

    /**
     * retorna el valor entero del código del vuelo
     * @return
     */
    public int getCodigoVuelo(){return this.codigoVuelo;}

    /**
     * retorna el valor entero del codigo del avión asociado al vuelo
     * @return
     */
    public int getCodigoAvion(){return this.codigoAvion;}

    /**
     * retorna el nombre del aeropuerto de origen
     * @return
     */
    public String getAeroPuertoOrigen(){return this.nombreAeroPuertoOrigen;}

    /**
     * retorna el nombre del aeropuerto de destino
     * @return
     */
    public String getAeroPuertoDestino(){return this.nombreAeroPuertoDestino;}

    /**
     * retorna el valor del precio del boleto del vuelo
     * @return
     */
    public double getPrecioBoleto(){return this.precioBoleto;}

    /**
     * 
     * retorna la fecha de salida del avión
     * @return
     */
    public Date getFechaSalida(){return this.fechaSalida;}

    /**
     * retorna el estado en el que se encuentra el vuelo
     * @return
     */
    public EstadoVuelo getEstadoVuelo(){return this.estado;}

    /**
     * retorna el número de pasajeros que iran en el vuelo
     * @return
     */
    public int getNoPasajeros(){return this.noPasajeros;}

    /**
     * aumenta el número de pasajeros en el vuelo
     */
    public void setNoPasajeros(){this.noPasajeros++;} 

    /**
     * cambia el estado del vuelo
     * @param estado
     */
    public void setEstadoVuelo(EstadoVuelo estado){this.estado = estado;}

    /**
     * cambia  la fecha del vuelo (pospone el vuelo)
     * @param fechaNueva
     */
    public void setFecha(Date fechaNueva){this.fechaSalida = fechaNueva;}

    /**
     * método para asignar la etiqueta que será utilizada en el hilo
     * @param etiqueta
     */
    public void asignarEtiqueta(JLabel etiqueta){
        this.etiquetaAvion = etiqueta;
        this.etiquetaAvion.setIcon(new ImageIcon(Constante.IMAGEN_AVION.getImage().getScaledInstance(this.etiquetaAvion.getHeight(), this.etiquetaAvion.getWidth(), Image.SCALE_SMOOTH)));
    }

    /**
     * Método Run que mueve una etiqueta en la ventana
     * cambia el aeropuerto actual del avión
     * cambia el pais en el que se encuentran los pasaportes que viajaron
     * cambia el estado del vuelo a completado
     */
    @Override
    public void run() {
        int contador =0;
        this.etiquetaAvion.setVisible(true);
        do{
            try{

                Thread.sleep(1000);
                contador++;
            } catch(InterruptedException e){
                System.err.println("Vuelo incompleto");
            }
            this.etiquetaAvion.setLocation(this.etiquetaAvion.getX() + 60, this.etiquetaAvion.getY());
        } while(contador<3);
        cambiarPaisPasaportes();
        this.etiquetaAvion.setLocation(180, this.etiquetaAvion.getY());
        this.etiquetaAvion.setVisible(false);
        this.estado = EstadoVuelo.COMPLETADO;
        ArchivoVuelo.agregarVuelo(this);
        JOptionPane.showMessageDialog(null, "Vuelo finalizado", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * método que cambia el país de los pasaportes que viajaron
     * cambia de aeropuerto al avión y cambia la cantidad de millas recorridas de los pasaportes.
     */
    private void cambiarPaisPasaportes(){

        ArrayList<Reservacion> reservaciones = ArchivoReservacion.leerReservacion();
        ArrayList<Pasaporte> pasaportes = ArchivoPasaporte.leerPasaporte();
        ArrayList<AeroPuerto> aeroPuertos = ArchivoAeroPuerto.leerAeroPuertos();
        ArrayList<Avion> aviones = ArchivoAvion.leerAvion();
        ArrayList<Distancia> distancias = ArchivoDistancia.leerDistancias();
        Distancia distanciaVuelo = null;
        if(distancias != null){
            for(Distancia distancia: distancias){
                if(this.nombreAeroPuertoOrigen.equals(distancia.getAeroPuertoOrigen()) && this.nombreAeroPuertoDestino.equals(distancia.getAeroPuertoDestino())){
                    distanciaVuelo = distancia;
                    break;
                }
            }
        }

        if(aviones != null){
            for(Avion avion: aviones){
                if(this.codigoAvion == avion.getCodigoAvion()){
                    for(int i = 0; i < avion.getObjetos().length; i++){
                        for(int j = 0; j < avion.getObjetos()[i].length; j++){
                            if(avion.getObjetos()[i][j] instanceof Asiento){
                                avion.venderAsiento(i, j, EstadoAsiento.DISPONIBLE);
                            }
                        }
                    }
                    avion.cambiarAeroPuertoActual(this.nombreAeroPuertoDestino);
                    if(distanciaVuelo != null){avion.setGasolinaConsumida(avion.getConsumoPorMilla()*distanciaVuelo.getCantidadMillas());}
                    System.out.println(avion.getAeroPuertoActual());
                    ArchivoAvion.agregarAvion(avion);
                    break;
                }
            }
        }
        if(reservaciones != null && pasaportes != null){
            for(Reservacion reservacion: reservaciones){
                if(this.codigoVuelo == reservacion.getCodigoVuelo()){
                    for(Pasaporte pasaporte: pasaportes){
                        if(reservacion.getNoPasaporte() == pasaporte.getNoPasaporte()){
                            for(AeroPuerto aeroPuerto: aeroPuertos){
                                if(this.nombreAeroPuertoDestino.equals(aeroPuerto.getNombreAeroPuerto())){
                                    pasaporte.setPaisActual(aeroPuerto.getPais());
                                    pasaporte.setMillasRecorridas(distanciaVuelo.getCantidadMillas());
                                    System.out.println(pasaporte.getPaisActual());
                                    ArchivoPasaporte.agregarPasaporte(pasaporte);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

}
