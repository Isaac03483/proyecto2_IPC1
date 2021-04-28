package com.mycompany.ventanas;

import java.awt.*;
import java.awt.event.*;
import com.mycompany.aeropuerto.AeroLinea;
import com.mycompany.constantes.*;
import com.mycompany.manejadores.ManejadorCrearEmpleado;

import javax.swing.*;

public class VentanaCrearEmpleado extends JFrame{
    
    private JPanel panel;
    private JButton botonMenuPrincipal, botonCrear, botonMenuAdmin;
    private JLabel etiquetaImagen, etiquetaEmpresa, etiquetaTitulo, etiquetaNombre, etiquetaApellido, etiquetaPuesto, etiquetaAeroLinea;
    private JTextField textoNombre, textoApellido;
    private JRadioButton radioGerente, radioOperador, radioAdmin;
    private ButtonGroup grupo;
    private JComboBox<AeroLinea> combo;
    private ManejadorCrearEmpleado manejador;

    public VentanaCrearEmpleado(){
        this.setTitle(Constante.TITULO);
        this.setSize(600,400);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        iniciarComponentes();
        manejador = new ManejadorCrearEmpleado(this);
    }

    private void iniciarComponentes(){

        colocarPanel();
        colocarEtiquetas();
        colocarTexto();
        colocarRadio();
        colocarCombo();
        colocarBoton();
    }

    private void colocarPanel(){

        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setBackground(Color.GRAY);
        panel.setLayout(null);
    }

    private void colocarEtiquetas(){

        etiquetaImagen = new JLabel();
        etiquetaImagen.setBounds(435, 20, 100,50);
        etiquetaImagen.setIcon(new ImageIcon(Constante.IMAGEN.getImage().getScaledInstance(etiquetaImagen.getWidth(), etiquetaImagen.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaImagen);

        etiquetaEmpresa= new JLabel("AeroBalamDevs");
        etiquetaEmpresa.setBounds(425, 75, 150, 20);
        panel.add(etiquetaEmpresa);
        etiquetaEmpresa.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaEmpresa.setForeground(Color.BLACK);

        etiquetaTitulo = new JLabel("CREAR EMPLEADO", SwingConstants.CENTER);
        etiquetaTitulo.setBounds(0, 40, 600, 30);
        panel.add(etiquetaTitulo);
        etiquetaTitulo.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaTitulo.setForeground(Color.BLACK);

        etiquetaNombre = new JLabel("Nombre:");
        etiquetaNombre.setBounds(40,120, 100, 20);
        panel.add(etiquetaNombre);
        etiquetaNombre.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaNombre.setForeground(Color.BLACK);

        etiquetaApellido = new JLabel("Apellido:");
        etiquetaApellido.setBounds(40,170, 100, 20);
        panel.add(etiquetaApellido);
        etiquetaApellido.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaApellido.setForeground(Color.BLACK);

        etiquetaPuesto = new JLabel("Puesto:");
        etiquetaPuesto.setBounds(40,220, 90, 20);
        panel.add(etiquetaPuesto);
        etiquetaPuesto.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaPuesto.setForeground(Color.BLACK);

        etiquetaAeroLinea = new JLabel("AeroLinea:");
        etiquetaAeroLinea.setBounds(40,290, 120, 20);
        panel.add(etiquetaAeroLinea);
        etiquetaAeroLinea.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaAeroLinea.setForeground(Color.BLACK);
        etiquetaAeroLinea.setVisible(true);
    }

    private void colocarTexto(){

        textoNombre = new JTextField();
        textoNombre.setBounds(140, 120, 150, 20);
        panel.add(textoNombre);

        textoApellido = new JTextField();
        textoApellido.setBounds(140, 170, 150, 20);
        panel.add(textoApellido);
    }

    private void colocarRadio(){
        radioAdmin = new JRadioButton("Administrador.");
        radioAdmin.setBounds(50, 250, 150, 20);
        panel.add(radioAdmin);
        radioAdmin.setBackground(Color.GRAY);
        radioAdmin.setForeground(Color.BLACK);
        radioAdmin.setFont(new Font("Basic", Font.BOLD, 14));

        radioGerente = new JRadioButton("Gerente.");
        radioGerente.setBounds(200, 250, 100, 20);
        panel.add(radioGerente);
        radioGerente.setBackground(Color.GRAY);
        radioGerente.setForeground(Color.BLACK);
        radioGerente.setFont(new Font("Basic", Font.BOLD, 14));

        radioOperador = new JRadioButton("Operador.");
        radioOperador.setBounds(300, 250, 110, 20);
        panel.add(radioOperador);
        radioOperador.setBackground(Color.GRAY);
        radioOperador.setForeground(Color.BLACK);
        radioOperador.setFont(new Font("Basic", Font.BOLD, 14));

        grupo = new ButtonGroup();
        grupo.add(radioAdmin);
        grupo.add(radioGerente);
        grupo.add(radioOperador);
    }

    private void colocarCombo(){
        
        combo = new JComboBox<>();
        combo.setBounds(150, 290, 120, 20);
        panel.add(combo);
    }

    private void colocarBoton(){

        botonCrear = new JButton("Crear");
        botonCrear.setBounds(410, 100, 100, 30);
        panel.add(botonCrear);
        botonCrear.setFont(new Font("Basic", Font.BOLD, 14));
        botonCrear.setForeground(Color.BLACK);

        oyenteCrear();

        botonMenuAdmin = new JButton("Administrar");
        botonMenuAdmin.setBounds(410, 150, 130, 30);
        panel.add(botonMenuAdmin);
        botonMenuAdmin.setFont(new Font("Basic", Font.BOLD, 14));
        botonMenuAdmin.setForeground(Color.BLACK);

        botonMenuPrincipal = new JButton("Principal");
        botonMenuPrincipal.setBounds(410, 200, 100, 30);
        panel.add(botonMenuPrincipal);
        botonMenuPrincipal.setFont(new Font("Basic", Font.BOLD, 14));
        botonMenuPrincipal.setForeground(Color.BLACK);
    }

    public JTextField getTextoNombre(){return this.textoNombre;}

    public JTextField getTextoApellido(){return this.textoApellido;}

    public JComboBox<AeroLinea> getCombo(){return this.combo;}

    public JRadioButton getRadioAdmin(){return this.radioAdmin;}

    public JRadioButton getRadioGerente(){return this.radioGerente;}

    public JRadioButton getRadioOperador(){return this.radioOperador;}

    private void oyenteCrear(){

        botonCrear.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){

                manejador.accionCrear();
            }
        });
    }

}
