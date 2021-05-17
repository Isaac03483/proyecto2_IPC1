package com.mycompany.manejadores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

import com.mycompany.archivos.*;
import com.mycompany.cargaDatos.Verificaciones;
import com.mycompany.constantes.Constante;
import com.mycompany.enums.*;
import com.mycompany.persona.pasajero.*;
import com.mycompany.ventanas.VentanaCrearPasaporte;

public class ManejadorCrearPasaporte {
    
    private VentanaCrearPasaporte ventana;

    /**
     * Constructor para el manejo de la ventanaCrearPasaporte
     * @param ventana
     */
    public ManejadorCrearPasaporte(VentanaCrearPasaporte ventana){
        this.ventana = ventana;
    }

    /**
     * Método que crea los pasaportes con la información proporcionada
     */
    public void accionCrear(){
        
        String nombre = this.ventana.getTextoNombre().getText();
        String apellido = this.ventana.getTextoApellido().getText();
        String nacionalidad = this.ventana.getTextoNacionalidad().getText();
        String fechaNacimiento = this.ventana.getTextoNacimiento().getText();
        String fechaEmision = this.ventana.getTextoEmision().getText();
        String fechaVencimiento = this.ventana.getTextoVencimiento().getText();

        if(!nombre.equals("")){

            if(!apellido.equals("")){

                if(!nacionalidad.equals("")){

                    if(!fechaNacimiento.equals("")){

                        if(!fechaEmision.equals("") && !fechaVencimiento.equals("")){

                            Sexo sexo = sexoSeleccionado();
                            EstadoCivil estadoCivil = estadoSeleccionado();

                            try{

                                Date nacimiento = verificarFormatoFecha(fechaNacimiento);
                                Date emision = verificarFormatoFecha(fechaEmision);
                                Date vencimiento = verificarFormatoFecha(fechaVencimiento);

                                if(vencimiento.after(emision)){

                                    String paisActual = (String)this.ventana.getComboPais().getSelectedItem();
                                    Pasaporte pasaporte = null;
                                    
                                    do{
                                        pasaporte = new Pasaporte(nombre, apellido, sexo, nacimiento, nacionalidad, estadoCivil, emision, vencimiento, paisActual);
                                    } while(Verificaciones.verificarPasaporte(pasaporte.getNoPasaporte())==true);

                                    if(pasaporte != null){
                                        ArchivoPasaporte.agregarPasaporte(pasaporte);
                                        ArchivoTarjeta.agregarTarjeta(new Tarjeta(pasaporte.getNoPasaporte(), 1000));
                                        JOptionPane.showMessageDialog(null, "Pasaporte no: "+pasaporte.getNoPasaporte()+" creado con éxito.",Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);

                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "La fecha de vencimiento no puede ser menor a la fecha de emisión.",Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);

                                }
                            } catch(ParseException e){
                                JOptionPane.showMessageDialog(null, "El formato de fecha no es el correcto.",Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Campo fecha emision/fecha vencimiento se encuentra vacío.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Campo fecha de nacimiento se encuentra vacío.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Campo nacionalidad se encuentra vacío.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);

                }
            } else {
                JOptionPane.showMessageDialog(null, "Campo apellido se encuentra vacío.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);

            }
        } else {
            JOptionPane.showMessageDialog(null, "Campo nombre se encuentra vacío.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * método que retorna el sexo del usuario dependiendo
     * del radioButton que esté seleccionado
     * @return
     */
    private Sexo sexoSeleccionado(){

        if(this.ventana.getRadioHombre().isSelected()){
            return Sexo.MASCULINO;
        } else if(this.ventana.getRadioMujer().isSelected()){
            return Sexo.FEMENINO;
        } else {
            return  Sexo.SINDEFINIR;
        }
    }

    /**
     * método que retorna el estado civil del usuario dependiendo
     * del radioButton seleccionado
     * @return
     */
    private EstadoCivil estadoSeleccionado(){

        if(this.ventana.getRadioCasado().isSelected()){
            return EstadoCivil.CASADO;
        } else if(this.ventana.getRadioDivorciado().isSelected()){
            return EstadoCivil.DIVORCIADO;
        } else {
            return EstadoCivil.SOLTERO;
        }
    }

    /**
     * método que convierte una cadena de texto a una fecha con formato predefinido
     * @param cadena
     * @return
     * @throws ParseException
     */
    private Date verificarFormatoFecha(String cadena) throws ParseException{

        return new SimpleDateFormat("dd/mm/yyyy").parse(cadena);
    }
}
