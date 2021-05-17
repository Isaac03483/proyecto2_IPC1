package com.mycompany.ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.mycompany.constantes.Constante;
import com.mycompany.manejadores.*;
public class Menu extends JFrame{
    
    private JLabel etiquetaMenu, etiquetaImagen, etiquetaEmpresa;
    private JButton botonClientes, botonEmpresa;
    private JPanel panel;
    private ManejadorMenu manejador;

    /**
     * constructor
     */
    public Menu(){

        this.setTitle(Constante.TITULO);
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        iniciarComponentes();
        this.manejador = new ManejadorMenu(this);
    }

    /**
     * inicia componentes
     */
    private void iniciarComponentes(){

        colocarPanel();
        colocarEtiquetas();
        colocarBotones();

    }

    /**
     * coloca panel
     */
    private void colocarPanel(){

        panel = new JPanel();

        this.getContentPane().add(panel);
        panel.setBackground(Color.GRAY);
        panel.setLayout(null);
    }

    /**
     * coloca etiquetas
     */
    private void colocarEtiquetas(){

        etiquetaMenu = new JLabel("Menú Principal", SwingConstants.CENTER);

        etiquetaMenu.setBounds(0, 70, 600, 20);
        panel.add(etiquetaMenu);
        etiquetaMenu.setFont(new Font("Basic", Font.BOLD, 20));
        etiquetaMenu.setForeground(Color.BLACK);

        etiquetaImagen = new JLabel();
        etiquetaImagen.setBounds(435, 20, 100,50);
        etiquetaImagen.setIcon(new ImageIcon(Constante.IMAGEN.getImage().getScaledInstance(etiquetaImagen.getWidth(), etiquetaImagen.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaImagen);

        etiquetaEmpresa= new JLabel("AeroBalamDevs");
        etiquetaEmpresa.setBounds(425, 75, 150, 20);
        panel.add(etiquetaEmpresa);
        etiquetaEmpresa.setFont(new Font("Basic", Font.BOLD, 14));
        etiquetaEmpresa.setForeground(Color.BLACK);

    }

    /**
     * coloca botones
     */
    private void colocarBotones(){

        botonClientes = new JButton("Clientes");
        botonClientes.setBounds(230, 130, 150, 40);
        panel.add(botonClientes);
        botonClientes.setFont(new Font("Basic", Font.BOLD, 16));
        botonClientes.setForeground(Color.BLACK);
        oyenteClientes();
        
        botonEmpresa = new JButton("Empresa");
        botonEmpresa.setBounds(230, 190, 150, 40);
        panel.add(botonEmpresa);
        botonEmpresa.setFont(new Font("Basic", Font.BOLD, 16));
        botonEmpresa.setForeground(Color.BLACK);
        oyenteEmpleado();
    }

    /**
     * oyente botonClientes
     */
    private void oyenteClientes(){

        botonClientes.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                manejador.accionCliente();
            }
        });
    }

    /**
     * oyente botonEmpresa
     */
    private void oyenteEmpleado(){

        botonEmpresa.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){

                manejador.accionEmpresa();
            }
        });
    }

    /**
     * retorna JButton
     * @return
     */
    public JButton getBotonClientes(){return this.botonClientes;}

    /**
     * retorna JButton
     * @return
     */
    public JButton getBotonEmpresa(){return this.botonEmpresa;}
}
