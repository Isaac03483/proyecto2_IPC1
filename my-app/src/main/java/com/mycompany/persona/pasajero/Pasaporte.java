package com.mycompany.persona.pasajero;

import java.io.Serializable;
import java.util.Date;
import com.mycompany.constantes.*;
import com.mycompany.enums.*;

public class Pasaporte implements Serializable{

    private int noPasaporte;
    private String contrasena;
    private Date fechaNacimiento;
    private String nacionalidad;
    private EstadoCivil estadoCivil;
    private String nombre;
    private String apellido;
    private Sexo sexo;
    private Date fechaVencimiento;
    private Date fechaEmision;
    private String paisActual;
    private double millasRecorridas;
    private int boletosComprados;
    
    {
        this.boletosComprados =0;
        this.contrasena="";
        this.millasRecorridas =0;
    }

    /**
     * Constructor para la creación de pasaportes en el programa
     * @param nombre
     * @param apellido
     * @param sexo
     * @param fechaNacimiento
     * @param nacionalidad
     * @param estadoCivil
     * @param fechaEmision
     * @param fechaVencimiento
     * @param paisActual
     */
    public Pasaporte(String nombre, String apellido, Sexo sexo, Date fechaNacimiento, String nacionalidad,EstadoCivil estadoCivil, Date fechaEmision, Date fechaVencimiento, String paisActual){
        
        this.nombre = nombre;
        this.apellido= apellido;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad= nacionalidad;
        this.estadoCivil = estadoCivil;
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.paisActual = paisActual;
        this.noPasaporte = Integer.parseInt(com.mycompany.generadorCodigos.GenerarCodigo.generarCodigo("", Constante.CARACTERES_NUMERO_PASAPORTE, false));
    }

    /**
     * Constructor utilizado para la carga de datos
     * @param noPasaporte
     * @param contrasena
     * @param fechaNacimiento
     * @param nacionalidad
     * @param estadoCivil
     * @param nombre
     * @param apellido
     * @param sexo
     * @param fechaVencimiento
     * @param fechaEmision
     * @param paisActual
     * @param millasRecorridas
     */
    
    public Pasaporte(int noPasaporte, String contrasena, Date fechaNacimiento, String nacionalidad,EstadoCivil estadoCivil, String nombre, String apellido, Sexo sexo, Date fechaVencimiento, Date fechaEmision, String paisActual, double millasRecorridas){
        
        this.noPasaporte=noPasaporte;
        this.contrasena=contrasena;
        this.nombre = nombre;
        this.apellido= apellido;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad= nacionalidad;
        this.estadoCivil = estadoCivil;
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.paisActual = paisActual;
        this.millasRecorridas=millasRecorridas;
    }

    /**
     * método que genera una constraseña para el pasaporte
     */
    public void generarContrasena(){
        this.contrasena = com.mycompany.generadorCodigos.GenerarCodigo.generarCodigo(this.contrasena, Constante.CARACTERES_CONTRASENA, true);
        
    }

    /**
     * método que cambia la fecha de vencimiento del pasaporte
     * @param fechaVencimiento
     */
    public void setFechaVencimiento(Date fechaVencimiento){this.fechaVencimiento = fechaVencimiento;}

    /**
     * método que cambia el país en el que se encuentra el pasaporte
     * @param pais
     */
    public void setPaisActual(String pais){this.paisActual = pais;}

    public void setBoletosComprados(){this.boletosComprados++;}

    public int getBoletosComprados(){return this.boletosComprados;}

    /**
     * método que retorna el número de pasaporte
     * @return
     */
    public int getNoPasaporte(){return this.noPasaporte;}

    /**
     * método que retorna la contraseña del pasaporte
     * @return
     */
    public String getContrasena(){return this.contrasena;}

    /**
     * método que retorna el nombre
     * @return
     */
    public String getNombre(){return this.nombre;}

    /**
     * método que retorna el apellido
     * @return
     */
    public String getApellido(){return this.apellido;}

    /**
     * método que retorna el sexo
     * @return
     */
    public Sexo getSexo(){return this.sexo;}

    /**
     * método que retorna la fecha de nacimiento
     * @return
     */
    public Date getFechaNacimiento(){return this.fechaNacimiento;}

    /**
     * método que retorna la fecha en la que se creo el pasaporte
     * @return
     */
    public Date getFechaEmision(){return this.fechaEmision;}

    /**
     * método que retorna la nacionalidad
     * @return
     */
    public String getNacionalidad(){return this.nacionalidad;}

    /**
     * método que retorna el estadoCivil
     * @return
     */
    public EstadoCivil getEstadoCivil(){return this.estadoCivil;}

    /**
     * método que retorna la fecha fe vencimiento del pasaporte
     * @return
     */
    public Date fechaVencimiento(){return this.fechaVencimiento;}

    /**
     * método que retorna el país en el que se encuentra el pasaporte
     * @return
     */
    public String getPaisActual(){return this.paisActual;}

    /**
     * método que retorna el total de millas recorridas del pasaporte
     * @return
     */
    public double getMillasRecorridas(){return this.millasRecorridas;}


}