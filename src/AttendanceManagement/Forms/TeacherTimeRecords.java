
package AttendanceManagement.Forms;

import AttendanceManagement.Controller.AttendanceController;
import AttendanceManagement.Controller.WmpAttendanceController;
import AttendanceManagement.Model.ModelAttendance;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.raven.datechooser.DateChooser;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import raven.glasspanepopup.GlassPanePopup;
import java.sql.Time;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JOptionPane;
import raven.glasspanepopup.DefaultOption;
import raven.popup.component.SimplePopupBorder;


public class TeacherTimeRecords extends javax.swing.JPanel {
    private AttendanceController controller = new AttendanceController();
    private DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
     private DateChooser dc = new DateChooser();
    public TeacherTimeRecords() {
        initComponents();
        init();
    
        loadData();
         dc.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        dc.setTextField(date);
    }

    private void init(){
          centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
              for (int i = 0; i < employeesTable.getColumnCount(); i++) {
            employeesTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }    
           employeesTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "font:bold;"
                + "hoverBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "");
        employeesTable.putClientProperty(FlatClientProperties.STYLE, ""
                + "rowHeight:60;"
                + "showHorizontalLines:true;"
                + "intercellSpacing:0,1;"
                + "cellFocusColor:$TableHeader.hoverBackground;"
                + "selectionBackground:$TableHeader.hoverBackground;"
                + "selectionForeground:$Table.foreground;");
        
         searching.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
           searching.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("AttendanceManagement/Images_Icons/search.svg"));
          searching.putClientProperty(FlatClientProperties.STYLE, "");
    }
    
    public void loadData(){
        try {
            DefaultTableModel model = (DefaultTableModel)employeesTable.getModel();
            model.setRowCount(0);
            List<ModelAttendance> list = controller.getAll();
            for (ModelAttendance modelAttendance : list) {
                model.addRow(modelAttendance.toTableRow(employeesTable.getRowCount()));
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    } private void searchData(String search,Date date){
        try {
            DefaultTableModel model = (DefaultTableModel)employeesTable.getModel();
            model.setRowCount(0);
            List<ModelAttendance> list = controller.searchData(search, date);
            for (ModelAttendance modelAttendance : list) {
                model.addRow(modelAttendance.toTableRow(employeesTable.getRowCount()));
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeesTable = new javax.swing.JTable();
        searching = new javax.swing.JTextField();
        date = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        employeesTable.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        employeesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Full Name", "DepartMent", "Am Arrival", "Am Departure", "Pm Arrival", "Pm Departure", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        employeesTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        employeesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employeesTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(employeesTable);

        searching.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        searching.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchingKeyReleased(evt);
            }
        });

        date.setEditable(false);
        date.setBackground(new java.awt.Color(255, 255, 255));
        date.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(searching, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 699, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                    .addComponent(searching))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
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

    private void employeesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeesTableMouseClicked
       int selectedRow = employeesTable.getSelectedRow();

    if (selectedRow >= 0) {
        TableModel model = employeesTable.getModel();
        ModelAttendance attendanceData = new ModelAttendance();

        int empId = Integer.parseInt(getStringValue(model.getValueAt(selectedRow, 0)));

        // Set values to form fields
        attendanceData.setEmployeesID(empId);
        attendanceData.setDepartment(getStringValue(model.getValueAt(selectedRow, 2)));
        attendanceData.setEmployeesFullName(getStringValue(model.getValueAt(selectedRow, 1)));

        // Convert time strings to LocalTime
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm"); // Adjust format if needed
        
        attendanceData.setAmTimeIn(convertToLocalTime(getStringValue(model.getValueAt(selectedRow, 3)), timeFormatter));
        attendanceData.setAmTimeOut(convertToLocalTime(getStringValue(model.getValueAt(selectedRow, 4)), timeFormatter));
        attendanceData.setPmTimeIn(convertToLocalTime(getStringValue(model.getValueAt(selectedRow, 5)), timeFormatter));
        attendanceData.setPmTimeOut(convertToLocalTime(getStringValue(model.getValueAt(selectedRow, 6)), timeFormatter));
        
        WmpAttendanceUpdateForms wmpAttendanceUpdateForms = new WmpAttendanceUpdateForms();
        DefaultOption option = new DefaultOption() {
            @Override
            public boolean closeWhenPressedEsc() {
              return true;
            }
 
        };
        String action [] = new String[]{"Update","Cancel"};
       
        GlassPanePopup.showPopup(new SimplePopupBorder(wmpAttendanceUpdateForms, "Update Teacher Time Record", action, (pc,i)->{
         
          
            if (i ==0) {
                if (Confirmation()) {
                controller.UpdateData(wmpAttendanceUpdateForms.getData());
                loadData();
                }
              
                
            }else{
           GlassPanePopup.closePopupAll();
            pc.consume();
            }
          
            
        }),option);
        wmpAttendanceUpdateForms.setData(attendanceData);
       
    }
        
    }//GEN-LAST:event_employeesTableMouseClicked

    private void searchingKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchingKeyReleased
        if(searching.getText().isEmpty()) {
            loadData();
        }else{
             String dateData = date.getText();
         Date parsedDate = null;
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Specify your date format
        try {
            parsedDate = dateFormat.parse(dateData);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(TeacherTimeRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        searchData(searching.getText().trim(), parsedDate);
        }
    }//GEN-LAST:event_searchingKeyReleased
private String getStringValue(Object value) {
    return value == null ? "" : value.toString();
}
private LocalTime convertToLocalTime(String timeString, DateTimeFormatter formatter) {
    if (timeString == null || timeString.trim().isEmpty()) {
        // Handle empty or null timeString as needed
        return null; // Or return a default LocalTime, if applicable
    }
    try {
        return LocalTime.parse(timeString, formatter);
    } catch (DateTimeParseException e) {
        e.printStackTrace();
        return null; // Handle the error as needed
    }
}
  private boolean Confirmation(){
        int reply = JOptionPane.showConfirmDialog(this, "Are you sure you want to update this data?", "Confirmation", JOptionPane.YES_NO_OPTION);
    return reply == JOptionPane.YES_OPTION;
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField date;
    private javax.swing.JTable employeesTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searching;
    // End of variables declaration//GEN-END:variables
}
