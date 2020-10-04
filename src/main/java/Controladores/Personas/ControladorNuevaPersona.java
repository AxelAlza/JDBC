/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Personas;

import Controladores.Interface;
import static Controladores.Interface.nuevaPersona;
import Modelo.Persona.Empleado;
import Modelo.Persona.TablaPersona;
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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
        nuevaPersona.ComboBox.setModel(new DefaultComboBoxModel(ConexionSql.SQLPersona.ListaTipoEmpleados().toArray()));
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

    private Boolean Validar() {
        Boolean b = true;
        for (var component : nuevaPersona.Formulario.getComponents()) {
            if (component instanceof JTextField) {
                if (((JTextField) component).getText().isEmpty()) {
                    b = false;
                }
            }
        }
        for (var component : nuevaPersona.Formulario2.getComponents()) {
            if (component instanceof JTextField) {
                if (((JTextField) component).getText().isEmpty()) {
                    b = false;
                }
            }
        }
        return b;
    }

    private void CancelarArticuloBtn(ActionEvent evt) {
        try {
            nuevaPersona.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ConfirmarArticuloBtn(ActionEvent evt) {
        if (Validar()) {
            Empleado e = new Empleado();
            e.setCedula(Integer.parseInt(nuevaPersona.cedula.getText()));
            e.setId_persona(Integer.parseInt(nuevaPersona.id_persona.getText()));
            e.setFoto(nuevaPersona.getFotoFile());
            e.setPrimer_nombre(nuevaPersona.primer_nombre.getText());
            e.setSegundo_nombre(nuevaPersona.segundo_nombre.getText());
            e.setPrimer_apellido(nuevaPersona.primer_apellido.getText());
            e.setSegundo_apellido(nuevaPersona.segundo_apellido.getText());
            e.setDireccion(nuevaPersona.direccion.getText());
            e.setTelefono(Integer.parseInt(nuevaPersona.telefono.getText()));
            e.setId_empleado(Integer.parseInt(nuevaPersona.id_empleado.getText()));
            e.setSueldoMens(Integer.parseInt(nuevaPersona.sueldoMens.getText()));
            e.setTipoEmpleado(((TipoEmpleado) nuevaPersona.ComboBox.getModel().getSelectedItem()));
            ConexionSql.SQLPersona.InsertarPersona(e);
            if ("M".equals(nuevaPersona.getMode())) {
                ConexionSql.SQLPersona.ModificarEmpleado(e);
                ((TablaPersona) listadoPersonas.TablaPersonas.getModel()).SetEmpleado(listadoPersonas.TablaPersonas.getSelectedRow(), e);
            } else {
                ConexionSql.SQLPersona.InsertarPersona(e);
                ((TablaPersona) listadoPersonas.TablaPersonas.getModel()).AddEmpleado(e);
            }

            try {
                nuevaPersona.setClosed(true);
            } catch (PropertyVetoException ex) {
                System.out.println("Que molesto... " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(nuevaPersona, "Algunos campos estan vacios", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
