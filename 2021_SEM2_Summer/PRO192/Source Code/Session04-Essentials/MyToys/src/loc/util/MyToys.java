package loc.util;

// đây là Class, Khuôn chứa có đặc điểm, hành dộng/ chứa các món đồ chơi dùng chung cho mọi nơi
import java.util.Scanner;

// vì nó k nhớ gì riêng tư cho nó
public class MyToys {

    // đặc điểm/ hành vi là static, cho người ta sử dụng thì thêm public
    // nếu k public thì phải qua get/set
    // k nhớ gì của riêng mình
    public static final double PI = 3.14;
    private static Scanner sc = new Scanner(System.in);
    // k phải là tính năng nên k public
    // chỉ dùng nội bộ bên trong
    
    public static int getAnInteger(String inputMsg) {
        int n;
       
        do {
            try {
                System.out.println(inputMsg);
                //n = sc.nextInt(); để lại rác
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println("Input an integer [1...7]!");
            }
            
        } while (true);
    }

    // trong quá trình làm app, việc nhập số là lặp lại nhiều lần
    public static int getAnInteger() {
        int n;
        //Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.println("Input an integer: ");
                //n = sc.nextInt(); để lại rác
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println("Not an integer!");
            }
            
        } while (true);
    }

}
