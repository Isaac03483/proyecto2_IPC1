package com.mycompany.aeropuerto;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mycompany.constantes.Constante;
import com.mycompany.persona.empleados.*;

public class AeroLinea implements Serializable{
    
    private String nombreAeroPuerto;
    private String nombreAeroLinea;
    private double ganancias;
    private Gerente gerente;
    private ArrayList<Operador> operadores;
    
    {
        this.ganancias =0;
        this.gerente = null;
        this.operadores = new ArrayList<>();
    }

    public AeroLinea(String nombreAeroPuerto, String nombreAeroLinea){

        this.nombreAeroPuerto=nombreAeroPuerto;
        this.nombreAeroLinea = nombreAeroLinea;
    }

    public Gerente getGerente(){return this.gerente;}

    public String getNombreAeroLinea(){return this.nombreAeroLinea;}

    public String getNombreAeroPuerto(){return this.nombreAeroPuerto;}

    public void setGanancias(double ganancias){this.ganancias+=ganancias;}

    public double getGanancias(){return this.ganancias;}

    @Override
    public String toString(){return this.nombreAeroLinea;}

    public void asignarGerente(Gerente gerente){
        if(this.gerente == null){
            this.gerente = gerente;
            JOptionPane.showMessageDialog(null, "Gerente asignado con éxito.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Esta aeroLinea ya cuenta con un gerente asignado.", Constante.TITULO, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void asignarOperador(Operador operador){this.operadores.add(operador);}
}
