
package AttendanceManagement.Forms;

import AttendanceManagement.Controller.EmployeesController;
import AttendanceManagement.EventItem.EventItem;
import AttendanceManagement.Items.EmployeesItem;
import AttendanceManagement.Model.ModelEmployees;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import raven.glasspanepopup.GlassPanePopup;

public class EmployeesProfileForms extends javax.swing.JPanel {

    public EventItem getEventItem() {
        return eventItem;
    }

   
    public void setEventItem(EventItem eventItem) {
        this.eventItem = eventItem;
    }
private EmployeesController employeesController = new EmployeesController(this);
  private EventItem eventItem; 
    public EmployeesProfileForms() {
        initComponents();
        setOpaque(false);
       init();        
    }
    private void init(){
          jPanel1.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:10;"
                + "");
                  Search.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
           Search.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("AttendanceManagement/Images_Icons/search.svg"));

    }
    public void addEmployees(ModelEmployees data){
        EmployeesItem employeesItem = new EmployeesItem();
        employeesItem.setData(data);
         employeesItem.addMouseListener(new MouseAdapter() {
           @Override
           public void mousePressed(MouseEvent e) {
                          if (SwingUtilities.isLeftMouseButton(e)) {
                    eventItem.itemClick(employeesItem, data);
                }
           }
       });
          employeesItem.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseEntered(MouseEvent e) {
        // Set the cursor to the hand cursor
        employeesItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Optionally, reset the cursor when the mouse exits the component
        employeesItem.setCursor(Cursor.getDefaultCursor());
    }
});
        panelItem.add(employeesItem);
        repaint();
        revalidate();
        
    }
      public void setSelected(Component item) {
        for (Component com : panelItem.getComponents()) {
            EmployeesItem i = (EmployeesItem) com;
            if (i.isSelected()) {
                i.setSelected(false);
            }
        }
        ((EmployeesItem) item).setSelected(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelItem = new AttendanceManagement.Components.PanelItem();
        Search = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Employees Profile", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 102, 102))); // NOI18N
        jScrollPane1.setViewportView(panelItem);

        Search.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1120, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyReleased
      
     panelItem.removeAll();
        repaint();
        revalidate();
        employeesController.searchEmployeesProfile(Search.getText().trim());

    }//GEN-LAST:event_SearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Search;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public AttendanceManagement.Components.PanelItem panelItem;
    // End of variables declaration//GEN-END:variables
}
