package com.mycompany.ventanas;

import java.awt.event.*;
import java.awt.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

import com.mycompany.aeropuerto.*;
import com.mycompany.archivos.*;
import com.mycompany.constantes.Constante;
import com.mycompany.enums.EstadoVuelo;
import com.mycompany.manejadores.ManejadorCliente;

public class VentanaCliente extends JFrame{
    
    private JPanel panel;
    private JLabel etiquetaRenovar, etiquetaNuevoPasaporte, etiquetaEmpresa, etiquetaImagen, etiquetaMenu, etiquetaOrigen, etiquetaDestino, etiquetaFecha, etiquetaAeroLinea;
    private JTextField textoOrigen, textoDestino, textoFecha;
    private JButton botonComprarBoleto, botonVolver, botonReporte;
    private JComboBox<AeroLinea> comboAeroLinea;
    private ManejadorCliente manejador;
    private JTable tablaVuelo, tablaAeropuerto;
    private JScrollPane scrollVuelo, scrollAeropuerto;
    private DefaultTableModel modeloVuelo, modeloAeropuerto;

    /**
     * constructor
     */
    public VentanaCliente(){

        this.setSize(800,450);
        this.setResizable(false);
        this.setTitle(Constante.TITULO);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        iniciarComponentes();

        this.manejador = new ManejadorCliente(this);
    }

