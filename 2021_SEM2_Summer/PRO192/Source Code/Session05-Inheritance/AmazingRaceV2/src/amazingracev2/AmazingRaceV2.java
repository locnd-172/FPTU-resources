
package amazingracev2;

import data.DeathRacer;
import data.Dog;
import data.Motor;
import data.Pet;
import java.util.Random;

public class AmazingRaceV2 {
    public static void main(String[] args) {
       runToDead();
    }
    
    public static void runToDead() {
        DeathRacer mnnCTT = new DeathRacer() {
            public static final double MAX_SPEED = 50.0;
            @Override
            public double runToDead() {
                return new Random().nextDouble() * MAX_SPEED * 3;
            }

            @Override
            public void showHowToDead() {
                 System.out.printf("|%-20s|%-15s|2015|%5.1f|\n",
                        "MERMAID-RACER", "MNN CTT", runToDead());
            }
        };
        
        Dog d1 = new Dog("NGÁO ĐÁ", 2021, 5.0);
        Pet d2 = new Dog("VÀNG ƠI", 1950, 10.0);
        DeathRacer d3 = new Dog("NANH TRẮNG", 1901, 20.0);
        // chấm chỉ xố full CLB, 2 hàm runToDead(), showHowToDead()
        
        Motor m1 = new Motor("EXCITER", "150.0cm3", "58F8-22204");
        DeathRacer m2 = new Motor("WINNER", "150.0cm3", "56F6-67889");
        
        DeathRacer racer[] = {d1, (Dog)d2, d3, m1, m2, mnnCTT};
        // toàn bộ là DeathRacer, đa hình trên runToDead(), showHowToDead()
        for (DeathRacer x : racer) {
            x.showHowToDead();
        }
    }
    
}
