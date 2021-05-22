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

    /**
     * constructor que almacena el archivo y la ventana que contiene la carga de archivos
     * @param archivoAProcesar
     * @param ventana
     */
    public HiloCarga(File archivoAProcesar, VentanaCarga ventana){

        this.archivoAProcesar = archivoAProcesar;
        this.ventana = ventana;

    }

    /**
     * método run que llama al método leerArchivo
     */
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

    /**
     * método que ira recorriendo el archivo almacenado y creando distintos archivos binarios dependiendo del caso
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ArrayIndexOutOfBoundsException
     */
    private void leerArchivo() throws FileNotFoundException, IOException, ArrayIndexOutOfBoundsException{

        this.ventana.getArea().setText("");
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
                        this.ventana.getArea().append("no se ha podido cargar el aeropuerto ya que ya existe.\n");
                    }
                    break;
    
                    case Constante.AEROLINEA:
                    if(Verificaciones.aeroPuertoExistente(datos[0])){
                        if(!Verificaciones.aeroLineaExistente(datos[0], datos[1])){
                            ArchivoAeroLinea.guardarAeroLinea(new AeroLinea(datos[0], datos[1]));
                        } else {
                            System.err.println("Ya hay una aerolinea con ese nombre.");
                            this.ventana.getArea().append("No se ha podido cargar la aerolinea ya que el aeropuerto ya cuenta con una con el mismo nombre.\n");
                        }
                    } else {

                        System.err.println("No se encontró el aeropuerto.");
                        this.ventana.getArea().append("No se pudo cargar la aerolinea ya que no se ha encontrado el aeropuerto.\n");

                    }
                    break;
    
                    case Constante.AVION:
                    if(Verificaciones.aeroPuertoExistente(datos[1])){

                        if(Verificaciones.aeroLineaExistente(datos[1], datos[0])){
                            if(!Verificaciones.verificarAvion(Integer.parseInt(datos[2]))){
                                ArchivoAvion.agregarAvion(new Avion(datos[0], datos[1], Integer.parseInt(datos[2]), Integer.parseInt(datos[3]), Double.parseDouble(datos[4]), Double.parseDouble(datos[5])));

                            } else {
                                this.ventana.getArea().append("No se pudo cargar el avión ya que ya existe.\n");
                            }
                        } else {
                            System.err.println("Error, no se encontró la aerolinea.");
                            this.ventana.getArea().append("No se pudo cargar el avión ya que no se encontró la aerolinea.\n");
                        }
                    } else {

                        System.err.println("Error, no se encontró el aeropuerto.");
                        this.ventana.getArea().append("No se pudo cargar el avión ya que no se encontró el aeropuerto.\n");
                    }
                    break;
                    
                    case Constante.DISTANCIA:
                    if(Verificaciones.aeroPuertoExistente(datos[0]) && Verificaciones.aeroPuertoExistente(datos[1])){
                        if(!Verificaciones.verificarDistancia(datos[0], datos[1])){
                            ArchivoDistancia.agregarDistancia(new Distancia(datos[0], datos[1], Double.parseDouble(datos[2])));
                        } else {
                            this.ventana.getArea().append("No se pudo cargar la distancia ya que ya existe.\n");
                        }
                    } else {

                        System.err.println("No se encontró uno de los aeropuertos.");
                        this.ventana.getArea().append("No se pudo cargar la distancia ya que no se encontró uno de los aeropuertos.\n");

                    }
                    break;
    
                    case  Constante.PASAPORTE:
                    Date fechaNacimiento = formatoFecha(datos[2]);
                    Date fechaVencimiento = formatoFecha(datos[8]);
                    Date fechaEmision = formatoFecha(datos[9]);
                    if( fechaNacimiento != null && fechaVencimiento != null && fechaEmision !=null){
                        if(!Verificaciones.verificarPasaporte(Integer.parseInt(datos[0]))){
                            ArchivoPasaporte.agregarPasaporte(new Pasaporte(Integer.parseInt(datos[0]), datos[1], fechaNacimiento, datos[3],EstadoCivil.valueOf(datos[4]), datos[5], datos[6], Sexo.valueOf(datos[7]), fechaVencimiento, fechaEmision, datos[10], Double.parseDouble(datos[11])));
                        } else {
                            System.err.println("Error al cargar pasaporte ya que ya existe.");
                            this.ventana.getArea().append("Error al cargar pasaporte ya que ya existe.\n");
                        }
                    } else {
                        System.err.println("Error al cargar pasaporte ya que no cuenta con el formato correcto");
                        this.ventana.getArea().append("Error al cargar pasaporte ya que no cuenta con el formato correcto.\n");
                    }
                    break;
    
                    case Constante.RENOVACION_PASAPORTE:
                    Date fechaRenovacion = formatoFecha(datos[1]);
                    if(fechaRenovacion != null){
                        if(Verificaciones.verificarPasaporte(Integer.parseInt(datos[0]))){
                            ArchivoPasaporte.renovacionPasaporte(Integer.parseInt(datos[0]), fechaRenovacion);
                        } else {

                            System.err.println("No se encontró el pasaporte.");
                            this.ventana.getArea().append("No se pudo cargar la renovación ya que no se ha encontrado el pasaporte.\n");

                        }
                    } else {
                        System.err.println("formato de fecha incorrecto.");
                        this.ventana.getArea().append("No se pudo cargar la renovación ya que la fecha no cuenta con el formato correcto.\n");

                    }
                    break;
    
                    case Constante.RESERVACION:
                    if(Verificaciones.verificarPasaporte(Integer.parseInt(datos[0]))){
                        if(Verificaciones.verificarVuelo(Integer.parseInt(datos[1]))){
                            if(Verificaciones.verificarTarjeta(Long.parseLong(datos[2]))){

                                if(!Verificaciones.verificarPasaporteVuelo(Integer.parseInt(datos[0]))){
                                    ArchivoReservacion.agregarReservacion(new Reservacion(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), Long.parseLong(datos[2]),datos[3]));
                                } else {
                                    System.err.println("Este pasaporte ya cuenta con un vuelo en proceso.");
                                    this.ventana.getArea().append("No se pudo cargar la reservación ya que este pasaporte tiene un vuelo en proceso.\n");
    
                                }
                            } else {

                                System.err.println("Este pasaporte no posee una tarjeta.");
                                this.ventana.getArea().append("No se pudo cargar la reservación ya que este pasaporte no cuenta con una tarjeta.\n");
                            }
                        } else {
                            System.err.println("vuelo no encontrado.");
                            this.ventana.getArea().append("No se pudo cargar la reservación ya que no se ha encontrado el vuelo.\n");

                        }
                    } else {
                        System.err.println("Pasaporte no encontrado.");
                        this.ventana.getArea().append("No se pudo cargar la reservación ya que no se ha encotnrado el pasaporte.\n");

                    }
                    
                    break;
    
                    case Constante.TARJETA:
                    if(Verificaciones.verificarPasaporte(Integer.parseInt(datos[1]))){
                        if(!Verificaciones.verificarTarjeta(Long.parseLong(datos[0]))){
                            ArchivoTarjeta.agregarTarjeta(new Tarjeta(Long.parseLong(datos[0]), Integer.parseInt(datos[1]), Double.parseDouble(datos[2]), Integer.parseInt(datos[3])));

                        } else {
                            System.err.println("Esta tarjeta ya existe.");
                            this.ventana.getArea().append("No se pudo cargar la tarjeta ya que ya existe.\n");
                        }
                    } else {
                        System.err.println("No se encontró el pasaporte.");
                        this.ventana.getArea().append("No se pudo cargar la reservación ya que no se ha encotnrado el pasaporte.\n");
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
                                        this.ventana.getArea().append("No se pudo cargar el vuelo ya que la distancia es mayor a la capacidad de gasolina que posee el avión.\n");

                                    }
                                } else {
                                    System.err.println("No se ha encontrado la distancia.");
                                    this.ventana.getArea().append("No se pudo cargar el vuelo ya que no se ha encontrado la distancia.\n");

                                }
                            } else {
                                System.err.println("Este avión ya tiene un vuelo en proceso.");
                                this.ventana.getArea().append("No se pudo cargar el vuelo ya que el avión seleccionado tiene un vuelo en proceso.\n");

                            }
                        } else {
                            System.err.println("No se ha encontrado el avion.");
                            this.ventana.getArea().append("No se pudo cargar el vuelo ya que no se ha encontrado el avión.\n");

                        }
                    } else {
                        this.ventana.getArea().append("Formato incorrecto en la fecha de salida.\n");
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
            this.ventana.getArea().append(auxiliarDos+"\n\n");
            
            auxiliar = lector.readLine();

        }
        JOptionPane.showMessageDialog(null, "Datos cargados con éxito.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
        lector.close();
    }

    /**
     * método que quita los paréntesis extra colocados en el formato del archivo de texto
     * @param auxiliarDos
     * @return
     */
    private String[] quitarParentesis(String auxiliarDos){

        int posicion = auxiliarDos.lastIndexOf(")");
        String textoDatos = auxiliarDos.substring(1, posicion);
        String[] datos = textoDatos.split(",");

        return datos;
    }

    /**
     * método que cambia un String y retorna una objeto de tipo Date
     * @param texto
     * @return
     */
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
