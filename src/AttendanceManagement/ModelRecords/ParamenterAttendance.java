/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttendanceManagement.ModelRecords;

import java.util.List;


public class ParamenterAttendance {

    /**
     * @return the principal
     */
    public String getPrincipal() {
        return principal;
    }

    /**
     * @param principal the principal to set
     */
    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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

    /**
     * @return the fields
     */
    public List<FieldReportAttendance> getFields() {
        return fields;
    }

  
    public void setFields(List<FieldReportAttendance> fields) {
        this.fields = fields;
    }

    public ParamenterAttendance() {
    }

    public ParamenterAttendance(String name, String month, int year, List<FieldReportAttendance> fields, String principal) {
        this.name = name;
        this.month = month;
        this.year = year;
        this.fields = fields;
        this.principal = principal;
    }

 
    
    private String name;
    private String month;
    private int year;
    private List<FieldReportAttendance>fields;
    private String principal;
}
