package container;

import data.Student;
import data.Vaccine;
import data.VaccineInjection;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import util.Validation;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class VaccineInjectionList {

    private int halt = 0, dup = 0;
    private String warn = "";
    private ArrayList<Student> studentList = new ArrayList();
    private ArrayList<Vaccine> vaccineList = new ArrayList();
    private ArrayList<VaccineInjection> vaccineInjectionList = new ArrayList();
    private final SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    private final String header = String.format("%-12s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-8s | %-8s", 
                            "INJECTION ID", "STUDENT ID", "VACCINE ID", "1st DATE", "1st PLACE", "2nd DATE", "2nd PLACE", "1st DOSE", "2nd DOSE");
    
    public VaccineInjectionList() {
        writeStudent();
        writeVaccine();
    }

    public void emptyNotify() {
        System.out.println("The vaccine injection list is empty.");
        Validation.getEnter("ENTER to back to main menu...");
    }

    public void addNewStudent(String stuId, String stuName) {
        int pos = searchStudentById(stuId);
        if (pos == -1) {
            studentList.add(new Student(stuId, stuName));
        } else {
            studentList.remove(pos);
            studentList.add(new Student(stuId, stuName));
            dup = 1;
        }
    }
    
    public void addNewVaccine(String vacId, String vacName) {
        int pos = searchVaccinetById(vacId);
        if (pos == -1) {
            vaccineList.add(new Vaccine(vacId, vacName));
        } else {
            vaccineList.remove(pos);
            vaccineList.add(new Vaccine(vacId, vacName));
            dup = 1;
        }
    }
    
    public void writeStudent() {
        try {
            String fileName = "student.dat";
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream oStream = new ObjectOutputStream(file);
            
            dup = 0;
            addNewStudent("SE160171", "Le Ngoc Son");
            addNewStudent("SE160174", "Nguyen Trung Hieu");
            addNewStudent("SE160182", "Tran Thanh Quang");
            addNewStudent("SE160189", "Dang Quoc Tuan");
            addNewStudent("SE160197", "Nguyen Phuoc Tung");
            addNewStudent("SE160199", "Nguyen Dang Loc");
            addNewStudent("SE160221", "Nguyen Vi Khang");
            addNewStudent("SE160226", "Le Ngoc Bao Duy");
            addNewStudent("SE160227", "Nguyen Huy Hieu");
            addNewStudent("SE160232", "Pham Khac Trieu");

            for (Student st : studentList) {
                oStream.writeObject(st);
            }
            if (dup == 1) {
                warn += "Waning: Student list contains duplicate ID, earlier ID will be overwriten by a newer one!";
            }
            oStream.close();
            file.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public void writeVaccine() {
        try {
            String fileName = "vaccine.dat";
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream oStream = new ObjectOutputStream(file);
            
            dup = 0;
            addNewVaccine("V001", "AstraZeneca");
            addNewVaccine("V002", "SPUTNIK V");
            addNewVaccine("V003", "Vero Cell");
            addNewVaccine("V004", "Pfizer");
            addNewVaccine("V005", "Moderna");
            
            for (Vaccine vc : vaccineList) {
                oStream.writeObject(vc);
            }
            if (dup == 1) {
                warn += "\nWaning: Vaccine list contains duplicate ID, arlier ID will be overwriten by a newer one!";
            }
            oStream.close();
            file.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public void printStudentList() {
        if (warn.length() != 0) System.out.println(warn);
        System.out.println("___________ STUDENT LIST ___________");
        System.out.printf("%-3s | %-10s | %-20s\n", "NO", "STUDENT ID", "NAME");
        for (int i = 0; i < studentList.size(); i++) {
            System.out.printf("%-3d | ", i + 1);
            studentList.get(i).showInfo();
        }
        Validation.getEnter("ENTER to back to main menu...");
    }

    public void addInjection() {
        if (fullTwoInjection() == true) return;
        
        System.out.println("___________ ADD INJECTION INFORMATION ___________");
        System.out.println("Injection ID includes 'I' (uppercase 'i') + 3 digits (e.g: I001)\n");

        halt = 0;
        do {
            if (checkInjectOfStudent() == 1) {
                System.out.println("All of the student have completed 1st injection!");
                Validation.getEnter("ENTER to back to main menu...");
                return;
            }
            if (addFirstInjection() == true) {
                System.out.println("ADD SUCCESSFUL!");
            } else {
                System.err.println("ADD FAILED!");
            }
            halt = (Validation.confirmContinue("\nContinue adding? (y/n)... ", "Input 'Y' / 'N'!") == 1 ? 0 : 1);
            System.out.println("");
        } while (halt == 0);

    }

    public boolean addFirstInjection() {
        try {
            String injectionId, studentId, vaccineId, place1, date1;
            
            int pos = 0;
            
            do {
                injectionId = Validation.inputId("Injection ID: ", "Error: Wrong injection ID format!\nID includes 'I' (uppercase 'i') + 3 digits (e.g: I001)!", "I");
                pos = searchInjectionById(injectionId);
                if (pos != -1) {
                    System.err.println("This injection ID already exist! Enter another one!");
                }
            } while (pos != -1);

            pos = 0;
            do {
                studentId = Validation.inputStudentId("Student ID: ", "Error: Wrong student ID format!\nID includes 2 uppercase letters + 6 digits (e.g: SE161011)!");
                pos = searchStudentById(studentId);
                if (pos == -1) {
                    System.err.println("Student " + studentId + " does not exist! Enter another one!");
                    continue;
                }
                int check = 0;
                check = searchInjectionByStudentId(studentId);
                if (check != -1) {
                    pos = -1;
                    String id = vaccineInjectionList.get(check).getInjectionId();
                    System.err.println("This student have already had an injection ID (" + id + ")");
                } else break;
                
            } while (pos == -1);
            
            
            printVaccineList();
            pos = 0;
            do {
                vaccineId = Validation.inputId("Vaccine ID: ", "Error: Wrong vaccine ID format!\nID includes 'V' (uppercase 'v') + 3 digits (e.g: V001)!", "V");
                pos = searchVaccinetById(vaccineId);
                if (pos == -1) {
                    System.err.println("Vaccine " + vaccineId + " does not exist! Enter another one!");
                }
            } while (pos == -1);

            place1 = Validation.inputString("1st Place: ").toUpperCase();
            date1 = Validation.inputDate("1st Date (dd-mm-yyyy): ", 1);

            vaccineInjectionList.add(new VaccineInjection(injectionId, studentId, vaccineId, place1, df.parse(date1), true));
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    public int searchInjectionByStudentId(String cmpId) {
        for (int i = 0; i < vaccineInjectionList.size(); ++i) {
            String curId = vaccineInjectionList.get(i).getStudentId();
            if (cmpId.equalsIgnoreCase(curId)) 
                return i;
        }
        return -1;
    }

    public int searchInjectionById(String cmpId) {
        for (int i = 0; i < vaccineInjectionList.size(); ++i) {
            String curId = vaccineInjectionList.get(i).getInjectionId();
            if (cmpId.equalsIgnoreCase(curId)) 
                return i;
        }
        return -1;
    }

    public int searchStudentById(String cmpId) {
        for (int i = 0; i < studentList.size(); ++i) {
            String curId = studentList.get(i).getStudentID();
            if (cmpId.equalsIgnoreCase(curId)) 
                return i;
        }
        return -1;
    }

    public int searchVaccinetById(String cmpId) {
        for (int i = 0; i < vaccineList.size(); ++i) {
            String curId = vaccineList.get(i).getVaccineId();
            if (cmpId.equalsIgnoreCase(curId)) 
                return i;
        }
        return -1;
    }

    public boolean fullTwoInjection() {
        if (checkInjectOfStudent() == 2) {
            System.out.println("All of the student have completed 2 injections!");
            Validation.getEnter("ENTER to back to main menu...");
            return true;
        }
        return false;
    }
    
    public void updateInjection() {
        if (vaccineInjectionList.isEmpty()) {
            emptyNotify();
            return;
        }
        if (fullTwoInjection() == true) return;
        if (nearlyFull2Injection() == true) return;
        
        System.out.println("___________ UPDATE INJECTION INFORMATION ___________");
        halt = 0;
        do {
            if (fullTwoInjection() == true) return;
            if (nearlyFull2Injection() == true) return;
            
            int udp = updateSecondInjection();
            if (udp == -1) {
                System.err.println("UPDATE FAILED!");
            }
            halt = (Validation.confirmContinue("\nContinue updating? (y/n)...", "Input 'Y' / 'N'!") == 1 ? 0 : 1);
            System.out.println("");
        } while (halt == 0);
    }
    
    public int updateSecondInjection() {
        try {
            String injectionId, place2, date2;

            int pos = 0;
            injectionId = Validation.inputId("Enter injection ID: ", "Error: Wrong ID format!\nID includes 'I' + 3 digits (e.g: I001)!", "I");

            pos = searchInjectionById(injectionId);
            if (pos == -1) {
                System.out.println("Injection ID " + injectionId + " does not exist!");
                return 0;
            }

            VaccineInjection curInjection = vaccineInjectionList.get(pos);
            System.out.print("Current status: ");
            printAnInjection(curInjection);
            
            // 2nd injection already
            if (curInjection.isShot2() == true) {
                System.out.println("Student has completed 2 injections!");
                return 0;
            }

            place2 = Validation.inputString("2nd Place: ").toUpperCase();
            date2 = Validation.inputDate("2nd Date (dd-mm-yyyy): ", 2);

            // check time between 2 injection
            Date d1 = curInjection.getDate1();
            Date d2 = df.parse(date2);
            long diffInMillies =  d2.getTime() - d1.getTime();
            long dWeeks = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) / 7;
            if (d2.before(d1)) {
                System.err.println("2nd injection can't be before 1st injection.");
                 throw new Exception();
            }
            if (dWeeks > 12) {
                System.err.println("Injection time is over 12 weeks!"
                        + "\n2nd injection must be given 4 - 12 weeks after 1st injection.");
                throw new Exception();
            }
            if (dWeeks < 4) {
                System.err.println("Injection time is too early!"
                        + "\n2nd injection must be given 4 - 12 weeks after 1st injection.");
                throw new Exception();
            }
            
            curInjection.setPlace2(place2);
            curInjection.setDate2(df.parse(date2));
            curInjection.setShot2(true);
            
            System.out.println("\nUpdated status: ");
            printAnInjection(curInjection);
            System.out.println("UPDATE SUCCESSFUL! Student has completed 2 injections!");
            
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }
    
    public boolean nearlyFull2Injection() {
        for (int i = 0; i < vaccineInjectionList.size(); i++) {
            boolean c1 = vaccineInjectionList.get(i).isShot1();
            boolean c2 = vaccineInjectionList.get(i).isShot2();
            if (c1 != true || c2 != true) return false;
        }
        System.out.println("Everyone in the vaccine injection list has completed 2 injections!");
        System.out.println("Try giving 1st injection for some other student and comeback.\n");
        Validation.getEnter("ENTER to back to main menu...");
        return true;
    }
    
    public int checkInjectOfStudent() {
        if (vaccineInjectionList.size() == studentList.size()) {
            for (int i = 0; i < studentList.size(); i++) {
                String stuId = studentList.get(i).getStudentID();
                int pos = searchInjectionByStudentId(stuId);

                VaccineInjection cur = vaccineInjectionList.get(pos);
                boolean c2 = cur.isShot2();
                if (c2 == false) return 1; 
            }
            return 2;
        }
        return 0;
    }

    public void removeInjectionById() {
        if (vaccineInjectionList.isEmpty()) {
            emptyNotify();
            return;
        }
        System.out.println("___________ REMOVE AN INJECTION BY ID ___________");
        String id;
        int pos;

        id = Validation.inputId("Enter injection ID: ", "Please input valid ID (e.g: I001)", "I");
        pos = searchInjectionById(id);

        if (pos == -1) {
            System.out.println("NOT FOUND! Injection with ID " + id + " does not exist.");
        } else {
            System.out.print("Injection to be removed: ");
            printAnInjection(vaccineInjectionList.get(pos));

            if (Validation.confirmContinue("\nAre you sure? (y/n)... ", "Input 'Y' / 'N'!") == 1) {
                vaccineInjectionList.remove(pos);
                System.out.println("REMOVE SUCCESSFUL!");
            } else {
                System.out.println("REMOVE FAILED!");
            }
        }
        Validation.getEnter("ENTER to back to main menu...\n");
    }

    public void searchInjection() {
        if (vaccineInjectionList.isEmpty()) {
            emptyNotify();
            return;
        }
        System.out.println("___________ SEARCH AN INJECTION BY STUDENT ID ___________");
        halt = 0;
        do {
            searchAnInjection();
            halt = (Validation.confirmContinue("\nContinue searching? (y/n)... ", "Input 'Y' / 'N'!") == 1 ? 0 : 1);
        } while (halt == 0);
    }

    public void searchAnInjection() {
        String cmpStudentId;

        int pos = 0;
        cmpStudentId = Validation.inputStudentId("Enter student ID: ", "Please input valid ID (e.g: SE161011)");
        
        pos = searchStudentById(cmpStudentId);
        if (pos == -1) {
            System.out.println("Student " + cmpStudentId + " does not exist! Enter another one!");
            return;
        }
        
        pos = searchInjectionByStudentId(cmpStudentId);
        if (pos == -1) {
            System.out.println("NOT FOUND! This student doesn't have any injection ID.");
        } else {
            System.out.println(header);
            vaccineInjectionList.get(pos).showInfo();
        }
    }
    
    public void printAnInjection(VaccineInjection curInjection) {
        String injId = curInjection.getInjectionId();
        String stuId = curInjection.getStudentId();
        String vacId = curInjection.getVaccineId();
        String d1 = df.format(curInjection.getDate1());
        String d2;
        if (curInjection.getDate2() == null) d2 = "not yet";
        else d2 = df.format(curInjection.getDate2());
        
        String p1 = curInjection.getPlace1();
        String p2;
        if (curInjection.getPlace2() == "") p2 = "not yet";
        else p2 = curInjection.getPlace2();
        
        String out = String.format("%s | %s | %s | 1st injection: %s, %s | 2nd injection: ", 
                                    injId, stuId, vacId, d1, p1);
        if (d2.equals("not yet")) out += d2;
        else out += String.format("%s, %s", d2, p2);
        
        System.out.println(out);

    }

    public void printVaccineList() {
        System.out.printf("%-10s | %-20s\n", "VACCINE ID", "NAME");
        for (Vaccine x : vaccineList) {
            x.showInfo();
        }
    }
}
