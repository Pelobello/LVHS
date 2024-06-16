
package AttendanceManagement.Forms;

import AttendanceManagement.Controller.EmployeesData;
import AttendanceManagement.Model.ModelEmployeesData;
import AttendanceManagement.ModelRecords.FieldEmployee;
import AttendanceManagement.ModelRecords.FieldReportAttendance;
import AttendanceManagement.ModelRecords.ParamenterAttendance;
import AttendanceManagement.ModelRecords.ParameterEmployee;
import AttendanceManagement.print.ReportManager;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class EmployeesRecords extends javax.swing.JPanel {

    private DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    private EmployeesData employeesData = new EmployeesData();
    public EmployeesRecords() {
        initComponents();
        loadData();
        init();
        testReport();
    }
private void init() {
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

}
   public void loadData(){
       try {
           DefaultTableModel model = (DefaultTableModel)employeesTable.getModel();
           model.setRowCount(0);
           
           List<ModelEmployeesData>list = employeesData.getallData();
           for (ModelEmployeesData d : list) {
               model.addRow(d.tableRow(employeesTable.getRowCount()+1));
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   private void searchData(){
       try {
           DefaultTableModel model = (DefaultTableModel)employeesTable.getModel();
           model.setRowCount(0);
           
           List<ModelEmployeesData>list = employeesData.searchlData((String)searchDepartment.getSelectedItem());
           for (ModelEmployeesData d : list) {
               model.addRow(d.tableRow(employeesTable.getRowCount()+1));
           }
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeesTable = new javax.swing.JTable();
        view = new javax.swing.JButton();
        searchDepartment = new javax.swing.JComboBox<>();
        pdf = new javax.swing.JButton();
        excel = new javax.swing.JButton();

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
                "Employee ID", "Last Name", "Name", "Middle Name", "Position", "Department", "Plantilla #", "DateAssumed"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(employeesTable);

        view.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        view.setText("View");
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });

        searchDepartment.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        searchDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL TEACHERS", "JHS", "SHS", "NTP" }));
        searchDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchDepartmentActionPerformed(evt);
            }
        });

        pdf.setText("PDF");
        pdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfActionPerformed(evt);
            }
        });

        excel.setText("Excel");
        excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(searchDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 928, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(excel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pdf, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(searchDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(excel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pdf, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void searchDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchDepartmentActionPerformed
      searchData();
    }//GEN-LAST:event_searchDepartmentActionPerformed

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
        try {
        List<FieldEmployee> list = extractEmployeesFromTable();
        ParameterEmployee printEmployee = createParameterEmployee(list);
        ReportManager.getInstance().printTeachersReport(printEmployee);
          
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_viewActionPerformed

    private void excelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excelActionPerformed
          try {
        File file = showFileChooser();
        if (file != null) {
            List<FieldEmployee> list = extractEmployeesFromTable();
            ParameterEmployee printEmployee = createParameterEmployee(list);
            String excelPath = getFormattedFilePath(file, ".xls");
            ReportManager.getInstance().exportReportTeachertoExcel(printEmployee, excelPath);
             JOptionPane.showMessageDialog(this, "Report generated successfully!");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_excelActionPerformed

    private void pdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfActionPerformed
       try {
        File file = showFileChooser();
        if (file != null) {
            List<FieldEmployee> list = extractEmployeesFromTable();
            ParameterEmployee printEmployee = createParameterEmployee(list);
            String pdfPath = getFormattedFilePath(file, ".pdf");
            ReportManager.getInstance().exportReportTeachertoPdf(printEmployee, pdfPath);
                JOptionPane.showMessageDialog(this, "Report generated successfully!");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_pdfActionPerformed
private List<FieldEmployee> extractEmployeesFromTable() {
    List<FieldEmployee> list = new ArrayList<>();
    DefaultTableModel data = (DefaultTableModel) employeesTable.getModel();
    for (int i = 0; i < data.getRowCount(); i++) {
        list.add(getFieldEmployeeFromRow(data, i));
    }
    return list;
}

private FieldEmployee getFieldEmployeeFromRow(DefaultTableModel data, int row) {
    Object col1 = data.getValueAt(row, 0);
    Object col2 = data.getValueAt(row, 1);
    Object col3 = data.getValueAt(row, 2);
    Object col4 = data.getValueAt(row, 3);
    Object col5 = data.getValueAt(row, 4);
    Object col6 = data.getValueAt(row, 5);
    Object col7 = data.getValueAt(row, 6);
    Object col8 = data.getValueAt(row, 7);

    return new FieldEmployee(getStringValue(col1), getStringValue(col2), getStringValue(col3),
                             getStringValue(col4), getStringValue(col5), getStringValue(col6),
                             getStringValue(col7), getStringValue(col8));
}

private String getStringValue(Object value) {
    return (value instanceof Integer) ? String.valueOf(value) : (String) value;
}

private ParameterEmployee createParameterEmployee(List<FieldEmployee> list) throws IOException {
    URL imageURL = getClass().getResource("/AttendanceManagement/Images_Icons/schoollogo.png");
    InputStream inputStream = imageURL.openStream();
    return new ParameterEmployee("LUPON VOCATIONAL HIGH SCHOOL", inputStream, list);
}

private String getFormattedFilePath(File file, String extension) {
    String filePath = file.getAbsolutePath();
    if (!filePath.toLowerCase().endsWith(extension)) {
        filePath += extension;
    }
    return filePath;
}
private File showFileChooser() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save Report");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel/PDF files", "xls", "pdf");
    fileChooser.setFileFilter(filter);
    int result = fileChooser.showSaveDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
        return fileChooser.getSelectedFile();
    }
    return null;
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable employeesTable;
    private javax.swing.JButton excel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton pdf;
    private javax.swing.JComboBox<String> searchDepartment;
    private javax.swing.JButton view;
    // End of variables declaration//GEN-END:variables

    
}
