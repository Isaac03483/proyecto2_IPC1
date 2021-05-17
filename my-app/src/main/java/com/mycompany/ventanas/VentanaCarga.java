package com.mycompany.ventanas;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.mycompany.constantes.Constante;
import com.mycompany.manejadores.ManejadorCarga;
import com.mycompany.persona.empleados.Administrador;

public class VentanaCarga extends JFrame{
    
    private JPanel panel;
    private JButton botonCargar, botonMenuAdministrador, botonMenuPrincipal;
    private JTextArea areaTexto;
    private JScrollPane scroll;
    private ManejadorCarga manejador;
    private Administrador admin;

    /**
     * constructor
     * @param admin
     */
    public VentanaCarga(Administrador admin){

        this.setTitle(Constante.TITULO);
        this.setSize(400,400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.admin = admin;
        iniciarComponentes();
        manejador = new ManejadorCarga(this);
    }

    /**
     * inicia componentes
     * 
     */
    private void iniciarComponentes(){

        colocarPanel();
        colocarBoton();
        colocarArea();
    }

    /**
     * Coloca componentes
     */
    private void colocarPanel(){

        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.GRAY);

    }

    /**
     * Coloca componentes
     */
    private void colocarBoton(){

        botonCargar = new JButton("Cargar archivo");
        botonCargar.setBounds(120, 50, 180, 30);
        panel.add(botonCargar);
        botonCargar.setFont(new Font("Basic", Font.BOLD, 16));
        botonCargar.setForeground(Color.BLACK);
        oyenteCarga();

        botonMenuAdministrador = new JButton("Administrar");
        botonMenuAdministrador.setBounds(50, 330, 150, 30);
        panel.add(botonMenuAdministrador);
        botonMenuAdministrador.setFont(new Font("Basic", Font.BOLD, 14));
        botonMenuAdministrador.setForeground(Color.BLACK);
        oyenteAdministrar();

        botonMenuPrincipal = new JButton("Principal");
        botonMenuPrincipal.setBounds(220, 330, 130, 30);
        panel.add(botonMenuPrincipal);
        botonMenuPrincipal.setFont(new Font("Basic", Font.BOLD, 14));
        botonMenuPrincipal.setForeground(Color.BLACK);
        oyentePrincipal();

    }

    /**
     * Coloca componentes
     */
    private void colocarArea(){

        areaTexto = new JTextArea();
        areaTexto.setBounds(40, 100, 320, 200);
        panel.add(areaTexto);

        scroll = new JScrollPane(areaTexto, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(40,100,320,200);
        panel.add(scroll);
    }

    
    /** 
     * @param oyenteCarga(
     * @return JButton
     */
    public JButton getBoton(){return this.botonCargar;}

    
    /** 
     * @param oyenteCarga(
     * @return JTextArea
     */
    public JTextArea getArea(){return this.areaTexto;}

    
    /** 
     * @param oyenteCarga(
     * @return Administrador
     */
    public Administrador getAdmin(){return this.admin;}

    /**
     * oyente botonCargar
     */
    private void oyenteCarga(){

        botonCargar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                
              manejador.accionCargar();  
            }
        });
    }

    /**
     * oyente botonMenuAdministrador
     */
    private void oyenteAdministrar(){

        botonMenuAdministrador.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                manejador.accionAdministrar();
            }
        });
    }

    /**
     * oyente botonMenuPrincipal
     */
    private void oyentePrincipal(){

        botonMenuPrincipal.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                manejador.accionPrincipal();
            }
        });
    }
}
