package com.mycompany.ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.mycompany.aeropuerto.*;
import com.mycompany.constantes.Constante;
import com.mycompany.manejadores.*;
import com.mycompany.persona.empleados.*;

public class VentanaCrearAero extends JFrame{
    
    private JPanel panel;
    private JLabel etiquetaImagen, etiquetaEmpresa, etiquetaTitulo, etiquetaEleccion, etiquetaNombreAeroPuerto, etiquetaPais, etiquetaCiudad, etiquetaNombreAeroLinea, etiquetaAeroPuertoActual;
    private JRadioButton radioAeroLinea, radioAeroPuerto;
    private ButtonGroup grupo;
    private JTextField textoNombreAeroPuerto, textoPais, textoCiudad, textoNombreAeroLinea;
    private JButton botonCrear, botonPrincipal, botonAdministrar;
    private JComboBox<AeroPuerto> comboAeroPuerto;
    private Administrador admin;
    private ManejadorCrearAero manejador;

    /**
     * constructor
     * @param admin
     */
    public VentanaCrearAero(Administrador admin){

        this.admin = admin;
        this.setTitle(Constante.TITULO);
        this.setSize(600,400);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        iniciarComponentes();

        manejador = new ManejadorCrearAero(this);
    }

    /**
     * iniciar componentes
     */
    private void iniciarComponentes(){

        colocarPanel();
        colocarEtiquetas();
        colocarTexto();
        colocarCombo();
        colocarRadio();
        colocarBotones();

    }

    /**
     * Colocar componentes
     */
    private void colocarPanel(){

        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setBackground(Color.GRAY);
        panel.setLayout(null);
    }

