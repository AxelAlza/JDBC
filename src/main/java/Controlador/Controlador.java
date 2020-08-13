package Controlador;

////Esto es normal?
import Modelo.Articulo;

import Modelo.TablaArticulo;
import Permanencia.ConexionSql;
import Permanencia.ConexionSql.ControlaBase;
import Permanencia.ConexionSql.Listador;
import Vista.ListadoArticulos;
import Vista.Mdi;
import Vista.NvoArticulo;
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
public class Controlador {

    private final Mdi principal;
    private final ListadoArticulos listadoArticulos;
    private final ConexionSql sql;
    private final NvoArticulo nvoArt;

    public Controlador(Mdi principal, ListadoArticulos listadoArticulos, ConexionSql sql, NvoArticulo nvoArt) {
        this.principal = principal;
        this.listadoArticulos = listadoArticulos;
        this.sql = sql;
        this.nvoArt = nvoArt;
        principal.MantenimientoArticulosBtn.addActionListener((var evt) -> {
            MantenimientoArticulosBtn(evt);
        });
        listadoArticulos.EliminarBtn.addActionListener((var evt) -> {
            EliminarBtn(evt);
        });
        listadoArticulos.MantenimientoCancelarBtn.addActionListener((var evt) -> {
            MantenimientoCancelarBtn(evt);
        });
        listadoArticulos.NuevoBtn.addActionListener((var evt) -> {
            NuevoBtn(evt);
        });
        this.nvoArt.ConfirmarBtn.addActionListener((var evt) -> {
            AgregarArticuloBaseBtn(evt);
        });
        this.nvoArt.CancelarBtn.addActionListener((var evt) -> {
            CancelarNuevoArtBtn(evt);
        });
        this.listadoArticulos.ModificarBtn.addActionListener((var evt) -> {
            ModificarBtn(evt);
        });
        this.nvoArt.foto.addMouseListener(new MouseAdapter() {

            //Solo quiero un metodo del mouse listener que es el mouse clicked, porque tengo que sobrescribir los demas!?
            //Hay alguna forma de no tener que hacer esto?
            //Actualizacion: Solucionado!
            @Override
            public void mouseClicked(MouseEvent e) {

                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Seleccionar archivo de imagen");
                FileNameExtensionFilter formatos = new FileNameExtensionFilter("JPG, PNG y GIF", "JPG", "PNG", "GIF");
                fc.setFileFilter(formatos);
                if (fc.showOpenDialog(nvoArt) == JFileChooser.APPROVE_OPTION) {
                    File archivo = new File(fc.getSelectedFile().toString());
                    ImageIcon img_icon = new ImageIcon(archivo.toString());
                    Icon icono = new ImageIcon(img_icon.getImage().getScaledInstance(nvoArt.foto.getWidth(), nvoArt.foto.getHeight(), Image.SCALE_DEFAULT));
                    nvoArt.foto.setIcon(icono);
                    nvoArt.setFotofile(archivo);

                }
            }
        });

    }

    public void Iniciar() {
        principal.setSize(1600, 900);
        principal.setVisible(true);

    }

