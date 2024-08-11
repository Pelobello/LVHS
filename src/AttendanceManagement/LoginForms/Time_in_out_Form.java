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
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
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
       DepartMent.setVisible(false);
        setOpaque(false);
     
//        DepartMent.setVisible(false);
        SearchField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "ID#");
     
       new Thread(this::clock).start();

 SearchField.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("AttendanceManagement/Images_Icons/search.svg"));


    }
private void SearchEmployees(){
     int searchID = Integer.parseInt(SearchField.getText());      
        ModelEmployees employees = employeesController.SearchEmployees(searchID);        
        if (employees!=null) {    
            
            if (employees.getDepartment().equals("WMP I")||employees.getDepartment().equals("WMP II")||employees.getDepartment().equals("WMP III")) {
               JOptionPane.showMessageDialog(this, "WMP not available in this form. Hold Alt F2 for WMP logs");
                SearchField.requestFocus();
            }else{
                 String idStr = Integer.toString(employees.getId());
             FullName.setText(employees.getFirstName().toUpperCase()+" "+employees.getLastName().toUpperCase());
             employeesID.setText(idStr);
             EmployeesImage.setImage(employees.getEmployeesImage());
             DepartMent.setText(employees.getDepartment());
            repaint();
            revalidate();    
            }
                   
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
 private void am_pm_Arrival(){
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
             LocalTime morning = LocalTime.of(12, 00);
            
            ModelAttendance attendanceRecord = new ModelAttendance();
            attendanceRecord.setEmployeesImage(employeesImageProfile);
            attendanceRecord.setDepartment(department);
            attendanceRecord.setEmployeesID(empID);
            attendanceRecord.setEmployeesFullName(fullName);
            LocalTime formattedLocalTime = LocalTime.parse(formattedTime, dtf);
          
            String timeLabel = (currentTime.isBefore(noonTime)) ? "AM Arrival" : "PM Arrival";
            int confirmation = JOptionPane.showConfirmDialog(null, "Do you agree to record the " + timeLabel + " at " + formattedTime + "?", "Confirm Time In", JOptionPane.YES_NO_OPTION);
            
            if (DepartMent.getText().equals("WMP I")||DepartMent.getText().equals("WMP II")||DepartMent.getText().equals("WMP III")) {
                 if (confirmation == JOptionPane.YES_OPTION) {
                    if (currentTime.isBefore(morning)) {          
                    attendanceRecord.setAmTimeIn(formattedLocalTime);
                    attendanceController.amtimeIn(attendanceRecord);
                    SearchField.setText("");
                    SearchField.requestFocus();
                } else {  
                    attendanceRecord.setPmTimeIn(formattedLocalTime);
                    attendanceController.pmtimeIn(attendanceRecord);
                    SearchField.setText("");
                    SearchField.requestFocus();
                } 
                     
                 }
                
                
                
            }else{
                if (confirmation == JOptionPane.YES_OPTION) {
                if (currentTime.isBefore(noonTime)) {          
                    attendanceRecord.setAmTimeIn(formattedLocalTime);
                    attendanceController.amtimeIn(attendanceRecord);
                    SearchField.setText("");
                    SearchField.requestFocus();
                } else {  
                    attendanceRecord.setPmTimeIn(formattedLocalTime);
                    attendanceController.pmtimeIn(attendanceRecord);
                    SearchField.setText("");
                    SearchField.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Time In was not recorded.");
            } 
            }
           
        }
          } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid input for Employee ID. Please enter a valid numeric ID.");
        e.printStackTrace();
    
    
    } catch (Exception e) {
        e.printStackTrace();
    }
 }
   @Override
    public void addNotify() {
        super.addNotify();
        setupKeyBindings();
    }

 private void am_pm_Departure(){
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
                     SearchField.setText("");
                     SearchField.setFocusable(true);
                 SearchField.requestFocus();
                } else {  
                    attendanceRecord.setPmTimeOut(formattedLocalTime);
                    attendanceController.pmtimeOut(attendanceRecord);
                    SearchField.setText("");
               SearchField.requestFocus();
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
 }
   private void setupKeyBindings() {
        // Get the root pane's input map and action map
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getRootPane().getActionMap();

        // Define the key strokes for Alt + F1 and Alt + F2
        KeyStroke altF1 = KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.ALT_DOWN_MASK);
        KeyStroke altF2 = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.ALT_DOWN_MASK);


        // Map the key strokes to action keys
        inputMap.put(altF1, "timeIn");
        inputMap.put(altF2, "timeOut");

        // Define the actions
        actionMap.put("timeIn", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                     am_pm_Arrival();
             
                
            }
        });

        actionMap.put("timeOut", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                am_pm_Departure();
                
            }
        });
    }
    private Icon image;
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        arrival = new javax.swing.JButton();
        departure = new javax.swing.JButton();
        DepartMent = new javax.swing.JLabel();
        timeLbl = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        SearchField = new javax.swing.JTextField();
        EmployeesImage = new AttendanceManagement.Components.ImageBox();
        FullName = new javax.swing.JLabel();
        employeesID = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        imageBox1 = new AttendanceManagement.Components.ImageBox();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        arrival.setBackground(new java.awt.Color(0, 51, 84));
        arrival.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        arrival.setForeground(new java.awt.Color(255, 255, 255));
        arrival.setText("TIME IN");
        arrival.setToolTipText("Alt I (Time In)");
        arrival.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arrivalActionPerformed(evt);
            }
        });

        departure.setBackground(new java.awt.Color(0, 51, 84));
        departure.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        departure.setForeground(new java.awt.Color(255, 255, 255));
        departure.setText("TIME OUT");
        departure.setToolTipText("Alt O (Time Out)");
        departure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departureActionPerformed(evt);
            }
        });

        DepartMent.setText("JHS");

        timeLbl.setFont(new java.awt.Font("Segoe UI Emoji", 1, 35)); // NOI18N
        timeLbl.setForeground(new java.awt.Color(255, 255, 255));
        timeLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeLbl.setText("CLOCK");
        timeLbl.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jPanel2.setOpaque(false);

        SearchField.setBackground(new java.awt.Color(0, 51, 84));
        SearchField.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SearchField.setForeground(new java.awt.Color(255, 255, 255));
        SearchField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchFieldActionPerformed(evt);
            }
        });

        EmployeesImage.setImage(new javax.swing.ImageIcon(getClass().getResource("/AttendanceManagement/Images_Icons/profile.png"))); // NOI18N

        FullName.setFont(new java.awt.Font("Segoe UI", 1, 45)); // NOI18N
        FullName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FullName.setText("Juan Dela Cruz");

        employeesID.setFont(new java.awt.Font("Segoe UI", 1, 45)); // NOI18N
        employeesID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        employeesID.setText("ID");
        employeesID.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(SearchField, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                .addGap(37, 37, 37))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(EmployeesImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FullName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(employeesID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmployeesImage, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FullName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(employeesID, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        imageBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/AttendanceManagement/Images_Icons/LVHS_BANNER-nobackground.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imageBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 15, Short.MAX_VALUE)
                .addComponent(imageBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(676, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DepartMent, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(arrival, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(departure, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(652, 652, 652))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arrival, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(departure, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(timeLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DepartMent, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
       this.getRootPane().requestFocusInWindow();
    }//GEN-LAST:event_SearchFieldActionPerformed
   
    private void arrivalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arrivalActionPerformed
    am_pm_Arrival();
    }//GEN-LAST:event_arrivalActionPerformed

    private void departureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departureActionPerformed
 am_pm_Departure();
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
    private AttendanceManagement.Components.ImageBox imageBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel timeLbl;
    // End of variables declaration//GEN-END:variables
}
