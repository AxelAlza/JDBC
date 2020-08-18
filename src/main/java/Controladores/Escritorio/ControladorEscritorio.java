/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Escritorio;

import Controladores.Interface;
import Modelo.Articulo.TablaArticulo;
import Modelo.Persona.TablaPersona;
import Permanencia.ConexionSql;
import Vistas.Personas.ListadoPersonas;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Axel Alza
 */
public final class ControladorEscritorio implements Interface {

    public ControladorEscritorio() {
        Inicializar();
    }

    @Override
    public final void Inicializar() {
        ControladorEscritorio.principal.MantenimientoArticulosBtn.addActionListener((var evt) -> {
            MantenimientoArticulosBtn(evt);
        });
        ControladorEscritorio.principal.MantenimientoPersonasBtn.addActionListener((var evt) -> {
            MantenimientoPersonasBtn(evt);
        });

    }

    public void run() {
        ControladorEscritorio.principal.setSize(1600, 900);
        ControladorEscritorio.principal.setVisible(true);
    }

    private void MantenimientoArticulosBtn(ActionEvent evt) {

        try {
            listadoArticulos.setClosed(false);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
        listadoArticulos.setSize(800, 600);
        principal.PanelPrincipal.add(listadoArticulos);
        listadoArticulos.setResizable(true);
        TablaArticulo tbl = new TablaArticulo(ConexionSql.SQLArticulo.ListaArticulos());
        listadoArticulos.TablaArticulos.setModel(tbl);
        listadoArticulos.setVisible(true);

    }

    private void MantenimientoPersonasBtn(ActionEvent evt) {
        try {
            listadoPersonas.setClosed(false);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
        listadoPersonas.setSize(800, 600);
        principal.PanelPrincipal.add(listadoPersonas);
        listadoPersonas.setResizable(true);
        TablaPersona tbl = new TablaPersona(ConexionSql.SQLArticulo.ListaEmpleados(), ConexionSql.SQLArticulo.ListaPersonas());
        listadoPersonas.TablaPersonas.setModel(tbl);
        listadoPersonas.setVisible(true);

    }
}
