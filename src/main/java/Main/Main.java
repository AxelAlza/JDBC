package Main;

import Controladores.Megacontrol;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Axel Alza
 */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Nope");
        }
        Megacontrol ctrl = new Megacontrol();
        ctrl.Run();

    }

}
