package Modelo;

import java.util.ArrayList;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Axel Alza
 */
public class TablaArticulo extends AbstractTableModel {

    public TablaArticulo(ArrayList<Articulo> art) {
        this.art = art;
    }

    ArrayList<Articulo> art;

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public int getRowCount() {
        return art.size();
    }

    @Override
    public String getColumnName(int ColumnIndex) {
        String ColumnName = null;
        switch (ColumnIndex) {
            case 0:
                ColumnName = "Id Articulo";
                break;
            case 1:
                ColumnName = "Codigo";
                break;
            case 2:
                ColumnName = "Foto";
                break;
            case 3:
                ColumnName = "Descripcion";
                break;
            case 4:
                ColumnName = "Precio";
                break;
            case 5:
                ColumnName = "Fecha Fabricacion";
                break;
        }
        return ColumnName;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object ret = null;
        switch (col) {
            case 0:
                ret = art.get(row).getId_articulo();
                break;
            case 1:
                ret = art.get(row).getCodigo();
                break;
            case 2:
                ret = art.get(row).getIconForTable();
                break;
            case 3:
                ret = art.get(row).getDescripcion();
                break;
            case 4:
                ret = art.get(row).getPrecio();
                break;
            case 5:
                ret = art.get(row).getFecha_fabricacion();
                break;
        }
        return ret;
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    //Problemas modernos requieren soluciones modernas
    public void AddArticulo(Articulo articulo) {
        art.add(articulo);
        fireTableDataChanged();
    }

    public void SetArticulo(int i, Articulo articulo) {
        art.set(i, articulo);
        fireTableDataChanged();
    }

    public void DelArticulo(int i) {
        art.remove(i);
        fireTableDataChanged();

    }

    public Articulo getArticulo(int row) {
        Articulo articulo = this.art.get(row);
        return articulo;
    }

    public void fireTableDataChanged() {
        fireTableChanged(new TableModelEvent(this));
    }

}
