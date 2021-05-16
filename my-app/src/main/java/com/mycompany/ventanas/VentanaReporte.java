package com.mycompany.ventanas;

import javax.swing.*;

import com.mycompany.constantes.Constante;

public class VentanaReporte extends JDialog{
    
    private JPanel panel;
    private JLabel etiquetaTitulo, etiquetaEmpresa, etiquetaImagen;
    private JRadioButton radioSexo, radioEstadoCivil;
    
    public VentanaReporte(){

        this.setTitle(Constante.TITULO);
        this.setSize(700,500);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