    /**
     * inicia componentes
     */
    private void iniciarComponentes(){

        colocarPanel();
        colocarEtiquetas();
        colocarTextos();
        colocarTabla();
        colocarCombo();
        colocarBoton();
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
    private void colocarEtiquetas(){

        etiquetaImagen = new JLabel();
        etiquetaImagen.setBounds(555, 20, 100,50);
        etiquetaImagen.setIcon(new ImageIcon(Constante.IMAGEN.getImage().getScaledInstance(etiquetaImagen.getWidth(), etiquetaImagen.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaImagen);

        etiquetaEmpresa= new JLabel(Constante.TITULO);
        etiquetaEmpresa.setBounds(545, 75, 150, 20);
        panel.add(etiquetaEmpresa);
        etiquetaEmpresa.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaEmpresa.setForeground(Color.BLACK);

        etiquetaMenu = new JLabel("COMPRAR BOLETO", SwingConstants.CENTER);
        etiquetaMenu.setBounds(0,40, 800, 20);
        panel.add(etiquetaMenu);
        etiquetaMenu.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaMenu.setForeground(Color.BLACK);

        etiquetaOrigen = new JLabel("Ciudad de Origen:");
        etiquetaOrigen.setBounds(30,100, 140, 20);
        panel.add(etiquetaOrigen);
        etiquetaOrigen.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaOrigen.setForeground(Color.BLACK);

        etiquetaDestino = new JLabel("Ciudad de Destino:");
        etiquetaDestino.setBounds(30,135, 150, 20);
        panel.add(etiquetaDestino);
        etiquetaDestino.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaDestino.setForeground(Color.BLACK);


        etiquetaFecha = new JLabel("Fecha de Vuelo:");
        etiquetaFecha.setBounds(30,170, 130, 20);
        panel.add(etiquetaFecha);
        etiquetaFecha.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaFecha.setForeground(Color.BLACK);

        etiquetaAeroLinea = new JLabel("AeroLinea:");
        etiquetaAeroLinea.setBounds(30,205, 100, 20);
        panel.add(etiquetaAeroLinea);
        etiquetaAeroLinea.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaAeroLinea.setForeground(Color.BLACK);

        etiquetaNuevoPasaporte = new JLabel("¿No cuenta con un pasaporte?");
        etiquetaNuevoPasaporte.setBounds(30, 270, 225, 13);
        panel.add(etiquetaNuevoPasaporte);
        etiquetaNuevoPasaporte.setFont(new Font("Basic", Font.BOLD, 12));
        oyentePasaporte();

        etiquetaRenovar = new JLabel("¿Desea renovar su pasaporte?");
        etiquetaRenovar.setBounds(30, 290, 225, 13);
        panel.add(etiquetaRenovar);
        etiquetaRenovar.setFont(new  Font("Basic", Font.BOLD, 12));
        oyenteRenovacion();

    }

    /**
     * coloca componentes
     */
    private void colocarTextos(){

        textoOrigen = new JTextField();
        textoOrigen.setBounds(170, 100, 100, 20);
        panel.add(textoOrigen);

        textoDestino = new JTextField();
        textoDestino.setBounds(175, 135, 95, 20);
        panel.add(textoDestino);

        textoFecha = new JTextField();
        textoFecha.setBounds(170, 170, 100, 20);
        panel.add(textoFecha);
    }

    /**
     * coloca componentes
     */
    private void colocarCombo(){

        comboAeroLinea = new JComboBox<>();
        comboAeroLinea.setBounds(120, 205, 150, 20);
        panel.add(comboAeroLinea);
        agregarAeroLineas();

    }

    /**
     * agrega aerolineas al combo
     */
    private void agregarAeroLineas(){

        ArrayList<AeroLinea> aeroLineas = ArchivoAeroLinea.leerAeroLinea();

        if(aeroLineas != null){
            for(AeroLinea aeroLinea: aeroLineas){
                comboAeroLinea.addItem(aeroLinea);
            }
        }
        
    }

    /**
     * coloca componentes
     */
    private void colocarBoton(){

        botonComprarBoleto = new JButton("Buscar vuelo");
        botonComprarBoleto.setBounds(80, 325, 170, 30);
        panel.add(botonComprarBoleto);
        botonComprarBoleto.setFont(new Font("Basic", Font.BOLD, 14));
        botonComprarBoleto.setForeground(Color.BLACK);

        oyenteComprar();

        botonReporte = new JButton("Información");
        botonReporte.setBounds(80,365, 170, 30);
        panel.add(botonReporte);
        botonReporte.setFont(new Font("Basic", Font.BOLD, 14));
        botonReporte.setForeground(Color.BLACK);
        oyenteInformacion();

        botonVolver = new JButton("Volver");
        botonVolver.setBounds(30, 30, 100, 30);
        panel.add(botonVolver);
        botonVolver.setFont(new Font("Basic", Font.BOLD, 13));
        botonVolver.setForeground(Color.BLACK);
        oyenteVolver();
    }

    /**
     * coloca componentes
     */
    private void colocarTabla(){

        modeloVuelo = new DefaultTableModel();
        modeloVuelo.addColumn("Vuelo");
        modeloVuelo.addColumn("Avión");
        modeloVuelo.addColumn("Origen");
        modeloVuelo.addColumn("Destino");
        modeloVuelo.addColumn("Precio");
        modeloVuelo.addColumn("Fecha");

        tablaVuelo = new JTable(modeloVuelo);
        tablaVuelo.setBounds(290, 110, 490, 150);
        panel.add(tablaVuelo);
        tablaVuelo.setEnabled(false);

        scrollVuelo = new JScrollPane(tablaVuelo, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollVuelo.setBounds(290, 110, 490, 150);
        panel.add(scrollVuelo);

        agregarVuelos();

        modeloAeropuerto = new DefaultTableModel();
        modeloAeropuerto.addColumn("Aeropuerto");
        modeloAeropuerto.addColumn("País");
        modeloAeropuerto.addColumn("Ciudad");

        tablaAeropuerto = new JTable(modeloAeropuerto);
        tablaAeropuerto.setBounds(290, 280, 490, 100);
        panel.add(tablaAeropuerto);
        tablaAeropuerto.setEnabled(false);

        scrollAeropuerto = new JScrollPane(tablaAeropuerto, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollAeropuerto.setBounds(290, 280, 490, 100);
        panel.add(scrollAeropuerto);

        agregarAeropuertos();
    }

    /**
     * agrega vuelso a la tabla
     */
    private void agregarVuelos(){

        ArrayList<Vuelo> vuelos = ArchivoVuelo.leerVuelos();

        if(vuelos != null){
            for(Vuelo vuelo: vuelos){
                if(vuelo.getEstadoVuelo() == EstadoVuelo.ENESPERA){
                    String[] fila = {Integer.toString(vuelo.getCodigoVuelo()), Integer.toString(vuelo.getCodigoAvion()), vuelo.getAeroPuertoOrigen(), vuelo.getAeroPuertoDestino(), Double.toString(vuelo.getPrecioBoleto()), new SimpleDateFormat("dd/mm/yyy").format(vuelo.getFechaSalida())};
                    modeloVuelo.addRow(fila);
                }
            }
        }
    }

    /**
     * agrega aeropuertos a la tabla
     */
    private void agregarAeropuertos(){
        ArrayList<AeroPuerto> aeroPuertos = ArchivoAeroPuerto.leerAeroPuertos();

        if(aeroPuertos != null){
            for(AeroPuerto aeroPuerto: aeroPuertos){
                String[] fila = {aeroPuerto.getNombreAeroPuerto(), aeroPuerto.getPais(), aeroPuerto.getCiudad()};
                modeloAeropuerto.addRow(fila);
            }
        }
    }

    /**
     * oyente botonVolver
     */
    private void oyenteVolver(){

        botonVolver.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){

                manejador.accionVolver();
            }
        });
    }

    /**
     * botonReporte
     */
    private void oyenteInformacion(){

        botonReporte.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                manejador.accionInformacion();
            }
        });
    }

    /**
     * oyente botonCompra
     */
    private void oyenteComprar(){

        botonComprarBoleto.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                manejador.accionComprar();
            }
        });
    }

    /**
     * oyente etiqueta
     */
    private void oyentePasaporte(){

        etiquetaNuevoPasaporte.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent me){

                manejador.accionCrearPasaporte();
            }
        });
    }

    /**
     * oyente etiquetaRenovar
     */
    private void oyenteRenovacion(){

        etiquetaRenovar.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent me){

                manejador.accionRenovarPasaporte();
            }
        });
    }

    /**
     * retorna JTextField
     * @return
     */
    public JTextField getTextoOrigen(){return this.textoOrigen;}

    /**
     * retorna JTextField
     * @return
     */
    public JTextField getTextoDestino(){return this.textoDestino;}

    /**
     * retorna JTextField
     * @return
     */
    public JTextField getTextoFecha(){return this.textoFecha;}

    /**
     * retorna JComboBox
     * @return
     */
    public JComboBox<AeroLinea> getComboAeroLinea(){return this.comboAeroLinea;}
}
