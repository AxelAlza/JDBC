package Modelo.Articulo;

import Modelo.ProcesadorFotos;
import java.awt.Image;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Axel Alza
 */
public class Articulo {

    int id_articulo;
    int codigo;
    File foto;
    String descripcion;
    float precio;
    String fecha_fabricacion;

    public Articulo() {

    }

    public Articulo(int id_articulo, int codigo, File foto, String descripcion, float precio, String fecha_fabricacion) {
        this.id_articulo = id_articulo;
        this.codigo = codigo;
        this.foto = foto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha_fabricacion = fecha_fabricacion;
    }

    public String getBase64foto() {
        return ProcesadorFotos.Convertir_imagen_a_Base64String(foto);
    }

    public Icon getIconForAbm(int width, int height) {
        ImageIcon img_icon = new ImageIcon(foto.toString());
        Icon icono = new ImageIcon(img_icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));

        return icono;
    }

    public Icon getIconForTable() {
        ImageIcon img_icon = new ImageIcon(foto.toString());
        Icon icono = new ImageIcon(img_icon.getImage().getScaledInstance(50, 50, 1));
        return icono;
    }

    public int getId_articulo() {
        return id_articulo;
    }

    public int getCodigo() {
        return codigo;
    }

    public File getFoto() {
        return foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public String getFecha_fabricacion() {
        return fecha_fabricacion;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setFecha_fabricacion(String fecha_fabricacion) {
        this.fecha_fabricacion = fecha_fabricacion;
    }

}
