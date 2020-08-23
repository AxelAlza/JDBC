/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Modelo.Persona.ComboBoxTipoEmpleadoModel;
import Permanencia.ConexionSql;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Axel Alza
 */
public class MainPruebas {

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Nope");
        }
        JFrame frame = new JFrame("ArrayListComboBoxModel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton btn1 = new JButton();

        JComboBox comboBox = new JComboBox();

        Container contentPane = frame.getContentPane();
        contentPane.add(comboBox, BorderLayout.NORTH);
        btn1.addActionListener((var e) -> {
            ComboBoxTipoEmpleadoModel model = new ComboBoxTipoEmpleadoModel();
            comboBox.setModel(model);

        });
        contentPane.add(btn1,BorderLayout.SOUTH);
        frame.setSize(300, 225);
        frame.setVisible(true);

    }

}
