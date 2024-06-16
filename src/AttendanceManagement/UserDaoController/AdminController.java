/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttendanceManagement.UserDaoController;

import AttendanceManagement.JDBC.DatabaseConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import raven.glasspanepopup.GlassPanePopup;

/**
 *
 * @author USER
 */
public class AdminController {
PreparedStatement ps;
ResultSet rs;
    public AdminController() {
        
    }
    
    
     public boolean createAdmin(ModelAdminData data){
         try {
             String sql = "insert into adminatable (Username,Password)values(?,?)";
             ps = prepareStatement(sql);
             ps.setString(1, data.getUsername());
             ps.setString(2, new String(data.getPassword()));
             
             ps.executeUpdate();
             JOptionPane.showMessageDialog(null, "Succesfully Register");
              return true;
             
         } catch (Exception e) {
             e.printStackTrace();
             return false;
         }
       
    }
     public ModelAdminData loginAdmin(ModelAdminData data){
         try {
             String sql = "SELECT * FROM adminatable WHERE Username LIKE ? and Password LIKE ?";
             ps = prepareStatement(sql);
             ps.setString(1, data.getUsername());
             ps.setString(2, new String(data.getPassword()));
             
             rs = ps.executeQuery();
             
             if (rs.next()) {
                 return new ModelAdminData(rs.getString("Password").toCharArray(),rs.getString("Username") ,rs.getString("Username"), rs.getString("Password").toCharArray());
             }else{
                 return null;
             }
             
         } catch (Exception e) {
             e.printStackTrace();
              return null;
         }
        
     }
    public boolean changeAdminPassword(ModelAdminData data){
 
        try {
           String sql = "UPDATE adminatable SET Username = ?, Password = ? WHERE Username = ? AND Password = ?";
           ps = prepareStatement(sql);
           ps.setString(1, data.getNewusername());
           ps.setString(2, new String(data.getNewpassword()));
           ps.setString(3, data.getUsername());
           ps.setString(4, new String(data.getPassword()));
           
           int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Success");
                GlassPanePopup.closePopupAll();
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Incorrect UserName or Password");
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
             return false;
        }
        
    }
       public String encryptPass(String password){
        try {
            MessageDigest digestor = MessageDigest.getInstance("SHA-256");
            byte [] encodeHash = digestor.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder encryptionValue = new StringBuilder(2 * encodeHash.length);
            
             for (int i = 0; i < encodeHash.length; i++) {
                String hexVal = Integer.toHexString(0xff & encodeHash[i]);
                if (hexVal.length() == 1) {
                    encryptionValue.append('0');
                }
                encryptionValue.append(hexVal);
            }
             return encryptionValue.toString();
        } catch (Exception e) {
             return e.getMessage();
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
