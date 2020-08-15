
package Modelo.Persona;

import Modelo.Persona.TipoEmpleado;
import Modelo.Persona.Persona;
import java.io.File;

/**
 *
 * @author Axel Alza
 */
public class Empleado extends Persona {

    int id_empleado;
    TipoEmpleado TipoEmpleado;
    int sueldoMens;

    public Empleado(int id_empleado, TipoEmpleado TipoEmpleado, int sueldoMens, int id_persona, int cedula, File foto, String primer_nombre, String segundo_nombre, String primer_apellido, String segundo_apellido, int telefono, String direccion) {
        super(id_persona, cedula, foto, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, telefono, direccion);
        this.id_empleado = id_empleado;
        this.TipoEmpleado = TipoEmpleado;
        this.sueldoMens = sueldoMens;
    }

    public TipoEmpleado getTipoEmpleado() {
        return TipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado TipoEmpleado) {
        this.TipoEmpleado = TipoEmpleado;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getSueldoMens() {
        return sueldoMens;
    }

    public void setSueldoMens(int sueldoMens) {
        this.sueldoMens = sueldoMens;
    }

}
