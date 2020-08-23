/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Persona;

import Permanencia.ConexionSql;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Axel Alza
 */
public final class ComboBoxTipoEmpleadoModel extends DefaultComboBoxModel<Object> {

    Object selection = null;

    public ComboBoxTipoEmpleadoModel() {
        addAll(ConexionSql.SQL.ListaTipoEmpleados());

    }

    public void setSelectedItem(Object anItem) {
        selection = anItem; // to select and register an
    } // item from the pull-down list

    // Methods implemented from the interface ComboBoxModel
    public Object getSelectedItem() {
        return selection; // to add the selection to the combo box
    }

}