    /**
     * Colocar componentes
     */
    private void colocarEtiquetas(){

        etiquetaImagen = new JLabel();
        etiquetaImagen.setBounds(455, 20, 100,50);
        etiquetaImagen.setIcon(new ImageIcon(Constante.IMAGEN.getImage().getScaledInstance(etiquetaImagen.getWidth(), etiquetaImagen.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaImagen);

        etiquetaEmpresa= new JLabel(Constante.TITULO);
        etiquetaEmpresa.setBounds(445, 75, 150, 20);
        panel.add(etiquetaEmpresa);
        etiquetaEmpresa.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaEmpresa.setForeground(Color.BLACK);

        etiquetaAeroPuertoActual = new  JLabel(this.admin.getAeroPuertoActual());
        etiquetaAeroPuertoActual.setBounds(30, 70, 150, 20);
        panel.add(etiquetaAeroPuertoActual);
        etiquetaAeroPuertoActual.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaAeroPuertoActual.setForeground(Color.BLACK);
        etiquetaAeroPuertoActual.setToolTipText("Click para cambiar de aeroPuerto.");
        oyenteAeroPuerto();

        etiquetaTitulo = new JLabel("CREAR AEROPUERTO/AEROLINEA", SwingConstants.CENTER);
        etiquetaTitulo.setBounds(0, 40, 600, 30);
        panel.add(etiquetaTitulo);
        etiquetaTitulo.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaTitulo.setForeground(Color.BLACK);

        etiquetaEleccion = new JLabel("Crear:");
        etiquetaEleccion.setBounds(40, 105, 70, 20);
        panel.add(etiquetaEleccion);
        etiquetaEleccion.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaEleccion.setForeground(Color.BLACK);

        etiquetaNombreAeroPuerto = new JLabel("Nombre Aeropuerto:");
        etiquetaNombreAeroPuerto.setBounds(40, 145, 200, 20);
        panel.add(etiquetaNombreAeroPuerto);
        etiquetaNombreAeroPuerto.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaNombreAeroPuerto.setForeground(Color.BLACK);

        etiquetaNombreAeroLinea = new JLabel("Nombre Aerolinea:");
        etiquetaNombreAeroLinea.setBounds(40, 185, 200, 20);
        panel.add(etiquetaNombreAeroLinea);
        etiquetaNombreAeroLinea.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaNombreAeroLinea.setForeground(Color.BLACK);
        etiquetaNombreAeroLinea.setVisible(false);

        etiquetaPais = new JLabel("País:");
        etiquetaPais.setBounds(40, 185, 90, 20);
        panel.add(etiquetaPais);
        etiquetaPais.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaPais.setForeground(Color.BLACK);

        etiquetaCiudad= new JLabel("Ciudad:");
        etiquetaCiudad.setBounds(40, 225, 100, 20);
        panel.add(etiquetaCiudad);
        etiquetaCiudad.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaCiudad.setForeground(Color.BLACK);
    }

    /**
     * Colocar componentes
     */
    private void colocarTexto(){

        textoNombreAeroPuerto = new JTextField();
        textoNombreAeroPuerto.setBounds(220, 145, 120, 20);
        panel.add(textoNombreAeroPuerto);

        textoNombreAeroLinea = new JTextField();
        textoNombreAeroLinea.setBounds(220, 185, 120, 20);
        panel.add(textoNombreAeroLinea);
        textoNombreAeroLinea.setVisible(false);

        textoPais = new JTextField();
        textoPais.setBounds(130, 185, 120, 20);
        panel.add(textoPais);

        textoCiudad = new JTextField();
        textoCiudad.setBounds(140, 225, 110, 20);
        panel.add(textoCiudad);
    }

    /**
     * Colocar componentes
     */
    private void colocarRadio(){

        radioAeroPuerto = new JRadioButton("Aeropuerto.");
        radioAeroPuerto.setBounds(120, 105, 125, 20);
        panel.add(radioAeroPuerto);
        radioAeroPuerto.setFont(new Font("Basic", Font.BOLD, 13));
        radioAeroPuerto.setForeground(Color.BLACK);
        radioAeroPuerto.setBackground(Color.GRAY);
        radioAeroPuerto.setSelected(true);

        radioAeroLinea = new JRadioButton("Aerolinea.");
        radioAeroLinea.setBounds(270, 105, 125, 20);
        panel.add(radioAeroLinea);
        radioAeroLinea.setFont(new Font("Basic", Font.BOLD, 13));
        radioAeroLinea.setForeground(Color.BLACK);
        radioAeroLinea.setBackground(Color.GRAY);
        
        grupo = new ButtonGroup();
        grupo.add(radioAeroPuerto);
        grupo.add(radioAeroLinea);

        oyentePuerto();
        
    }

    /**
     * Colocar componentes
     */
    private void colocarCombo(){
        
        comboAeroPuerto = new JComboBox<>();
        comboAeroPuerto.setBounds(220, 145, 200, 20);
        panel.add(comboAeroPuerto);
        comboAeroPuerto.setVisible(false);
    }

    /**
     * Colocar componentes
     */
    private void colocarBotones(){

        botonCrear = new JButton("Crear");
        botonCrear.setBounds(60, 270, 100, 30);
        panel.add(botonCrear);
        botonCrear.setFont(new Font("Basic", Font.BOLD, 13));
        botonCrear.setForeground(Color.BLACK);
        accionCrear();

        botonAdministrar = new JButton("Administrar");
        botonAdministrar.setBounds(180, 270, 130, 30);
        panel.add(botonAdministrar);
        botonAdministrar.setFont(new Font("Basic", Font.BOLD, 13));
        botonAdministrar.setForeground(Color.BLACK);
        accionAdministrar();

        botonPrincipal = new JButton("Principal");
        botonPrincipal.setBounds(330, 270, 100, 30);
        panel.add(botonPrincipal);
        botonPrincipal.setFont(new Font("Basic", Font.BOLD, 13));
        botonPrincipal.setForeground(Color.BLACK);
        accionPrincipal();
    }

    /**
     * limpia los campos
     */
    public void limpiarTexto(){
        textoNombreAeroPuerto.setText("");
        textoPais.setText("");
        textoCiudad.setText("");
        textoNombreAeroLinea.setText("");
    }

    
    /** 
     * @param oyenteAeroPuerto(
     * @return JTextField
     */
    public JTextField getTextoNombreAeroPuerto(){return this.textoNombreAeroPuerto;}

    
    /** 
     * @param oyenteAeroPuerto(
     * @return JTextField
     */
    public JTextField getTextoNombreAeroLinea(){return this.textoNombreAeroLinea;}

    
    /** 
     * @param oyenteAeroPuerto(
     * @return JTextField
     */
    public JTextField getTextoPais(){return this.textoPais;}

    
    /** 
     * @param oyenteAeroPuerto(
     * @return JTextField
     */
    public JTextField getTextoCiudad(){return this.textoCiudad;}

    
    /** 
     * @param oyenteAeroPuerto(
     * @return JLabel
     */
    public JLabel getEtiquetaNombreAeroPuerto(){return this.etiquetaNombreAeroPuerto;}

    
    /** 
     * @param oyenteAeroPuerto(
     * @return JLabel
     */
    public JLabel getEtiquetaNombreAeroLinea(){return this.etiquetaNombreAeroLinea;}

    
    /** 
     * @param oyenteAeroPuerto(
     * @return JLabel
     */
    public JLabel getEtiquetaPais(){return this.etiquetaPais;}

    
    /** 
     * @param oyenteAeroPuerto(
     * @return JLabel
     */
    public JLabel getEtiquetaCiudad(){return this.etiquetaCiudad;}

    
    /** 
     * @param oyenteAeroPuerto(
     * @return JRadioButton
     */
    public JRadioButton getRadioAeroPuerto(){return this.radioAeroPuerto;}

    
    /** 
     * @param oyenteAeroPuerto(
     * @return JRadioButton
     */
    public JRadioButton getRadioAeroLinea(){return this.radioAeroLinea;}

    
    /** 
     * @param oyenteAeroPuerto(
     * @return JComboBox<AeroPuerto>
     */
    public JComboBox<AeroPuerto> getComboBox(){return this.comboAeroPuerto;}

    
    /** 
     * @param oyenteAeroPuerto(
     * @return Administrador
     */
    public Administrador getAdministrador(){return this.admin;}

    /**
     * oyente etiquetaAEropuerto
     */
    private void oyenteAeroPuerto(){

        etiquetaAeroPuertoActual.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent me){

                manejador.accionAeroPuerto();
            }
        });
    }

    /**
     * oyente radios
     */
    private void oyentePuerto(){

        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                manejador.accionEleccionPuerto();
                
            }
        };

        radioAeroPuerto.addActionListener(oyenteAccion);
        radioAeroLinea.addActionListener(oyenteAccion);
    }

    /**
     * oyente botonCrear
     */
    private void accionCrear(){

        botonCrear.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent ae){
                manejador.accionCrear();
            }
        });
    }

    /**
     * oyente botonAdministrar
     */
    private void accionAdministrar(){

        botonAdministrar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){

                manejador.accionAdministrar();
            }
        });
    }

    /**
     * acción principal
     */
    private void accionPrincipal(){

        botonPrincipal.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                manejador.accionPrincipal();
            }
        });
    }


}
