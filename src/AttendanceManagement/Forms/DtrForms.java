
package AttendanceManagement.Forms;

import AttendanceManagement.Controller.DtrController;
import AttendanceManagement.Controller.EmployeesController;
import AttendanceManagement.Main.Main;
import AttendanceManagement.Model.ModelDtr;
import AttendanceManagement.Model.ModelEmployees;
import AttendanceManagement.ModelRecords.FieldReportAttendance;
import AttendanceManagement.ModelRecords.ParamenterAttendance;
import AttendanceManagement.print.ReportManager;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import jnafilechooser.api.JnaFileChooser;

/**
 *
 * @author USER
 */
public class DtrForms extends javax.swing.JPanel {

    private EmployeesController employeesController = new EmployeesController();
    private DtrController dtrController = new DtrController();
   
   
    public DtrForms() {
        
        initComponents();
        setOpaque(false);
       
        init();
        testReport();
         year_monthDATA();
       
    }
private void init(){

        panelTable.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:10;"
                + "");
       
        dtrTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "font:bold;"
                + "hoverBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "");
        dtrTable.putClientProperty(FlatClientProperties.STYLE, ""
                + "rowHeight:60;"
                + "showHorizontalLines:true;"
                + "intercellSpacing:0,1;"
                + "cellFocusColor:$TableHeader.hoverBackground;"
                + "selectionBackground:$TableHeader.hoverBackground;"
                + "selectionForeground:$Table.foreground;");
           
           SearchField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Employee's ID");
           Principal.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Principal's Full Name");
           SearchField.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("AttendanceManagement/Images_Icons/search.svg"));
        
}

private TableCellRenderer getAlignmentCellRenderer(TableCellRenderer oldRender, boolean header) {
    return new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component com = oldRender.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (com instanceof JLabel) {
                JLabel label = (JLabel) com;
                label.setForeground(Color.BLACK); // default color

                setAlignment(label, column);

                if (!header) {
                    if (column == 1) {
                        try {
                            Date cellTime = parseTime(value.toString());
                            Color color = getThresholdColor(cellTime);
                            label.setForeground(color);
                        } catch (ParseException e) {
                            label.setForeground(Color.BLACK); // default color
                        }
                    }
                     if (column == 3) {
                       try {
                            Date cellTime = parseTime(value.toString());
                            Date thresholdTime = parseTime("1:01 PM");
                            if (cellTime.before(thresholdTime)) {
                                label.setForeground(new Color(17, 182, 62)); // green color
                            } else {
                                label.setForeground(Color.RED); // red color
                            }
                        } catch (ParseException e) {
                            label.setForeground(Color.BLACK); // default color
                        }
                    }
                }
            }
            return com;
        }
    };
            }

  private void setAlignment(JLabel label, int column) {
            if (column == 0 || column == 7 || column == 3 || column == 6) {
                label.setHorizontalAlignment(SwingConstants.CENTER);
            } else {
                label.setHorizontalAlignment(SwingConstants.CENTER);
            }
        }

        private Date parseTime(String time) throws ParseException {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            return sdf.parse(time);
        }

        private Color getThresholdColor(Date cellTime) throws ParseException {
            Date thresholdTime;
            if (DepartMent.getText().equals("JHS") || DepartMent.getText().equals("SHS")) {
                thresholdTime = parseTime("7:30 AM");
            } else if (DepartMent.getText().equals("NTP")) {
                thresholdTime = parseTime("8:00 AM");
            } else {
                throw new RuntimeException("Unknown department");
            }

            if (cellTime.before(thresholdTime) || cellTime.equals(thresholdTime)) {
                return new Color(17, 182, 62); // green color
            } else {
                return Color.RED; // red color
            }
        }
 

private void year_monthDATA(){
     Calendar c = Calendar.getInstance();
    int current_year = c.get(Calendar.YEAR);
    int current_month = c.get(Calendar.MONTH); // Note: Months are 0-based (January is 0)
    // Clear existing items
    Year.removeAllItems();
    Month.removeAllItems();

    // Add years from 2015 to the current year
    for (int year = 2015; year <= current_year; year++) {
        String yearStr = Integer.toString(year);
        Year.addItem(yearStr);
    }

    // Set the selected item to the current year
    Year.setSelectedItem(Integer.toString(current_year));

    // Add months to the Month combobox
    String[] months = {
        "January", "February", "March", "April", "May", "June", 
        "July", "August", "September", "October", "November", "December"
    };

    for (String month : months) {
        Month.addItem(month);
    }
    // Set the selected item to the current month
    Month.setSelectedItem(months[current_month]);  
}

