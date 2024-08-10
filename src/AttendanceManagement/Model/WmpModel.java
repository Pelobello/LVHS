
package AttendanceManagement.Model;

import java.time.LocalTime;


public class WmpModel {

  
    public int getEmployeesID() {
        return employeesID;
    }

 
    public void setEmployeesID(int employeesID) {
        this.employeesID = employeesID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

 
    public String getDepartment() {
        return department;
    }

 
    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalTime getAmTimeIn() {
        return amTimeIn;
    }

    public void setAmTimeIn(LocalTime amTimeIn) {
        this.amTimeIn = amTimeIn;
    }

    public LocalTime getAmTimeOut() {
        return amTimeOut;
    }

    public void setAmTimeOut(LocalTime amTimeOut) {
        this.amTimeOut = amTimeOut;
    }

    public LocalTime getPmTimeIn() {
        return pmTimeIn;
    }

    public void setPmTimeIn(LocalTime pmTimeIn) {
        this.pmTimeIn = pmTimeIn;
    }

    public LocalTime getPmTimeOut() {
        return pmTimeOut;
    }

    public void setPmTimeOut(LocalTime pmTimeOut) {
        this.pmTimeOut = pmTimeOut;
    }

    public WmpModel(int employeesID, String fullName, String department, LocalTime amTimeIn, LocalTime amTimeOut, LocalTime pmTimeIn, LocalTime pmTimeOut) {
        this.employeesID = employeesID;
        this.fullName = fullName;
        this.department = department;
        this.amTimeIn = amTimeIn;
        this.amTimeOut = amTimeOut;
        this.pmTimeIn = pmTimeIn;
        this.pmTimeOut = pmTimeOut;
    }

    public WmpModel() {
    }
    
    
        private int employeesID;
    private String fullName;
    private String department;
    private LocalTime amTimeIn;
    private LocalTime amTimeOut;
    private LocalTime pmTimeIn;
    private LocalTime pmTimeOut;
}
