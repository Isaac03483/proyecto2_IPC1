package com.mycompany.ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import com.mycompany.aeropuerto.*;
import com.mycompany.archivos.ArchivoAeroPuerto;
import com.mycompany.manejadores.ManejadorCrearDistancia;

public class VentanaCrearDistancia extends JDialog{

    private VentanaCrearVuelo vuelo;
    private JPanel panel;
    private JLabel etiquetaTitulo, etiquetaAeroPuertoOrigen, etiquetaAeroPuertoDestino, etiquetaMillas;
    private JTextField textoMillas;
    private JComboBox<AeroPuerto> comboOrigen, comboDestino;
    private JButton botonCrear;
    private ManejadorCrearDistancia manejador;

    /**
     * Constructor
     * @param padre
     * @param modal
     */
    public VentanaCrearDistancia(VentanaCrearVuelo padre, boolean modal){

        super(padre, modal);
        this.vuelo = padre;
        this.setSize(500, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        iniciarComponentes();
        manejador = new ManejadorCrearDistancia(this);
    }

    /**
     * inicia componentes
     */
    private void iniciarComponentes(){

        colocarPanel();
        colocarEtiqueta();
        colocarBoton();
        colocarCombo();
        colocarTexto();
    }

    /**
     * coloca componentes
     */
    private void colocarPanel(){

        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.GRAY);
    }

    /**
     * coloca componentes
     */
    private void colocarEtiqueta(){

        etiquetaTitulo = new JLabel("CREAR DISTANCIA", SwingConstants.CENTER);
        etiquetaTitulo.setBounds(0, 30, 500, 20);
        panel.add(etiquetaTitulo);
        etiquetaTitulo.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaTitulo.setForeground(Color.BLACK);

        etiquetaAeroPuertoOrigen = new JLabel("Aeropuerto de origen:");
        etiquetaAeroPuertoOrigen.setBounds(40, 70, 170, 20);
        panel.add(etiquetaAeroPuertoOrigen);
        etiquetaAeroPuertoOrigen.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaAeroPuertoOrigen.setForeground(Color.BLACK);

        etiquetaAeroPuertoDestino = new JLabel("Aeropuerto de destino:");
        etiquetaAeroPuertoDestino.setBounds(40, 110, 180, 20);
        panel.add(etiquetaAeroPuertoDestino);
        etiquetaAeroPuertoDestino.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaAeroPuertoDestino.setForeground(Color.BLACK);

        etiquetaMillas = new JLabel("Cantidad de millas:");
        etiquetaMillas.setBounds(40, 150, 150, 20);
        panel.add(etiquetaMillas);
        etiquetaMillas.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaMillas.setForeground(Color.BLACK);

    }

    /**
     * coloca componentes
     */
    private void colocarTexto(){

        textoMillas = new JTextField();
        textoMillas.setBounds(200, 150, 130, 20);
        panel.add(textoMillas);
    }

    /**
     * coloca componentes
     */
    private void colocarBoton(){
        
        botonCrear = new JButton("Crear");
        botonCrear.setBounds(180, 200, 120, 30);
        panel.add(botonCrear);
        botonCrear.setFont(new Font("Basic", Font.BOLD, 13));
        botonCrear.setForeground(Color.BLACK);

        oyenteCrear();
    }

    /**
     * coloca componentes
     */
    private void colocarCombo(){

        comboOrigen = new JComboBox<>();
        comboOrigen.setBounds(230, 70, 210, 20);
        panel.add(comboOrigen);

        comboDestino = new JComboBox<>();
        comboDestino.setBounds(230, 110, 210, 20);
        panel.add(comboDestino);

        agregarAeroPuertos();
    }

    /**
     * agrega aeropuertos al combo
     */
    private void agregarAeroPuertos(){

        ArrayList<AeroPuerto> aeroPuertos = ArchivoAeroPuerto.leerAeroPuertos();

        if(aeroPuertos != null){

            for(AeroPuerto aeroPuerto: aeroPuertos){
                
                comboOrigen.addItem(aeroPuerto);
                comboDestino.addItem(aeroPuerto);
                
            }
        }
    }

    /**
     * oyente botonCrear
     */
    private void oyenteCrear(){

        botonCrear.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){

                manejador.accionCrear();
            }
        });
    }

    /**
     * retorna combo
     * @return
     */
    public JComboBox<AeroPuerto> getOrigen(){return this.comboOrigen;}

    /**
     * retorna combo
     * @return
     */
    public JComboBox<AeroPuerto> getDestino(){return this.comboDestino;}

    /**
     * retorna JTextField
     * @return
     */
    public JTextField getTextoMillas(){return this.textoMillas;}

    /**
     * retorna ventanaVuelo
     * @return
     */
    public VentanaCrearVuelo getVentanaVuelo(){return this.vuelo;}
}
