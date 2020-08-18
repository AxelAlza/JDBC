package Controladores;

import Vistas.Articulos.ListadoArticulos;
import Vistas.Escritorio.Escritorio;
import Vistas.Articulos.NuevoArticulo;
import Vistas.Personas.ListadoPersonas;

/**
 *
 * @author Axel Alza
 */
public interface Interface {

    final static Escritorio principal = new Escritorio();
    final static ListadoArticulos listadoArticulos = new ListadoArticulos();
    final static NuevoArticulo nuevoArticulo = new NuevoArticulo();
    final static ListadoPersonas listadoPersonas = new ListadoPersonas();
    public void Inicializar();

}
