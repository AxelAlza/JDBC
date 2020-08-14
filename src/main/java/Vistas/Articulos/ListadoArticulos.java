
package Vistas.Articulos;

/**
 *
 * @author Axel Alza
 */
public class ListadoArticulos extends javax.swing.JInternalFrame {

    /**
     * Creates new form ArticuloABM
     */
    public ListadoArticulos() {
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TablaArticulos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        NuevoBtn = new javax.swing.JButton();
        ModificarBtn = new javax.swing.JButton();
        EliminarBtn = new javax.swing.JButton();
        MantenimientoCancelarBtn = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        TablaArticulos.setBorder(new javax.swing.border.MatteBorder(null));
        TablaArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TablaArticulos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TablaArticulos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TablaArticulos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TablaArticulos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TablaArticulos);
        TablaArticulos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING, 20, 2);
        flowLayout1.setAlignOnBaseline(true);
        jPanel1.setLayout(flowLayout1);

        NuevoBtn.setText("Nuevo");
        jPanel1.add(NuevoBtn);

        ModificarBtn.setText("Modificar");
        jPanel1.add(ModificarBtn);

        EliminarBtn.setText("Eliminar");
        jPanel1.add(EliminarBtn);

        MantenimientoCancelarBtn.setText("Cancelar");
        jPanel1.add(MantenimientoCancelarBtn);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton EliminarBtn;
    public javax.swing.JButton MantenimientoCancelarBtn;
    public javax.swing.JButton ModificarBtn;
    public javax.swing.JButton NuevoBtn;
    public javax.swing.JTable TablaArticulos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
