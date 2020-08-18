/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Persona;

import java.text.MessageFormat;
import java.util.ArrayList;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

public class TablaPersona extends AbstractTableModel {

    boolean mode;
    ArrayList<Empleado> emp;
    ArrayList<Persona> per;

    public TablaPersona(ArrayList<Empleado> emp, ArrayList<Persona> per) {
        this.emp = emp;
        this.per = per;
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
                    ret = MessageFormat.format("{0} {0} {0} {0}", per.get(row).getPrimer_nombre(), per.get(row).getSegundo_nombre(), per.get(row).getPrimer_apellido(), per.get(row).getSegundo_apellido());
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
                    ret = MessageFormat.format("{0} {0} {0} {0}", emp.get(row).getPrimer_nombre(), emp.get(row).getSegundo_nombre(), emp.get(row).getPrimer_apellido(), emp.get(row).getSegundo_apellido());
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
                    ret = MessageFormat.format("{0} {0}", tipo.getId_tipo_empleado(), tipo.getTipo_empleado());
                    break;
            }
        }
        return ret;
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    //Problemas modernos requieren soluciones modernas
    public void AddPersona(Persona persona) {
        per.add(persona);
        fireTableDataChanged();
    }

    public void SetPersona(int i, Persona persona) {
        per.set(i, persona);
        fireTableDataChanged();
    }

    public void DelPersona(int i) {
        per.remove(i);
        fireTableDataChanged();

    }

    public void AddEmpleado(Empleado empleado) {
        per.add(empleado);
        fireTableDataChanged();
    }

    public void SetEmpleado(int i, Empleado empleado) {
        per.set(i, empleado);
        fireTableDataChanged();
    }

    public void DelEmpleado(int i) {
        per.remove(i);
        fireTableDataChanged();

    }

    public Persona getPersona(int row) {

        return per.get(row);
    }

    public Empleado getEmpleado(int row) {

        return emp.get(row);
    }

    public void setMode(Boolean b) {
        mode = b;
        fireTableDataChanged();
    }

    public void fireTableDataChanged() {
        fireTableChanged(new TableModelEvent(this));
    }

}
