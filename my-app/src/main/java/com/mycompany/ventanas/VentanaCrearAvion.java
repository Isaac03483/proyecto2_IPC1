package com.mycompany.ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import com.mycompany.aeropuerto.*;
import com.mycompany.archivos.ArchivoAeroPuerto;
import com.mycompany.constantes.Constante;
import com.mycompany.manejadores.*;
import com.mycompany.persona.empleados.Administrador;

public class VentanaCrearAvion extends JFrame{
    
    private JPanel panel;
    private JLabel etiquetaTitulo, etiquetaImagen, etiquetaEmpresa, etiquetaAeroPuertoActual, etiquetaNombreAeroPuerto, etiquetaNombreAeroLinea, etiquetaCapacidadGasolina, etiquetaConsumoMilla;
    private JComboBox<AeroPuerto> comboAeroPuerto;
    private JComboBox<AeroLinea> comboAeroLinea;
    private JTextField textoCapacidadGasolina, textoConsumoMilla;
    private JButton botonCrear, botonAdministrar, botonPrincipal;
    private Administrador admin;
    private ManejadorCrearAvion manejador;

    public VentanaCrearAvion(Administrador admin){

        this.admin = admin;
        this.setTitle(Constante.TITULO);
        this.setSize(500,360);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        iniciarComponentes();

        this.manejador = new ManejadorCrearAvion(this);
    }

    private void iniciarComponentes(){

        colocarPanel();
        colocarEtiquetas();
        colocarCombo();
        colocarTexto();
        colocarBoton();
    }

    private void colocarPanel(){

        panel = new JPanel();
        this.getContentPane().add(panel);

        panel.setLayout(null);
        panel.setBackground(Color.GRAY);
    }

    private void colocarEtiquetas(){

        etiquetaImagen = new JLabel();
        etiquetaImagen.setBounds(365, 20, 100,50);
        etiquetaImagen.setIcon(new ImageIcon(Constante.IMAGEN.getImage().getScaledInstance(etiquetaImagen.getWidth(), etiquetaImagen.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaImagen);

        etiquetaEmpresa= new JLabel(Constante.TITULO);
        etiquetaEmpresa.setBounds(355, 75, 150, 20);
        panel.add(etiquetaEmpresa);
        etiquetaEmpresa.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaEmpresa.setForeground(Color.BLACK);

        etiquetaAeroPuertoActual = new  JLabel(this.admin.getAeroPuertoActual());
        etiquetaAeroPuertoActual.setBounds(30, 70, 180, 20);
        panel.add(etiquetaAeroPuertoActual);
        etiquetaAeroPuertoActual.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaAeroPuertoActual.setForeground(Color.BLACK);
        etiquetaAeroPuertoActual.setToolTipText("Click para cambiar de aeroPuerto.");
        oyenteAeroPuerto();

        etiquetaTitulo = new JLabel("CREAR AVIÓN", SwingConstants.CENTER);
        etiquetaTitulo.setBounds(0, 30, 500, 30);
        panel.add(etiquetaTitulo);
        etiquetaTitulo.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaTitulo.setForeground(Color.BLACK);

        etiquetaNombreAeroPuerto = new JLabel("Nombre del aeropuerto:");
        etiquetaNombreAeroPuerto.setBounds(40,105, 180, 20);
        panel.add(etiquetaNombreAeroPuerto);
        etiquetaNombreAeroPuerto.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaNombreAeroPuerto.setForeground(Color.BLACK);

        etiquetaNombreAeroLinea = new JLabel("Nombre de la aerolinea:");
        etiquetaNombreAeroLinea.setBounds(40,145, 190, 20);
        panel.add(etiquetaNombreAeroLinea);
        etiquetaNombreAeroLinea.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaNombreAeroLinea.setForeground(Color.BLACK);

        etiquetaCapacidadGasolina = new JLabel("Capacidad de gasolina:");
        etiquetaCapacidadGasolina.setBounds(40,185, 185, 20);
        panel.add(etiquetaCapacidadGasolina);
        etiquetaCapacidadGasolina.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaCapacidadGasolina.setForeground(Color.BLACK);

        etiquetaConsumoMilla = new JLabel("Consumo por milla:");
        etiquetaConsumoMilla.setBounds(40,225, 155, 20);
        panel.add(etiquetaConsumoMilla);
        etiquetaConsumoMilla.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaConsumoMilla.setForeground(Color.BLACK);
    }

    private void colocarCombo(){

        comboAeroPuerto = new JComboBox<>();
        comboAeroPuerto.setBounds(230, 105, 180, 20);
        panel.add(comboAeroPuerto);
        agregarAeroPuertos();
        oyenteAeroLinea();

        comboAeroLinea = new JComboBox<>();
        comboAeroLinea.setBounds(230, 145, 150, 20);
        panel.add(comboAeroLinea);
    }

    private void colocarTexto(){

        textoCapacidadGasolina = new JTextField();
        textoCapacidadGasolina.setBounds(230, 185, 100, 20);
        panel.add(textoCapacidadGasolina);

        textoConsumoMilla = new JTextField();
        textoConsumoMilla.setBounds(210,225, 120, 20);
        panel.add(textoConsumoMilla);

    }

    private void colocarBoton(){

        botonCrear = new JButton("Crear");
        botonCrear.setBounds(60, 270, 100, 30);
        panel.add(botonCrear);
        botonCrear.setFont(new Font("Basic", Font.BOLD, 13));
        botonCrear.setForeground(Color.BLACK);
        oyenteCrearAvion();

        botonAdministrar = new JButton("Administrar");
        botonAdministrar.setBounds(180, 270, 130, 30);
        panel.add(botonAdministrar);
        botonAdministrar.setFont(new Font("Basic", Font.BOLD, 13));
        botonAdministrar.setForeground(Color.BLACK);
        oyenteAdministrar();

        botonPrincipal = new JButton("Principal");
        botonPrincipal.setBounds(330, 270, 100, 30);
        panel.add(botonPrincipal);
        botonPrincipal.setFont(new Font("Basic", Font.BOLD, 13));
        botonPrincipal.setForeground(Color.BLACK);
        oyentePrincipal();
    }

    public Administrador getAdministrador(){return this.admin;}

    public JComboBox<AeroPuerto> getComboAeroPuerto(){return this.comboAeroPuerto;}

    public JComboBox<AeroLinea> getComboAeroLinea(){return this.comboAeroLinea;}

    public JTextField getTextoGasolina(){return this.textoCapacidadGasolina;}

    public JTextField getTextoMillas(){return this.textoConsumoMilla;}

    private void agregarAeroPuertos(){
        ArrayList<AeroPuerto> aeroPuertos = ArchivoAeroPuerto.leerAeroPuertos();

        for(AeroPuerto aeroPuerto: aeroPuertos){
            this.comboAeroPuerto.addItem(aeroPuerto);
        }
        this.comboAeroPuerto.setSelectedItem(aeroPuertos.get(0));
    }

    private void oyentePrincipal(){

        botonPrincipal.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                manejador.accionPrincipal();
            }
        });
    }

    private void oyenteAdministrar(){
        
        botonAdministrar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                manejador.accionAdministrar();
            }
        });
    }

    private void oyenteAeroPuerto(){

        etiquetaAeroPuertoActual.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent me){

                manejador.accionAeroPuerto();
            }
        });
    }

    private void oyenteCrearAvion(){

        botonCrear.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){

                manejador.accionCrear();
            }
        });
    }

    private  void oyenteAeroLinea(){
        comboAeroPuerto.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent ae){
                manejador.seleccionarAeroLinea();
            }
        });
    }

}
