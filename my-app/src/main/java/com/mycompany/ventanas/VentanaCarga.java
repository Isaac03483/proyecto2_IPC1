package com.mycompany.ventanas;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.mycompany.constantes.Constante;
import com.mycompany.manejadores.ManejadorCarga;

public class VentanaCarga extends JFrame{
    
    private JPanel panel;
    private JButton botonCargar;
    private JTextArea areaTexto;
    private ManejadorCarga manejador;

    public VentanaCarga(){

        this.setTitle(Constante.TITULO);
        this.setSize(400,400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        iniciarComponentes();
        manejador = new ManejadorCarga(this);
    }

    private void iniciarComponentes(){

        colocarPanel();
        colocarBoton();
        colocarArea();
    }

    private void colocarPanel(){

        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.GRAY);

    }

    private void colocarBoton(){

        botonCargar = new JButton("Cargar archivo");
        botonCargar.setBounds(120, 50, 180, 30);
        panel.add(botonCargar);
        botonCargar.setFont(new Font("Basic", Font.BOLD, 16));
        botonCargar.setForeground(Color.BLACK);
        oyenteCarga();

    }

    private void colocarArea(){

        areaTexto = new JTextArea();
        areaTexto.setBounds(40, 100, 320, 220);
        panel.add(areaTexto);
    }

    public JButton getBoton(){return this.botonCargar;}

    public JTextArea getArea(){return this.areaTexto;}

    private void oyenteCarga(){

        botonCargar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                
              manejador.accionCargar();  
            }
        });
    }
}