private void setTxtFieldDefault(){
      FullName.setText("Juan Dela Cruz");
      employeesID.setText("123456");
      DepartMent.setText("SHS");
      Icon defaultImage = new ImageIcon(getClass().getResource("/AttendanceManagement/Images_Icons/profile.png"));
      EmployeesImage.setImage(defaultImage);
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
        }else{
          setTxtFieldDefault();
        }
}
private void srchFieldScn(){
       if (SearchField.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please input EmployeesID");
        setTxtFieldDefault();
    } else {
        try {
            SearchEmployees();
             srcDtr();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid EmployeesID format. Please enter a valid number.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while searching for the employee.");
        }
    }
}
private void srcDtr(){
    int empIDstr = Integer.parseInt(SearchField.getText());
    int yearStr = Integer.parseInt((String) Year.getSelectedItem());
    
      ModelDtr modelDtr = new ModelDtr(empIDstr, (String) Month.getSelectedItem(), yearStr);
    try {
        dtrController.populateDtr(dtrTable, modelDtr);
         dtrTable.getTableHeader().setDefaultRenderer(getAlignmentCellRenderer(dtrTable.getTableHeader().getDefaultRenderer(), true));
         dtrTable.setDefaultRenderer(Object.class,getAlignmentCellRenderer(dtrTable.getDefaultRenderer(Object.class), false));
        
    } catch (Exception e) {
        e.printStackTrace();
    }   
}
private void testReport(){
    try {
        ReportManager.getInstance().compileReport();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dtrTable = new javax.swing.JTable();
        Month = new javax.swing.JComboBox<>();
        Year = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        EmployeesImage = new AttendanceManagement.Components.ImageBox();
        FullName = new javax.swing.JLabel();
        employeesID = new javax.swing.JLabel();
        DepartMent = new javax.swing.JLabel();
        view = new javax.swing.JButton();
        SearchField = new javax.swing.JTextField();
        Principal = new javax.swing.JTextField();
        pdf = new javax.swing.JButton();
        excel = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1145, 659));

        panelTable.setBackground(new java.awt.Color(255, 255, 255));

        dtrTable.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        dtrTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Day", "Arrival", "Departure", "Arrival", "Departure", "Total Working Hours", "UnderTime", "OverTime"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(dtrTable);
        if (dtrTable.getColumnModel().getColumnCount() > 0) {
            dtrTable.getColumnModel().getColumn(0).setResizable(false);
            dtrTable.getColumnModel().getColumn(0).setPreferredWidth(25);
            dtrTable.getColumnModel().getColumn(1).setResizable(false);
            dtrTable.getColumnModel().getColumn(2).setResizable(false);
            dtrTable.getColumnModel().getColumn(3).setResizable(false);
            dtrTable.getColumnModel().getColumn(4).setResizable(false);
            dtrTable.getColumnModel().getColumn(5).setResizable(false);
            dtrTable.getColumnModel().getColumn(6).setResizable(false);
            dtrTable.getColumnModel().getColumn(7).setResizable(false);
        }

        Month.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        Month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MonthActionPerformed(evt);
            }
        });

        Year.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2014" }));
        Year.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YearActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        EmployeesImage.setImage(new javax.swing.ImageIcon(getClass().getResource("/AttendanceManagement/Images_Icons/profile.png"))); // NOI18N

        FullName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        FullName.setForeground(new java.awt.Color(102, 102, 102));
        FullName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        FullName.setText("Juan Dela Cruz");

        employeesID.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        employeesID.setForeground(new java.awt.Color(102, 102, 102));
        employeesID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        employeesID.setText("123456");

        DepartMent.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        DepartMent.setForeground(new java.awt.Color(102, 102, 102));
        DepartMent.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        DepartMent.setText("SHS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(FullName, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DepartMent, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(employeesID, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmployeesImage, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EmployeesImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(FullName, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(employeesID, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DepartMent, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        view.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        view.setText("View");
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });

        SearchField.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        SearchField.setForeground(new java.awt.Color(102, 102, 102));
        SearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchFieldActionPerformed(evt);
            }
        });

        Principal.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Principal.setForeground(new java.awt.Color(102, 102, 102));

        pdf.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        pdf.setText("Pdf");
        pdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfActionPerformed(evt);
            }
        });

        excel.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        excel.setText("Excel");
        excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTableLayout = new javax.swing.GroupLayout(panelTable);
        panelTable.setLayout(panelTableLayout);
        panelTableLayout.setHorizontalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelTableLayout.createSequentialGroup()
                        .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelTableLayout.createSequentialGroup()
                                .addComponent(Month, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Year, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelTableLayout.createSequentialGroup()
                                .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Principal, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(panelTableLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(excel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pdf, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        panelTableLayout.setVerticalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTableLayout.createSequentialGroup()
                .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTableLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Principal, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                            .addComponent(SearchField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Month, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Year, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(view, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(excel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pdf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
    if (Principal.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please Fill out Principal Field");
    } else {
        try {
            List<FieldReportAttendance> list = populateFieldReportAttendanceList();
            if (list == null) {
                return;
            }
            
            int cmbtoStr = Integer.valueOf((String) Year.getSelectedItem());
            ParamenterAttendance dataprint = new ParamenterAttendance(
                FullName.getText(),
                (String) Month.getSelectedItem(),
                cmbtoStr,
                list,
                Principal.getText()
            );

            ReportManager.getInstance().printAttendanceReport(dataprint);
        } catch (Exception e) {
            handleReportGenerationError(e);
        }
    }
    }//GEN-LAST:event_viewActionPerformed

    private void YearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YearActionPerformed
    
    }//GEN-LAST:event_YearActionPerformed

    private void SearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchFieldActionPerformed
    srchFieldScn();
    }//GEN-LAST:event_SearchFieldActionPerformed

    private void MonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MonthActionPerformed
     
    }//GEN-LAST:event_MonthActionPerformed

    private void pdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfActionPerformed
         if (Principal.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please Fill out Principal Field");
    } else {
        try {
            List<FieldReportAttendance> list = populateFieldReportAttendanceList();
            if (list == null) {
                return;
            }
            
            int cmbtoStr = Integer.valueOf((String) Year.getSelectedItem());
            ParamenterAttendance dataprint = new ParamenterAttendance(
                FullName.getText(),
                (String) Month.getSelectedItem(),
                cmbtoStr,
                list,
                Principal.getText()
            );

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PDF files", "pdf"));

            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String outputPath = ensurePdfExtension(fileToSave.getAbsolutePath());

                ReportManager.getInstance().exportDtrReportToPdf(dataprint, outputPath);
                JOptionPane.showMessageDialog(this, "Report generated successfully!");
            }
        } catch (Exception e) {
            handleReportGenerationError(e);
        }
    }
    }//GEN-LAST:event_pdfActionPerformed

    private void excelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excelActionPerformed
        if (Principal.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please Fill out Principal Field");
    } else {
        File file = showFileChooser();
        if (file != null) {
            try {
                List<FieldReportAttendance> list = populateFieldReportAttendanceList();
                if (list == null) {
                    return;
                }

                int cmbtoStr = Integer.parseInt((String) Year.getSelectedItem());
                ParamenterAttendance dataprint = new ParamenterAttendance(
                    FullName.getText(),
                    (String) Month.getSelectedItem(),
                    cmbtoStr,
                    list,
                    Principal.getText()
                );

                String excelPath = ensureExcelExtension(file.getAbsolutePath());

                ReportManager.getInstance().exportReportToExcel(dataprint, excelPath);       
                 JOptionPane.showMessageDialog(this, "Report generated successfully!");
            } catch (Exception e) {
                handleReportGenerationError(e);
            }
        }
    }
    }//GEN-LAST:event_excelActionPerformed

