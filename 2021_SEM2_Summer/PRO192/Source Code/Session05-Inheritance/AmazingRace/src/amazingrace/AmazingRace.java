package amazingrace;

// 02/07/2021 + 06/07/2021 [live] - Anonymous

import data.Cat;
import data.Dog;
import data.Hamster;
import data.Pet;
import java.util.Random;


public class AmazingRace {
    public static void main(String[] args) {
        showRecords();
    }
    
    public static void showRecords() {
        
        Pet ufo = new Pet("UFO", 2021, 0.3) {
            @Override
            public double run() {
                return new Random().nextDouble() * 30;
            }
            
            @Override
            public void showRecord() {
                System.out.printf("|%-10s|%-10s|%4d|%4.1f|%4.1f|\n",
                            "XXX", name, yob, weight, run());
            }
        };
        Hamster x = new Hamster("NHẤT", 2021, 0.3);
        Pet racer[] = new Pet[] {new Dog("CHIHUHU", 2021, 0.5),
                                 new Dog("VÀNG ƠI", 1950, 10.0),
                                 new Cat("TOM", 1960, 15.0),
                                 new Cat("KITTY", 1990, 5.0),
                                 new Hamster("JERRY", 1960, 0.5),
                                 x,
                                 ufo
                                };
        System.out.println("The records table");
        for (Pet xx : racer) {
            xx.showRecord();
        }
        
        for (int i = 0; i < racer.length - 1; i++) {
            for (int j = i+1; j < racer.length; j++) {
                // ở bài này, mỗi lần gọi RUN() thì tốc độ sẽ thay đổi
            }
        }
    }
}
