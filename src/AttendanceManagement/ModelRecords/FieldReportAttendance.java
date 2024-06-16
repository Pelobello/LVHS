/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttendanceManagement.ModelRecords;


/**
 *
 * @author USER
 */
public class FieldReportAttendance {

    /**
     * @return the day
     */
    public String getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * @return the day
     */


    /**
     * @return the amArrival
     */
    public String getAmArrival() {
        return amArrival;
    }

    /**
     * @param amArrival the amArrival to set
     */
    public void setAmArrival(String amArrival) {
        this.amArrival = amArrival;
    }

    /**
     * @return the amDeparture
     */
    public String getAmDeparture() {
        return amDeparture;
    }

    /**
     * @param amDeparture the amDeparture to set
     */
    public void setAmDeparture(String amDeparture) {
        this.amDeparture = amDeparture;
    }

    /**
     * @return the pmArrival
     */
    public String getPmArrival() {
        return pmArrival;
    }

    /**
     * @param pmArrival the pmArrival to set
     */
    public void setPmArrival(String pmArrival) {
        this.pmArrival = pmArrival;
    }

    /**
     * @return the pmDeparture
     */
    public String getPmDeparture() {
        return pmDeparture;
    }

    /**
     * @param pmDeparture the pmDeparture to set
     */
    public void setPmDeparture(String pmDeparture) {
        this.pmDeparture = pmDeparture;
    }

    /**
     * @return the workhours
     */
    public String getWorkhours() {
        return workhours;
    }

    /**
     * @param workhours the workhours to set
     */
    public void setWorkhours(String workhours) {
        this.workhours = workhours;
    }

    /**
     * @return the undertime
     */
    public String getUndertime() {
        return undertime;
    }

    /**
     * @param undertime the undertime to set
     */
    public void setUndertime(String undertime) {
        this.undertime = undertime;
    }

    /**
     * @return the overtime
     */
    public String getOvertime() {
        return overtime;
    }

    public FieldReportAttendance(String day, String amArrival, String amDeparture, String pmArrival, String pmDeparture, String workhours, String undertime, String overtime) {
        this.day = day;
        this.amArrival = amArrival;
        this.amDeparture = amDeparture;
        this.pmArrival = pmArrival;
        this.pmDeparture = pmDeparture;
        this.workhours = workhours;
        this.undertime = undertime;
        this.overtime = overtime;
    }

    public void setOvertime(String overtime) {
        this.overtime = overtime;
    }

    public FieldReportAttendance() {
    }


    
   private String day;
   private String amArrival;
   private String amDeparture;
   private String pmArrival;
   private String pmDeparture;
   private String workhours;
    private String undertime;
     private String overtime;
   
 
}
