package Controladores;

import Vistas.Articulos.ListadoArticulos;
import Vistas.Escritorio.Escritorio;
import Vistas.Articulos.NuevoArticulo;
import Vistas.Personas.ListadoPersonas;
import Vistas.Personas.NuevaPersona;

/**
 *
 * @author Axel Alza
 */
public interface Interface {

    Escritorio principal = new Escritorio();
    ListadoArticulos listadoArticulos = new ListadoArticulos();
    NuevoArticulo nuevoArticulo = new NuevoArticulo();
    ListadoPersonas listadoPersonas = new ListadoPersonas();
    NuevaPersona nuevaPersona = new NuevaPersona();

    public void Inicializar();

}
