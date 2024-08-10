/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package AttendanceManagement.LoginForms;

import AttendanceManagement.Controller.AttendanceController;
import AttendanceManagement.Controller.EmployeesController;
import AttendanceManagement.Controller.WmpAttendanceController;
import AttendanceManagement.Model.ModelAttendance;
import AttendanceManagement.Model.ModelEmployees;
import AttendanceManagement.Model.WmpModel;
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
public class WmpAttendanceLogs extends javax.swing.JPanel {

     private EmployeesController employeesController = new EmployeesController();
  private AttendanceController attendanceController = new AttendanceController();
    public WmpAttendanceLogs() {
        initComponents();
       
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
            
            if (employees.getDepartment().equals("SHS")||employees.getDepartment().equals("JHS")||employees.getDepartment().equals("NTP")) {
               JOptionPane.showMessageDialog(this, "SHS,JHS,NTP not available in this form. Hold Alt F1 for WMP logs");
                SearchField.requestFocus();
            }else{
                 String idStr = Integer.toString(employees.getId());
             nameTxtField.setText(employees.getFirstName().toUpperCase()+" "+employees.getLastName().toUpperCase());
             idTxtField.setText(idStr);
             EmployeesImage.setImage(employees.getEmployeesImage());
             departmentTxtField.setText(employees.getDepartment());
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
    nameTxtField.setText("Juan Dela Cruz");
    idTxtField.setText("ID");
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
 
   @Override
    public void addNotify() {
        super.addNotify();
        setupKeyBindings();
    }

      private WmpModel createAttendanceModel() {
    int employeesID = Integer.parseInt(idTxtField.getText());
    String fullname = nameTxtField.getText();
    String department = departmentTxtField.getText();
    LocalTime currentTime = LocalTime.now();
    
    // Extract hour and minute
    int hour = currentTime.getHour();
    int minute = currentTime.getMinute();
    
    // Create a LocalTime object with seconds set to zero
    LocalTime formattedTime = LocalTime.of(hour, minute, 0);
    
    // Optionally, format the time as a string if needed
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
    String formattedTimeString = formattedTime.format(dtf);
    
    // Use formattedTime in your WmpModel
    return new WmpModel(employeesID, fullname, department, formattedTime, formattedTime, formattedTime, formattedTime);
}
         private boolean confirmTimeAction(String action) {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter dtf12Hour = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = currentTime.format(dtf12Hour);

        int result = JOptionPane.showConfirmDialog(this,
                "Do you want to record " + action + " at " + formattedTime + "?",
                "Confirm " + action,
                JOptionPane.YES_NO_OPTION);
        
        return result == JOptionPane.YES_OPTION;
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
                  
             
                
            }
        });

        actionMap.put("timeOut", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
                
            }
        });
    }
    private Icon image;
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        amTimeIn = new javax.swing.JButton();
        amTimeOut = new javax.swing.JButton();
        departmentTxtField = new javax.swing.JLabel();
        timeLbl = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        SearchField = new javax.swing.JTextField();
        EmployeesImage = new AttendanceManagement.Components.ImageBox();
        nameTxtField = new javax.swing.JLabel();
        idTxtField = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        imageBox1 = new AttendanceManagement.Components.ImageBox();
        pmTimein = new javax.swing.JButton();
        pmTimeOut = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        amTimeIn.setBackground(new java.awt.Color(0, 51, 84));
        amTimeIn.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        amTimeIn.setForeground(new java.awt.Color(255, 255, 255));
        amTimeIn.setText("AM TIME IN");
        amTimeIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amTimeInActionPerformed(evt);
            }
        });

        amTimeOut.setBackground(new java.awt.Color(0, 51, 84));
        amTimeOut.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        amTimeOut.setForeground(new java.awt.Color(255, 255, 255));
        amTimeOut.setText("AM TIME OUT");
        amTimeOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amTimeOutActionPerformed(evt);
            }
        });

        departmentTxtField.setText("JHS");

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

        nameTxtField.setFont(new java.awt.Font("Segoe UI", 1, 45)); // NOI18N
        nameTxtField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameTxtField.setText("Juan Dela Cruz");

        idTxtField.setFont(new java.awt.Font("Segoe UI", 1, 45)); // NOI18N
        idTxtField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        idTxtField.setText("ID");
        idTxtField.setVerticalAlignment(javax.swing.SwingConstants.TOP);

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
                .addComponent(nameTxtField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(idTxtField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmployeesImage, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nameTxtField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

        pmTimein.setBackground(new java.awt.Color(0, 51, 84));
        pmTimein.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        pmTimein.setForeground(new java.awt.Color(255, 255, 255));
        pmTimein.setText("PM TIME IN");
        pmTimein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmTimeinActionPerformed(evt);
            }
        });

        pmTimeOut.setBackground(new java.awt.Color(0, 51, 84));
        pmTimeOut.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        pmTimeOut.setForeground(new java.awt.Color(255, 255, 255));
        pmTimeOut.setText("PM TIME OUT");
        pmTimeOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmTimeOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(678, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(departmentTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1136, 1136, 1136))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(timeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(amTimeIn, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(amTimeOut, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(pmTimein, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(pmTimeOut, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(642, 642, 642))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amTimeIn, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amTimeOut, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pmTimein, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pmTimeOut, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(departmentTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchFieldActionPerformed
       srchFieldScn();
       this.getRootPane().requestFocusInWindow();
    }//GEN-LAST:event_SearchFieldActionPerformed
   
    private void amTimeInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amTimeInActionPerformed
    if (confirmTimeAction("AM Time In")) {
            WmpAttendanceController attendanceController = new WmpAttendanceController();
            WmpModel attendance = createAttendanceModel();
            attendanceController.amTimeIn(attendance);
        }
    }//GEN-LAST:event_amTimeInActionPerformed

    private void amTimeOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amTimeOutActionPerformed
 if (confirmTimeAction("AM Time In")) {
            WmpAttendanceController attendanceController = new WmpAttendanceController();
            WmpModel attendance = createAttendanceModel();
            attendanceController.amTimeOut(attendance);
        }
    }//GEN-LAST:event_amTimeOutActionPerformed

    private void pmTimeinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmTimeinActionPerformed
       if (confirmTimeAction("AM Time In")) {
            WmpAttendanceController attendanceController = new WmpAttendanceController();
            WmpModel attendance = createAttendanceModel();
            attendanceController.pmTimeIn(attendance);
        }
    }//GEN-LAST:event_pmTimeinActionPerformed

    private void pmTimeOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmTimeOutActionPerformed
       if (confirmTimeAction("AM Time In")) {
            WmpAttendanceController attendanceController = new WmpAttendanceController();
            WmpModel attendance = createAttendanceModel();
            attendanceController.pmTimeOut(attendance);
        }
    }//GEN-LAST:event_pmTimeOutActionPerformed

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
    private AttendanceManagement.Components.ImageBox EmployeesImage;
    private javax.swing.JTextField SearchField;
    private javax.swing.JButton amTimeIn;
    private javax.swing.JButton amTimeOut;
    private javax.swing.JLabel departmentTxtField;
    private javax.swing.JLabel idTxtField;
    private AttendanceManagement.Components.ImageBox imageBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel nameTxtField;
    private javax.swing.JButton pmTimeOut;
    private javax.swing.JButton pmTimein;
    private javax.swing.JLabel timeLbl;
    // End of variables declaration//GEN-END:variables
}