private List<FieldReportAttendance> populateFieldReportAttendanceList() {
    List<FieldReportAttendance> list = new ArrayList<>();
    DefaultTableModel data = (DefaultTableModel) dtrTable.getModel();

    // Iterate through each row of the table
    for (int i = 0; i < data.getRowCount(); i++) {
        // Retrieve the values of each column in the current row
        String col1 = (String) data.getValueAt(i, 0);
        String col2 = (String) data.getValueAt(i, 1);
        String col3 = (String) data.getValueAt(i, 2);
        String col4 = (String) data.getValueAt(i, 3);
        String col5 = (String) data.getValueAt(i, 4);
        String col6 = (String) data.getValueAt(i, 5);
        String col7 = (String) data.getValueAt(i, 6);
        String col8 = (String) data.getValueAt(i, 7);

        // Create a new FieldReportAttendance object with the values and add it to the list
        list.add(new FieldReportAttendance(col1, col2, col3, col4, col5, col6, col7, col8));
    }

    return list;
}

private void handleReportGenerationError(Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "An error occurred while generating the report.");
}

private String ensurePdfExtension(String path) {
    if (!path.toLowerCase().endsWith(".pdf")) {
        return path + ".pdf";
    }
    return path;
}

private String ensureExcelExtension(String path) {
    if (!path.toLowerCase().endsWith(".xls")) {
        return path + ".xls";
    }
    return path;
}

private File showFileChooser() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save Report as Excel");
    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel", "xls"));

    int result = fileChooser.showSaveDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
        return fileChooser.getSelectedFile();  // Return the selected file
    } else {
        return null;  // Return null if cancel or close the dialog
    }
}
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DepartMent;
    private AttendanceManagement.Components.ImageBox EmployeesImage;
    private javax.swing.JLabel FullName;
    private javax.swing.JComboBox<String> Month;
    private javax.swing.JTextField Principal;
    private javax.swing.JTextField SearchField;
    private javax.swing.JComboBox<String> Year;
    private javax.swing.JTable dtrTable;
    private javax.swing.JLabel employeesID;
    private javax.swing.JButton excel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelTable;
    private javax.swing.JButton pdf;
    private javax.swing.JButton view;
    // End of variables declaration//GEN-END:variables
}
