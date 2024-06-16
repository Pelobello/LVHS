/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package AttendanceManagement.Forms;

import AttendanceManagement.Controller.AttendanceController;
import AttendanceManagement.Model.ModelAttendance;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import java.text.SimpleDateFormat;
import java.sql.Time;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;

public class AttendanceUpdateForms extends javax.swing.JPanel {
    private EmployeesDataForms dataForms;
    private AttendanceController controller = new AttendanceController();
    public AttendanceUpdateForms(EmployeesDataForms dataForms) {
        initComponents();
        this.dataForms = dataForms;

            amArrivalTP.set24hourMode(true);
            amDepartureTP.set24hourMode(true);
            pmArrivalTP.set24hourMode(true);
            pmDepartureTP.set24hourMode(true);
            amArrival.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("AttendanceManagement/Images_Icons/clock.svg"));
             amDeparture.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("AttendanceManagement/Images_Icons/clock.svg"));
              pmArrival.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("AttendanceManagement/Images_Icons/clock.svg"));
               pmDeparture.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("AttendanceManagement/Images_Icons/clock.svg"));

    }
    
      private boolean Confirmation(){
        int reply = JOptionPane.showConfirmDialog(this, "Do you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
    return reply == JOptionPane.YES_OPTION;
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        amArrivalTP = new com.raven.swing.TimePicker();
        amDepartureTP = new com.raven.swing.TimePicker();
        pmArrivalTP = new com.raven.swing.TimePicker();
        pmDepartureTP = new com.raven.swing.TimePicker();
        jPanel1 = new javax.swing.JPanel();
        id = new javax.swing.JLabel();
        fname = new javax.swing.JLabel();
        department = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        amArrival = new javax.swing.JTextField();
        amDeparture = new javax.swing.JTextField();
        pmArrival = new javax.swing.JTextField();
        pmDeparture = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        id.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        id.setText("0");

        fname.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        fname.setText("Full Name");

        department.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        department.setText("DepartMent");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AttendanceManagement/Images_Icons/icons8_update_35px.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        amArrival.setEditable(false);
        amArrival.setBackground(new java.awt.Color(255, 255, 255));
        amArrival.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        amArrival.setForeground(new java.awt.Color(51, 51, 51));
        amArrival.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        amArrival.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Am Arrival", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 102, 102))); // NOI18N
        amArrival.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                amArrivalMouseClicked(evt);
            }
        });
        amArrival.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amArrivalActionPerformed(evt);
            }
        });

        amDeparture.setEditable(false);
        amDeparture.setBackground(new java.awt.Color(255, 255, 255));
        amDeparture.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        amDeparture.setForeground(new java.awt.Color(51, 51, 51));
        amDeparture.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        amDeparture.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Am Departure", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 102, 102))); // NOI18N
        amDeparture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                amDepartureMouseClicked(evt);
            }
        });

        pmArrival.setEditable(false);
        pmArrival.setBackground(new java.awt.Color(255, 255, 255));
        pmArrival.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        pmArrival.setForeground(new java.awt.Color(51, 51, 51));
        pmArrival.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pmArrival.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pm Arrival", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 102, 102))); // NOI18N
        pmArrival.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pmArrivalMouseClicked(evt);
            }
        });

        pmDeparture.setEditable(false);
        pmDeparture.setBackground(new java.awt.Color(255, 255, 255));
        pmDeparture.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        pmDeparture.setForeground(new java.awt.Color(51, 51, 51));
        pmDeparture.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pmDeparture.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pm Departure", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(0, 102, 102))); // NOI18N
        pmDeparture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pmDepartureMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(department, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(amArrival, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(amDeparture, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pmArrival, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pmDeparture, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(department, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amArrival, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pmArrival, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pmDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  
       if (Confirmation()) {
        try {
            ModelAttendance attendance = new ModelAttendance();
            int idData = Integer.parseInt(id.getText());
            
            attendance.setAmTimeIn(parseTime(amArrival.getText()));
            attendance.setAmTimeOut(parseTime(amDeparture.getText()));
            attendance.setPmTimeIn(parseTime(pmArrival.getText()));
            attendance.setPmTimeOut(parseTime(pmDeparture.getText()));
            
            attendance.setEmployeesID(idData);
            controller.UpdateData(attendance);
            dataForms.loadData();
        } catch (NumberFormatException e) {
            // Handle the case where id is not a valid integer
            JOptionPane.showMessageDialog(null, "Invalid ID format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (DateTimeParseException e) {
            // Handle the case where time strings are not valid times
            JOptionPane.showMessageDialog(null, "Invalid time format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Handle all other exceptions
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void amArrivalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amArrivalActionPerformed
     
    }//GEN-LAST:event_amArrivalActionPerformed

    private void amArrivalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_amArrivalMouseClicked
         amArrivalTP.setDisplayText(amArrival);
        amArrivalTP.showPopup(this, 400, 100);
    }//GEN-LAST:event_amArrivalMouseClicked

    private void amDepartureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_amDepartureMouseClicked
         amDepartureTP.setDisplayText(amDeparture);
        amDepartureTP.showPopup(this, 400, 100);
    }//GEN-LAST:event_amDepartureMouseClicked

    private void pmArrivalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pmArrivalMouseClicked
         pmArrivalTP.setDisplayText(pmArrival);
        pmArrivalTP.showPopup(this, 400, 100);
    }//GEN-LAST:event_pmArrivalMouseClicked

    private void pmDepartureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pmDepartureMouseClicked
       pmDepartureTP.setDisplayText(pmDeparture);
        pmDepartureTP.showPopup(this, 400, 100);
    }//GEN-LAST:event_pmDepartureMouseClicked

private LocalTime parseTime(String timeStr) {
    return timeStr.isEmpty() ? null : LocalTime.parse(timeStr);
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField amArrival;
    private com.raven.swing.TimePicker amArrivalTP;
    public javax.swing.JTextField amDeparture;
    private com.raven.swing.TimePicker amDepartureTP;
    public javax.swing.JLabel department;
    public javax.swing.JLabel fname;
    public javax.swing.JLabel id;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField pmArrival;
    private com.raven.swing.TimePicker pmArrivalTP;
    public javax.swing.JTextField pmDeparture;
    private com.raven.swing.TimePicker pmDepartureTP;
    // End of variables declaration//GEN-END:variables
}
