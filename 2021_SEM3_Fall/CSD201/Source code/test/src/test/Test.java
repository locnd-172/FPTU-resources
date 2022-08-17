/*
    THIS keyword

 */
package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author LocNgD <locndse160199@fpt.edu.vn>
 */
public class Test {

    private int radius;

    public Test() {
    }

    public Test(int radius) {
        this.radius = radius;
    }

    void m() {
        System.out.println("Gọi phương thức bằng từ khóa this");
    }

    void n() {
        this.m();
    }

    void p() {
        n();// trình biên dịch sẽ thêm this để gọi phương thức n() như this.n()
    }

    void M(Test obj) {
        System.out.println("Hello Java");
    }

    void P() {
        M(this);
    }

    public static void main(String[] args) throws ParseException {
        // 1. Tham chiếu tới biến INSTANCE
        //Test circle = new Test(4); //Khởi tạo một circle có radius = 4
        //System.out.println(circle.radius); //In giá trị của radiusra màn hình
        //--------------

        // 2. Gọi tới 1 CONSTRUCTOR khác trong CLASS
        //Student std_1 = new Student(1510510); //Khởi tạo một sinh viên có id là 1510510
        //std_1.display();
        //Student std_2 = new Student(1510510, "Steven");//Khởi tạo một sinh viên có id là 1510510 và tên Steven
        //std_2.display();
        //--------------
        // 3. Trả về CLASS hiện tại
        //Student std = new Student("Steven", 1);
        //System.out.println("Student's name: " + std.getStudent().name);
        //System.out.println("Student's id: " + std.getStudent().id);
        //System.out.println("");
        //System.out.println("Student's name: " + std.name);
        //System.out.println("Student's id: " + std.id);
        //--------------
        // 4. Gọi METHODS của CLASS hiện tại
//        Test o1 = new Test();
//        o1.p();
//        //--------------
//        
//        // 5. Sử dụng THIS như tham số của METHOD
//        Test o2 = new Test();
//        o2.P();
//        //--------------
        // 6. Trả về INSTANCE của CLASS hiện tại
        
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date firstDate = sdf.parse("06/24/2017");
        Date secondDate = sdf.parse("07/22/2017");

        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        long week = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) / 7;

        System.out.println(diff + " " + week);
    }

}
