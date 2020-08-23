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

    boolean mode;
    ArrayList<Empleado> emp;
    ArrayList<Persona> per;

    public TablaPersona() {
        this.emp = ConexionSql.SQL.ListaEmpleados();
        this.per = ConexionSql.SQL.ListaPersonas();
        setMode(Boolean.TRUE);
    }

    @Override
    public int getColumnCount() {
        int ret;
        if (mode == true) {
            ret = 5;
        } else {
            ret = 8;
        }
        return ret;
    }

    @Override
    public int getRowCount() {
        if (mode == true) {
            return per.size();
        } else {
            return emp.size();
        }
    }

    @Override
    public String getColumnName(int ColumnIndex) {
        String ColumnName = null;
        if (mode == true) {
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
            }
        } else {
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
        }
        return ColumnName;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object ret = null;
        if (mode == true) {
            switch (col) {
                case 0:
                    ret = per.get(row).getId_persona();
                    break;
                case 1:
                    ret = per.get(row).getCedula();
                    break;
                case 2:
                    ret = per.get(row).getIconForTable();
                    break;
                case 3:
                    ret = MessageFormat.format("{0} {1} {2} {3}", per.get(row).getPrimer_nombre(), per.get(row).getSegundo_nombre(), per.get(row).getPrimer_apellido(), per.get(row).getSegundo_apellido());
                    break;
                case 4:
                    ret = per.get(row).getTelefono();
                    break;
                case 5:
                    ret = per.get(row).getDireccion();
                    break;
            }
        } else {
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
        }
        return ret;
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public void AddPersona(Persona persona) {
        per.add(persona);
        fireTableDataChanged();
    }

    public void SetPersona(int i, Persona persona) {
        per.set(i, persona);
        fireTableDataChanged();
    }

    public void RemoveSelection(int i){
        if(isMode()){
            per.remove(i);
        }
        else{
            emp.remove(i);
        }
    }

    public void AddEmpleado(Empleado empleado) {
        emp.add(empleado);
        fireTableDataChanged();
    }

    public void SetEmpleado(int i, Empleado empleado) {
        emp.set(i, empleado);
        fireTableDataChanged();
    }

 

    public Persona getSelection(int i) {
        Persona o;
        if (isMode()) {
            o = per.get(i);
        } else {
            o = emp.get(i);
        }

        return o;
    }

    public void setMode(Boolean b) {
        mode = b;
        fireTableStructureChanged();
    }

    public boolean isMode() {
        return mode;
    }

}
