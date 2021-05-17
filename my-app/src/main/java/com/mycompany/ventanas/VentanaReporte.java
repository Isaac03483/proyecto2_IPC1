package com.mycompany.ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mycompany.constantes.Constante;
import com.mycompany.manejadores.ManejadorVentanaReporte;
import com.mycompany.persona.empleados.Administrador;

public class VentanaReporte extends JFrame{
    
    private JPanel panel;
    private JLabel etiquetaTitulo, etiquetaEmpresa, etiquetaImagen;
    private JButton botonSexo, botonEstadoCivil, botonVuelo, botonAvion, botonVolver, botonAdministrar;
    private JTable tabla;
    private JScrollPane scroll;
    private DefaultTableModel modelo;
    private Administrador admin;
    private ManejadorVentanaReporte manejador;
    
    /**
     * Constructor ventana
     */
    public VentanaReporte( Administrador admin){

        this.admin = admin;
        this.setTitle(Constante.TITULO);
        this.setSize(700,500);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        iniciarComponentes();

        this.manejador = new ManejadorVentanaReporte(this);
    }

    private void iniciarComponentes(){

        colocarPanel();
        colocarEtiqueta();
        colocarBoton();
        colocarTabla();

    }

    private void colocarPanel(){
        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setBackground(Color.GRAY);
        panel.setLayout(null);

    }

    private void colocarEtiqueta(){

        etiquetaImagen = new JLabel();
        etiquetaImagen.setBounds(555, 20, 100,50);
        etiquetaImagen.setIcon(new ImageIcon(Constante.IMAGEN.getImage().getScaledInstance(etiquetaImagen.getWidth(), etiquetaImagen.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaImagen);

        etiquetaEmpresa= new JLabel(Constante.TITULO);
        etiquetaEmpresa.setBounds(545, 75, 150, 20);
        panel.add(etiquetaEmpresa);
        etiquetaEmpresa.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaEmpresa.setForeground(Color.BLACK);

        etiquetaTitulo = new JLabel("REPORTES", SwingConstants.CENTER);
        etiquetaTitulo.setBounds(0, 30, 700, 30);
        panel.add(etiquetaTitulo);
        etiquetaTitulo.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaTitulo.setForeground(Color.BLACK);
    }

    private void colocarBoton(){

        botonAdministrar = new JButton("Administrar");
        botonAdministrar.setBounds(100, 420, 120,30);
        panel.add(botonAdministrar);

        botonVolver = new JButton("Volver");
        botonVolver.setBounds(300, 420, 100,30);
        panel.add(botonVolver);

        botonSexo = new JButton("Sexo");
        botonSexo.setBounds(530, 120, 100, 20);
        panel.add(botonSexo);

        botonEstadoCivil = new JButton("Estado Civil");
        botonEstadoCivil.setBounds(530, 150, 150, 20);
        panel.add(botonEstadoCivil);

        botonAvion = new JButton("Avion");
        botonAvion.setBounds(530, 180, 100, 20);
        panel.add(botonAvion);

        botonVuelo = new JButton("Vuelo");
        botonVuelo.setBounds(530, 210, 100,20);
        panel.add(botonVuelo);

        agregarOyentes();

    }

    private void colocarTabla(){

        modelo = new DefaultTableModel();

        tabla = new JTable(modelo);
        tabla.setBounds(30, 100, 450, 300);
        panel.add(tabla);

        scroll = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(30,100,450, 300);
        panel.add(scroll);
    }

    private void agregarOyentes(){

        botonVolver.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent  ae){
                manejador.accionPrincipal();
            }
        });

        botonAdministrar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                manejador.accionAdministrar();
            }
        });

        botonSexo.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                manejador.accionSexo();

            }
        });

        botonEstadoCivil.addActionListener(new  ActionListener(){

            public void actionPerformed(ActionEvent ae){
                manejador.accionEstado();
            }
        });

        botonVuelo.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                manejador.accionVuelo();
            }
        });

        botonAvion.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ea){
                manejador.accionAvion();
            }
        });
    }

    public DefaultTableModel getModelo(){return this.modelo;}

    public Administrador getAdministrador(){return this.admin;}
}
