/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Personas;

import Controladores.Interface;
import Modelo.Persona.TablaPersona;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.event.TableModelEvent;

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
                System.out.println("Seleccione persona");

            } else {
                ((TablaPersona) listadoPersonas.TablaPersonas.getModel()).setMode(Boolean.FALSE);
                System.out.println("Seleccione empleado");
            }

        });
    }

}
