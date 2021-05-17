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

    /**
     * constructor
     * @param menu
     * @param modal
     */
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

    /**
     * inicia componentes
     */
    private void iniciarComponentes(){

        colocarPanel();
        colocarEtiqueta();
        colocarTexto();
        colocarBoton();
    }

    /**
     * coloca panel
     */
    private void colocarPanel(){
        
        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.GRAY);
    }

    /**
     * coloca etiquetas
     */
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

    /**
     * coloca textField
     */
    private void colocarTexto(){

        textoUsuario = new JTextField();
        textoUsuario.setBounds(170, 40,150, 20);
        panel.add(textoUsuario);

        textoContrasena = new JPasswordField();
        textoContrasena.setBounds(190, 80, 130, 20);
        panel.add(textoContrasena);
    }

    /**
     * 
     * coloca botones
     */
    private void colocarBoton(){

        botonIngreso = new JButton("Ingresar");
        botonIngreso.setBounds(160, 125, 100, 30);
        panel.add(botonIngreso);
        botonIngreso.setFont(new Font("Basic", Font.BOLD, 13));
        botonIngreso.setForeground(Color.BLACK);

        oyenteIngreso();
    }

    /**
     * retorna botonIngreso
     * @return
     */
    public JButton getBoton(){return this.botonIngreso;}

    /**
     * retorna usuario
     * @return
     */
    public String getUsuario(){return this.textoUsuario.getText();}

    /**
     * retorna contraseña
     * @return
     */
    public String getContrasena(){

        char[] letras = textoContrasena.getPassword();
        String contrasena="";
        for(int i =0; i<letras.length; i++){
            contrasena += letras[i];
        }

        return contrasena;
    }

    /**
     * oyente botonIngreso
     */
    private void oyenteIngreso(){

        botonIngreso.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent ae){

                manejador.verificarUsuario(menu);
            }
        });
    }
    
}
