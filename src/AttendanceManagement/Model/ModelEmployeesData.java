/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttendanceManagement.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModelEmployeesData {

    /**
     * @return the plantillaNumber
     */
    public String getPlantillaNumber() {
        return plantillaNumber;
    }

    /**
     * @param plantillaNumber the plantillaNumber to set
     */
    public void setPlantillaNumber(String plantillaNumber) {
        this.plantillaNumber = plantillaNumber;
    }

   

    /**
     * @return the idNumber
     */
    public int getIdNumber() {
        return idNumber;
    }

    /**
     * @param idNumber the idNumber to set
     */
    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
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
     * @return the dateAssumed
     */
    public Date getDateAssumed() {
        return dateAssumed;
    }

    /**
     * @param dateAssumed the dateAssumed to set
     */
    public void setDateAssumed(Date dateAssumed) {
        this.dateAssumed = dateAssumed;
    }

    public ModelEmployeesData() {
    }

    public ModelEmployeesData(int idNumber, String firstName, String middleName, String lastName, String position, String department, String plantillaNumber, Date dateAssumed) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.position = position;
        this.department = department;
        this.plantillaNumber = plantillaNumber;
        this.dateAssumed = dateAssumed;
    }


    
private int idNumber;
private String firstName;
private String middleName;
private String lastName;
private String position;
private String department;
private String plantillaNumber;
private Date dateAssumed;

public Object[]tableRow(int row){
    DateFormat df = new SimpleDateFormat("MMMM dd,yyyy");
    
    return new Object[]{idNumber,lastName,firstName,middleName,position,department, getPlantillaNumber(),dateAssumed == null ?"":df.format(dateAssumed)};
}

}
