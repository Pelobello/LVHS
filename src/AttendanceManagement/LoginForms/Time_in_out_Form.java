/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package AttendanceManagement.LoginForms;

import AttendanceManagement.Controller.AttendanceController;
import AttendanceManagement.Controller.EmployeesController;
import AttendanceManagement.Model.ModelAttendance;
import AttendanceManagement.Model.ModelEmployees;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static org.apache.xmlbeans.impl.schema.StscState.start;

/**
 *
 * @author USER
 */
public class Time_in_out_Form extends javax.swing.JPanel {

     private EmployeesController employeesController = new EmployeesController();
  private AttendanceController attendanceController = new AttendanceController();
    public Time_in_out_Form() {
        initComponents();
        
        setOpaque(false);
        DepartMent.setVisible(false);
        SearchField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "ID#");
     
       new Thread(this::clock).start();

 SearchField.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("AttendanceManagement/Images_Icons/search.svg"));
 

    }
private void SearchEmployees(){
     int searchID = Integer.parseInt(SearchField.getText());      
        ModelEmployees employees = employeesController.SearchEmployees(searchID);        
        if (employees!=null) {           
            String idStr = Integer.toString(employees.getId());
             FullName.setText(employees.getFirstName()+" "+employees.getLastName());
             employeesID.setText(idStr);
             EmployeesImage.setImage(employees.getEmployeesImage());
             DepartMent.setText(employees.getDepartment());
            repaint();
            revalidate();            
        }
}
private void srchFieldScn(){
       if (SearchField.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please input EmployeesID");
    } else {
        try {
            SearchEmployees();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid EmployeesID format. Please enter a valid number.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while searching for the employee.");
        }
    }
}
private void defaultComponents(){
    SearchField.setText("");
    FullName.setText("Juan Dela Cruz");
    employeesID.setText("ID");
    EmployeesImage.setImage(new ImageIcon(getClass().getResource("/AttendanceManagement/Images_Icons/profile.png")));
}
public void clock(){
    while (true) {        
   
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss aa");
    SimpleDateFormat date = new SimpleDateFormat("MMMM dd");
    SimpleDateFormat day = new SimpleDateFormat("EEEE");
    
   String time = sdf.format(Calendar.getInstance().getTime());
   String todaysDate = date.format(Calendar.getInstance().getTime());
   String todaysDay = day.format(Calendar.getInstance().getTime());
    
    timeLbl.setText("<html><center>" + time + "<br>" + todaysDay +", " + todaysDate +"</center></html>");
        try {
            Thread.sleep(1000);
            
        } catch (InterruptedException ex) {
           ex.printStackTrace();
        }
    } 
}
  public Icon getImage() {
        return image;
    }

    public void setImage(Icon image) {
        this.image = image;
        repaint();
    }

    private Icon image;
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        EmployeesImage = new AttendanceManagement.Components.ImageBox();
        SearchField = new javax.swing.JTextField();
        arrival = new javax.swing.JButton();
        departure = new javax.swing.JButton();
        employeesID = new javax.swing.JLabel();
        FullName = new javax.swing.JLabel();
        DepartMent = new javax.swing.JLabel();
        timeLbl = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        EmployeesImage.setImage(new javax.swing.ImageIcon(getClass().getResource("/AttendanceManagement/Images_Icons/profile.png"))); // NOI18N

        SearchField.setBackground(new java.awt.Color(0, 51, 84));
        SearchField.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        SearchField.setForeground(new java.awt.Color(255, 255, 255));
        SearchField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchFieldActionPerformed(evt);
            }
        });

        arrival.setBackground(new java.awt.Color(0, 51, 84));
        arrival.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        arrival.setForeground(new java.awt.Color(255, 255, 255));
        arrival.setText("TIME IN");
        arrival.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arrivalActionPerformed(evt);
            }
        });

        departure.setBackground(new java.awt.Color(0, 51, 84));
        departure.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        departure.setForeground(new java.awt.Color(255, 255, 255));
        departure.setText("TIME OUT");
        departure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departureActionPerformed(evt);
            }
        });

        employeesID.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        employeesID.setForeground(new java.awt.Color(255, 255, 255));
        employeesID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        employeesID.setText("ID");

        FullName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        FullName.setForeground(new java.awt.Color(255, 255, 255));
        FullName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FullName.setText("Juan Dela Cruz");

        DepartMent.setText("JHS");

        timeLbl.setFont(new java.awt.Font("Segoe UI Emoji", 1, 22)); // NOI18N
        timeLbl.setForeground(new java.awt.Color(255, 255, 255));
        timeLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeLbl.setText("CLOCK");
        timeLbl.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(498, 498, 498)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(DepartMent, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(timeLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(arrival, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                                .addGap(30, 30, 30)
                                .addComponent(departure, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                            .addComponent(employeesID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(FullName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SearchField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EmployeesImage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(498, 498, 498))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(EmployeesImage, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FullName, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(employeesID, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arrival, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(departure, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(timeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DepartMent, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchFieldActionPerformed
       srchFieldScn();
    }//GEN-LAST:event_SearchFieldActionPerformed

    private void arrivalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arrivalActionPerformed
      try {
        if (employeesID.getText().equals("ID")) {
            JOptionPane.showMessageDialog(null, "Invalid ID");
        } else {
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
            String formattedTime = currentTime.format(dtf);
            int currentHour = currentTime.getHour();
            int empID = Integer.parseInt(employeesID.getText());
            Icon employeesImageProfile = EmployeesImage.getImage();
            String department = DepartMent.getText();
            String fullName = FullName.getText();
            
             LocalTime noonTime = LocalTime.of(12, 30);
            
            ModelAttendance attendanceRecord = new ModelAttendance();
            attendanceRecord.setEmployeesImage(employeesImageProfile);
            attendanceRecord.setDepartment(department);
            attendanceRecord.setEmployeesID(empID);
            attendanceRecord.setEmployeesFullName(fullName);
            LocalTime formattedLocalTime = LocalTime.parse(formattedTime, dtf);
          
            String timeLabel = (currentTime.isBefore(noonTime)) ? "AM Arrival" : "PM Arrival";
            int confirmation = JOptionPane.showConfirmDialog(null, "Do you agree to record the " + timeLabel + " at " + formattedTime + "?", "Confirm Time In", JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                if (currentTime.isBefore(noonTime)) {          
                    attendanceRecord.setAmTimeIn(formattedLocalTime);
                    attendanceController.amtimeIn(attendanceRecord);
                    defaultComponents();
                } else {  
                    attendanceRecord.setPmTimeIn(formattedLocalTime);
                    attendanceController.pmtimeIn(attendanceRecord);
                    defaultComponents();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Time In was not recorded.");
            }
        }
          } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid input for Employee ID. Please enter a valid numeric ID.");
        e.printStackTrace();
    
    
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_arrivalActionPerformed

    private void departureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departureActionPerformed
         try {
        if (employeesID.getText().equals("ID")) {
            JOptionPane.showMessageDialog(null, "Invalid ID");
        } else {
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
            String formattedTime = currentTime.format(dtf);
            int currentHour = currentTime.getHour();
            int empID = Integer.parseInt(employeesID.getText());
            Icon employeesImageProfile = EmployeesImage.getImage();
            String department = DepartMent.getText();
            String fullName = FullName.getText();
  
             LocalTime noonTime = LocalTime.of(12, 30);
            
            ModelAttendance attendanceRecord = new ModelAttendance();
            attendanceRecord.setEmployeesImage(employeesImageProfile);
            attendanceRecord.setDepartment(department);
            attendanceRecord.setEmployeesID(empID);
            attendanceRecord.setEmployeesFullName(fullName);
            LocalTime formattedLocalTime = LocalTime.parse(formattedTime, dtf);

            String timeLabel = (currentTime.isBefore(noonTime)) ? "AM Departure" : "PM Departure";
            int confirmation = JOptionPane.showConfirmDialog(null, "Do you agree to record the " + timeLabel + " at " + formattedTime + "?", "Confirm Time In", JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                if (currentTime.isBefore(noonTime)) {          
                    attendanceRecord.setAmTimeOut(formattedLocalTime);
                    attendanceController.amtimeOut(attendanceRecord);
                 defaultComponents();
                } else {  
                    attendanceRecord.setPmTimeOut(formattedLocalTime);
                    attendanceController.pmtimeOut(attendanceRecord);
                   defaultComponents();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Time In was not recorded.");
            }
        }
           } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid input for Employee ID. Please enter a valid numeric ID.");
        e.printStackTrace();
    
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_departureActionPerformed

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        if (image != null) {
            Rectangle size = getAutoSize(image);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(toImage(image), size.getLocation().x, size.getLocation().y, size.getSize().width, size.getSize().height, null);
        }
        int width = getWidth();
        int height = getHeight();
        Area area = new Area(new Rectangle2D.Double(0, 0, width, height));
        Rectangle rec = jPanel1.getBounds();
        rec.setLocation(rec.x + 1, rec.y + 1);
        rec.setSize(rec.width - 1, rec.height - 1);
        area.subtract(new Area(rec));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2.setColor(new Color(160, 160, 160));
        g2.fill(area);
        g2.dispose();
        super.paintComponent(grphcs);
    }

    private Rectangle getAutoSize(Icon image) {
        int w = getWidth();
        int h = getHeight();
        if (w > image.getIconWidth()) {
            w = image.getIconWidth();
        }
        if (h > image.getIconHeight()) {
            h = image.getIconHeight();
        }
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double) w / iw;
        double yScale = (double) h / ih;
        double scale = Math.max(xScale, yScale);
        int width = (int) (scale * iw);
        int height = (int) (scale * ih);
        int x = getWidth() / 2 - (width / 2);
        int y = getHeight() / 2 - (height / 2);
        return new Rectangle(new Point(x, y), new Dimension(width, height));
    }

    private Image toImage(Icon icon) {
        return ((ImageIcon) icon).getImage();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DepartMent;
    private AttendanceManagement.Components.ImageBox EmployeesImage;
    private javax.swing.JLabel FullName;
    private javax.swing.JTextField SearchField;
    private javax.swing.JButton arrival;
    private javax.swing.JButton departure;
    private javax.swing.JLabel employeesID;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel timeLbl;
    // End of variables declaration//GEN-END:variables
}
