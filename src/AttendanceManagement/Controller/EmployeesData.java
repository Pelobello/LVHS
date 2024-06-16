/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttendanceManagement.Controller;

import AttendanceManagement.JDBC.DatabaseConnection;
import AttendanceManagement.Model.ModelEmployeesData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class EmployeesData {
private PreparedStatement ps;
private ResultSet rs;
    public EmployeesData() {
    }

public List<ModelEmployeesData>getallData() throws SQLException{
  
    try {
        String sql = "SELECT IDnumber, FirstName, CASE WHEN TRIM(MiddleName) = '' THEN 'N-M' ELSE MiddleName END AS MiddleName, LastName, Position, Department, DateAssumed, PlantillaNumber FROM employees_data WHERE DateDeleted IS NULL ORDER BY LastName ASC";
        ps = prepareStatement(sql);
        rs = ps.executeQuery();
        
         List<ModelEmployeesData>list = new ArrayList<>();
    
        while (rs.next()) {            
            int idNumber = rs.getInt("IDnumber");
            String firstName = rs.getString("FirstName");
            String middleName = rs.getString("MiddleName");
            String lastName = rs.getString("LastName");
            String position = rs.getString("Position");
            String department = rs.getString("Department");
            int plantillaNumber = rs.getInt("PlantillaNumber");
            Date dateAssumed = rs.getDate("DateAssumed");
            
            list.add(new ModelEmployeesData(idNumber, firstName, middleName, lastName, position, department, plantillaNumber, dateAssumed));
            
            
        }
       return list;
       
        
    }finally{
       if(ps!=null)ps.close();
       if(rs!=null)rs.close();
    }
    
     
}
public List<ModelEmployeesData>searchlData(String Search) throws SQLException{
  
    try {
        String sql = "SELECT IDnumber, FirstName, CASE WHEN TRIM(MiddleName) = '' THEN 'N-M' ELSE MiddleName END AS MiddleName, LastName, Position, Department, DateAssumed, PlantillaNumber FROM employees_data WHERE DateDeleted IS NULL ";
       
      
         if (Search.equalsIgnoreCase("JHS")) {
            sql += " AND Department = 'JHS' ORDER BY LastName ASC";
        } else if (Search.equalsIgnoreCase("SHS")) {
            sql += " AND Department = 'SHS' ORDER BY LastName ASC";
        } else if (Search.equalsIgnoreCase("NTP")) {
            sql += " AND Department = 'NTP' ORDER BY LastName ASC";
        }
        ps = prepareStatement(sql);
        rs = ps.executeQuery();
        
         List<ModelEmployeesData>list = new ArrayList<>();
    
        while (rs.next()) {            
            int idNumber = rs.getInt("IDnumber");
            String firstName = rs.getString("FirstName");
            String middleName = rs.getString("MiddleName");
            String lastName = rs.getString("LastName");
            String position = rs.getString("Position");
            String department = rs.getString("Department");
            int plantillaNumber = rs.getInt("PlantillaNumber");
            Date dateAssumed = rs.getDate("DateAssumed");
            
            list.add(new ModelEmployeesData(idNumber, firstName, middleName, lastName, position, department, plantillaNumber, dateAssumed));
            
            
        }
       return list;
       
        
    }finally{
       if(ps!=null)ps.close();
       if(rs!=null)rs.close();
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
