package Main;

import Controlador.Controlador;
import Permanencia.ConexionSql;
import Vista.ListadoArticulos;
import Vista.Mdi;
import Vista.NvoArticulo;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;


/**
 *
 * @author Axel Alza
 */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Nope");
        }

        
        ConexionSql c = new ConexionSql();
        Mdi p = new Mdi();
        ListadoArticulos la = new ListadoArticulos();
        NvoArticulo nvo = new NvoArticulo();
        Controlador control = new Controlador(p, la, c, nvo);
        control.Iniciar();
        

    }

}
