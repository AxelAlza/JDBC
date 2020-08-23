/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Persona;

import Modelo.ProcesadorFotos;
import java.awt.Image;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Axel Alza
 */
public class Persona {

    private int id_persona;
    private int cedula;
    private File foto;
    private String primer_nombre;
    private String segundo_nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private int telefono;
    private String direccion;

    public Persona() {
    }

    public Persona(int id_persona, int cedula, File foto, String primer_nombre, String segundo_nombre, String primer_apellido, String segundo_apellido, int telefono, String direccion) {
        if (primer_nombre == null) {
            primer_nombre = "";
        }
        if (segundo_nombre == null) {
            segundo_nombre = "";
        }
        if (primer_apellido == null) {
            primer_apellido = "";
        }
        if (segundo_apellido == null) {
            segundo_apellido = "";
        }
        this.id_persona = id_persona;
        this.cedula = cedula;
        this.foto = foto;
        this.primer_nombre = primer_nombre;
        this.segundo_nombre = segundo_nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public File getFoto() {
        return foto;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public String getPrimer_nombre() {
        return primer_nombre;
    }

    public void setPrimer_nombre(String primer_nombre) {
        this.primer_nombre = primer_nombre;
    }

    public String getSegundo_nombre() {
        return segundo_nombre;
    }

    public void setSegundo_nombre(String segundo_nombre) {
        this.segundo_nombre = segundo_nombre;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBase64foto() {
        return ProcesadorFotos.Convertir_imagen_a_Base64String(getFoto());
    }

    public Icon getIconForAbm(int width, int height) {
        ImageIcon img_icon = new ImageIcon(getFoto().toString());
        Icon icono = new ImageIcon(img_icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));

        return icono;
    }

    public Icon getIconForTable() {
        ImageIcon img_icon = new ImageIcon(getFoto().toString());
        Icon icono = new ImageIcon(img_icon.getImage().getScaledInstance(50, 50, 1));
        return icono;
    }

}
