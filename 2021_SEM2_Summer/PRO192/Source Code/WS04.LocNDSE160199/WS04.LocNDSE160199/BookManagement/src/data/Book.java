package data;


public class Book {
    private String name;
    private double price;
    private String author;
    private String publisher;
    private int yop;
    private int nPages;

    public Book(String name, double price, String author, String publisher, int yop, int nPages) {
        this.name = name;
        this.price = price;
        this.author = author;
        this.publisher = publisher;
        this.yop = yop;
        this.nPages = nPages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublishYear() {
        return yop;
    }

    public void setPublishYear(int yop) {
        this.yop = yop;
    }

    public int getnPages() {
        return nPages;
    }

    public void setnPages(int nPages) {
        this.nPages = nPages;
    }
    
    public void showInfo() {
        System.out.printf(" | %-20s| %-8.1f VND | %-15s| %-15s| %4d | %5d pages|\n", name, price, author, publisher, yop, nPages);
    }

}
