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
    String email;
    
    public Docpatientlist(String doctorID,String docName,String email) throws SQLException {
        this.docID=doctorID;
        this.docName=docName;
        this.email=email;
        initComponents();
        uniqueid.setText("UniqueID:-"+email);
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
             rs1 = stmt1.executeQuery("select distinct(appointmentID),(patient.name),(patient.address),(patient.phonenumber) from current_appointment join patient join doctor where current_appointment.doctorID= '"+ doctorID +"' and current_appointment.patientID=patient.patientID");
          
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
        cell.setBackground(new java.awt.Color(102,102,255));
//        javax.swing.JLabel celltxt;
//        celltxt=new javax.swing.JLabel();
//        celltxt.setText("HELLLO");
//        cell.setSize(new Dimension(30,100));
        javax.swing.JLabel PatientNameField = new javax.swing.JLabel();
        javax.swing.JLabel AppointmentID = new javax.swing.JLabel();
        javax.swing.JLabel PatientAddressField = new javax.swing.JLabel();
        javax.swing.JLabel PatientPhoneNo = new javax.swing.JLabel();
        javax.swing.JButton delete = new javax.swing.JButton();
        
        PatientNameField.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        PatientNameField.setForeground(new java.awt.Color(255,255,255));
        PatientNameField.setText(name);
//        AppointmentID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
//        AppointmentID.setText(appointmentID);
        System.out.println(name);

        PatientAddressField.setFont(new java.awt.Font("Microsoft Tai Le", 1, 12)); // NOI18N
        PatientAddressField.setForeground(new java.awt.Color(255,255,255));
        PatientAddressField.setText(address);
        

        PatientPhoneNo.setFont(new java.awt.Font("Microsoft Tai Le", 1, 12)); // NOI18N
        PatientPhoneNo.setForeground(new java.awt.Color(255,255,255));
        PatientPhoneNo.setText(phoneno);

        delete.setBackground(new java.awt.Color(255, 255, 255));
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
                            .addComponent(AppointmentID)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(cellLayout.createSequentialGroup()
                        .addComponent(PatientPhoneNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(delete)
                        .addGap(47, 47, 47))))
