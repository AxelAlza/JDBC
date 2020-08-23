/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Persona;

/**
 *
 * @author Axel Alza
 */
public class TipoEmpleado {

    int id_tipo_empleado;
    String tipo_empleado;

    public TipoEmpleado(int id_tipo_empleado, String tipo_empleado) {
        this.id_tipo_empleado = id_tipo_empleado;
        this.tipo_empleado = tipo_empleado;
    }

    public int getId_tipo_empleado() {
        return id_tipo_empleado;
    }

    public void setId_tipo_empleado(int id_tipo_empleado) {
        this.id_tipo_empleado = id_tipo_empleado;
    }

    public String getTipo_empleado() {
        return tipo_empleado;
    }

    public void setTipo_empleado(String tipo_empleado) {
        this.tipo_empleado = tipo_empleado;
    }
    @Override
    public String toString() {
    
        return tipo_empleado;
}

}
