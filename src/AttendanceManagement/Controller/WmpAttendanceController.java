/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttendanceManagement.Controller;

import AttendanceManagement.JDBC.DatabaseConnection;
import AttendanceManagement.Model.ModelAttendance;
import AttendanceManagement.Model.ModelDtr;
import AttendanceManagement.Model.WmpModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import raven.glasspanepopup.GlassPanePopup;


public class WmpAttendanceController {
 private PreparedStatement ps;
    private ResultSet rs;
    public WmpAttendanceController() {
        
    }
    
    public List<ModelAttendance>getAll() throws SQLException{
   
    try {
        
        List<ModelAttendance> list = new ArrayList<>();
        String sql = "SELECT * FROM wmp_attendancesheet";
        ps = prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
           int employeesId = rs.getInt("ID");
            String fullName = rs.getString("Name");
            String department = rs.getString("Department");
            
            Time amTimeIn = rs.getTime("AmTimeIn");
            Time amTimeOut = rs.getTime("AmTimeOut");
            Time pmTimeIn = rs.getTime("PmTimeIn");
            Time pmTimeOut = rs.getTime("PmTimeOut");
          
            ModelAttendance attendance = new ModelAttendance();
            attendance.setEmployeesID(employeesId);
            attendance.setEmployeesFullName(fullName);
            attendance.setDepartment(department);
           attendance.setAmTimeIn(amTimeIn != null ? amTimeIn.toLocalTime() : null);  // Converting to LocalTime
            attendance.setAmTimeOut(amTimeOut != null ? amTimeOut.toLocalTime() : null); // Fixed setters
            attendance.setPmTimeIn(pmTimeIn != null ? pmTimeIn.toLocalTime() : null);  // Converting to LocalTime
            attendance.setPmTimeOut(pmTimeOut != null ? pmTimeOut.toLocalTime() : null); // Fixed setters
            attendance.setDateCreated(  rs.getDate("DateCreated"));
           

            list.add(attendance);
        }
        
        
         return list;
        
    } finally{
        ps.close();
        rs.close();
        
    }
   
}
public List<ModelAttendance>searchData(String search,Date date) throws SQLException{
   
    try {
        
        List<ModelAttendance> list = new ArrayList<>();
        String sql = "SELECT * FROM wmp_attendancesheet WHERE Name LIKE ? AND DATE(DateCreated) = ?";
        ps = prepareStatement(sql);
        ps.setString(1, "%" + search + "%");
      java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        ps.setDate(2, sqlDate);
        rs = ps.executeQuery();
        while (rs.next()) {
           int employeesId = rs.getInt("ID");
            String fullName = rs.getString("Name");
            String department = rs.getString("Department");
            
            Time amTimeIn = rs.getTime("AmTimeIn");
            Time amTimeOut = rs.getTime("AmTimeOut");
            Time pmTimeIn = rs.getTime("PmTimeIn");
            Time pmTimeOut = rs.getTime("PmTimeOut");

            ModelAttendance attendance = new ModelAttendance();
            attendance.setEmployeesID(employeesId);
            attendance.setEmployeesFullName(fullName);
            attendance.setDepartment(department);
           attendance.setAmTimeIn(amTimeIn != null ? amTimeIn.toLocalTime() : null);  // Converting to LocalTime
            attendance.setAmTimeOut(amTimeOut != null ? amTimeOut.toLocalTime() : null); // Fixed setters
            attendance.setPmTimeIn(pmTimeIn != null ? pmTimeIn.toLocalTime() : null);  // Converting to LocalTime
            attendance.setPmTimeOut(pmTimeOut != null ? pmTimeOut.toLocalTime() : null); // Fixed setters
             attendance.setDateCreated(  rs.getDate("DateCreated"));

           

            list.add(attendance);
        }
        
        
         return list;
        
    } finally{
        ps.close();
        rs.close();
        
    }
   
}

