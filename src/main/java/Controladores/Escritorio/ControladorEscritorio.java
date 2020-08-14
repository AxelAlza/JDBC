/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Escritorio;

import Controladores.Interface;
import Modelo.TablaArticulo;
import Permanencia.ConexionSql;
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
        this.principal.MantenimientoArticulosBtn.addActionListener((var evt) -> {
            MantenimientoArticulosBtn(evt);
        });
    }
    
    public void run(){
        this.principal.setSize(1600, 900);
        this.principal.setVisible(true);
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

}
