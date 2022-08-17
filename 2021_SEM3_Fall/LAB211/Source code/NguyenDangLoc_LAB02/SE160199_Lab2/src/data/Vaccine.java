package data;

import java.io.Serializable;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Vaccine implements Serializable {
    private String vaccineId;
    private String vaccineName;

    public Vaccine(String vaccineId, String vaccineName) {
        this.vaccineId = vaccineId;
        this.vaccineName = vaccineName;
    }

    public String getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-20s", vaccineId, vaccineName);
    }
    
    public void showInfo() {
        System.out.println(this.toString());
    }
}
