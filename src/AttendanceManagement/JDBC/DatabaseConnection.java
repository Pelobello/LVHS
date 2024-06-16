/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttendanceManagement.JDBC;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {

    public DatabaseConnection() {
    }
     public Connection getCConnection() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/attendance_management_db", "root", "Gwapoko123");
        } catch (ClassNotFoundException | SQLException e) {
            java.util.logging.Logger.getLogger(DatabaseConnection.class.getName()).log(java.util.logging.Level.SEVERE, null, e);

        return null;
    }
    }
      public void closeConnection(Connection connection){
        try{
            if (connection != null&& !connection.isClosed()){
               connection.close(); 
            }
        } catch (SQLException e){
          e.printStackTrace();
        }
    }
}