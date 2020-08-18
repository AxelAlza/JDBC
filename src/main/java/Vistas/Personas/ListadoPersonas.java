
package Vistas.Personas;


/**
 *
 * @author Axel Alza
 */
public class ListadoPersonas extends javax.swing.JInternalFrame {

    /**
     * Creates new form ArticuloABM
     */
    public ListadoPersonas() {
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TablaPersonas = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        NuevoBtn = new javax.swing.JButton();
        ModificarBtn = new javax.swing.JButton();
        EliminarBtn = new javax.swing.JButton();
        MantenimientoCancelarBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        PersonasComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);

        TablaPersonas.setBorder(new javax.swing.border.MatteBorder(null));
        TablaPersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TablaPersonas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TablaPersonas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TablaPersonas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TablaPersonas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TablaPersonas);
        TablaPersonas.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

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

        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING);
        flowLayout2.setAlignOnBaseline(true);
        jPanel2.setLayout(flowLayout2);

        PersonasComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Personas", "Empleados" }));
        jPanel2.add(PersonasComboBox);

        jLabel1.setText("Seleccionar");
        jPanel2.add(jLabel1);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton EliminarBtn;
    public javax.swing.JButton MantenimientoCancelarBtn;
    public javax.swing.JButton ModificarBtn;
    public javax.swing.JButton NuevoBtn;
    public javax.swing.JComboBox<String> PersonasComboBox;
    public javax.swing.JTable TablaPersonas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
