
package Vista;

/**
 *
 * @author Axel Alza
 */
public class Mdi extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public Mdi() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPrincipal = new javax.swing.JDesktopPane();
        MenuBar = new javax.swing.JMenuBar();
        Menu = new javax.swing.JMenu();
        MantenimientoArticulosBtn = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 817, Short.MAX_VALUE)
        );
        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
        );

        getContentPane().add(PanelPrincipal, java.awt.BorderLayout.CENTER);

        Menu.setText("Mantenimiento");

        MantenimientoArticulosBtn.setText("Articulos");
        MantenimientoArticulosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MantenimientoArticulosBtnActionPerformed(evt);
            }
        });
        Menu.add(MantenimientoArticulosBtn);

        MenuBar.add(Menu);

        setJMenuBar(MenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MantenimientoArticulosBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MantenimientoArticulosBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MantenimientoArticulosBtnActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenuItem MantenimientoArticulosBtn;
    public javax.swing.JMenu Menu;
    private javax.swing.JMenuBar MenuBar;
    public javax.swing.JDesktopPane PanelPrincipal;
    // End of variables declaration//GEN-END:variables
}
