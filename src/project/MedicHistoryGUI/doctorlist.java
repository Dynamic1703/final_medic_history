/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package project.MedicHistoryGUI;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
//import java.awt.Box;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.database.DatabaseConnection;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JScrollPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.awt.GridBagConstraints;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

/**
 *
 * @author HP
 */
public class doctorlist extends javax.swing.JFrame {

    /**jPanel1.setLayout(new GridLayout(0, 1));
     * Creates new form doctorlist
     */
    ArrayList<String> appointmentIds = new ArrayList<>();
                ArrayList<String> hname = new ArrayList<>();
               HashSet<String> doctorIds = new HashSet<>();
               
               
//                ArrayList<time.Pair<String, String,String, String,String, String>> timestampDataPairs = new ArrayList<>();
//   Creates new form timelineGUI
                 String patientID;
    public doctorlist(String patientID)  {
        this.patientID=patientID;
       initComponents();
         jPanel1.setLayout(new GridLayout(3,3,20,20));
    /**
     * Creates new form timelineGUI
     */
  
        
        try (Connection connection = DatabaseConnection.getConnection()) {
//            System.out.println("Getting Connection");
        Statement statement = connection.createStatement();
        
            // Execute the query
            String query = "SELECT appointmentID,doctorID FROM appointment WHERE patientID = '" + patientID+"'";
            
            ResultSet resultSet = statement.executeQuery(query);

            // Process the ResultSet and populate the ArrayList
            while (resultSet.next()) {
                String appointmentId = resultSet.getString("appointmentID");
                        String doctorId = resultSet.getString("doctorID");
                appointmentIds.add(appointmentId);
                doctorIds.add(doctorId);
            }
            
           
        
        }
            catch (SQLException ex) {
            System.out.println(ex);
        }
        
//        setResizable(false); 
        
        //print in j label
       
        
        
        
        
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            
             Statement statement = connection.createStatement();
Statement stat = connection.createStatement();
           
for (String element : doctorIds) {
    String doctorQuery = "SELECT * FROM doctor WHERE doctorID = '" + element + "'";
    String hospitalQuery = "SELECT h.name FROM hospital h JOIN works w ON h.hospitalID = w.hospitalID WHERE w.doctorID = '" + element + "'";
    
    ResultSet doctorResultSet = statement.executeQuery(doctorQuery);
    ResultSet hospitalResultSet = stat.executeQuery(hospitalQuery);
    
    while (doctorResultSet.next() && hospitalResultSet.next()) {
        String doctorName = doctorResultSet.getString("name");
        String specialization = doctorResultSet.getString("specialization");
        String hospitalName = hospitalResultSet.getString("name");
        
        // Assuming dynamicCell() method is for displaying data
        dynamicCell(doctorName, specialization, hospitalName,element);
        
        
    }
}
             
             
            
           
            String nameq="Select name from patient where patientID= '"+patientID+"'";
            ResultSet resultSet = statement.executeQuery(nameq);
            while (resultSet.next()) {
               
            String patientName = resultSet.getString("name");
            PatientNameF.setText("Patient Name :"+patientName);
//            PatientIDF.setText("Patient ID :"+patientID);
            }

       
    } catch (SQLException ex) {
            System.out.println(ex);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        PatientNameF = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        PatientNameF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PatientNameF.setText("jLabel1");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Medic History");

        jButton1.setBackground(new java.awt.Color(104, 104, 240));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("HOME");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(104, 104, 240));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("TIMELINE");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(PatientNameF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                .addComponent(PatientNameF, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel1.setText("Doctors Consulted");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void showPatientPan(String patientID) throws SQLException{
        // Create an instance of the patient panel GUI
        System.out.println("Hello");
         try {
        PatientPanelUI patientPanel = new PatientPanelUI(patientID);
        patientPanel.setVisible(true);
        this.dispose(); // or this.setVisible(false);
    } catch (Exception e) {
        e.printStackTrace();
    }

//         Show the patient panel GUI
System.out.println("Hello");
        // Dispose or hide the login GUI
        this.dispose(); // or this.setVisible(false);
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
try {
            showPatientPan(patientID);
        } catch (SQLException ex) {
            Logger.getLogger(time.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
             // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
private void showTimeline(String patientID) throws SQLException{
        // Create an instance of the patient panel GUI
        time timelinePanel = new time(patientID);

        // Show the patient panel GUI
//        System.out.println("hello3");
        timelinePanel.setVisible(true);
//System.out.println("hello4");
        // Dispose or hide the login GUI
        this.dispose(); // or this.setVisible(false);
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
try {
            showTimeline(patientID);
        } catch (SQLException ex) {
            Logger.getLogger(PatientPanelUI.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    /**
     * @param args the command line arguments
     */
   private void dynamicCell(String name, String specialization, String Did, String id) {
    JPanel cell = new JPanel();
    cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    cell.setBackground(new Color(102, 102, 255));
    cell.setForeground(Color.WHITE);

    JLabel PatientNameField = new JLabel(name);
    JLabel PatientAddressField = new JLabel("Specialization: " + specialization);
    JLabel PatientPhoneNo = new JLabel("Hospital: " + Did);
    JLabel Docid = new JLabel("Doctor ID: " + id);

    PatientNameField.setFont(new Font("Segoe UI", Font.BOLD, 18));
    PatientAddressField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    PatientPhoneNo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    Docid.setFont(new Font("Segoe UI", Font.PLAIN, 14));

    // Set preferred sizes to prevent text overflow
    PatientNameField.setPreferredSize(new Dimension(150, 30));
    PatientAddressField.setPreferredSize(new Dimension(150, 30));
    PatientPhoneNo.setPreferredSize(new Dimension(150, 30));
    Docid.setPreferredSize(new Dimension(150, 30));

    PatientPhoneNo.setForeground(Color.WHITE);
    Docid.setForeground(Color.WHITE);
    PatientNameField.setForeground(Color.WHITE);
    PatientAddressField.setForeground(Color.WHITE);

    GroupLayout cellLayout = new GroupLayout(cell);
    cell.setLayout(cellLayout);
    cellLayout.setAutoCreateGaps(true);
    cellLayout.setAutoCreateContainerGaps(true);

    cellLayout.setHorizontalGroup(cellLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(cellLayout.createSequentialGroup()
            .addGroup(cellLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(PatientNameField)
                .addComponent(PatientAddressField)
                .addComponent(PatientPhoneNo)
                )
                ))
    ;

    cellLayout.setVerticalGroup(cellLayout.createSequentialGroup()
        .addComponent(PatientNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        .addComponent(PatientAddressField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        .addComponent(PatientPhoneNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        )
    ;

    jPanel1.add(cell);
}
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
            java.util.logging.Logger.getLogger(doctorlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(doctorlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(doctorlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(doctorlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new doctorlist().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PatientNameF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
