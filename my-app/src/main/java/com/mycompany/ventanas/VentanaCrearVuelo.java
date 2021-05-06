package com.mycompany.ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import com.mycompany.aeropuerto.*;
import com.mycompany.aeropuerto.avion.Avion;
import com.mycompany.archivos.*;
import com.mycompany.constantes.Constante;
import com.mycompany.manejadores.ManejadorCrearVuelo;
import com.mycompany.persona.empleados.Administrador;

public class VentanaCrearVuelo extends JFrame{
    
    private JPanel panel;
    private JLabel etiquetaAeroPuertoActual,etiquetaTitulo, etiquetaEmpresa, etiquetaImagen, etiquetaOrigenDestino, etiquetaPrecio, etiquetaCodigoAvion, etiquetaFechaSalida;
    private JTextField textoFecha, textoPrecio;
    private JComboBox<Distancia> comboOrigenDestino;
    private JComboBox<Avion> comboAvion;
    private JButton botonCrear, botonAdministrar, botonPrincipal;
    private Administrador admin;
    private ManejadorCrearVuelo manejador;

    public VentanaCrearVuelo(Administrador admin){
        
        this.admin = admin;
        this.setSize(600,400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        iniciarComponentes();
        manejador = new ManejadorCrearVuelo(this);
    }

    private void iniciarComponentes(){

        colocarPanel();
        colocarEtiquetas();
        colocarTexto();
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
        etiquetaAeroPuertoActual.setBounds(30, 70, 180, 20);
        panel.add(etiquetaAeroPuertoActual);
        etiquetaAeroPuertoActual.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaAeroPuertoActual.setForeground(Color.BLACK);
        etiquetaAeroPuertoActual.setToolTipText("Click para cambiar de aeroPuerto.");
        oyenteAeroPuerto();

        etiquetaTitulo = new JLabel("CREAR VUELO", SwingConstants.CENTER);
        etiquetaTitulo.setBounds(0, 40, 600, 30);
        panel.add(etiquetaTitulo);
        etiquetaTitulo.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaTitulo.setForeground(Color.BLACK);

        etiquetaOrigenDestino = new JLabel("Origen -> Destino:");
        etiquetaOrigenDestino.setBounds(40, 115, 150, 20);
        panel.add(etiquetaOrigenDestino);
        etiquetaOrigenDestino.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaOrigenDestino.setForeground(Color.BLACK);
        etiquetaOrigenDestino.setToolTipText("Agregar nuevo Origen-Destino.");
        oyenteCrearDistancia();

        etiquetaCodigoAvion = new JLabel("Código avión:");
        etiquetaCodigoAvion.setBounds(40, 155, 110, 20);
        panel.add(etiquetaCodigoAvion);
        etiquetaCodigoAvion.setFont(new Font("Basic",Font.BOLD, 13));
        etiquetaCodigoAvion.setForeground(Color.BLACK);

        etiquetaPrecio = new JLabel("Precio Del Boleto:");
        etiquetaPrecio.setBounds(40, 195, 135, 20);
        panel.add(etiquetaPrecio);
        etiquetaPrecio.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaPrecio.setForeground(Color.BLACK);

        etiquetaFechaSalida = new JLabel("Fecha De Salida:");
        etiquetaFechaSalida.setBounds(40, 235, 135, 20);
        panel.add(etiquetaFechaSalida);
        etiquetaFechaSalida.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaFechaSalida.setForeground(Color.BLACK);
    }

    private void colocarTexto(){

        textoPrecio = new JTextField();
        textoPrecio.setBounds(180, 195, 120, 20);
        panel.add(textoPrecio);

        textoFecha = new JTextField();
        textoFecha.setBounds(180, 235, 120, 20);
        panel.add(textoFecha);

    }

    private void colocarCombo(){

        comboOrigenDestino = new JComboBox<>();
        comboOrigenDestino.setBounds(190, 115, 225, 20);
        panel.add(comboOrigenDestino);
        agregarOrigenDestino();

        comboAvion = new JComboBox<>();
        comboAvion.setBounds(160, 155, 140, 20);
        panel.add(comboAvion);
        agregarAviones();
    }

    private void colocarBoton(){
        
        botonCrear = new JButton("Crear");
        botonCrear.setBounds(60, 270, 100, 30);
        panel.add(botonCrear);
        botonCrear.setFont(new Font("Basic", Font.BOLD, 13));
        botonCrear.setForeground(Color.BLACK);
        oyenteCrearVuelo();

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

    private void agregarOrigenDestino(){
        ArrayList<Distancia> distancias = ArchivoDistancia.leerDistancias();

        if(distancias != null){
            
            for(Distancia distancia: distancias){
                if(this.admin.getAeroPuertoActual().equals(distancia.getAeroPuertoOrigen())){
                    comboOrigenDestino.addItem(distancia);
                }
            }
        }
    }

    private void oyenteCrearVuelo(){

        botonCrear.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                manejador.accionCrearVuelo();
            }
        });
    }
    private void oyenteCrearDistancia(){

        etiquetaOrigenDestino.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent ae){

                manejador.accionCrearDistancia();
            }
        });
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

    private void agregarAviones(){
        ArrayList<Avion> aviones = ArchivoAvion.leerAvion();
        
        if(aviones != null){
            for(Avion avion: aviones){
                if(this.admin.getAeroPuertoActual().equals(avion.getAeroPuertoActual())){
                    comboAvion.addItem(avion);
                }
            }
        }
    }

    private void oyenteAeroPuerto(){

        etiquetaAeroPuertoActual.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent me){

                manejador.accionAeroPuerto();
            }
        });
    }

    public Administrador getAdministrador(){return this.admin;}

    public JComboBox<Distancia> getComboOrigenDestino(){return this.comboOrigenDestino;}

    public JTextField getTextoFecha(){return this.textoFecha;}

    public JComboBox<Avion> getComboCodigoAvion(){return this.comboAvion;}

    public JTextField getTextoPrecio(){return this.textoPrecio;}
}