//                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        );
        cellLayout.setVerticalGroup(
            cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cellLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PatientNameField)
                 .addComponent(AppointmentID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PatientAddressField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGap(25,25,25)
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

        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        docname = new javax.swing.JLabel();
        home = new javax.swing.JButton();
        patientlist1 = new javax.swing.JButton();
        newpres = new javax.swing.JButton();
        medreq = new javax.swing.JButton();
        patientlist = new javax.swing.JLabel();
        uniqueid = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cell = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        accept = new javax.swing.JButton();
        decline = new javax.swing.JButton();
        name = new javax.swing.JLabel();
        address = new javax.swing.JLabel();
        phone = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel9.setText("Medic History");

        docname.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        docname.setText("DoctorName");

        home.setBackground(new java.awt.Color(102, 102, 255));
        home.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        home.setForeground(new java.awt.Color(255, 255, 255));
        home.setText("Home");
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });

        patientlist1.setBackground(new java.awt.Color(102, 102, 255));
        patientlist1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        patientlist1.setForeground(new java.awt.Color(255, 255, 255));
        patientlist1.setText("Appointment List");
        patientlist1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientlist1ActionPerformed(evt);
            }
        });

        newpres.setBackground(new java.awt.Color(102, 102, 255));
        newpres.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        newpres.setForeground(new java.awt.Color(255, 255, 255));
        newpres.setText("Med Request");
        newpres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newpresActionPerformed(evt);
            }
        });

        medreq.setBackground(new java.awt.Color(102, 102, 255));
        medreq.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        medreq.setForeground(new java.awt.Color(255, 255, 255));
        medreq.setText("Create New Prescription");
        medreq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medreqActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(docname)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(newpres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(medreq, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(patientlist1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel9)
                .addGap(64, 64, 64)
                .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(newpres, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(patientlist1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(medreq, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addComponent(docname)
                .addGap(28, 28, 28))
        );

        patientlist.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        patientlist.setText("Appointment List");

        uniqueid.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        uniqueid.setText("Unique User ID:DoctorID");

        jPanel1.setLayout(new java.awt.GridLayout(3, 3, 20, 20));

        cell.setBackground(new java.awt.Color(102, 102, 242));
        cell.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(242, 242, 242));
        jLabel1.setText("Name:");

        jLabel2.setFont(new java.awt.Font("Microsoft Tai Le", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(242, 242, 242));
        jLabel2.setText("Address:");

        jLabel3.setFont(new java.awt.Font("Microsoft Tai Le", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(242, 242, 242));
        jLabel3.setText("Phone No.:");

        accept.setFont(new java.awt.Font("Microsoft Tai Le", 1, 12)); // NOI18N
        accept.setForeground(new java.awt.Color(0, 204, 0));
        accept.setText("Accept");
        accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptActionPerformed(evt);
            }
        });

        decline.setFont(new java.awt.Font("Microsoft Tai Le", 1, 12)); // NOI18N
        decline.setForeground(new java.awt.Color(255, 0, 0));
        decline.setText("Decline");

        name.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        name.setForeground(new java.awt.Color(242, 242, 242));
        name.setText("Name");

        address.setFont(new java.awt.Font("Microsoft Tai Le", 1, 12)); // NOI18N
        address.setForeground(new java.awt.Color(242, 242, 242));
        address.setText("Address");

        phone.setFont(new java.awt.Font("Microsoft Tai Le", 1, 12)); // NOI18N
        phone.setForeground(new java.awt.Color(242, 242, 242));
        phone.setText("Phone");

        javax.swing.GroupLayout cellLayout = new javax.swing.GroupLayout(cell);
        cell.setLayout(cellLayout);
        cellLayout.setHorizontalGroup(
            cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cellLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cellLayout.createSequentialGroup()
                        .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cellLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
                                .addComponent(accept))
                            .addGroup(cellLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(decline)))
                        .addGap(16, 16, 16))
                    .addGroup(cellLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        cellLayout.setVerticalGroup(
            cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cellLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(cellLayout.createSequentialGroup()
                        .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(name))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(address)))
                    .addComponent(accept))
                .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cellLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(decline))
                    .addGroup(cellLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(cellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(phone))))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(patientlist)
                        .addGap(286, 286, 286)
                        .addComponent(uniqueid))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cell, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(patientlist, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(uniqueid, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        // TODO add your handling code here:
                DoctorPanelUI homePanel = new DoctorPanelUI(docID);

        // Show the patient panel GUI
//        System.out.println(doctorID);
        homePanel.setVisible(true);
System.out.println("hello3");
        // Dispose or hide the login GUI
        this.dispose();
    }//GEN-LAST:event_homeActionPerformed

    private void patientlist1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientlist1ActionPerformed
        // TODO add your handling code here:
//        System.out.println("hello");
//
//        try {
//            doctorpatientlistui(doctorID,name,email);
//        } catch (SQLException ex) {
//            Logger.getLogger(DoctorPanelUI.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_patientlist1ActionPerformed

    private void newpresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newpresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newpresActionPerformed

    private void medreqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medreqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_medreqActionPerformed

    private void acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acceptActionPerformed

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
    private javax.swing.JButton accept;
    private javax.swing.JLabel address;
    private javax.swing.JPanel cell;
    private javax.swing.JButton decline;
    private javax.swing.JLabel docname;
    private javax.swing.JButton home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JButton medreq;
    private javax.swing.JLabel name;
    private javax.swing.JButton newpres;
    private javax.swing.JLabel patientlist;
    private javax.swing.JButton patientlist1;
    private javax.swing.JLabel phone;
    private javax.swing.JLabel uniqueid;
    // End of variables declaration//GEN-END:variables
}