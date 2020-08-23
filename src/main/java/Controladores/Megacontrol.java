/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.Articulo.ControladorListadoArticulos;
import Controladores.Articulo.ControladorNvoArticulo;
import Controladores.Escritorio.ControladorEscritorio;
import Controladores.Personas.ControladorListadoPersonas;
import Controladores.Personas.ControladorNuevaPersona;

/**
 *
 * @author Axel Alza
 */
public class Megacontrol {

    ControladorNvoArticulo CtrlN;
    ControladorEscritorio CtrlE;
    ControladorListadoArticulos CtrlL;
    ControladorListadoPersonas CtrlP;
    ControladorNuevaPersona CtrlNP;

    public Megacontrol() {
        this.CtrlL = new ControladorListadoArticulos();
        this.CtrlE = new ControladorEscritorio();
        this.CtrlN = new ControladorNvoArticulo();
        this.CtrlP = new ControladorListadoPersonas();
        this.CtrlNP = new ControladorNuevaPersona();

    }

    public void Run() {
        CtrlE.run();
    }
}
