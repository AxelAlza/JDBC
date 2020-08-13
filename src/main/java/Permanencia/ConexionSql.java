package Permanencia;

import Modelo.Articulo;
import Modelo.ProcesadorFotos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Axel Alza
 */
public class ConexionSql {
    /////Configurar conexion sql aca
    private Connection con = null;
    private final String puerto = "3306";
    private final String server = "localhost:" + puerto;
    private final String base = "empresa";
    private final String user = "root";
    private final String pass = "";

    public Connection Conectar() {
        try {

            con = DriverManager.getConnection("jdbc:mysql://"
                    + server + "/" + base, user, pass);

        } catch (SQLException e) {
            System.out.println(" Error al conectar :: " + e.getMessage());
        }

        return con;
    }

    public void Cerrar() throws SQLException {
        if (con != null) {
            if (!con.isClosed()) {
                con.close();
            }
        }
    }

    public static class ControlaBase {

        private final Connection con;

        public ControlaBase(Connection con) {
            this.con = con;
        }

        public Articulo CheckExistente(int id_articulo) {
            Articulo art = null;
            try {
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
                Logger.getLogger(ConexionSql.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            return art;

        }

        public void EliminarArticulo(Articulo art) {

            try {
                String Sql = "DELETE FROM articulos WHERE id_articulo = ?";
                PreparedStatement puntero;
                puntero = con.prepareStatement(Sql);
                puntero.setInt(1, art.getId_articulo());
                puntero.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(ConexionSql.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        public void ModificarArticulo(Articulo art) {

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
                this.con.close();
            } catch (SQLException e) {
                System.out.println(" ERROR AL EJECUTAR LA CONSULTA :: " + e.getMessage());
            }

        }

        public void InsertarArticulo(Articulo art) {

            try {
                String sql = "INSERT INTO articulos (id_articulo,codigo,foto,descripcion,precio,fecha_fabricacion) VAlUES (?,?,?,?,?,?)";
                PreparedStatement puntero;
                puntero = con.prepareStatement(sql);
                puntero.setInt(1, art.getId_articulo());
                puntero.setInt(2, art.getCodigo());
                puntero.setString(3, art.getBase64foto());
                puntero.setString(4, art.getDescripcion());
                puntero.setFloat(5, art.getPrecio());
                puntero.setString(6, art.getFecha_fabricacion());
                puntero.executeUpdate();
                puntero.close();
                this.con.close();
            } catch (SQLException e) {
                System.out.println(" ERROR AL EJECUTAR LA CONSULTA :: " + e.getMessage());
            }

        }

    }

    public static class Listador {

        private final Connection con;
        private final ArrayList<Articulo> Articulos;

        public Listador(Connection con) {
            this.Articulos = new ArrayList<>();
            this.con = con;
        }

        public ArrayList ListaArticulos() {

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

}
