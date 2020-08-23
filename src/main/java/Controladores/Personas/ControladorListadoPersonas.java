/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Personas;
import Controladores.Interface;
import Modelo.Persona.Empleado;
import Modelo.Persona.Persona;
import Modelo.Persona.TablaPersona;
import Permanencia.ConexionSql;
import Vistas.Personas.NuevaPersona;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Axel Alza
 */
public final class ControladorListadoPersonas implements Interface {
    
    public ControladorListadoPersonas() {
        Inicializar();
    }
    
    @Override
    public void Inicializar() {
        listadoPersonas.PersonasComboBox.addItemListener((ItemEvent e) -> {
            var combo = listadoPersonas.PersonasComboBox.getModel().getSelectedItem().toString();
            if ("Personas".equals(combo)) {
                ((TablaPersona) listadoPersonas.TablaPersonas.getModel()).setMode(Boolean.TRUE);
            } else {
                ((TablaPersona) listadoPersonas.TablaPersonas.getModel()).setMode(Boolean.FALSE);
                
            }
            
        });
        listadoPersonas.EliminarBtn.addActionListener((var evt) -> {
            EliminarBtn(evt);
        });
        listadoPersonas.MantenimientoCancelarBtn.addActionListener((var evt) -> {
            MantenimientoCancelarBtn(evt);
        });
        listadoPersonas.NuevoBtn.addActionListener((var evt) -> {
            NuevoBtn(evt);
        });
        
        listadoPersonas.ModificarBtn.addActionListener((var evt) -> {
            ModificarBtn(evt);
        });
        
    }
    
    private void EliminarBtn(ActionEvent evt) {
        int i = listadoPersonas.TablaPersonas.getSelectedRow();
        if (i != -1) {
             Object o = ((TablaPersona) listadoPersonas.TablaPersonas.getModel()).getSelection(i);
             ConexionSql.SQL.EliminarPersona(o);
             ((TablaPersona) listadoPersonas.TablaPersonas.getModel()).RemoveSelection(i);
        } else {
            JOptionPane.showMessageDialog(listadoPersonas, "No se selecciono ninguna Persona", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void MantenimientoCancelarBtn(ActionEvent evt) {
        try {
            ControladorListadoPersonas.listadoPersonas.setClosed(true);
        } catch (PropertyVetoException ex) {
            System.out.println("Paso algo raro");
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void NuevoBtn(ActionEvent evt) {
        try {
            nuevaPersona.setClosed(false);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        nuevaPersona.setSize(800, 650);
        nuevaPersona.setMode("I");
        nuevaPersona.foto.setIcon(new ImageIcon(NuevaPersona.class.getClassLoader().getResource("ico.png")));
        String s = "";
        nuevaPersona.Titulo.setText("Nueva Persona");
        for (Component component : nuevaPersona.Formulario.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(s);
            }
        }
        for (Component component : nuevaPersona.Formulario2.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(s);
            }
        }
        nuevaPersona.setFotofile(null);
        
        
        nuevaPersona.id_persona.setEditable(true);
        principal.PanelPrincipal.add(nuevaPersona);
        nuevaPersona.setVisible(true);
    }
    
    private void ModificarBtn(ActionEvent evt) {
        try {
            nuevaPersona.setClosed(false);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
        nuevaPersona.setMode("M");
        int i = listadoPersonas.TablaPersonas.getSelectedRow();
        if (i != -1) {
           
            nuevaPersona.setSize(800, 600);
            Persona P = ((TablaPersona) listadoPersonas.TablaPersonas.getModel()).getSelection(i);
            nuevaPersona.Titulo.setText("Modificar Persona");
            nuevaPersona.id_persona.setText(String.valueOf(P.getId_persona()));
            nuevaPersona.cedula.setText(String.valueOf(P.getCedula()));
            nuevaPersona.primer_nombre.setText(P.getPrimer_nombre());
            nuevaPersona.segundo_nombre.setText(P.getSegundo_nombre());
            nuevaPersona.primer_apellido.setText(P.getPrimer_apellido());
            nuevaPersona.segundo_apellido.setText(P.getSegundo_apellido());
            nuevaPersona.telefono.setText(String.valueOf(P.getTelefono()));
            nuevaPersona.direccion.setText(P.getDireccion());
            nuevaPersona.setFotofile(P.getFoto());
            nuevaPersona.id_persona.setEditable(false);
            principal.PanelPrincipal.add(nuevaPersona);
            nuevaPersona.setVisible(true);
            if (nuevaPersona.getFotoFile() == null) {
                nuevaPersona.foto.setIcon(new javax.swing.ImageIcon(NuevaPersona.class.getClassLoader().getResource("ico.png")));
            } else {
                nuevaPersona.foto.setIcon(P.getIconForAbm(nuevaPersona.foto.getWidth(), nuevaPersona.foto.getHeight()));
            }
            if (!((TablaPersona)listadoPersonas.TablaPersonas.getModel()).isMode()) {
                nuevaPersona.isEmpleado.setSelected(true);
                nuevaPersona.id_empleado.setText(String.valueOf(((Empleado) P).getId_empleado()));
                nuevaPersona.sueldoMens.setText(String.valueOf(((Empleado) P).getSueldoMens()));
                nuevaPersona.ComboBox.setSelectedItem(((Empleado) P).getTipoEmpleado());
                nuevaPersona.Formulario2.setVisible(true);
            }
            
        } else {
            
            JOptionPane.showMessageDialog(listadoArticulos, "No se selecciono ninguna Persona", "Error", JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
}
