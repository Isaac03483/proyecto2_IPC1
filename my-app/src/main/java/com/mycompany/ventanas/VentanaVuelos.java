package com.mycompany.ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import com.mycompany.aeropuerto.*;
import com.mycompany.aeropuerto.avion.*;
import com.mycompany.aeropuerto.avion.objeto_avion.Asiento;
import com.mycompany.archivos.*;
import com.mycompany.constantes.Constante;
import com.mycompany.enums.EstadoAsiento;
import com.mycompany.manejadores.ManejadorVuelos;
import com.mycompany.persona.empleados.*;

public class VentanaVuelos extends JDialog{
    
    private Empleado empleado;
    private Vuelo vuelo;
    private JPanel panel;
    private JLabel etiquetaTitulo, etiquetaIngresar;
    private JButton botonCompra,objetos[][];
    private JTextField textoAsiento;
    private Avion avion;
    private int noPasaporte;
    private ManejadorVuelos manejador;
    
    /**
     * Constructor para la ventana VentanaVuelos
     * @param padre
     * @param modal
     * @param empleado
     * @param vuelo
     * @param noPasaporte
     */
    public VentanaVuelos(JFrame padre, boolean modal, Empleado empleado, Vuelo vuelo, int noPasaporte){
        super(padre, modal);
        this.setSize(500,700);
        this.setTitle(Constante.TITULO);
        this.setLocationRelativeTo(null);

        this.empleado = empleado;
        this.vuelo = vuelo;
        this.noPasaporte = noPasaporte;

        iniciarComponentes();

        manejador = new ManejadorVuelos(this);
    }

    /**
     * método para iniciar todos los componentes
     */
    private void iniciarComponentes(){

        colocarPanel();
        colocarEtiqueta();
        colocarAsientos();
        colocarTexto();
    }

    /**
     * método para colocar el panel
     */
    private void colocarPanel(){

        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setBackground(Color.GRAY);
        panel.setLayout(null);
    }

    /**
     * método para colocar etiquetas
     */
    private void colocarEtiqueta(){

        etiquetaTitulo = new JLabel("Distribuición de asientos vuelo no: "+this.vuelo.getCodigoVuelo(), SwingConstants.CENTER);
        etiquetaTitulo.setBounds(0, 20, 500, 14);
        panel.add(etiquetaTitulo);
        etiquetaTitulo.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaTitulo.setForeground(Color.BLACK);
        if(this.empleado == null){
            etiquetaIngresar = new JLabel("Ingrese el no. asiento:");
            panel.add(etiquetaIngresar);
            etiquetaIngresar.setBounds(30, 60, 170, 20);
        }
    }

    /**
     * método para colocar botones
     */
    private void colocarAsientos(){

        avion = buscarAvion(this.vuelo.getCodigoAvion());

        if(avion != null){
            int x = 40, y = 100;
            objetos = new JButton[this.avion.getObjetos().length][this.avion.getObjetos()[0].length];
            //System.out.println(this.avion.getObjetos().length+"    "+this.avion.getObjetos()[0].length);
            for(int i = 0; i < objetos.length; i++){
                for(int j= 0; j < objetos[0].length; j++){

                    if(this.avion.getObjetos()[i][j] instanceof Asiento){

                        objetos[i][j] = new JButton(((Asiento)this.avion.getObjetos()[i][j]).getNoAsiento());
                        objetos[i][j].setBounds(x, y, 60, 20);
                        //System.out.println(((Asiento)this.avion.getObjetos()[i][j]).getNoAsiento());
                        if(((Asiento)this.avion.getObjetos()[i][j]).getEstado() == EstadoAsiento.DISPONIBLE){

                            objetos[i][j].setForeground(Color.BLUE);
                        } else {
                            objetos[i][j].setForeground(Color.RED);
                        }
                        panel.add(objetos[i][j]);
                    } else {

                        objetos[i][j] = new JButton();
                        objetos[i][j].setBounds(x, y, 60, 20);
                        panel.add(objetos[i][j]);
                        
                    }
                    objetos[i][j].setFont(new Font("Basic", Font.BOLD, 9));

                    if(j == (objetos[i].length-1)){

                        x = 40;
                        y += 40;
                    } else {
                        x += 70;
                    }

                }
            }
        }

        if(this.empleado == null){
            botonCompra = new JButton("Comprar");
            botonCompra.setBounds(350, 60, 100, 20);
            panel.add(botonCompra);
            oyenteSeleccion();
        }

    }

    /**
     * método para colocar textField
     */
    private void colocarTexto(){

        if(this.empleado == null){
            textoAsiento = new JTextField();
            panel.add(textoAsiento);
            textoAsiento.setBounds(230, 60, 100, 20);
        }
    }

    /**
     * oyente para botones
     */
    private void oyenteSeleccion(){

        botonCompra.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){

                manejador.accionComprar();
            }
        });
    }

    /**
     * método que retorna un avión dependiendo del código
     * @param codigoAvion
     * @return
     */
    private Avion buscarAvion(int codigoAvion){

        ArrayList<Avion> aviones = ArchivoAvion.leerAvion();

        if(aviones != null){
            for(Avion avion: aviones){
                if(avion.getCodigoAvion() == codigoAvion){
                    return avion;
                }
            }
        }
        return null;
    }

    /**
     * retorna el vuelo seleccionado
     * @return
     */
    public Vuelo getVuelo(){return this.vuelo;}

    /**
     * retorna el textField
     * @return
     */
    public JTextField getTexto(){return this.textoAsiento;}

    /**
     * retorna el avión seleccionado
     * @return
     */
    public Avion getAvion(){return this.avion;}

    /**
     * retorna el número de pasaporte
     * @return
     */
    public int getNoPasaporte(){return this.noPasaporte;}
}
