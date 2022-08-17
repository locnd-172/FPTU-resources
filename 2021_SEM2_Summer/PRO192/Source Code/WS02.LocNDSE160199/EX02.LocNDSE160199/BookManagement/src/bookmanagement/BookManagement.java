package bookmanagement;

import data.Book;

public class BookManagement {

    public static void main(String[] args) {
        System.out.println("INFORMATION OF SOME BOOKS ON TIKI.");        
        
        Book matBiec = new Book("Mat Biec", 62000, "Nguyen Nhat Anh", "NXB Tre", 2019, "Novel", 300);
        Book luocSu = new Book("Sapiens: Luoc su loai nguoi", 170000, "Noah Harari", "NXB Tri thuc", 2017, "Science", 554);
        Book bayCuu = new Book("Su im lang cua bay cuu", 75000, "Thomas Harris", "NXB Hoi nha van", 2018, "Novel", 347);
        
        System.out.println("#1");
        matBiec.showBookInfo();
        
        System.out.println("___________________");
        System.out.println("#2");
        luocSu.showBookInfo();
        
        System.out.println("___________________");
        System.out.println("#3");
        bayCuu.showBookInfo();
    }
    
}
