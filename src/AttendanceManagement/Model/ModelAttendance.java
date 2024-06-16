/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttendanceManagement.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.Icon;


public class ModelAttendance {

    /**
     * @return the pastEmpID
     */
    public int getPastEmpID() {
        return pastEmpID;
    }

    /**
     * @param pastEmpID the pastEmpID to set
     */
    public void setPastEmpID(int pastEmpID) {
        this.pastEmpID = pastEmpID;
    }

    /**
     * @return the updatedEmpID
     */
 

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the employeesImage
     */
    public Icon getEmployeesImage() {
        return employeesImage;
    }

    /**
     * @param employeesImage the employeesImage to set
     */
    public void setEmployeesImage(Icon employeesImage) {
        this.employeesImage = employeesImage;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the employeesID
     */
    public int getEmployeesID() {
        return employeesID;
    }

    /**
     * @param employeesID the employeesID to set
     */
    public void setEmployeesID(int employeesID) {
        this.employeesID = employeesID;
    }

    /**
     * @return the employeesFullName
     */
    public String getEmployeesFullName() {
        return employeesFullName;
    }

    /**
     * @param employeesFullName the employeesFullName to set
     */
    public void setEmployeesFullName(String employeesFullName) {
        this.employeesFullName = employeesFullName;
    }

    /**
     * @return the amTimeIn
     */
    public LocalTime getAmTimeIn() {
        return amTimeIn;
    }

    /**
     * @param amTimeIn the amTimeIn to set
     */
    public void setAmTimeIn(LocalTime amTimeIn) {
        this.amTimeIn = amTimeIn;
    }

    /**
     * @return the amTimeOut
     */
    public LocalTime getAmTimeOut() {
        return amTimeOut;
    }

    /**
     * @param amTimeOut the amTimeOut to set
     */
    public void setAmTimeOut(LocalTime amTimeOut) {
        this.amTimeOut = amTimeOut;
    }

    /**
     * @return the pmTimeIn
     */
    public LocalTime getPmTimeIn() {
        return pmTimeIn;
    }

    /**
     * @param pmTimeIn the pmTimeIn to set
     */
    public void setPmTimeIn(LocalTime pmTimeIn) {
        this.pmTimeIn = pmTimeIn;
    }

    /**
     * @return the pmTimeOut
     */
    public LocalTime getPmTimeOut() {
        return pmTimeOut;
    }

    /**
     * @param pmTimeOut the pmTimeOut to set
     */
    public void setPmTimeOut(LocalTime pmTimeOut) {
        this.pmTimeOut = pmTimeOut;
    }

    /**
     * @return the department
     */
  
  
    private int pastEmpID;
    private Icon employeesImage;
    private String department;
    private int employeesID;
    private String employeesFullName;
    private LocalTime amTimeIn;
    private LocalTime amTimeOut;
    private LocalTime pmTimeIn;
    private LocalTime pmTimeOut;
    private Date dateCreated;
    
    public Object[]toTableRow(int rowNum){
        DateFormat df = new SimpleDateFormat("MMMM dd,yyyy");
 
        return new Object[]{employeesID,employeesFullName,department,amTimeIn,amTimeOut,pmTimeIn,pmTimeOut,df.format(dateCreated)};
    }

    @Override
    public String toString() {
        return employeesFullName; 
    }
    
}
