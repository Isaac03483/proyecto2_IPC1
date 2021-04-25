package com.mycompany.ventanas;

import java.awt.*;
import javax.swing.*;

import com.mycompany.aeropuerto.AeroLinea;
import com.mycompany.manejadores.ManejadorCliente;

public class VentanaCliente extends JFrame{
    
    private JPanel panel;
    private JLabel etiquetaMenu, etiquetaOrigen, etiquetaDestino, etiquetaCantidad, etiquetaFecha, etiquetaAeroLinea;
    private JTextField textoOrigen, textoDestino, textoCantidad, textoFecha;
    private JButton botonComprarBoleto;
    private JComboBox<AeroLinea> comboAeroLinea;
    private ManejadorCliente manejador;

    public VentanaCliente(JFrame padre){

        this.setSize(600,500);
        this.setResizable(false);
        this.setTitle(padre.getTitle());
        this.setLocationRelativeTo(padre);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        iniciarComponentes();

        this.manejador = new ManejadorCliente(this);
    }

    private void iniciarComponentes(){

        colocarPanel();
        colocarEtiquetas();
        colocarTextos();
        colocarCombo();
        colocarBoton();
    }

    private void colocarPanel(){

        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.GRAY);
    
    }

    private void colocarEtiquetas(){

        etiquetaMenu = new JLabel("Comprar Boleto", SwingConstants.CENTER);
        etiquetaMenu.setBounds(0,40, 600, 20);
        panel.add(etiquetaMenu);
        etiquetaMenu.setFont(new Font("Basic", Font.BOLD, 16));
        etiquetaMenu.setForeground(Color.BLACK);

        etiquetaOrigen = new JLabel("Ciudad de Origen:");
        etiquetaOrigen.setBounds(30,100, 150, 20);
        panel.add(etiquetaOrigen);
        etiquetaOrigen.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaOrigen.setForeground(Color.BLACK);

        etiquetaDestino = new JLabel("Ciudad de Destino:");
        etiquetaDestino.setBounds(30,135, 150, 20);
        panel.add(etiquetaDestino);
        etiquetaDestino.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaDestino.setForeground(Color.BLACK);

        etiquetaCantidad = new JLabel("Cantidad de Boletos:");
        etiquetaCantidad.setBounds(30,170, 170, 20);
        panel.add(etiquetaCantidad);
        etiquetaCantidad.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaCantidad.setForeground(Color.BLACK);

        etiquetaFecha = new JLabel("Fecha de Vuelo:");
        etiquetaFecha.setBounds(30,205, 150, 20);
        panel.add(etiquetaFecha);
        etiquetaFecha.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaFecha.setForeground(Color.BLACK);

        etiquetaAeroLinea = new JLabel("AeroLinea:");
        etiquetaAeroLinea.setBounds(30,240, 120, 20);
        panel.add(etiquetaAeroLinea);
        etiquetaAeroLinea.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaAeroLinea.setForeground(Color.BLACK);

    }

    private void colocarTextos(){

        textoOrigen = new JTextField();
        textoOrigen.setBounds(190, 100, 100, 20);
        panel.add(textoOrigen);

        textoDestino = new JTextField();
        textoDestino.setBounds(190, 135, 100, 20);
        panel.add(textoDestino);

        textoCantidad = new JTextField();
        textoCantidad.setBounds(200, 170, 90, 20);
        panel.add(textoCantidad);

        textoFecha = new JTextField();
        textoFecha.setBounds(190, 205, 100, 20);
        panel.add(textoFecha);
    }

    private void colocarCombo(){

        comboAeroLinea = new JComboBox<>();
        comboAeroLinea.setBounds(140, 240, 150, 20);
        try{
            manejador.agregarAeroLineas();
        } catch(NullPointerException e){
            System.err.println("Funciona pero no hay ningún dato.");
        }
        panel.add(comboAeroLinea);
    }

    private void colocarBoton(){
        botonComprarBoleto = new JButton("Comprar Boleto");
        botonComprarBoleto.setBounds(80, 300, 170, 30);
        panel.add(botonComprarBoleto);
        botonComprarBoleto.setFont(new Font("Basic", Font.BOLD, 14));
        botonComprarBoleto.setForeground(Color.BLACK);
    }

    public JComboBox<AeroLinea> getCombo(){return this.comboAeroLinea;}
}
