package Controlador;

////Esto es normal?
import Modelo.Articulo;
import Modelo.TablaArticulo;
import Permanencia.ConexionSql.SQLArticulo;
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
    private final NvoArticulo nuevoArticulo;

    public Controlador(Mdi principal, ListadoArticulos listadoArticulos, NvoArticulo nvoArt) {
        this.principal = principal;
        this.listadoArticulos = listadoArticulos;
        this.nuevoArticulo = nvoArt;
        Inicializar();

    }
    
    ///Este metodo agrega los ActionListeners de los botones
    public final void Inicializar() {
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
        this.nuevoArticulo.ConfirmarBtn.addActionListener((var evt) -> {
            ConfirmarArticuloBtn(evt);
        });
        this.nuevoArticulo.CancelarBtn.addActionListener((var evt) -> {
            CancelarArticuloBtn(evt);
        });
        this.listadoArticulos.ModificarBtn.addActionListener((var evt) -> {
            ModificarBtn(evt);
        });
        this.nuevoArticulo.foto.addMouseListener(new MouseAdapter() {

            //Solo quiero un metodo del mouse listener que es el mouse clicked, porque tengo que sobrescribir los demas!?
            //Hay alguna forma de no tener que hacer esto?
            //Actualizacion: Solucionado!
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

    public void Run() {
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
        TablaArticulo tbl = new TablaArticulo(SQLArticulo.ListaArticulos());
        listadoArticulos.TablaArticulos.setModel(tbl);
        listadoArticulos.setVisible(true);

    }

    ///////////////////////////////
    //Bloques de Eventos///////////
    //ListadoArticulos.java////////
    ///////////////////////////////
    private void EliminarBtn(ActionEvent evt) {
        int i = listadoArticulos.TablaArticulos.getSelectedRow();
        if (i != -1) {
            Articulo A = ((TablaArticulo) listadoArticulos.TablaArticulos.getModel()).getArticulo(i);
            SQLArticulo.EliminarArticulo(A);
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
            nuevoArticulo.setClosed(false);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        nuevoArticulo.setSize(800, 600);
        nuevoArticulo.setMode("I");
        nuevoArticulo.foto.setIcon(new ImageIcon(NvoArticulo.class.getClassLoader().getResource("ico.png")));
        String s = "";
        nuevoArticulo.Titulo.setText("Nuevo Articulo");
        nuevoArticulo.id_articulo.setText(s);
        nuevoArticulo.codigo.setText(s);
        nuevoArticulo.descripcion.setText(s);
        nuevoArticulo.fecha_fabricacion.setText(s);
        nuevoArticulo.precio.setText(s);
        nuevoArticulo.setFotofile(null);
        nuevoArticulo.id_articulo.setEditable(true);
        principal.PanelPrincipal.add(nuevoArticulo);
        nuevoArticulo.setVisible(true);

    }

    private void ModificarBtn(ActionEvent evt) {

        try {
            nuevoArticulo.setClosed(false);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        nuevoArticulo.setMode("M");
        int i = listadoArticulos.TablaArticulos.getSelectedRow();
        if (i != -1) {
            nuevoArticulo.setSize(800, 600);
            Articulo A = ((TablaArticulo) listadoArticulos.TablaArticulos.getModel()).getArticulo(i);
            ModoModificar(A);

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
            if (nuevoArticulo.getMode() == "I") {

                //Este metodo devuelve null si no existe un articulo con el mismo id, si llegase a existir trae el articulo para cambiar los campos 
                Articulo art = SQLArticulo.CheckExistente(articulo.getId_articulo());
                ////////////////////////////////////////////////////////////////////

                if (art == null) {
                    SQLArticulo.InsertarArticulo(articulo);
                    ((TablaArticulo) listadoArticulos.TablaArticulos.getModel()).AddArticulo(articulo);
                    try {
                        nuevoArticulo.setClosed(true);
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(nuevoArticulo, "Ya existe un articulo con ese id", "error", JOptionPane.ERROR_MESSAGE);
                    ModoModificar(art);
                    nuevoArticulo.foto.setIcon(art.getIconForAbm(nuevoArticulo.getWidth(), nuevoArticulo.getHeight()));
                }

            } else if (nuevoArticulo.getMode() == "M") {
                SQLArticulo.ModificarArticulo(articulo);
                ((TablaArticulo) listadoArticulos.TablaArticulos.getModel()).SetArticulo(listadoArticulos.TablaArticulos.getSelectedRow(), articulo);
                try {
                    nuevoArticulo.setClosed(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
            nuevoArticulo.foto.setIcon(new javax.swing.ImageIcon(NvoArticulo.class.getClassLoader().getResource("ico.png")));
        } else {
            nuevoArticulo.foto.setIcon(art.getIconForAbm(nuevoArticulo.foto.getWidth(), nuevoArticulo.getHeight()));
        }

    }
}
