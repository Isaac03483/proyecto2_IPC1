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
    
    {
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

    public void generarContrasena(){
        if(this.contrasena == ""){
            this.contrasena = com.mycompany.generadorCodigos.GenerarCodigo.generarCodigo(this.contrasena, Constante.CARACTERES_CONTRASENA, true);
        }
    }

    public void setFechaVencimiento(Date fechaVencimiento){this.fechaVencimiento = fechaVencimiento;}

    public int getNoPasaporte(){return this.noPasaporte;}

    public String getContrasena(){return this.contrasena;}

    public String getNombre(){return this.nombre;}

    public String getApellido(){return this.apellido;}

    public Sexo getSexo(){return this.sexo;}

    public Date getFechaNacimiento(){return this.fechaNacimiento;}

    public Date getFechaEmision(){return this.fechaEmision;}

    public String getNacionalidad(){return this.nacionalidad;}

    public EstadoCivil getEstadoCivil(){return this.estadoCivil;}

    public Date fechaVencimiento(){return this.fechaVencimiento;}

    public String getPaisActual(){return this.paisActual;}

    public double getMillasRecorridas(){return this.millasRecorridas;}


}