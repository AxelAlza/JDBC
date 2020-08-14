package Main;

import Controlador.Controlador;
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

        
      
        Mdi p = new Mdi();
        ListadoArticulos la = new ListadoArticulos();
        NvoArticulo nvo = new NvoArticulo();
        Controlador control = new Controlador(p, la, nvo);
        control.Iniciar();
        

    }

}
