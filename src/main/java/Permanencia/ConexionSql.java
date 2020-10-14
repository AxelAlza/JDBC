package Permanencia;

import Modelo.Articulo.Articulo;
import Modelo.Persona.Empleado;
import Modelo.Persona.TipoEmpleado;
import Utils.ProcesadorFotos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Axel Alza
 */
public abstract class ConexionSql {

    public static Connection Conectar() {
        Connection con = null;
        String puerto = "3306";
        String server = "localhost:" + puerto;
        String base = "empresa";
        String user = "root";
        String pass = "";
        try {

            con = DriverManager.getConnection("jdbc:mysql://"
                    + server + "/" + base, user, pass);

        } catch (SQLException e) {
            System.out.println(" Error al conectar :: " + e.getMessage());
        }

        return con;
    }

    public static class SQLArtiuclo extends ConexionSql {

        public static Articulo CheckExistente(int id_articulo) {
            Articulo art = null;
            try {
                Connection con = Conectar();
                String Sql = "SELECT * FROM articulos WHERE id_articulo = ?";
                PreparedStatement puntero;
                puntero = con.prepareStatement(Sql);
                puntero.setInt(1, id_articulo);
                puntero.execute();
                ResultSet res = puntero.executeQuery();
                if (res.next() == false) {
                    System.out.println("Todo bien");
                } else {
                    do {
                        art = new Articulo(res.getInt("id_articulo"),
                                res.getInt("codigo"),
                                ProcesadorFotos.Convertir_Base64String_a_imagen(res.getString("foto")),
                                res.getString("descripcion"),
                                res.getFloat("precio"),
                                res.getString("fecha_fabricacion"));
                    } while (res.next());
                }

            } catch (SQLException ex) {
                System.out.println("Todo mal : " + ex.getMessage());
            }

            return art;

        }

        public static void EliminarArticulo(Articulo art) {
            Connection con = Conectar();
            try {
                String Sql = "DELETE FROM articulos WHERE id_articulo = ?";
                PreparedStatement puntero;
                puntero = con.prepareStatement(Sql);
                puntero.setInt(1, art.getId_articulo());
                puntero.executeUpdate();

            } catch (SQLException ex) {
                System.out.println("Todo mal : " + ex.getMessage());
            }

        }

        public static void ModificarArticulo(Articulo art) {
            Connection con = Conectar();
            try {
                String sql = "UPDATE articulos set codigo = ?, foto = ? , descripcion = ?, precio = ?, fecha_fabricacion = ? where id_articulo = ?";
                PreparedStatement puntero;
                puntero = con.prepareStatement(sql);
                puntero.setInt(1, art.getCodigo());
                puntero.setString(2, art.getBase64foto());
                puntero.setString(3, art.getDescripcion());
                puntero.setFloat(4, art.getPrecio());
                puntero.setString(5, art.getFecha_fabricacion());
                puntero.setInt(6, art.getId_articulo());
                puntero.executeUpdate();
                puntero.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(" ERROR AL EJECUTAR LA CONSULTA :: " + e.getMessage());
            }

        }

        public static void InsertarArticulo(Articulo art) {
            Connection cn = Conectar();
            try {
                String sql = "INSERT INTO articulos (id_articulo,codigo,foto,descripcion,precio,fecha_fabricacion) VAlUES (?,?,?,?,?,?)";
                PreparedStatement puntero;
                puntero = cn.prepareStatement(sql);
                puntero.setInt(1, art.getId_articulo());
                puntero.setInt(2, art.getCodigo());
                puntero.setString(3, art.getBase64foto());
                puntero.setString(4, art.getDescripcion());
                puntero.setFloat(5, art.getPrecio());
                puntero.setString(6, art.getFecha_fabricacion());
                puntero.executeUpdate();
                puntero.close();
                cn.close();
            } catch (SQLException e) {
                System.out.println(" ERROR AL EJECUTAR LA CONSULTA :: " + e.getMessage());
            }

        }

        //// Usado por la clase TablaArticulo
        public static ArrayList ListaArticulos() {
            Connection con = Conectar();
            ArrayList<Articulo> Articulos = new ArrayList<>();
            try {
                String sql = "SELECT * FROM articulos";
                PreparedStatement puntero;
                puntero = con.prepareStatement(sql);
                ResultSet res = puntero.executeQuery();
                while (res.next()) {
                    Articulo arti = new Articulo(res.getInt("id_articulo"),
                            res.getInt("codigo"),
                            ProcesadorFotos.Convertir_Base64String_a_imagen(res.getString("foto")),
                            res.getString("descripcion"),
                            res.getFloat("precio"),
                            res.getString("fecha_fabricacion")
                    );
                    Articulos.add(arti);
                }

                puntero.close();
                con.close();

            } catch (SQLException e) {
                System.out.println(" ERROR AL EJECUTAR LA CONSULTA :: " + e.getMessage());
            }
            return Articulos;
        }

    }

    public static class SQLPersona extends ConexionSql {

        public static Boolean PersonaYaExiste(int id_persona) {
            Boolean b = false;
            Connection con = Conectar();
            String sql = "SELECT id_persona from persona where id_persona = ?";
            try {
                PreparedStatement puntero = con.prepareStatement(sql);
                puntero.setInt(1, id_persona);
                ResultSet res = puntero.executeQuery();
                if (res.next() == true) {
                    b = true;
                }
            } catch (SQLException e) {
                System.out.println("ay : " + e.getMessage());
            }
            return b;
        }

