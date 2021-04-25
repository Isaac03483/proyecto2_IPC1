package com.mycompany.ventanas;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.mycompany.manejadores.ManejadorLogin;

public class VentanaLog extends JDialog{

    private Menu menu;
    private JPanel panel;
    private JLabel etiquetaUsuario, etiquetaContrasena;
    private JTextField textoUsuario;
    private JPasswordField textoContrasena;
    private JButton botonIngreso;
    private ManejadorLogin manejador;

    public VentanaLog(Menu menu, boolean modal){
        super(menu, modal);

        this.menu = menu;
        this.setSize(400, 200);
        this.setResizable(false);
        this.setTitle(menu.getTitle());
        this.setLocationRelativeTo(menu);
        iniciarComponentes();

        manejador = new ManejadorLogin(this);

    }

    private void iniciarComponentes(){

        colocarPanel();
        colocarEtiqueta();
        colocarTexto();
        colocarBoton();
    }

    private void colocarPanel(){
        
        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.GRAY);
    }

    private void colocarEtiqueta(){

        etiquetaUsuario = new JLabel("Usuario:");
        etiquetaUsuario.setBounds(70,40, 100, 20);
        panel.add(etiquetaUsuario);
        etiquetaUsuario.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaUsuario.setForeground(Color.BLACK);
        
        etiquetaContrasena = new JLabel("Contraseña:");
        etiquetaContrasena.setBounds(70,80, 120, 20);
        panel.add(etiquetaContrasena);
        etiquetaContrasena.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaContrasena.setForeground(Color.BLACK);
    }

    private void colocarTexto(){

        textoUsuario = new JTextField();
        textoUsuario.setBounds(200, 40,120, 20);
        panel.add(textoUsuario);

        textoContrasena = new JPasswordField();
        textoContrasena.setBounds(200, 80, 120, 20);
        panel.add(textoContrasena);
    }

    private void colocarBoton(){

        botonIngreso = new JButton("Ingresar");
        botonIngreso.setBounds(160, 125, 100, 30);
        panel.add(botonIngreso);
        botonIngreso.setFont(new Font("Basic", Font.BOLD, 13));
        botonIngreso.setForeground(Color.BLACK);

        oyenteIngreso();
    }

    public JButton getBoton(){return this.botonIngreso;}

    public String getUsuario(){return this.textoUsuario.getText();}

    public String getContrasena(){

        char[] letras = textoContrasena.getPassword();
        String contrasena="";
        for(int i =0; i<letras.length; i++){
            contrasena += letras[i];
        }

        return contrasena;
    }

    private void oyenteIngreso(){

        botonIngreso.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent ae){

                manejador.verificarUsuario();
            }
        });
    }
    
}
