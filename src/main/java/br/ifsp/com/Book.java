package br.ifsp.com;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private String publisher;
    private Float price;
    private Integer stock;

    public Book( Integer id, String title, String author, String publisher, Float price, Integer stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.stock = stock;
    }

    public Book() {
        this.id = 0;
        this.title = "";
        this.author = "";
        this.publisher = "";
        this.price = 0.00f;
        this.stock = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
