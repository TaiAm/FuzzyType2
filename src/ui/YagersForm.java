/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import fuzzy2.CriteriaAHP;
import fuzzy2.Type2YagersMinMax;
import fuzzy2.YagersMinMax;
import java.awt.FileDialog;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bamo
 */
public class YagersForm extends javax.swing.JFrame {

    /**
     * Creates new form YagersForm
     */
    //YagersMinMax yg;
    public YagersForm() {
        initComponents();
        if (CriteriaAHP.comparisons == null || CriteriaAHP.comparisons.length ==0) {
            
            JOptionPane.showMessageDialog(this, "Make sure the corresponding result has been generated by the AHP class and deffuzification class else no result will be displayed", "warning", JOptionPane.WARNING_MESSAGE);
           // System.exit(1);
        }
        
        loadType1();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        fuzzyType = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        weightList = new javax.swing.JList();
        jScrollPane6 = new javax.swing.JScrollPane();
        minList = new javax.swing.JList();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("YagersMinMax");

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));

        jLabel6.setText("Fuzzy Logic Type");

        fuzzyType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Type 1", "Type 2" }));
        fuzzyType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fuzzyTypeItemStateChanged(evt);
            }
        });
        fuzzyType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fuzzyTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel6)
                .addGap(31, 31, 31)
                .addComponent(fuzzyType, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fuzzyType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(102, 102, 102))
        );

        jLabel5.setText("Weight List");

        jScrollPane5.setViewportView(weightList);

        jScrollPane6.setViewportView(minList);

        jLabel7.setText("Weighted Decision Matrix");

        jLabel8.setText("Min Value of Alt");

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(dataTable);

        jScrollPane1.setViewportView(jScrollPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 332, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(6, 6, 6)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(36, 36, 36)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                        .addComponent(jScrollPane6))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fuzzyTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fuzzyTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fuzzyTypeActionPerformed

    private void fuzzyTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fuzzyTypeItemStateChanged
        // TODO add your handling code here:
        if (fuzzyType.getSelectedIndex() ==0) {
            loadType1();
        }
        else{
            loadType2();
        }
      
    }//GEN-LAST:event_fuzzyTypeItemStateChanged
        private void loadType1(){
         YagersMinMax yg = new YagersMinMax(); 
        double[] weight = yg.calculateYagersWeight();
        //String [] weightObject = new String[weight.length];
        weightList.removeAll();
        DefaultListModel model = new DefaultListModel();

        for (int i = 0; i < weight.length; i++) {
            // weightObject[i] = ""+weight[i];
            model.addElement(YagersMinMax.approx(weight[i], 4));
        }
        weightList.setModel(model);
        //load the decision matrix
        double [][] decisionMatrix = yg.calculateYagersWeightedDecisionmatrix();
        String [] col = new String[decisionMatrix.length];
        decisionMatrix = DeffuzificationForm.transpose(decisionMatrix);
        String [][] data = DeffuzificationForm.convertToString(decisionMatrix);
        DefaultTableModel model1 = new DefaultTableModel(data, col);
        dataTable.setModel(model1);
        double [] minValues = yg.calculateMinValueOfAlternatives();
        //set the model for decision value
        DefaultListModel listModel = new DefaultListModel();
         for (int i = 0; i < minValues.length; i++) {
            // weightObject[i] = ""+weight[i];
            listModel.addElement(YagersMinMax.approx(minValues[i], 4));
        }
         minList.setModel(listModel);
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(YagersForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(YagersForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(YagersForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(YagersForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new YagersForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable dataTable;
    private javax.swing.JComboBox fuzzyType;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JList minList;
    private javax.swing.JList weightList;
    // End of variables declaration//GEN-END:variables

    private void loadType2() {
         Type2YagersMinMax yg = new Type2YagersMinMax(); 
        double[] weight = yg.calculateYagersWeight();
        //String [] weightObject = new String[weight.length];
        weightList.removeAll();
        DefaultListModel model = new DefaultListModel();

        for (int i = 0; i < weight.length; i++) {
            // weightObject[i] = ""+weight[i];
            model.addElement(YagersMinMax.approx(weight[i], 4));
        }
        weightList.setModel(model);
        //load the decision matrix
        double [][] decisionMatrix = yg.calculateYagersWeightedDecisionmatrix();
        String [] col = new String[decisionMatrix.length];
        decisionMatrix = DeffuzificationForm.transpose(decisionMatrix);
        String [][] data = DeffuzificationForm.convertToString(decisionMatrix);
        DefaultTableModel model1 = new DefaultTableModel(data, col);
        dataTable.setModel(model1);
        double [] minValues = yg.calculateMinValueOfAlternatives();
        //set the model for decision value
        DefaultListModel listModel = new DefaultListModel();
         for (int i = 0; i < minValues.length; i++) {
            // weightObject[i] = ""+weight[i];
            listModel.addElement(YagersMinMax.approx(minValues[i], 4));
        }
         minList.setModel(listModel);

    }
}