public void UpdateData(ModelAttendance data){
    try {
        String sql = "UPDATE wmp_attendancesheet SET AmTimeIn = ?,AmTimeOut = ?, PmTimeIn = ?, PmTimeOut = ? WHERE ID = ?";
        ps = prepareStatement(sql);
         if (data.getAmTimeIn() != null) {
            ps.setTime(1, java.sql.Time.valueOf(data.getAmTimeIn()));
        } else {
            ps.setNull(1, java.sql.Types.TIME);
        }

        if (data.getAmTimeOut() != null) {
            ps.setTime(2, java.sql.Time.valueOf(data.getAmTimeOut()));
        } else {
            ps.setNull(2, java.sql.Types.TIME);
        }

        if (data.getPmTimeIn() != null) {
            ps.setTime(3, java.sql.Time.valueOf(data.getPmTimeIn()));
        } else {
            ps.setNull(3, java.sql.Types.TIME);
        }

        if (data.getPmTimeOut() != null) {
            ps.setTime(4, java.sql.Time.valueOf(data.getPmTimeOut()));
        } else {
            ps.setNull(4, java.sql.Types.TIME);
        }
           ps.setInt(5, data.getEmployeesID());
          int rowsUpdated = ps.executeUpdate();
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(null, "Successfully Updated");
            GlassPanePopup.closePopupLast();
        } else {
            JOptionPane.showMessageDialog(null, "Update Failed");
        }
         
    } catch (Exception e) {
        e.printStackTrace();
    }
}
   public void populateData(JTable table,ModelDtr data) {
    String sql = "WITH RECURSIVE days AS ( " +
                 "    SELECT 1 AS Day " +
                 "    UNION ALL " +
                 "    SELECT Day + 1 " +
                 "    FROM days " +
                 "    WHERE Day + 1 <= DAY(LAST_DAY(STR_TO_DATE(CONCAT(?, '-', ?, '-01'), '%Y-%m-%d'))) " +
                 "), " +
                 "attendance_data AS ( " +
                 "    SELECT " +
                 "        d.Day, " +
                 "        COALESCE(DATE_FORMAT(a.AmTimeIn, '%l:%i %p'), ' ') AS AmTimeIn, " +
                 "        COALESCE(DATE_FORMAT(a.AmTimeOut, '%l:%i %p'), ' ') AS AmTimeOut, " +
                 "        COALESCE(DATE_FORMAT(a.PmTimeIn, '%l:%i %p'), ' ') AS PmTimeIn, " +
                 "        COALESCE(DATE_FORMAT(a.PmTimeOut, '%l:%i %p'), ' ') AS PmTimeOut, " +
                 "        CASE " +
                 "            WHEN a.AmTimeIn IS NULL AND a.AmTimeOut IS NULL AND a.PmTimeIn IS NULL AND a.PmTimeOut IS NULL THEN ' ' " +
                 "            ELSE COALESCE( " +
                 "                TIME_FORMAT( " +
                 "                    SEC_TO_TIME( " +
                 "                        TIMESTAMPDIFF(SECOND, a.AmTimeIn, a.AmTimeOut) " +
                 "                        + TIMESTAMPDIFF(SECOND, a.PmTimeIn, a.PmTimeOut) " +
                 "                    ), '%H:%i:%s' " +
                 "                ), '00:00:00' " +
                 "            ) " +
                 "        END AS TotalWorkingHours, " +
                 "        CASE " +
                 "            WHEN a.AmTimeIn IS NULL OR a.AmTimeOut IS NULL OR a.PmTimeIn IS NULL OR a.PmTimeOut IS NULL THEN ' ' " +
                 "            ELSE " +
                 "                CASE " +
                 "                    WHEN a.Department = 'WMP I' THEN " +
                 "                        CASE " +
                 "                            WHEN TIMEDIFF(COALESCE(a.PmTimeOut, '16:00:00'), '16:00:00') > '00:00:00' THEN TIME_FORMAT(TIMEDIFF(COALESCE(a.PmTimeOut, '16:00:00'), '16:00:00'), '%H:%i:%s') " +
                 "                            ELSE ' ' " +
                 "                        END " +
                 "                    WHEN a.Department = 'WMP II' THEN " +
                 "                        CASE " +
                 "                            WHEN TIMEDIFF(COALESCE(a.PmTimeOut, '00:00:00'), '00:00:00') > '00:00:00' THEN TIME_FORMAT(TIMEDIFF(COALESCE(a.PmTimeOut, '00:00:00'), '00:00:00'), '%H:%i:%s') " +
                 "                            ELSE ' ' " +
                 "                        END " +
                 "                    WHEN a.Department = 'WMP III' THEN " +
                 "                        CASE " +
                 "                            WHEN TIMEDIFF(COALESCE(a.PmTimeOut, '08:00:00'), '08:00:00') > '00:00:00' THEN TIME_FORMAT(TIMEDIFF(COALESCE(a.PmTimeOut, '08:00:00'), '08:00:00'), '%H:%i:%s') " +
                 "                            ELSE ' ' " +
                 "                        END " +
                 "                    ELSE ' ' " +
                 "                END " +
                 "        END AS UnderTime, " +
                 "        CASE " +
                 "            WHEN a.AmTimeIn IS NULL OR a.AmTimeOut IS NULL OR a.PmTimeIn IS NULL OR a.PmTimeOut IS NULL THEN ' ' " +
                 "            ELSE " +
                 "                CASE " +
                 "                    WHEN a.Department = 'WMP I' THEN " +
                 "                        CASE " +
                 "                            WHEN TIMEDIFF('16:00:00', COALESCE(a.PmTimeOut, '16:00:00')) < '00:00:00' THEN ' ' " +
                 "                            ELSE TIME_FORMAT(TIMEDIFF('16:00:00', COALESCE(a.PmTimeOut, '16:00:00')), '%H:%i:%s') " +
                 "                        END " +
                 "                    WHEN a.Department = 'WMP II' THEN " +
                 "                        CASE " +
                 "                            WHEN TIMEDIFF('00:00:00', COALESCE(a.PmTimeOut, '00:00:00')) < '00:00:00' THEN ' ' " +
                 "                            ELSE TIME_FORMAT(TIMEDIFF('00:00:00', COALESCE(a.PmTimeOut, '00:00:00')), '%H:%i:%s') " +
                 "                        END " +
                 "                    WHEN a.Department = 'WMP III' THEN " +
                 "                        CASE " +
                 "                            WHEN TIMEDIFF('08:00:00', COALESCE(a.PmTimeOut, '08:00:00')) < '00:00:00' THEN ' ' " +
                 "                            ELSE TIME_FORMAT(TIMEDIFF('08:00:00', COALESCE(a.PmTimeOut, '08:00:00')), '%H:%i:%s') " +
                 "                        END " +
                 "                    ELSE ' ' " +
                 "                END " +
                 "        END AS OverTime " +
                 "    FROM " +
                 "        days d " +
                 "    LEFT JOIN " +
                 "        attendance_management_db.wmp_attendancesheet a " +
                 "    ON " +
                 "        DAY(a.DateCreated) = d.Day " +
                 "        AND MONTH(a.DateCreated) = ? " +
                 "        AND YEAR(a.DateCreated) = ? " +
                 "        AND a.empID = ? " +
                 ") " +
                 "SELECT " +
                 "    Day, " +
                 "    AmTimeIn, " +
                 "    AmTimeOut, " +
                 "    PmTimeIn, " +
                 "    PmTimeOut, " +
                 "    TotalWorkingHours, " +
                 "    UnderTime, " +
                 "    OverTime " +
                 "FROM attendance_data " +
                 "UNION ALL " +
                 "SELECT " +
                 "    NULL AS Day, " +
                 "    NULL AS AmTimeIn, " +
                 "    NULL AS AmTimeOut, " +
                 "    NULL AS PmTimeIn, " +
                 "    'Total' AS PmTimeOut, " +
                 "    CASE WHEN SUM(TIME_TO_SEC(TotalWorkingHours)) = 0 THEN ' ' ELSE SEC_TO_TIME(SUM(TIME_TO_SEC(TotalWorkingHours))) END AS TotalWorkingHours, " +
                 "    CASE WHEN SUM(TIME_TO_SEC(UnderTime)) = 0 THEN ' ' ELSE SEC_TO_TIME(SUM(TIME_TO_SEC(UnderTime))) END AS UnderTime, " +
                 "    CASE WHEN SUM(TIME_TO_SEC(OverTime)) = 0 THEN ' ' ELSE SEC_TO_TIME(SUM(TIME_TO_SEC(OverTime))) END AS OverTime " +
                 "FROM attendance_data;";

    try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
        ps.setInt(1, data.getYear());
        ps.setInt(2, data.getMonths());
        ps.setInt(3, data.getMonths());
        ps.setInt(4, data.getYear());
        ps.setInt(5, data.getEmployeesId());

        ResultSet rs = ps.executeQuery();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        while (rs.next()) {
            Vector<Object> row = new Vector<>();
            row.add(rs.getObject("Day")); // Changed to getObject to handle nulls
            row.add(rs.getString("AmTimeIn"));
            row.add(rs.getString("AmTimeOut"));
            row.add(rs.getString("PmTimeIn"));
            row.add(rs.getString("PmTimeOut"));
            row.add(rs.getString("TotalWorkingHours"));
            row.add(rs.getString("UnderTime"));
            row.add(rs.getString("OverTime"));

            model.addRow(row);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

     public void amTimeIn(WmpModel data) {
        try {
            if (isAlreadyTimeIn(data)) {
                showMessage("Already timed in.");
                return;
            }

            String sql = "INSERT INTO wmp_attendancesheet(empID, Name, Department, AmTimeIn) VALUES (?,?,?,?)";
            ps = prepareStatement(sql);

            ps.setInt(1, data.getEmployeesID());
            ps.setString(2, data.getFullName());
            ps.setString(3, data.getDepartment());
            ps.setTime(4, java.sql.Time.valueOf(data.getAmTimeIn()));

            ps.executeUpdate();
            showMessage("AM Time In recorded successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage("Failed to record AM Time In.");
        }
    }

    public void amTimeOut(WmpModel data) {
        try {
            if (!isAlreadyTimeIn(data)) {
                showMessage("AM Time in entry not found.");
                return;
            }

            if (isAlreadyTimeOut(data)) {
                showMessage("Already timed out for AM.");
                return;
            }

            String sql = "UPDATE wmp_attendancesheet SET AmTimeOut = ? WHERE empID = ? AND DATE(DateCreated) = CURDATE() AND AmTimeOut IS NULL";
            ps = prepareStatement(sql);

            ps.setTime(1, java.sql.Time.valueOf(data.getAmTimeOut()));
            ps.setInt(2, data.getEmployeesID());

            ps.executeUpdate();
            showMessage("AM Time Out recorded successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage("Failed to record AM Time Out.");
        }
    }

    public void pmTimeIn(WmpModel data) {
        try {
            if (!isAlreadyTimeOut(data)) {
                showMessage("AM Time Out entry not found.");
                return;
            }

            if (isAlreadyPmTimeIn(data)) {
                showMessage("Already timed in for PM.");
                return;
            }

            String sql = "UPDATE wmp_attendancesheet SET PmTimeIn = ? WHERE empID = ? AND DATE(DateCreated) = CURDATE() AND PmTimeIn IS NULL";
            ps = prepareStatement(sql);

            ps.setTime(1, java.sql.Time.valueOf(data.getPmTimeIn()));
            ps.setInt(2, data.getEmployeesID());

            ps.executeUpdate();
            showMessage("PM Time In recorded successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage("Failed to record PM Time In.");
        }
    }

    public void pmTimeOut(WmpModel data) {
        try {
            if (isPmTimeOutForPreviousDay(data)) {
                updatePmTimeOutForPreviousDay(data);
                showMessage("PM Time Out recorded for previous day.");
            } else {
                if (isAlreadyPmTimeIn(data)) {
                  if (isAlreadyPmTimeOut(data)) {
                    showMessage("Already timed out for PM.");
                    return;
                }
                    String sql = "UPDATE wmp_attendancesheet SET PmTimeOut = ? WHERE empID = ? AND DATE(DateCreated) = CURDATE() AND PmTimeOut IS NULL";
                ps = prepareStatement(sql);

                ps.setTime(1, java.sql.Time.valueOf(data.getPmTimeOut()));
                ps.setInt(2, data.getEmployeesID());

                ps.executeUpdate();
                showMessage("PM Time Out recorded successfully.");
                }else{
                    JOptionPane.showMessageDialog(null, "PM Time In entry not found.");
                }
                

              
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage("Failed to record PM Time Out.");
        }
    }

    private boolean isAlreadyTimeIn(WmpModel data) {
        return isTimeRecorded(data, "AmTimeIn");
    }

    private boolean isAlreadyTimeOut(WmpModel data) {
        return isTimeRecorded(data, "AmTimeOut");
    }

    private boolean isAlreadyPmTimeIn(WmpModel data) {
        return isTimeRecorded(data, "PmTimeIn");
    }

    private boolean isAlreadyPmTimeOut(WmpModel data) {
        return isTimeRecorded(data, "PmTimeOut");
    }

    private boolean isTimeRecorded(WmpModel data, String timeColumn) {
        try {
            String sql = "SELECT empID FROM wmp_attendancesheet WHERE empID = ? AND DATE(DateCreated) = CURDATE() AND " + timeColumn + " IS NOT NULL";
            ps = prepareStatement(sql);
            ps.setInt(1, data.getEmployeesID());
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isPmTimeOutForPreviousDay(WmpModel data) {
        try {
            String sql = "SELECT empID FROM wmp_attendancesheet WHERE empID = ? AND DATE(DateCreated) = CURDATE() - INTERVAL 1 DAY AND PmTimeOut IS NULL";
            ps = prepareStatement(sql);
            ps.setInt(1, data.getEmployeesID());
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void updatePmTimeOutForPreviousDay(WmpModel data) throws SQLException {
        String sql = "UPDATE wmp_attendancesheet SET PmTimeOut = ? WHERE empID = ? AND DATE(DateCreated) = CURDATE() - INTERVAL 1 DAY AND PmTimeOut IS NULL";
        ps = prepareStatement(sql);
        ps.setTime(1, java.sql.Time.valueOf(data.getPmTimeOut()));
        ps.setInt(2, data.getEmployeesID());
        ps.executeUpdate();
    }

    private Connection getConnection() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            return databaseConnection.getCConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private PreparedStatement prepareStatement(String sql) {
        try {
            Connection con = getConnection();
            if (con != null) {
                return con.prepareStatement(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
    
}
