package com.mycompany.ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.mycompany.constantes.Constante;
import com.mycompany.manejadores.ManejadorOperador;
import com.mycompany.persona.empleados.*;

public class VentanaOperador extends JFrame{
    
    private JPanel panel;
    private JLabel etiquetaActual, etiquetaTitulo, etiquetaEmpresa, etiquetaImagen, etiquetaAvion, etiquetaOrigen, etiquetaDestino;
    private JButton botonIniciar,botonPosponer, botonCancelar, botonPrincipal, botonMenu, botonVisualizar;
    private Empleado empleado;
    private ManejadorOperador manejador;

    public VentanaOperador(Empleado empleado){

        this.setTitle(Constante.TITULO);
        this.setSize(500,400);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.empleado = empleado;

        iniciarComponentes();
        manejador = new ManejadorOperador(this);
    }

    private void iniciarComponentes(){

        colocarPanel();
        colocarEtiqueta();
        colocarBoton();
    }

    private void colocarPanel(){

        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setBackground(Color.GRAY);
        panel.setLayout(null);

    }

    private void colocarEtiqueta(){

        etiquetaImagen = new JLabel();
        etiquetaImagen.setBounds(365, 20, 100,50);
        etiquetaImagen.setIcon(new ImageIcon(Constante.IMAGEN.getImage().getScaledInstance(etiquetaImagen.getWidth(), etiquetaImagen.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaImagen);

        etiquetaEmpresa= new JLabel(Constante.TITULO);
        etiquetaEmpresa.setBounds(355, 75, 150, 20);
        panel.add(etiquetaEmpresa);
        etiquetaEmpresa.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaEmpresa.setForeground(Color.BLACK);

        if(empleado instanceof Administrador){
            etiquetaActual = new  JLabel(((Administrador)this.empleado).getAeroPuertoActual());
            etiquetaActual.setBounds(30, 70, 180, 20);
            panel.add(etiquetaActual);
            etiquetaActual.setFont(new Font("Basic", Font.BOLD, 14));
            etiquetaActual.setForeground(Color.BLACK);
            etiquetaActual.setToolTipText("Click para cambiar de aeroPuerto.");

        } else if(empleado instanceof Gerente){

            etiquetaActual = new  JLabel(((Gerente)this.empleado).getNombreAeroLinea());
            etiquetaActual.setBounds(30, 70, 180, 20);
            panel.add(etiquetaActual);
            etiquetaActual.setFont(new Font("Basic", Font.BOLD, 14));
            etiquetaActual.setForeground(Color.BLACK);
        } else {
            etiquetaActual = new  JLabel(((Operador)this.empleado).getNombreAeroLinea());
            etiquetaActual.setBounds(30, 70, 180, 20);
            panel.add(etiquetaActual);
            etiquetaActual.setFont(new Font("Basic", Font.BOLD, 14));
            etiquetaActual.setForeground(Color.BLACK);
        }

        etiquetaTitulo = new JLabel("OPERAR VUELO", SwingConstants.CENTER);
        etiquetaTitulo.setBounds(0, 30, 500, 30);
        panel.add(etiquetaTitulo);
        etiquetaTitulo.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaTitulo.setForeground(Color.BLACK);

        etiquetaOrigen = new JLabel();
        etiquetaOrigen.setBounds(180, 220, 150, 20);
        panel.add(etiquetaOrigen);

        etiquetaDestino = new JLabel();
        etiquetaDestino.setBounds(355, 220, 150, 20);
        panel.add(etiquetaDestino);

        etiquetaAvion = new JLabel();
        etiquetaAvion.setBounds(180,150, 80, 80);
        panel.add(etiquetaAvion);
    }

    private void colocarBoton(){

        botonIniciar = new JButton("Iniciar");
        botonIniciar.setBounds(30, 130, 120, 30);
        panel.add(botonIniciar);

        botonPosponer = new JButton("Posponer");
        botonPosponer.setBounds(30, 180, 120, 30);
        panel.add(botonPosponer);

        botonCancelar = new JButton("Cancelar");
        botonCancelar.setBounds(30, 230, 120, 30);
        panel.add(botonCancelar);

        botonPrincipal = new JButton("Principal");
        botonPrincipal.setBounds(160, 300, 120, 25);
        panel.add(botonPrincipal);

        botonVisualizar = new JButton("Vizualizar");
        botonVisualizar.setBounds(30, 280, 120, 25);
        panel.add(botonVisualizar);

        oyentesBotones();

        if(this.empleado instanceof Administrador){

            botonMenu = new JButton("Administrar");
            botonMenu.setBounds(300, 300, 120, 25);
            panel.add(botonMenu);
            oyenteMenu();
        } else if(this.empleado instanceof Gerente){

            botonMenu = new JButton("Gerente");
            botonMenu.setBounds(300, 300, 120, 25);
            panel.add(botonMenu);
            oyenteMenu();
        }

    }
    private void oyenteMenu(){
        botonMenu.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                manejador.accionMenu();
            }
        });
    }

    private void oyentesBotones(){

        botonCancelar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){

                manejador.accionCancelar();
            }
        });

        botonPosponer.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                manejador.accionPosponer();
            }
        });

        botonIniciar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){

                manejador.accionVolar();
            }
        });

        botonPrincipal.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                manejador.accionPrincipal();
            }
        });

        botonVisualizar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                manejador.accionVisualizar();
            }
        });
    }

    public JLabel getEtiquetaOrigen(){return this.etiquetaOrigen;}

    public JLabel getEtiquetaDestino(){return this.etiquetaDestino;}

    public JLabel getEtiquetaAvion(){return this.etiquetaAvion;}

    public Empleado getEmpleado(){return this.empleado;}


}