    //////////////////////
    //Bloques de Eventos//
    //Mdi.java////////////
    ///////////////////////
    private void MantenimientoArticulosBtn(ActionEvent evt) {

        try {
            listadoArticulos.setClosed(false);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        listadoArticulos.setSize(800, 600);
        principal.PanelPrincipal.add(listadoArticulos);
        listadoArticulos.setResizable(true);
        Listador list;
        list = new ConexionSql.Listador(sql.Conectar());
        TablaArticulo tbl = new TablaArticulo(list.ListaArticulos());
        listadoArticulos.TablaArticulos.setModel(tbl);
        listadoArticulos.setVisible(true);

    }

    ///////////////////////////////
    //Bloques de Eventos///////////
    //ListadoArticulos.java////////
    ///////////////////////////////
    private void EliminarBtn(ActionEvent evt) {
        if (listadoArticulos.TablaArticulos.getSelectedRow() != -1) {
            int i = listadoArticulos.TablaArticulos.getSelectedRow();
            Articulo A = ((TablaArticulo) listadoArticulos.TablaArticulos.getModel()).getArticulo(i);
            ControlaBase CtrlB = new ConexionSql.ControlaBase(sql.Conectar());
            CtrlB.EliminarArticulo(A);
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
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void NuevoBtn(ActionEvent evt) {

        try {
            nvoArt.setClosed(false);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        nvoArt.setSize(800, 600);
        nvoArt.setMode("I");
        nvoArt.foto.setIcon(new ImageIcon(NvoArticulo.class.getClassLoader().getResource("ico.png")));
        String s = "";
        nvoArt.Titulo.setText("Nuevo Articulo");
        nvoArt.id_articulo.setText(s);
        nvoArt.codigo.setText(s);
        nvoArt.descripcion.setText(s);
        nvoArt.fecha_fabricacion.setText(s);
        nvoArt.precio.setText(s);
        nvoArt.foto.setText(s);
        nvoArt.id_articulo.setEditable(true);
        principal.PanelPrincipal.add(nvoArt);
        nvoArt.setVisible(true);

    }

    private void ModificarBtn(ActionEvent evt) {

        try {
            nvoArt.setClosed(false);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        nvoArt.setMode("M");

        if (listadoArticulos.TablaArticulos.getSelectedRow() != -1) {
            nvoArt.setSize(800, 600);
            Articulo A = ((TablaArticulo) listadoArticulos.TablaArticulos.getModel()).getArticulo(listadoArticulos.TablaArticulos.getSelectedRow());
            //// Si lo se eso es una chanchada
            nvoArt.Titulo.setText("Modificar Articulo");
            nvoArt.id_articulo.setText(String.valueOf(A.getId_articulo()));
            nvoArt.codigo.setText(String.valueOf(A.getCodigo()));
            nvoArt.descripcion.setText(A.getDescripcion());
            nvoArt.fecha_fabricacion.setText(A.getFecha_fabricacion());
            nvoArt.precio.setText(String.valueOf(A.getPrecio()));
            nvoArt.setFotofile(A.getFoto());
            ImageIcon img_icon = new ImageIcon(A.getFoto().toString());
            nvoArt.id_articulo.setEditable(false);
            principal.PanelPrincipal.add(nvoArt);
            nvoArt.setVisible(true);
            Icon icono = new ImageIcon(img_icon.getImage().getScaledInstance(nvoArt.foto.getWidth(), nvoArt.foto.getHeight(), Image.SCALE_DEFAULT));
            nvoArt.foto.setIcon(icono);

        } else {

            JOptionPane.showMessageDialog(listadoArticulos, "No se selecciono ningun articulo", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    ///////////////////////////////
    //Bloques de Eventos///////////
    //NvoArticulo.java/////////////
    //////////////////////////////
    public Boolean Validar() {
        Boolean Ok = true;
        if (nvoArt.codigo.getText().isEmpty() || nvoArt.descripcion.getText().isEmpty() || nvoArt.precio.getText().isEmpty() || nvoArt.fecha_fabricacion.getText().isEmpty() || nvoArt.id_articulo.getText().isEmpty() || nvoArt.getFotoFile() == null) {
            Ok = false;
        }

        return Ok;
    }

    private void AgregarArticuloBaseBtn(ActionEvent evt) {
        if (Validar()) {
            Articulo articulo = new Articulo();
            articulo.setFoto(nvoArt.getFotoFile());
            articulo.setId_articulo(Integer.parseInt(nvoArt.id_articulo.getText()));
            articulo.setCodigo(Integer.parseInt(nvoArt.codigo.getText()));
            articulo.setDescripcion(nvoArt.descripcion.getText());
            articulo.setPrecio(Float.parseFloat(nvoArt.precio.getText()));
            articulo.setFecha_fabricacion(nvoArt.fecha_fabricacion.getText());
            ControlaBase CtrlB = new ConexionSql.ControlaBase(sql.Conectar());
            if (nvoArt.getMode() == "I") {

                Articulo art = CtrlB.CheckExistente(articulo.getId_articulo());
                System.out.println(art);
                if (art == null) {
                    CtrlB.InsertarArticulo(articulo);
                    ((TablaArticulo) listadoArticulos.TablaArticulos.getModel()).AddArticulo(articulo);
                    try {
                        nvoArt.setClosed(true);
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(nvoArt, "Ya existe un articulo con ese id", "error", JOptionPane.ERROR_MESSAGE);
                    nvoArt.setMode("M");
                    nvoArt.Titulo.setText("Modificar Articulo");
                    nvoArt.id_articulo.setText(String.valueOf(art.getId_articulo()));
                    nvoArt.codigo.setText(String.valueOf(art.getCodigo()));
                    nvoArt.descripcion.setText(art.getDescripcion());
                    nvoArt.fecha_fabricacion.setText(art.getFecha_fabricacion());
                    nvoArt.precio.setText(String.valueOf(art.getPrecio()));
                    nvoArt.setFotofile(art.getFoto());
                    ImageIcon img_icon = new ImageIcon(art.getFoto().toString());
                    nvoArt.id_articulo.setEditable(false);
                    Icon icono = new ImageIcon(img_icon.getImage().getScaledInstance(nvoArt.foto.getWidth(), nvoArt.foto.getHeight(), Image.SCALE_DEFAULT));
                    nvoArt.foto.setIcon(icono);
                }
            } else if (nvoArt.getMode() == "M") {
                CtrlB.ModificarArticulo(articulo);
                ((TablaArticulo) listadoArticulos.TablaArticulos.getModel()).SetArticulo(listadoArticulos.TablaArticulos.getSelectedRow(), articulo);
                try {
                    nvoArt.setClosed(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            JOptionPane.showMessageDialog(nvoArt, "Hay campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void CancelarNuevoArtBtn(ActionEvent evt) {
        try {
            nvoArt.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
