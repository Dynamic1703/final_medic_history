/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package project.MedicHistoryGUI;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import project.database.DatabaseConnection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.UUID;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author vaibh
 */
public class Docpatientlist extends javax.swing.JFrame {

    /**
     * Creates new form Docpatientlist
     */
    String docID;
    String docName;
    public Docpatientlist(String doctorID,String docName) throws SQLException {
        this.docID=doctorID;
        this.docName=docName;
        initComponents();
        uniqueid.setText("UniqueID:-"+this.docID);
        docname.setText(this.docName);
        int num = 0;
      try (Connection connection = DatabaseConnection.getConnection()){
    Statement stmt = connection.createStatement();
    ResultSet rs;
    rs = stmt.executeQuery("SELECT doctorID, COUNT(DISTINCT patientID) AS num_patients FROM current_appointment where doctorID = '" + doctorID + "'");
    
    // Check if the result set has any rows
    if (rs.next()) {
        // Retrieve the value of "num_patients" column
        num = rs.getInt("num_patients");
    } else {
        // Handle the case where no rows were returned
        // For example, set num to a default value or log a message
        num = 0; // Default value
        System.out.println("No rows returned from the query");
    }
} catch (SQLException ex) {
    Logger.getLogger(Docpatientlist.class.getName()).log(Level.SEVERE, null, ex);
}
       System.out.println(num);
            String name=null;
            String phoneno=null;
            String address=null;
            String appointmentID=null;
             try (Connection connection1 = DatabaseConnection.getConnection()){
             Statement stmt1 = connection1.createStatement();
             ResultSet rs1;
             rs1 = stmt1.executeQuery("select distinct (patient.name),(patient.address),(patient.phonenumber),appointmentID from current_appointment join patient join doctor where current_appointment.doctorID= '"+ doctorID +"' and current_appointment.patientID=patient.patientID");
          
             //System.out.println(rs1.getString("name"));
             for(int i=0;i<num;i++){
                 if(rs1.next()){
                      name = rs1.getString("name");
        System.out.println("called");
        phoneno=rs1.getString("phonenumber");
        address=rs1.getString("address");
        appointmentID=rs1.getString("appointmentID");
        dynamiccell(name,phoneno,address,appointmentID);
        //System.out.println("called");
                 }
                 else{
                     System.out.println("calledelse");
                 }
             }
             
            
}
             catch (SQLException ex) {
    Logger.getLogger(Docpatientlist.class.getName()).log(Level.SEVERE, null, ex);
}
        
    }
    
//    welcomeDoctorName.setText(this.name+"👋");
    private void dynamiccell(String name,String phoneno,String address,String appointmentID)//for creating the dynamic cells having info about patients 
    {   
        
        javax.swing.JPanel cell;
        cell = new javax.swing.JPanel();
        cell.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
//        javax.swing.JLabel celltxt;
//        celltxt=new javax.swing.JLabel();
//        celltxt.setText("HELLLO");
        
        javax.swing.JLabel PatientNameField = new javax.swing.JLabel();
        javax.swing.JLabel PatientAddressField = new javax.swing.JLabel();
        javax.swing.JLabel PatientPhoneNo = new javax.swing.JLabel();
        javax.swing.JButton delete = new javax.swing.JButton();
        
        PatientNameField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PatientNameField.setText(name);
        System.out.println(name);

        PatientAddressField.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        PatientAddressField.setText(address);

        PatientPhoneNo.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        PatientPhoneNo.setText(phoneno);

        delete.setBackground(new java.awt.Color(242, 242, 242));
        delete.setForeground(new java.awt.Color(255, 51, 51));
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt,appointmentID);
            }
        });
        

        javax.swing.GroupLayout cellLayout = new javax.swing.GroupLayout(cell);
        cell.setLayout(cellLayout);
        cellLayout.setHorizontalGroup(
            cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cellLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cellLayout.createSequentialGroup()
                        .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PatientAddressField)
                            .addComponent(PatientNameField))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(cellLayout.createSequentialGroup()
                        .addComponent(PatientPhoneNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(delete)
                        .addGap(47, 47, 47))))
        );
        cellLayout.setVerticalGroup(
            cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cellLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PatientNameField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PatientAddressField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGap(18, 18, 18)
                .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PatientPhoneNo)
                    .addComponent(delete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(cell);
        jPanel1.revalidate();
        jPanel1.repaint();  
    }
    
    private void deleteActionPerformed(ActionEvent evt,String appointmentID) {
    JButton deleteButton = (JButton) evt.getSource();
    JPanel cellPanel = (JPanel) deleteButton.getParent();
    jPanel1.remove(cellPanel);
     // Call method to delete patient from database passing appointmentID
    deletePatientFromDatabase(appointmentID);
    jPanel1.revalidate();
    jPanel1.repaint();
}
    private void deletePatientFromDatabase(String appointmentID) {
    System.out.println(appointmentID);
    
    try (Connection connection = DatabaseConnection.getConnection()) {
        String sql = "DELETE FROM current_appointment WHERE appointmentID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, appointmentID);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Patient removed from current appointment.");
            } else {
                System.out.println("Patient not found in current appointment.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Docpatientlist.class.getName()).log(Level.SEVERE, null, ex);
        }
    } catch (SQLException ex) {
        Logger.getLogger(Docpatientlist.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        docname = new javax.swing.JLabel();
        home = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        patientlist = new javax.swing.JLabel();
        uniqueid = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel9.setText("Medic History");

        docname.setText("Doctor Name");

        home.setBackground(new java.awt.Color(51, 255, 51));
        home.setText("Home");
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(51, 255, 51));
        jButton7.setText("Patient List");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(51, 255, 51));
        jButton8.setText("New Prescription Reciept");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(home, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(docname))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel9)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(43, 43, 43)
                .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addComponent(docname)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        patientlist.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        patientlist.setText("Current Patient List");

        uniqueid.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        uniqueid.setText("Unique User ID:DoctorID");

        jPanel1.setLayout(new java.awt.GridLayout(3, 3, 20, 20));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(patientlist)
                        .addGap(204, 204, 204)
                        .addComponent(uniqueid)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(37, 37, 37))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(uniqueid))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(patientlist, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        // TODO add your handling code here:
        DoctorPanelUI docpanel=new DoctorPanelUI(docID);
        docpanel.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_homeActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Docpatientlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Docpatientlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Docpatientlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Docpatientlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
//        Docpatientlist obj=new Docpatientlist()
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new Docpatientlist(docID,docName).setVisible(true);
            }
        });}
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel docname;
    private javax.swing.JButton home;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel patientlist;
    private javax.swing.JLabel uniqueid;
    // End of variables declaration//GEN-END:variables
}
