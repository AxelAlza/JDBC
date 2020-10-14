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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Axel Alza
 */
public class ControladorNvoArticulo implements Interface {

    public ControladorNvoArticulo() {
        Inicializar();
    }

    public final void Inicializar() {
        nuevoArticulo.ConfirmarBtn.addActionListener((var evt) -> {
            ConfirmarArticuloBtn(evt);
        });
        nuevoArticulo.CancelarBtn.addActionListener((var evt) -> {
            CancelarArticuloBtn(evt);
        });
        nuevoArticulo.foto.addMouseListener(new MouseAdapter() {

            //Codigo copiado del profe y de stack overflow jaja
            @Override
            public void mouseClicked(MouseEvent e) {

                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Seleccionar archivo de imagen");
                FileNameExtensionFilter formatos = new FileNameExtensionFilter("JPG, PNG y GIF", "JPG", "PNG", "GIF");
                fc.setFileFilter(formatos);
                if (fc.showOpenDialog(nuevoArticulo) == JFileChooser.APPROVE_OPTION) {
                    File archivo = new File(fc.getSelectedFile().toString());
                    ImageIcon img_icon = new ImageIcon(archivo.toString());
                    Icon icono = new ImageIcon(img_icon.getImage().getScaledInstance(nuevoArticulo.foto.getWidth(), nuevoArticulo.foto.getHeight(), Image.SCALE_DEFAULT));
                    nuevoArticulo.foto.setIcon(icono);
                    nuevoArticulo.setFotofile(archivo);

                }
            }
        });
    }

    private Boolean Validar() {
        Boolean Ok = true;
        if (nuevoArticulo.codigo.getText().isEmpty() || nuevoArticulo.descripcion.getText().isEmpty() || nuevoArticulo.precio.getText().isEmpty() || nuevoArticulo.fecha_fabricacion.getText().isEmpty() || nuevoArticulo.id_articulo.getText().isEmpty() || nuevoArticulo.getFotoFile() == null) {
            Ok = false;
        }

        return Ok;
    }

    private void ConfirmarArticuloBtn(ActionEvent evt) {
           
        if (Validar()) {
            Articulo articulo = new Articulo();
            articulo.setFoto(nuevoArticulo.getFotoFile());
            articulo.setId_articulo(Integer.parseInt(nuevoArticulo.id_articulo.getText()));
            articulo.setCodigo(Integer.parseInt(nuevoArticulo.codigo.getText()));
            articulo.setDescripcion(nuevoArticulo.descripcion.getText());
            articulo.setPrecio(Float.parseFloat(nuevoArticulo.precio.getText()));
            articulo.setFecha_fabricacion(nuevoArticulo.fecha_fabricacion.getText());
            
            //Checkeo el modo de la interfaz, "I" = Insertar
            if ("I".equals(nuevoArticulo.getMode())) {

                //Este metodo devuelve null si no existe un articulo con el mismo id, si llegase a existir trae el articulo para cambiar los campos 
                Articulo art = ConexionSql.SQLArtiuclo.CheckExistente(articulo.getId_articulo());
                ////////////////////////////////////////////////////////////////////

                if (art == null) {
                    ConexionSql.SQLArtiuclo.InsertarArticulo(articulo);
                    ((TablaArticulo) listadoArticulos.TablaArticulos.getModel()).AddArticulo(articulo);
                    try {
                        nuevoArticulo.setClosed(true);
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(nuevoArticulo, "Ya existe un articulo con ese id", "error", JOptionPane.ERROR_MESSAGE);
                    ModoModificar(art);
                    nuevoArticulo.foto.setIcon(art.getIconForAbm(nuevoArticulo.getWidth(), nuevoArticulo.getHeight()));
                }

            } else if ("M".equals(nuevoArticulo.getMode())) {
                ConexionSql.SQLArtiuclo.ModificarArticulo(articulo);
                ((TablaArticulo) listadoArticulos.TablaArticulos.getModel()).SetArticulo(listadoArticulos.TablaArticulos.getSelectedRow(), articulo);
                try {
                    nuevoArticulo.setClosed(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            JOptionPane.showMessageDialog(nuevoArticulo, "Hay campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void CancelarArticuloBtn(ActionEvent evt) {
        try {
            nuevoArticulo.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Este metodo cambia toda la interfaz segun el articulo
    private void ModoModificar(Articulo art) {
        nuevoArticulo.Titulo.setText("Modificar Articulo");
        nuevoArticulo.id_articulo.setText(String.valueOf(art.getId_articulo()));
        nuevoArticulo.codigo.setText(String.valueOf(art.getCodigo()));
        nuevoArticulo.descripcion.setText(art.getDescripcion());
        nuevoArticulo.fecha_fabricacion.setText(art.getFecha_fabricacion());
        nuevoArticulo.precio.setText(String.valueOf(art.getPrecio()));
        nuevoArticulo.setFotofile(art.getFoto());
        nuevoArticulo.id_articulo.setEditable(false);
        principal.PanelPrincipal.add(nuevoArticulo);
        nuevoArticulo.setVisible(true);
        if (nuevoArticulo.getFotoFile() == null) {
            nuevoArticulo.foto.setIcon(new javax.swing.ImageIcon(NuevoArticulo.class.getClassLoader().getResource("ico.png")));
        } else {
            nuevoArticulo.foto.setIcon(art.getIconForAbm(nuevoArticulo.foto.getWidth(), nuevoArticulo.foto.getHeight()));
        }

    }
}
