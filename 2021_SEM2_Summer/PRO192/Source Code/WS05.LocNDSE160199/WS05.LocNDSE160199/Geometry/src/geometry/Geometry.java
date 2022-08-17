package geometry;

import data.RightTriangle;
import data.Triangle;
import data.CheckTriangle;

public class Geometry {

    public static void main(String[] args) {
        Triangle Tr[] = new Triangle[6];
        int size = makeTriangles(Tr);
        sortTriangles(Tr, size);
    }

    public static int makeTriangles(Triangle Tr[]) {
        CheckTriangle Pre[] = new CheckTriangle[6];

        // make general triangles
        Pre[0] = new CheckTriangle(10.5, 2.0, 7.5);
        Pre[1] = new CheckTriangle(9.5, 4.75, 5);
        Pre[2] = new CheckTriangle(8.0, 6.0, 11.0);

        int cnt = 0;
        for (int i = 0; i < 3; ++i) {
            if (Pre[i].isTriangle() == false) {
                Pre[i].printInfo();
                System.out.println(" Not a triangle!");
                continue;
            }
            Tr[cnt++] = new Triangle(Pre[i].getA(), Pre[i].getB(), Pre[i].getC());
        }
        
        // make right triangles
        Tr[cnt++] = new RightTriangle(7.0, 4.0);
        Tr[cnt++] = new RightTriangle(12.0, 6.5);
        Tr[cnt++] = new RightTriangle(6.5, 6.5);
        
        return cnt;
    }

    public static void sortTriangles(Triangle Tr[], int size) {
        System.out.println("Triangle list before sorting");
        for (int i = 0; i < size; i++) 
            Tr[i].printInfo();
        

        for (int i = 0; i < size - 1; i++) 
            for (int j = i + 1; j < size; j++) 
                if (Tr[i].getArea() > Tr[j].getArea()) {
                    Triangle tmp = Tr[i];
                    Tr[i] = Tr[j];
                    Tr[j] = tmp;
                }
            
        System.out.println("___________________________");
        System.out.println("Triangle list after sorting");
        for (int i = 0; i < size; i++) 
            Tr[i].printInfo();
    }
}
