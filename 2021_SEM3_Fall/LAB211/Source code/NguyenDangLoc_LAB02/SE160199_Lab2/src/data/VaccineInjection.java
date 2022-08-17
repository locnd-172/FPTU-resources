package data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class VaccineInjection {
    private String injectionId;
    private String studentId;
    private String vaccineId;
    private String place1, place2 = "";
    private Date date1, date2 = null;
    private boolean shot1, shot2 = false;

    public VaccineInjection(String injectionId, String studentId, String vaccineId, String place1, Date date1, boolean shot1) {
        this.injectionId = injectionId;
        this.studentId = studentId;
        this.vaccineId = vaccineId;
        this.place1 = place1;
        this.date1 = date1;
        this.shot1 = shot1;
    }

    public String getInjectionId() {
        return injectionId;
    }

    public void setInjectionId(String injectionId) {
        this.injectionId = injectionId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getPlace1() {
        return place1;
    }

    public void setPlace1(String place1) {
        this.place1 = place1;
    }

    public String getPlace2() {
        return place2;
    }

    public void setPlace2(String place2) {
        this.place2 = place2;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public boolean isShot1() {
        return shot1;
    }

    public void setShot1(boolean shot1) {
        this.shot1 = shot1;
    }

    public boolean isShot2() {
        return shot2;
    }

    public void setShot2(boolean shot2) {
        this.shot2 = shot2;
    }

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String d1 = df.format(date1);
        String d2; 
        if (date2 == null) d2 = "not yet";
        else d2 = df.format(date2);
        if (place2 == "") place2 = "not yet";
        String s1 = (shot1 == true ? "YES" : "NO");
        String s2 = (shot2 == true ? "YES" : "NO");
        return String.format("%-12s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %8s | %8s", 
                   injectionId, studentId, vaccineId, d1, place1, d2, place2, s1, s2);
    }
    
    public void showInfo() {
        System.out.println(this.toString());
    }
    
}
