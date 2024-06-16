/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttendanceManagement.UserDaoController;

/**
 *
 * @author USER
 */
public class DaoController {
    AdminController Dao;
    public DaoController() {
        
     this.Dao = new AdminController();
    
    }
    
    public boolean registerAdmin(ModelAdminData data){
        String encryptedPassword = Dao.encryptPass(new String(data.getPassword()));
        data.setPassword(encryptedPassword.toCharArray());
        return Dao.createAdmin(data);
    }
   public ModelAdminData loginAdmin(ModelAdminData data){
       String encryptedPassword = Dao.encryptPass(new String(data.getPassword()));
       data.setPassword(encryptedPassword.toCharArray());
       return Dao.loginAdmin(data);
   }
   public boolean changePassword(ModelAdminData data){
       String encryptedNewPassword = Dao.encryptPass(new String(data.getNewpassword()));
       data.setNewpassword(encryptedNewPassword.toCharArray());
       String encryptedPassword = Dao.encryptPass(new String(data.getPassword()));
       data.setPassword(encryptedPassword.toCharArray());
       return Dao.changeAdminPassword(data);
   }
    
 
}
