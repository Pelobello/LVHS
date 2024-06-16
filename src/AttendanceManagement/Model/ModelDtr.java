
package AttendanceManagement.Model;


public class ModelDtr {

    /**
     * @return the employeesId
     */
    public int getEmployeesId() {
        return employeesId;
    }

    /**
     * @param employeesId the employeesId to set
     */
    public void setEmployeesId(int employeesId) {
        this.employeesId = employeesId;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    public ModelDtr(int employeesId, String month, int year) {
        this.employeesId = employeesId;
        this.month = month;
        this.year = year;
    }

    
    public ModelDtr() {
    }
    
    
    private int employeesId;
    private String month;
    private int year;
}
