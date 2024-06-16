/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttendanceManagement.ModelRecords;

import AttendanceManagement.Model.ModelEmployeesData;
import java.io.InputStream;
import java.util.List;
import javax.swing.ImageIcon;


public class ParameterEmployee {

    /**
     * @return the field
     */
    public List<FieldEmployee> getField() {
        return field;
    }

    /**
     * @param field the field to set
     */
    public void setField(List<FieldEmployee> field) {
        this.field = field;
    }


    public String getSchool() {
        return school;
    }


    public void setSchool(String school) {
        this.school = school;
    }

    public InputStream getSchoolImage() {
        return schoolImage;
    }

    public void setSchoolImage(InputStream schoolImage) {
        this.schoolImage = schoolImage;
    }

 

    public ParameterEmployee() {
    }

    public ParameterEmployee(String school, InputStream schoolImage, List<FieldEmployee> field) {
        this.school = school;
        this.schoolImage = schoolImage;
        this.field = field;
    }

   
    
    private String school;
    private InputStream schoolImage;
    private List<FieldEmployee>field;
}
