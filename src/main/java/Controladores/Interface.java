package Controladores;

import Vistas.Articulos.ListadoArticulos;
import Vistas.Escritorio.Escritorio;
import Vistas.Articulos.NuevoArticulo;

/**
 *
 * @author Axel Alza
 */
public interface Interface {

    final static Escritorio principal = new Escritorio();
    final static ListadoArticulos listadoArticulos = new ListadoArticulos();
    final static NuevoArticulo nuevoArticulo = new NuevoArticulo();
    public void Inicializar();

}
