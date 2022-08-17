package data;

public class Book {
    private String name;
    private int price;
    private String author;
    private String publisher;
    private int publishYear;
    private String type;
    private int nPages;
    
    //Constructor
    public Book(String name, int price, String author, String publisher, int publishYear, String type, int nPages) {
        this.name = name;
        this.price = price;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.type = type;
        this.nPages = nPages;
    }

    public void showBookInfo() {
        System.out.println("Name: " + name);
        System.out.println("Price: " + price + " VND");
        System.out.println("Author: " + author);
        System.out.println("Publisher: " + publisher);
        System.out.println("Year of Publication: " + publishYear);
        System.out.println("Book type: " + type);
        System.out.println("Number of pages: " + nPages);        
    }
    
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public String getType() {
        return type;
    }

    public int getNPages() {
        return nPages;
    }
}
