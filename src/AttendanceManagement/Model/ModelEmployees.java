
package AttendanceManagement.Model;

import java.sql.Date;
import javax.swing.Icon;



public class ModelEmployees {

  
    public int getIdData() {
        return idData;
    }

    /**
     * @param idData the idData to set
     */
    public void setIdData(int idData) {
        this.idData = idData;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getDateAssumed() {
        return dateAssumed;
    }

    public void setDateAssumed(Date dateAssumed) {
        this.dateAssumed = dateAssumed;
    }

    public int getPlantillaNumber() {
        return plantillaNumber;
    }

    public void setPlantillaNumber(int plantillaNumber) {
        this.plantillaNumber = plantillaNumber;
    }

    public Icon getEmployeesImage() {
        return employeesImage;
    }

    public void setEmployeesImage(Icon employeesImage) {
        this.employeesImage = employeesImage;
    }

    public ModelEmployees() {
        
    }

    public ModelEmployees(int id, String firstName, String middleName, String lastName, String position, String department, Date dateAssumed, int plantillaNumber, Icon employeesImage) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.position = position;
        this.department = department;
        this.dateAssumed = dateAssumed;
        this.plantillaNumber = plantillaNumber;
        this.employeesImage = employeesImage;
    }
    
   
    private int idData;
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String position;
    private String department;
    private Date dateAssumed;
    private int plantillaNumber;
    private Icon employeesImage;

    
}
