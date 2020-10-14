package Modelo.Articulo;

import Modelo.Articulo.Articulo;
import Permanencia.ConexionSql;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


//Todos estos son metodos, que usa el jtable

public final class TablaArticulo extends AbstractTableModel {

    ArrayList<Articulo> art;

    public TablaArticulo() {
        this.art = ConexionSql.SQLArtiuclo.ListaArticulos();
        //Trae los articulos de la base de datos
    }

    @Override
    // Devuelve el numero de columnas de cada tupla de la BD
    public int getColumnCount() {
        return 6;
    }

    // Devuelve el numero de tuplas de la bd, informacion que el Jtable necesita
    @Override
    public int getRowCount() {
        return art.size();
    }

    // Devuelve el nombre de cada columna, para que el Jtable lo muestre
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

    // Devuelve el valor de cierta columna en cierta fila
    
  
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

    // Devuelve el tipo de dato de la columna
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    //Problemas modernos requieren soluciones modernas
    //Estos metodos son para agregar Articulos al modelo que jtable usa y luego que se refresque
    
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

}
