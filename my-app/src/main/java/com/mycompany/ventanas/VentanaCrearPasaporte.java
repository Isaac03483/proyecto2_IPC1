package com.mycompany.ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import com.mycompany.aeropuerto.AeroPuerto;
import com.mycompany.archivos.*;
import com.mycompany.constantes.Constante;
import com.mycompany.manejadores.ManejadorCrearPasaporte;

public class VentanaCrearPasaporte extends JDialog{
    
    private JPanel panel;
    private JLabel etiquetaTitulo, etiquetaEmpresa, etiquetaImagen, etiquetaNombre, etiquetaApellido, etiquetaFechaNacimiento, etiquetaSexo, etiquetaNacionalidad, etiquetaEstadoCivil, etiquetaFechaVencimiento, etiquetaFechaEmision, etiquetaPaisActual;
    private JComboBox<String> comboPaisActual;
    private JRadioButton radioHombre, radioMujer, radioOtro, radioCasado, radioSoltero, radioDivorciado;
    private JTextField textoNombre, textoApellido, textoFechaNacimiento, textoNacionalidad, textoFechaVencimiento, textoFechaEmision;
    private JButton botonCrear;
    private ButtonGroup grupoEstado, grupoSexo;
    private ManejadorCrearPasaporte manejador;

    public VentanaCrearPasaporte(JFrame padre, boolean modal){

        super(padre, modal);
        this.setSize(530,400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        iniciarComponentes();
        manejador = new ManejadorCrearPasaporte(this);
    }

    private void iniciarComponentes(){

        colocarPanel();
        colocarEtiquetas();
        colocarTextos();
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
        etiquetaImagen.setBounds(355, 20, 100,50);
        etiquetaImagen.setIcon(new ImageIcon(Constante.IMAGEN.getImage().getScaledInstance(etiquetaImagen.getWidth(), etiquetaImagen.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaImagen);

        etiquetaEmpresa= new JLabel(Constante.TITULO);
        etiquetaEmpresa.setBounds(345, 75, 150, 20);
        panel.add(etiquetaEmpresa);
        etiquetaEmpresa.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaEmpresa.setForeground(Color.BLACK);

        etiquetaTitulo = new JLabel("CREAR PASAPORTE", SwingConstants.CENTER);
        etiquetaTitulo.setBounds(0, 30, 500, 30);
        panel.add(etiquetaTitulo);
        etiquetaTitulo.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaTitulo.setForeground(Color.BLACK);

        etiquetaNombre = new JLabel("Nombre:");
        etiquetaNombre.setBounds(40, 85, 100, 14);
        panel.add(etiquetaNombre);
        etiquetaNombre.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaNombre.setForeground(Color.BLACK);

        etiquetaApellido = new JLabel("Apellido:");
        etiquetaApellido.setBounds(40, 115, 120, 14);
        panel.add(etiquetaApellido);
        etiquetaApellido.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaApellido.setForeground(Color.BLACK);

        etiquetaFechaNacimiento = new JLabel("Fecha de nacimiento:");
        etiquetaFechaNacimiento.setBounds(40, 145, 170, 14);
        panel.add(etiquetaFechaNacimiento);
        etiquetaFechaNacimiento.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaFechaNacimiento.setForeground(Color.BLACK);

        etiquetaEstadoCivil = new JLabel("Estado civil:");
        etiquetaEstadoCivil.setBounds(40, 175, 110, 14);
        panel.add(etiquetaEstadoCivil);
        etiquetaEstadoCivil.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaEstadoCivil.setForeground(Color.BLACK);

        etiquetaNacionalidad = new JLabel("Nacionalidad:");
        etiquetaNacionalidad.setBounds(40, 205, 120, 14);
        panel.add(etiquetaNacionalidad);
        etiquetaNacionalidad.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaNacionalidad.setForeground(Color.BLACK);

        etiquetaPaisActual = new JLabel("País actual:");
        etiquetaPaisActual.setBounds(40, 235, 130, 14);
        panel.add(etiquetaPaisActual);
        etiquetaPaisActual.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaPaisActual.setForeground(Color.BLACK);
        
        etiquetaSexo = new JLabel("Sexo:");
        etiquetaSexo.setBounds(40, 265, 80, 14);
        panel.add(etiquetaSexo);
        etiquetaSexo.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaSexo.setForeground(Color.BLACK);

        etiquetaFechaEmision = new JLabel("Fecha de emisión:");
        etiquetaFechaEmision.setBounds(40, 295, 150, 14);
        panel.add(etiquetaFechaEmision);
        etiquetaFechaEmision.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaFechaEmision.setForeground(Color.BLACK);

        etiquetaFechaVencimiento = new JLabel("Fecha de vencimiento:");
        etiquetaFechaVencimiento.setBounds(40, 325, 170, 14);
        panel.add(etiquetaFechaVencimiento);
        etiquetaFechaVencimiento.setFont(new Font("Basic", Font.BOLD, 13));
        etiquetaFechaVencimiento.setForeground(Color.BLACK);

    }

    private void colocarTextos(){

        textoNombre = new JTextField();
        textoNombre.setBounds(160, 85, 110, 15);
        panel.add(textoNombre);

        textoApellido = new JTextField();
        textoApellido.setBounds(170, 115, 100, 15);
        panel.add(textoApellido);

        textoNacionalidad = new JTextField();
        textoNacionalidad.setBounds(180, 205, 110, 15);
        panel.add(textoNacionalidad);

        textoFechaNacimiento = new JTextField();
        textoFechaNacimiento.setBounds(220, 145, 110, 15);
        panel.add(textoFechaNacimiento);

        textoFechaEmision = new JTextField();
        textoFechaEmision.setBounds(200, 295, 110, 15);
        panel.add(textoFechaEmision);

        textoFechaVencimiento = new JTextField();
        textoFechaVencimiento.setBounds(220, 325, 110, 15);
        panel.add(textoFechaVencimiento);

    }

    private void colocarRadio(){

        radioCasado = new JRadioButton("CASADO.");
        radioCasado.setBounds(155, 175, 90, 13);
        panel.add(radioCasado);
        radioCasado.setFont(new Font("Basic", Font.BOLD, 12));
        radioCasado.setBackground(Color.GRAY);
        radioCasado.setSelected(true);

        radioDivorciado = new JRadioButton("DIVORCIADO.");
        radioDivorciado.setBounds(260, 175, 120, 13);
        panel.add(radioDivorciado);
        radioDivorciado.setFont(new Font("Basic", Font.BOLD, 12));
        radioDivorciado.setBackground(Color.GRAY);

        radioSoltero = new JRadioButton("SOLTERO.");
        radioSoltero.setBounds(390, 175, 100, 13);
        panel.add(radioSoltero);
        radioSoltero.setFont(new Font("Basic", Font.BOLD, 12));
        radioSoltero.setBackground(Color.GRAY);

        grupoEstado = new ButtonGroup();
        grupoEstado.add(radioCasado);
        grupoEstado.add(radioDivorciado);
        grupoEstado.add(radioSoltero);

        radioHombre = new JRadioButton("HOMBRE.");
        radioHombre.setBounds(130, 265, 90, 13);
        panel.add(radioHombre);
        radioHombre.setFont(new Font("Basic", Font.BOLD, 12));
        radioHombre.setBackground(Color.GRAY);
        radioHombre.setSelected(true);

        radioMujer = new JRadioButton("MUJER.");
        radioMujer.setBounds(240, 265, 80, 13);
        panel.add(radioMujer);
        radioMujer.setFont(new Font("Basic", Font.BOLD, 12));
        radioMujer.setBackground(Color.GRAY);

        radioOtro = new JRadioButton("SIN DEFINIR.");
        radioOtro.setBounds(340, 265, 120, 13);
        panel.add(radioOtro);
        radioOtro.setFont(new Font("Basic", Font.BOLD, 12));
        radioOtro.setBackground(Color.GRAY);

        grupoSexo = new ButtonGroup();
        grupoSexo.add(radioHombre);
        grupoSexo.add(radioMujer);
        grupoSexo.add(radioOtro);
    }

    private void colocarCombo(){

        comboPaisActual = new JComboBox<>();
        comboPaisActual.setBounds(190,235,120, 20);
        panel.add(comboPaisActual);

        agregarPaises();
    }

    private void colocarBoton(){

        botonCrear = new JButton("CREAR");
        botonCrear.setBounds(350, 300, 90, 40);
        panel.add(botonCrear);
        oyenteBoton();
    }

    private void agregarPaises(){

        ArrayList<AeroPuerto> aeroPuertos = ArchivoAeroPuerto.leerAeroPuertos();

        if(aeroPuertos != null){

            for(AeroPuerto aeroPuerto: aeroPuertos){
                comboPaisActual.addItem(aeroPuerto.getPais());
            }
        }

    }

    private void oyenteBoton(){

        botonCrear.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){

                manejador.accionCrear();
            }
        });
    }

    public JComboBox<String> getComboPais(){return this.comboPaisActual;}

    public JTextField getTextoNombre(){return this.textoNombre;}

    public JTextField getTextoApellido(){return this.textoApellido;}

    public JTextField getTextoNacimiento(){return this.textoFechaNacimiento;}

    public JTextField getTextoNacionalidad(){return this.textoNacionalidad;}

    public JTextField getTextoEmision(){return this.textoFechaEmision;}

    public JTextField getTextoVencimiento(){return this.textoFechaVencimiento;}

    public JRadioButton getRadioHombre(){return this.radioHombre;}

    public JRadioButton getRadioMujer(){return this.radioMujer;}

    public JRadioButton getRadioOtro(){return this.radioOtro;}

    public JRadioButton getRadioCasado(){return this.radioCasado;}

    public JRadioButton getRadioDivorciado(){return this.radioDivorciado;}

    public JRadioButton getRadioSoltero(){return this.radioSoltero;}

}
