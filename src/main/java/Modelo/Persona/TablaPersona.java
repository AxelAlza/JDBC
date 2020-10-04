/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Persona;

import Permanencia.ConexionSql;
import java.text.MessageFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public final class TablaPersona extends AbstractTableModel {

    ArrayList<Empleado> emp;

    public TablaPersona() {
        this.emp = ConexionSql.SQLPersona.ListaEmpleados();
    }

    @Override
    public int getColumnCount() {
        int ret;
        ret = 8;
        return ret;
    }

    @Override
    public int getRowCount() {
        return emp.size();
    }

    @Override
    public String getColumnName(int ColumnIndex) {
        String ColumnName = null;

        switch (ColumnIndex) {
            case 0:
                ColumnName = "Id Persona";
                break;
            case 1:
                ColumnName = "Cedula";
                break;
            case 2:
                ColumnName = "Foto";
                break;
            case 3:
                ColumnName = "Nombre Completo";
                break;
            case 4:
                ColumnName = "Telefono";
                break;
            case 5:
                ColumnName = "Direccion";
                break;
            case 6:
                ColumnName = "Id Empleado";
                break;
            case 7:
                ColumnName = "Tipo de empleado";
                break;
            case 8:
                ColumnName = "Sueldo Mensual";
                break;
        }

        return ColumnName;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object ret = null;

        switch (col) {
            case 0:
                ret = emp.get(row).getId_persona();
                break;
            case 1:
                ret = emp.get(row).getCedula();
                break;
            case 2:
                ret = emp.get(row).getIconForTable();
                break;
            case 3:
                ret = MessageFormat.format("{0} {1} {2} {3}", emp.get(row).getPrimer_nombre(), emp.get(row).getSegundo_nombre(), emp.get(row).getPrimer_apellido(), emp.get(row).getSegundo_apellido());
                break;
            case 4:
                ret = emp.get(row).getTelefono();
                break;
            case 5:
                ret = emp.get(row).getDireccion();
                break;
            case 6:
                ret = emp.get(row).getId_empleado();
                break;
            case 7:
                TipoEmpleado tipo = emp.get(row).getTipoEmpleado();
                ret = MessageFormat.format("{0} {1}", tipo.getId_tipo_empleado(), tipo.getTipo_empleado());
                break;
        }
        return ret;
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public void RemoveSelection(int i) {
        emp.remove(i);
    }

    public void AddEmpleado(Empleado empleado) {
        emp.add(empleado);
        fireTableDataChanged();
    }

    public void SetEmpleado(int i, Empleado empleado) {
        emp.set(i, empleado);
        fireTableDataChanged();
    }

    public Empleado getSelection(int i) {
        Empleado o;
        o = emp.get(i);
        return o;
    }
}
