/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Articulo;

import Controladores.Interface;
import Modelo.Articulo.Articulo;
import Modelo.Articulo.TablaArticulo;
import Permanencia.ConexionSql;
import Vistas.Articulos.NuevoArticulo;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Axel Alza
 */
public final class ControladorListadoArticulos implements Interface {

    public ControladorListadoArticulos() {
        Inicializar();
    }

    public void Inicializar() {
        //Inicializo los Action listeners que estan atentos a cuando clickeas en los botones,
        //Puedo acceder a los botones de los listados por que especifique que todos los elementos de la interfaz
        //Sean publicos y asi implementar el modelo MVC
        
        listadoArticulos.EliminarBtn.addActionListener((var evt) -> {
            EliminarBtn(evt);
        });
        listadoArticulos.MantenimientoCancelarBtn.addActionListener((var evt) -> {
            MantenimientoCancelarBtn(evt);
        });
        listadoArticulos.NuevoBtn.addActionListener((var evt) -> {
            NuevoBtn(evt);
        });

        listadoArticulos.ModificarBtn.addActionListener((var evt) -> {
            ModificarBtn(evt);
        });

    }

    private void EliminarBtn(ActionEvent evt) {
        int i = listadoArticulos.TablaArticulos.getSelectedRow();
        if (i != -1) {
            Articulo A = ((TablaArticulo) listadoArticulos.TablaArticulos.getModel()).getArticulo(i);
            ConexionSql.SQLArtiuclo.EliminarArticulo(A);
            ((TablaArticulo) listadoArticulos.TablaArticulos.getModel()).DelArticulo(i);
        } else {
            JOptionPane.showMessageDialog(listadoArticulos, "No se selecciono ningun articulo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void MantenimientoCancelarBtn(ActionEvent evt) {
        try {
            this.listadoArticulos.setClosed(true);
        } catch (PropertyVetoException ex) {
            System.out.println("Paso algo raro");
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void NuevoBtn(ActionEvent evt) {

        try {
            nuevoArticulo.setClosed(false);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }

        nuevoArticulo.setSize(800, 600);
        nuevoArticulo.setMode("I");
        nuevoArticulo.foto.setIcon(new ImageIcon(NuevoArticulo.class.getClassLoader().getResource("ico.png")));
        nuevoArticulo.Titulo.setText("Nuevo Articulo");
        
        //Vacio cada campo de texto que haya en cada formulario, es necesario por si entraste a modificar un producto antes, 
        //Ya que cada modo (Modificar, Insertar) comparten la misma interfaz
        String s = "";
        Stream.of(nuevoArticulo.formulario.getComponents())
                .filter(c -> c instanceof JTextField)
                .map(c -> ((JTextField) c))
                .forEach((c) -> {
                    c.setText(s);
                });
        nuevoArticulo.descripcion.setText(s);
        nuevoArticulo.setFotofile(null);
        nuevoArticulo.id_articulo.setEditable(true);
        principal.PanelPrincipal.add(nuevoArticulo);
        nuevoArticulo.setVisible(true);

    }

    private void ModificarBtn(ActionEvent evt) {

        try {
            nuevoArticulo.setClosed(false);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Setear el modo de la interfaz a modificar
        //Es un metodo que implemente en la vista
        nuevoArticulo.setMode("M");
        int i = listadoArticulos.TablaArticulos.getSelectedRow();
        if (i != -1) {
            nuevoArticulo.setSize(800, 600);
            Articulo A = ((TablaArticulo) listadoArticulos.TablaArticulos.getModel()).getArticulo(i);
            nuevoArticulo.Titulo.setText("Modificar Articulo");
            nuevoArticulo.id_articulo.setText(String.valueOf(A.getId_articulo()));
            nuevoArticulo.codigo.setText(String.valueOf(A.getCodigo()));
            nuevoArticulo.descripcion.setText(A.getDescripcion());
            nuevoArticulo.fecha_fabricacion.setText(A.getFecha_fabricacion());
            nuevoArticulo.precio.setText(String.valueOf(A.getPrecio()));
            nuevoArticulo.setFotofile(A.getFoto());
            nuevoArticulo.id_articulo.setEditable(false);
            principal.PanelPrincipal.add(nuevoArticulo);
            nuevoArticulo.setVisible(true);
            if (nuevoArticulo.getFotoFile().getName().equals("null.png")) {
                nuevoArticulo.foto.setIcon(new javax.swing.ImageIcon(NuevoArticulo.class.getClassLoader().getResource("ico.png")));
            } else {
                nuevoArticulo.foto.setIcon(A.getIconForAbm(nuevoArticulo.foto.getWidth(), nuevoArticulo.foto.getHeight()));
            }

        } else {

            JOptionPane.showMessageDialog(listadoArticulos, "No se selecciono ningun articulo", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

}