        public static ArrayList<TipoEmpleado> ListaTipoEmpleados() {
            Connection con = Conectar();
            ArrayList<TipoEmpleado> tipoempleados = new ArrayList<>();
            String sql = "SELECT * FROM tipos_empleados";
            try {
                PreparedStatement puntero = con.prepareStatement(sql);
                ResultSet res = puntero.executeQuery();
                while (res.next()) {
                    TipoEmpleado tipo = new TipoEmpleado(res.getInt("id_tipo_empleado"), res.getString("tipo_empleado"));
                    tipoempleados.add(tipo);
                }
            } catch (SQLException e) {
                System.out.println("Todo mal : " + e.getMessage());
            }
            return tipoempleados;
        }

        public static ArrayList ListaEmpleados() {
            Connection con = Conectar();
            ArrayList<Empleado> empleados = new ArrayList<Empleado>();
            try {
                String sql = "SELECT * FROM personas inner join empleados on personas.id_persona = empleados.id_persona inner join tipos_empleados on empleados.id_tipo = tipos_empleados.id_tipo_empleado;";
                PreparedStatement puntero;
                puntero = con.prepareStatement(sql);
                ResultSet res = puntero.executeQuery();
                while (res.next()) {
                    Empleado empl = new Empleado(res.getInt("id_empleado"), new TipoEmpleado(res.getInt("id_tipo_empleado"), res.getString("tipo_empleado")), res.getInt("sueldoMens"), res.getInt("id_persona"), res.getInt("cedula"), ProcesadorFotos.Convertir_Base64String_a_imagen(res.getString("foto")), res.getString("primer_nombre"), res.getString("segundo_nombre"), res.getString("primer_apellido"), res.getString("segundo_apellido"), res.getInt("telefono"), res.getString("direccion"));
                    empleados.add(empl);
                }
                puntero.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(" ERROR AL EJECUTAR LA CONSULTA :: " + e.getMessage());
            }
            return empleados;
        }

        public static void ModificarEmpleado(Empleado e) {
            Connection con = Conectar();
            try {
                String sql = "UPDATE empleados set id_empleado = ? , sueldoMens = ?, id_tipo = ? where id_persona = ?";
                PreparedStatement puntero;
                puntero = con.prepareStatement(sql);
                puntero.setInt(1, e.getId_empleado());
                puntero.setInt(2, e.getSueldoMens());
                puntero.setInt(3, e.getTipoEmpleado().getId_tipo_empleado());
                puntero.executeUpdate();
                sql = "UPDATE personas set cedula = ?, foto = ?, primer_nombre = ?, segundo_nombre = ?, primer_apellido = ?, segundo_apellido = ?, telefono = ?, direccion = ? where id_persona =?";
                puntero = con.prepareStatement(sql);
                puntero.setInt(1, e.getCedula());
                puntero.setString(2, e.getBase64foto());
                puntero.setString(3, e.getPrimer_nombre());
                puntero.setString(4, e.getSegundo_nombre());
                puntero.setString(5, e.getPrimer_apellido());
                puntero.setString(6, e.getSegundo_apellido());
                puntero.setInt(7, e.getTelefono());
                puntero.setString(8, e.getDireccion());
                puntero.executeUpdate();
                puntero.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println(" ERROR AL EJECUTAR LA CONSULTA :: " + ex.getMessage());
            }

        }

        public static void InsertarPersona(Empleado p) {

            Connection cn = Conectar();
            try {
                String sql = "INSERT INTO personas (id_persona,cedula,foto,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,telefono,direccion) values (?,?,?,?,?,?,?,?,?)";
                PreparedStatement puntero = cn.prepareStatement(sql);
                puntero.setInt(1, p.getId_persona());
                puntero.setInt(2, p.getCedula());
                puntero.setString(3, p.getBase64foto());
                puntero.setString(4, p.getPrimer_nombre());
                puntero.setString(5, p.getSegundo_nombre());
                puntero.setString(6, p.getPrimer_apellido());
                puntero.setString(7, p.getSegundo_apellido());
                puntero.setInt(8, p.getTelefono());
                puntero.setString(9, p.getDireccion());
                puntero.executeUpdate();
                sql = "INSERT INTO empleados (id_persona,id_empleado,id_tipo,sueldoMens) values (?,?,?,?)";
                puntero = cn.prepareStatement(sql);
                puntero.setInt(1, p.getId_persona());
                puntero.setInt(2, p.getId_empleado());
                puntero.setInt(3, p.getTipoEmpleado().getId_tipo_empleado());
                puntero.setInt(4, p.getSueldoMens());
                puntero.executeUpdate();
                puntero.close();
                cn.close();
            } catch (SQLException ex) {
                System.out.println("error sql ay : " + ex.getMessage());
            }
        }

        public static void EliminarPersona(Empleado o) {
            Connection con = Conectar();
            try {
                String Sql;
                PreparedStatement puntero;
                Sql = "DELETE FROM personas WHERE id_persona = ?";
                puntero = con.prepareStatement(Sql);
                puntero.setInt(1, o.getId_persona());
                puntero.executeUpdate();
                Sql = "DELETE FROM empleados WHERE id_persona = ?";
                puntero = con.prepareStatement(Sql);
                puntero.setInt(1, o.getId_persona());
                puntero.executeUpdate();
                puntero.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println(" ERROR AL EJECUTAR LA CONSULTA :: " + ex.getMessage());
            }
        }
    }
}
