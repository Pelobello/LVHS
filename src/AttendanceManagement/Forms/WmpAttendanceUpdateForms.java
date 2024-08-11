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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;

public class WmpAttendanceUpdateForms extends javax.swing.JPanel {

   
    private AttendanceController controller = new AttendanceController();
    public WmpAttendanceUpdateForms() {
        initComponents();
     
id.setVisible(false);
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
        int reply = JOptionPane.showConfirmDialog(this, "Are you sure you want to update this data?", "Confirmation", JOptionPane.YES_NO_OPTION);
    return reply == JOptionPane.YES_OPTION;
        
    }
     public void setData(ModelAttendance data) {
    this.data = data;
    String wmpID = Integer.toString(data.getEmployeesID());
    id.setText(wmpID);
    fname.setText(data.getEmployeesFullName());
    department.setText(data.getDepartment());

    // Handle null values gracefully
    amArrival.setText(data.getAmTimeIn() != null ? data.getAmTimeIn().toString() : "");
    amDeparture.setText(data.getAmTimeOut() != null ? data.getAmTimeOut().toString() : "");
    pmArrival.setText(data.getPmTimeIn() != null ? data.getPmTimeIn().toString() : "");
    pmDeparture.setText(data.getPmTimeOut() != null ? data.getPmTimeOut().toString() : "");
}
          public ModelAttendance getData() {
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
              int wmpID = Integer.parseInt(id.getText());
            data.setEmployeesID(wmpID);
            data.setEmployeesFullName(fname.getText());
            data.setDepartment(department.getText());
            data.setAmTimeIn(convertToLocalTime(amArrival.getText(), timeFormatter));
            data.setAmTimeOut(convertToLocalTime(amDeparture.getText(), timeFormatter));
            data.setPmTimeIn(convertToLocalTime(pmArrival.getText(), timeFormatter));
            data.setPmTimeOut(convertToLocalTime(pmDeparture.getText(), timeFormatter));
            
            return data;
    }
     private ModelAttendance data;
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(amArrival)
                    .addComponent(amDeparture)
                    .addComponent(pmArrival)
                    .addComponent(pmDeparture)
                    .addComponent(id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(department, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

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

private LocalTime convertToLocalTime(String timeString, DateTimeFormatter formatter) {
    if (timeString == null || timeString.trim().isEmpty()) {
      
        return null; 
    }
    try {
        return LocalTime.parse(timeString, formatter);
    } catch (DateTimeParseException e) {
        e.printStackTrace();
        return null; // Handle the error as needed
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField amArrival;
    private com.raven.swing.TimePicker amArrivalTP;
    public javax.swing.JTextField amDeparture;
    private com.raven.swing.TimePicker amDepartureTP;
    public javax.swing.JLabel department;
    public javax.swing.JLabel fname;
    public javax.swing.JLabel id;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField pmArrival;
    private com.raven.swing.TimePicker pmArrivalTP;
    public javax.swing.JTextField pmDeparture;
    private com.raven.swing.TimePicker pmDepartureTP;
    // End of variables declaration//GEN-END:variables
}
