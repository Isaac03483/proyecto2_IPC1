package com.mycompany.cargaDatos;

import java.io.*;
import java.text.*;
import java.util.Date;

import javax.swing.JOptionPane;

import com.mycompany.aeropuerto.*;
import com.mycompany.aeropuerto.avion.Avion;
import com.mycompany.archivos.*;
import com.mycompany.constantes.*;
import com.mycompany.enums.*;
import com.mycompany.persona.pasajero.*;
import com.mycompany.ventanas.VentanaCarga;

public class HiloCarga extends Thread{
    
    private File archivoAProcesar;
    private VentanaCarga ventana;

    public HiloCarga(File archivoAProcesar, VentanaCarga ventana){

        this.archivoAProcesar = archivoAProcesar;
        this.ventana = ventana;

    }

    @Override
    public void run(){

        try {

            leerArchivo();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    private void leerArchivo() throws FileNotFoundException, IOException, ArrayIndexOutOfBoundsException{

        BufferedReader lector = new BufferedReader(new FileReader(this.archivoAProcesar));

        String auxiliar = lector.readLine();
        int posicion;
        String auxiliarUno, auxiliarDos, datos[];
        
        while(auxiliar != null){
            
            posicion = auxiliar.indexOf("(");
            auxiliarUno = auxiliar.substring(0, posicion);
            auxiliarDos = auxiliar.substring(posicion);
            datos = quitarParentesis(auxiliarDos);
            try{
                switch(auxiliarUno){
                    case Constante.AEROPUERTO:
                    if(!Verificaciones.aeroPuertoExistente(datos[0])){
                        ArchivoAeroPuerto.guardarAeroPuerto(new AeroPuerto(datos[0], datos[1], datos[2]));
                    } else {
                        System.err.println("Ya existe ese aeropuerto en dicho país.");
                    }
                    break;
    
                    case Constante.AEROLINEA:
                    if(!Verificaciones.aeroLineaExistente(datos[0], datos[1])){
                        ArchivoAeroLinea.guardarAeroLinea(new AeroLinea(datos[0], datos[1]));
                    } else {
                        System.err.println("Ya hay una aerolinea con ese nombre.");
                    }
                    break;
    
                    case Constante.AVION:
                    if(Verificaciones.aeroLineaExistente(datos[1], datos[0])){
                        ArchivoAvion.agregarAvion(new Avion(datos[0], datos[1], Integer.parseInt(datos[2]), Integer.parseInt(datos[3]), Double.parseDouble(datos[4]), Double.parseDouble(datos[5])));
                    } else {
                        System.err.println("Error, no se encontró la aerolinea o aeropuerto seleccionados.");
                    }
                    break;
                    
                    case Constante.DISTANCIA:
                    ArchivoDistancia.agregarDistancia(new Distancia(datos[0], datos[1], Double.parseDouble(datos[2])));
                    break;
    
                    case  Constante.PASAPORTE:
                    Date fechaNacimiento = formatoFecha(datos[2]);
                    Date fechaVencimiento = formatoFecha(datos[8]);
                    Date fechaEmision = formatoFecha(datos[9]);
                    if( fechaNacimiento != null && fechaVencimiento != null && fechaEmision !=null){
                        ArchivoPasaporte.agregarPasaporte(new Pasaporte(Integer.parseInt(datos[0]), datos[1], fechaNacimiento, datos[3],EstadoCivil.valueOf(datos[4]), datos[5], datos[6], Sexo.valueOf(datos[7]), fechaVencimiento, fechaEmision, datos[10], Double.parseDouble(datos[11])));
                    } else {
                        System.out.println("Error al cargar pasaporte ya que no cuenta con el formato correcto: "+auxiliar);
                    }
                    break;
    
                    case Constante.RENOVACION_PASAPORTE:
                    Date fechaRenovacion = formatoFecha(datos[1]);
                    if(fechaRenovacion != null){
                        if(Verificaciones.verificarPasaporte(Integer.parseInt(datos[0]))){
                            ArchivoPasaporte.renovacionPasaporte(Integer.parseInt(datos[0]), fechaRenovacion);
                        }
                    }
                    break;
    
                    case Constante.RESERVACION:
                    ArchivoReservacion.agregarReservacion(new Reservacion(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), Long.parseLong(datos[2]),datos[3]));
                    break;
    
                    case Constante.TARJETA:
                    if(Verificaciones.verificarPasaporte(Integer.parseInt(datos[1]))){
                        ArchivoTarjeta.agregarTarjeta(new Tarjeta(Long.parseLong(datos[0]), Integer.parseInt(datos[1]), Double.parseDouble(datos[2]), Integer.parseInt(datos[3])));
                    } else {
                        System.err.println("No se encontró el pasaporte.");
                    }
                    break;
    
                    case Constante.VUELO:
                    Date fechaSalida = formatoFecha(datos[5]);
                    if(fechaSalida != null){
                        if(Verificaciones.verificarAvion(Integer.parseInt(datos[1]))){
                            if(!Verificaciones.verificarAvionConVuelo(Integer.parseInt(datos[1]))){
                                if(Verificaciones.verificarDistancia(datos[2], datos[3])){
                                    if(Verificaciones.verificarGasolina(Integer.parseInt(datos[1]), datos[2], datos[3])){
                                        ArchivoVuelo.agregarVuelo(new Vuelo(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), datos[2], datos[3], Double.parseDouble(datos[4]), fechaSalida));
                                    } else {
                                        System.err.println("La distancia es mayor a la capacidad de gasolina que posee el avion.");
                                    }
                                } else {
                                    System.err.println("No se ha encontrado la distancia.");
                                }
                            } else {
                                System.err.println("Este avión ya tiene un vuelo en proceso.");
                            }
                        } else {
                            System.err.println("No se ha encontrado el avion.");
                        }
                    }
                    break;
    
                    default:
                    System.out.println("Formato inválido. "+auxiliar);
                    break;
                }
            } catch(NumberFormatException e){
                System.err.println("Error en el ingreso de datos en el campo de tipo número en la línea: "+auxiliar);
                e.printStackTrace();
            }
            this.ventana.getArea().append(auxiliarDos+"\n");
            try{
                Thread.sleep(500);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            auxiliar = lector.readLine();

        }
        JOptionPane.showMessageDialog(null, "Datos cargados con éxito.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
        lector.close();
    }

    private String[] quitarParentesis(String auxiliarDos){

        int posicion = auxiliarDos.lastIndexOf(")");
        String textoDatos = auxiliarDos.substring(1, posicion);
        String[] datos = textoDatos.split(",");

        return datos;
    }

    private Date formatoFecha(String texto){
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
        Date fecha;
        try {
            fecha = formato.parse(texto);
            return fecha;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }
}
