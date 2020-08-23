/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Personas;

import Controladores.Interface;
import Modelo.Persona.Empleado;
import Modelo.Persona.Persona;
import Modelo.Persona.TipoEmpleado;
import Permanencia.ConexionSql;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Axel Alza
 */
public final class ControladorNuevaPersona implements Interface {

    public ControladorNuevaPersona() {

        Inicializar();
    }

    @Override
    public void Inicializar() {
        nuevaPersona.ComboBox.setModel(new DefaultComboBoxModel(ConexionSql.SQL.ListaTipoEmpleados().toArray()));
        nuevaPersona.Formulario2.setVisible(false);
        nuevaPersona.isEmpleado.addActionListener((var evt) -> {
            IsEmpleado(evt);

        });

        nuevaPersona.ConfirmarBtn.addActionListener((var evt) -> {
            ConfirmarArticuloBtn(evt);
        });
        nuevaPersona.CancelarBtn.addActionListener((var evt) -> {
            CancelarArticuloBtn(evt);
        });

        nuevaPersona.foto.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Seleccionar archivo de imagen");
                FileNameExtensionFilter formatos = new FileNameExtensionFilter("JPG, PNG y GIF", "JPG", "PNG", "GIF");
                fc.setFileFilter(formatos);
                if (fc.showOpenDialog(nuevaPersona) == JFileChooser.APPROVE_OPTION) {
                    File archivo = new File(fc.getSelectedFile().toString());
                    ImageIcon img_icon = new ImageIcon(archivo.toString());
                    Icon icono = new ImageIcon(img_icon.getImage().getScaledInstance(nuevaPersona.foto.getWidth(), nuevaPersona.foto.getHeight(), Image.SCALE_DEFAULT));
                    nuevaPersona.foto.setIcon(icono);
                    nuevaPersona.setFotofile(archivo);

                }
            }
        });
    }

    private void CancelarArticuloBtn(ActionEvent evt) {
        try {
            nuevaPersona.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ConfirmarArticuloBtn(ActionEvent evt) {
        Persona p = new Persona();
        p.setCedula(Integer.parseInt(nuevaPersona.cedula.getText()));
        p.setId_persona(Integer.parseInt(nuevaPersona.id_persona.getText()));
        p.setFoto(nuevaPersona.getFotoFile());
        p.setPrimer_nombre(nuevaPersona.primer_nombre.getText());
        p.setSegundo_nombre(nuevaPersona.segundo_nombre.getText());
        p.setPrimer_apellido(nuevaPersona.primer_apellido.getText());
        p.setSegundo_apellido(nuevaPersona.segundo_apellido.getText());
        p.setDireccion(nuevaPersona.direccion.getText());
        p.setTelefono(Integer.parseInt(nuevaPersona.telefono.getText()));
        if (nuevaPersona.isEmpleado.isSelected()) {
            var E = new Empleado(Integer.parseInt(nuevaPersona.id_empleado.getText()), (TipoEmpleado) nuevaPersona.ComboBox.getModel().getSelectedItem(), Integer.parseInt(nuevaPersona.sueldoMens.getText()), p);
        } else {

        }
    }

    private void IsEmpleado(ActionEvent evt) {
        if (nuevaPersona.isEmpleado.isSelected()) {
            nuevaPersona.Formulario2.setVisible(true);
            nuevaPersona.Formulario2.setEnabled(true);
        } else {
            nuevaPersona.Formulario2.setVisible(false);
            nuevaPersona.Formulario2.setEnabled(false);
        }
    }

}
