/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package AttendanceManagement.Items;

import AttendanceManagement.Model.ModelEmployees;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author USER
 */
public class EmployeesItem extends javax.swing.JPanel {

   
    public ModelEmployees getData() {
        return data;
    }

  
    public void setData(ModelEmployees data) {
        this.data = data;
        profileImage.setImage(data.getEmployeesImage());
        String idData = Integer.toString(data.getId());
        firstName.setText(data.getFirstName()+" "+data.getLastName());
//        lastName.setText(data.getLastName());
        idNumber.setText(idData);
    }

  
    public boolean isSelected() {
        return selected;
    }

   
    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }

    public EmployeesItem() {
        initComponents();
        putClientProperty(FlatClientProperties.STYLE, ""
                + "arc: 0;"
                + "background: #F7FBFC;"
                + "foreground: #333333;"
                
        );
           panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc: 20;"
                + "background: #F7FBFC;"
                + "foreground: #333333;"
                
        );
        
    }
    private ModelEmployees data;
    private boolean selected;
    @Override
    public void paint(Graphics g) {
         Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(234, 223, 200));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
        if (isSelected()) {
            g2.setColor(new Color(200,156,255));
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
        }
        
        g2.dispose();
        super.paint(g); 
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        profileImage = new AttendanceManagement.Components.ImageBox();
        firstName = new javax.swing.JLabel();
        idNumber = new javax.swing.JLabel();

        panel.setBackground(new java.awt.Color(255, 255, 255));

        profileImage.setImage(new javax.swing.ImageIcon(getClass().getResource("/AttendanceManagement/Images_Icons/profile.png"))); // NOI18N

        firstName.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        firstName.setForeground(new java.awt.Color(51, 51, 51));
        firstName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        firstName.setText("Dazzle");

        idNumber.setBackground(new java.awt.Color(255, 255, 255));
        idNumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        idNumber.setForeground(new java.awt.Color(51, 51, 51));
        idNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        idNumber.setText("59798");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(profileImage, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(firstName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profileImage, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(idNumber)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel firstName;
    private javax.swing.JLabel idNumber;
    private javax.swing.JPanel panel;
    private AttendanceManagement.Components.ImageBox profileImage;
    // End of variables declaration//GEN-END:variables
}
