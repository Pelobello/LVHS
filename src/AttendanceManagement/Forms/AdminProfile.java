
package AttendanceManagement.Forms;

import AttendanceManagement.UserDaoController.DaoController;
import AttendanceManagement.UserDaoController.ModelAdminData;
import com.formdev.flatlaf.FlatClientProperties;
import java.util.Arrays;
import javax.swing.JOptionPane;


public class AdminProfile extends javax.swing.JPanel {

    
    public AdminProfile() {
        initComponents();
        setOpaque(false);
         username.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your Username");
         newUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your New Username");
        password.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your Password");
        password.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true"
                + "");
         newPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your New Password");
        newPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true"
                + "");
         confirmNewPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Confirm Password");
        confirmNewPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true"
                + "");
    }

    private void changeAdminPassword(){
        try {
      if (username.getText().equals("")||password.getPassword().length == 0||newUsername.getText().equals("")||newPassword.getPassword().length==0||confirmNewPassword.getPassword().length==0) {
             JOptionPane.showMessageDialog(this, "Please Fill out all Fields");
            }else{
          if (!Arrays.equals(newPassword.getPassword(), confirmNewPassword.getPassword())) {
              JOptionPane.showMessageDialog(this, "New Password and Confirm Password are not matched");
          }else{
              String UserName = username.getText();
              char[] PassWord = password.getPassword();
              String NewUserName = newUsername.getText();
              char[] NewPassWord = confirmNewPassword.getPassword();
              
              DaoController daoController = new DaoController();
              daoController.changePassword(new ModelAdminData(NewPassWord, NewUserName, UserName, PassWord));
              
              
          }
          
          
      }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        username = new javax.swing.JTextField();
        newUsername = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        newPassword = new javax.swing.JPasswordField();
        confirmNewPassword = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        username.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        newUsername.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        password.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        newPassword.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        confirmNewPassword.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton1.setText("CONFIRM");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setText("CHANGE PASSWORD");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(380, 380, 380)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(confirmNewPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(newUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(newPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(password)
                    .addComponent(username)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(393, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(confirmNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            changeAdminPassword();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField confirmNewPassword;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField newPassword;
    private javax.swing.JTextField newUsername;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
