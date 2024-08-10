
package AttendanceManagement.Controller;

import AttendanceManagement.JDBC.DatabaseConnection;
import AttendanceManagement.Model.ModelAttendance;
import AttendanceManagement.Model.ModelDtr;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class DtrController {
    private PreparedStatement ps;
    private ResultSet rs;
    public DtrController() {
    }

public void populateDtr(JTable table, ModelDtr data) {
   String sql = "WITH RECURSIVE Calendar AS (\n" +
"    SELECT \n" +
"        DATE_FORMAT(DATE(? * 10000 + ? * 100 + 1), '%Y-%m-%d') AS Date\n" +
"    UNION ALL\n" +
"    SELECT \n" +
"        DATE_ADD(Date, INTERVAL 1 DAY)\n" +
"    FROM \n" +
"        Calendar\n" +
"    WHERE \n" +
"        Date < LAST_DAY(DATE(? * 10000 + ? * 100 + 1))\n" +
")\n" +
"SELECT\n" +
"    ROW_NUMBER() OVER () AS DayNumber,\n" +
"    DATE_FORMAT(cal.Date, '%e') AS Day,\n" +
"    CASE\n" +
"        WHEN ad.AmTimeIn IS NULL THEN ''\n" +
"        ELSE DATE_FORMAT(ad.AmTimeIn, '%l:%i %p')\n" +
"    END AS AmTimeIn,\n" +
"    CASE\n" +
"        WHEN ad.AmTimeOut IS NULL THEN ''\n" +
"        ELSE DATE_FORMAT(ad.AmTimeOut, '%l:%i %p')\n" +
"    END AS AmTimeOut,\n" +
"    CASE\n" +
"        WHEN ad.PmTimeIn IS NULL THEN ''\n" +
"        ELSE DATE_FORMAT(ad.PmTimeIn, '%l:%i %p')\n" +
"    END AS PmTimeIn,\n" +
"    CASE\n" +
"        WHEN ad.PmTimeOut IS NULL THEN ''\n" +
"        ELSE DATE_FORMAT(ad.PmTimeOut, '%l:%i %p')\n" +
"    END AS PmTimeOut,\n" +
"    CASE\n" +
"        WHEN ad.AmTimeIn IS NULL OR ad.AmTimeOut IS NULL THEN ''\n" +
"        ELSE IFNULL(TIMEDIFF(ad.AmTimeOut, ad.AmTimeIn), '00:00:00')\n" +
"    END AS TotalAmWorkHours,\n" +
"    CASE\n" +
"        WHEN ad.PmTimeIn IS NULL OR ad.PmTimeOut IS NULL THEN ''\n" +
"        ELSE IFNULL(TIMEDIFF(ad.PmTimeOut, ad.PmTimeIn), '00:00:00')\n" +
"    END AS TotalPmWorkHours,\n" +
"    CASE\n" +
"        WHEN ad.AmTimeIn IS NULL AND ad.AmTimeOut IS NULL AND ad.PmTimeIn IS NULL AND ad.PmTimeOut IS NULL THEN ''\n" +
"        ELSE SEC_TO_TIME(\n" +
"            IFNULL(TIME_TO_SEC(TIMEDIFF(ad.AmTimeOut, ad.AmTimeIn)), 0) +\n" +
"            IFNULL(TIME_TO_SEC(TIMEDIFF(ad.PmTimeOut, ad.PmTimeIn)), 0)\n" +
"        )\n" +
"    END AS TotalWorkHours,\n" +
"    CASE\n" +
"        WHEN ad.AmTimeIn IS NULL OR ad.AmTimeOut IS NULL OR ad.PmTimeIn IS NULL OR ad.PmTimeOut IS NULL THEN ''\n" +
"        ELSE SEC_TO_TIME(\n" +
"            TIME_TO_SEC(\n" +
"                IFNULL(\n" +
"                    GREATEST(\n" +
"                        CASE ad.DepartMent\n" +
"                            WHEN 'SHS' THEN TIMEDIFF('12:00:00', TIME(ad.AmTimeOut))\n" +
"                            WHEN 'JHS' THEN TIMEDIFF('11:45:00', TIME(ad.AmTimeOut))\n" +
"                            WHEN 'NTP' THEN TIMEDIFF('12:00:00', TIME(ad.AmTimeOut))\n" +
"                            ELSE '00:00:00'\n" +
"                        END,\n" +
"                        '00:00:00'\n" +
"                    ),\n" +
"                    '00:00:00'\n" +
"                )\n" +
"            ) +\n" +
"            TIME_TO_SEC(\n" +
"                IFNULL(\n" +
"                    GREATEST(\n" +
"                        CASE ad.DepartMent\n" +
"                            WHEN 'SHS' THEN TIMEDIFF('16:30:00', TIME(ad.PmTimeOut))\n" +
"                            WHEN 'JHS' THEN TIMEDIFF('16:45:00', TIME(ad.PmTimeOut))\n" +
"                            WHEN 'NTP' THEN TIMEDIFF('17:00:00', TIME(ad.PmTimeOut))\n" +
"                            ELSE '00:00:00'\n" +
"                        END,\n" +
"                        '00:00:00'\n" +
"                    ),\n" +
"                    '00:00:00'\n" +
"                )\n" +
"            )\n" +
"        )\n" +
"    END AS AM_PM_UnderTime,\n" +
"    CASE\n" +
"        WHEN ad.PmTimeOut IS NULL THEN ''\n" +
"        ELSE IFNULL(\n" +
"            GREATEST(\n" +
"                CASE ad.DepartMent\n" +
"                    WHEN 'SHS' THEN\n" +
"                        CASE\n" +
"                            WHEN TIME(ad.PmTimeOut) > '16:30:00' THEN TIMEDIFF(TIME(ad.PmTimeOut), '16:30:00')\n" +
"                            ELSE '00:00:00'\n" +
"                        END\n" +
"                    WHEN 'JHS' THEN\n" +
"                        CASE\n" +
"                            WHEN TIME(ad.PmTimeOut) > '16:45:00' THEN TIMEDIFF(TIME(ad.PmTimeOut), '16:45:00')\n" +
"                            ELSE '00:00:00'\n" +
"                        END\n" +
"                    WHEN 'NTP' THEN\n" +
"                        CASE\n" +
"                            WHEN TIME(ad.PmTimeOut) > '17:00:00' THEN TIMEDIFF(TIME(ad.PmTimeOut), '17:00:00')\n" +
"                            ELSE '00:00:00'\n" +
"                        END\n" +
"                    ELSE '00:00:00'\n" +
"                END,\n" +
"                '00:00:00'\n" +
"            ),\n" +
"            '00:00:00'\n" +
"        )\n" +
"    END AS OverTime\n" +
"FROM\n" +
"    Calendar cal\n" +
"    LEFT JOIN attendance_data ad ON cal.Date = DATE_FORMAT(ad.DateCreated, '%Y-%m-%d')\n" +
"        AND ad.EmployeesID = ?\n" +
"        AND ad.DateDeleted IS NULL\n" +
"WHERE\n" +
"    MONTH(cal.Date) = ?\n" +
"    AND YEAR(cal.Date) = ?\n" +
"\n" +
"UNION ALL\n" +
"\n" +
"SELECT\n" +
"    '' AS DayNumber,\n" +
"    '' AS Day,\n" +
"    '' AS AmTimeIn,\n" +
"    '' AS AmTimeOut,\n" +
"    '' AS PmTimeIn,\n" +
"    'Total' AS PmTimeOut,\n" +
"    CASE\n" +
"        WHEN SUM(IFNULL(TIME_TO_SEC(TIMEDIFF(ad.AmTimeOut, ad.AmTimeIn)), 0)) = 0 THEN ''\n" +
"        ELSE SEC_TO_TIME(SUM(IFNULL(TIME_TO_SEC(TIMEDIFF(ad.AmTimeOut, ad.AmTimeIn)), 0)))\n" +
"    END AS TotalAmWorkHours,\n" +
"    CASE\n" +
"        WHEN SUM(IFNULL(TIME_TO_SEC(TIMEDIFF(ad.PmTimeOut, ad.PmTimeIn)), 0)) = 0 THEN ''\n" +
"        ELSE SEC_TO_TIME(SUM(IFNULL(TIME_TO_SEC(TIMEDIFF(ad.PmTimeOut, ad.PmTimeIn)), 0)))\n" +
"    END AS TotalPmWorkHours,\n" +
"    CASE\n" +
"        WHEN SUM(IFNULL(TIME_TO_SEC(TIMEDIFF(ad.AmTimeOut, ad.AmTimeIn)), 0) + IFNULL(TIME_TO_SEC(TIMEDIFF(ad.PmTimeOut, ad.PmTimeIn)), 0)) = 0 THEN ''\n" +
"        ELSE SEC_TO_TIME(\n" +
"            SUM(IFNULL(TIME_TO_SEC(TIMEDIFF(ad.AmTimeOut, ad.AmTimeIn)), 0) + IFNULL(TIME_TO_SEC(TIMEDIFF(ad.PmTimeOut, ad.PmTimeIn)), 0))\n" +
"        )\n" +
"    END AS TotalWorkHours,\n" +
"    CASE\n" +
"        WHEN SUM(\n" +
"            IFNULL(\n" +
"                TIME_TO_SEC(GREATEST(\n" +
"                    CASE ad.DepartMent\n" +
"                        WHEN 'SHS' THEN TIMEDIFF('12:00:00', TIME(ad.AmTimeOut))\n" +
"                        WHEN 'JHS' THEN TIMEDIFF('11:45:00', TIME(ad.AmTimeOut))\n" +
"                        WHEN 'NTP' THEN TIMEDIFF('12:00:00', TIME(ad.AmTimeOut))\n" +
"                        ELSE '00:00:00'\n" +
"                    END,\n" +
"                    '00:00:00'\n" +
"                )),\n" +
"                0\n" +
"            ) +\n" +
"            IFNULL(\n" +
"                TIME_TO_SEC(GREATEST(\n" +
"                    CASE ad.DepartMent\n" +
"                        WHEN 'SHS' THEN TIMEDIFF('16:30:00', TIME(ad.PmTimeOut))\n" +
"                        WHEN 'JHS' THEN TIMEDIFF('16:45:00', TIME(ad.PmTimeOut))\n" +
"                        WHEN 'NTP' THEN TIMEDIFF('17:00:00', TIME(ad.PmTimeOut))\n" +
"                        ELSE '00:00:00'\n" +
"                    END,\n" +
"                    '00:00:00'\n" +
"                )),\n" +
"                0\n" +
"            )\n" +
"        ) = 0 THEN ''\n" +
"        ELSE SEC_TO_TIME(SUM(\n" +
"            IFNULL(\n" +
"                TIME_TO_SEC(GREATEST(\n" +
"                    CASE ad.DepartMent\n" +
"                        WHEN 'SHS' THEN TIMEDIFF('12:00:00', TIME(ad.AmTimeOut))\n" +
"                        WHEN 'JHS' THEN TIMEDIFF('11:45:00', TIME(ad.AmTimeOut))\n" +
"                        WHEN 'NTP' THEN TIMEDIFF('12:00:00', TIME(ad.AmTimeOut))\n" +
"                        ELSE '00:00:00'\n" +
"                    END,\n" +
"                    '00:00:00'\n" +
"                )),\n" +
"                0\n" +
"            ) +\n" +
"            IFNULL(\n" +
"                TIME_TO_SEC(GREATEST(\n" +
"                    CASE ad.DepartMent\n" +
"                        WHEN 'SHS' THEN TIMEDIFF('16:30:00', TIME(ad.PmTimeOut))\n" +
"                        WHEN 'JHS' THEN TIMEDIFF('16:45:00', TIME(ad.PmTimeOut))\n" +
"                        WHEN 'NTP' THEN TIMEDIFF('17:00:00', TIME(ad.PmTimeOut))\n" +
"                        ELSE '00:00:00'\n" +
"                    END,\n" +
"                    '00:00:00'\n" +
"                )),\n" +
"                0\n" +
"            )\n" +
"        ))\n" +
"    END AS AM_PM_UnderTime,\n" +
"    CASE\n" +
"        WHEN SUM(\n" +
"            IFNULL(\n" +
"                TIME_TO_SEC(GREATEST(\n" +
"                    CASE ad.DepartMent\n" +
"                        WHEN 'SHS' THEN\n" +
"                            CASE\n" +
"                                WHEN TIME(ad.PmTimeOut) > '16:30:00' THEN TIMEDIFF(TIME(ad.PmTimeOut), '16:30:00')\n" +
"                                ELSE '00:00:00'\n" +
"                            END\n" +
"                        WHEN 'JHS' THEN\n" +
"                            CASE\n" +
"                                WHEN TIME(ad.PmTimeOut) > '16:45:00' THEN TIMEDIFF(TIME(ad.PmTimeOut), '16:45:00')\n" +
"                                ELSE '00:00:00'\n" +
"                            END\n" +
"                        WHEN 'NTP' THEN\n" +
"                            CASE\n" +
"                                WHEN TIME(ad.PmTimeOut) > '17:00:00' THEN TIMEDIFF(TIME(ad.PmTimeOut), '17:00:00')\n" +
"                                ELSE '00:00:00'\n" +
"                            END\n" +
"                        ELSE '00:00:00'\n" +
"                    END,\n" +
"                    '00:00:00'\n" +
"                )),\n" +
"                0\n" +
"            )\n" +
"        ) = 0 THEN ''\n" +
"        ELSE SEC_TO_TIME(\n" +
"            SUM(\n" +
"                IFNULL(\n" +
"                    TIME_TO_SEC(GREATEST(\n" +
"                        CASE ad.DepartMent\n" +
"                            WHEN 'SHS' THEN\n" +
"                                CASE\n" +
"                                    WHEN TIME(ad.PmTimeOut) > '16:30:00' THEN TIMEDIFF(TIME(ad.PmTimeOut), '16:30:00')\n" +
"                                    ELSE '00:00:00'\n" +
"                                END\n" +
"                            WHEN 'JHS' THEN\n" +
"                                CASE\n" +
"                                    WHEN TIME(ad.PmTimeOut) > '16:45:00' THEN TIMEDIFF(TIME(ad.PmTimeOut), '16:45:00')\n" +
"                                    ELSE '00:00:00'\n" +
"                                END\n" +
"                            WHEN 'NTP' THEN\n" +
"                                CASE\n" +
"                                    WHEN TIME(ad.PmTimeOut) > '17:00:00' THEN TIMEDIFF(TIME(ad.PmTimeOut), '17:00:00')\n" +
"                                    ELSE '00:00:00'\n" +
"                                END\n" +
"                            ELSE '00:00:00'\n" +
"                        END,\n" +
"                        '00:00:00'\n" +
"                    )),\n" +
"                    0\n" +
"                )\n" +
"            )\n" +
"        )\n" +
"    END AS OverTime\n" +
"FROM\n" +
"    attendance_data ad\n" +
"WHERE\n" +
"    ad.EmployeesID = ?\n" +
"    AND ad.DateDeleted IS NULL\n" +
"    AND YEAR(ad.DateCreated) = ?\n" +
"    AND MONTH(ad.DateCreated) = ?";

   

    try {
        String monthName = data.getMonth();

// Define a map to map month names to their corresponding numbers
Map<String, Integer> monthMap = new HashMap<>();
monthMap.put("January", 1);
monthMap.put("February", 2);
monthMap.put("March", 3);
monthMap.put("April", 4);
monthMap.put("May", 5);
monthMap.put("June", 6);
monthMap.put("July", 7);
monthMap.put("August", 8);
monthMap.put("September", 9);
monthMap.put("October", 10);
monthMap.put("November", 11);
monthMap.put("December", 12);
  DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0);
// Get the corresponding month number
int monthNumber = monthMap.get(monthName);
        ps = prepareStatement(sql);
        ps.setInt(1, data.getYear());
        ps.setInt(2, monthNumber);
         ps.setInt(3, data.getYear());
        ps.setInt(4, monthNumber);
        ps.setInt(5, data.getEmployeesId());
         ps.setInt(6, monthNumber);
         ps.setInt(7, data.getYear());      
        ps.setInt(8, data.getEmployeesId());
         ps.setInt(9, data.getYear());
        ps.setInt(10, monthNumber);
 

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Vector<Object> v = new Vector<>();
            v.add(rs.getString("Day"));
            v.add(rs.getString("AmTimeIn"));
            v.add(rs.getString("AmTimeOut"));
            v.add(rs.getString("PmTimeIn"));
            v.add(rs.getString("PmTimeOut"));
            v.add(rs.getString("TotalWorkHours"));
            v.add(rs.getString("AM_PM_UnderTime"));
            v.add(rs.getString("OverTime"));
            model.addRow(v);
        }

        ps.close();
        rs.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
  public List<ModelAttendance> populateDtrData(){
      try {
          List<ModelAttendance> list = new ArrayList<>();
          ps = prepareStatement("SELECT * FROM attendance_data WHERE DateDeleted IS NULL");
          rs = ps.executeQuery();
          while (rs.next()) {              
              int id = rs.getInt("ID");
              
          }
           return list;
      } catch (Exception e) {
          return null;
      }
  
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
}
