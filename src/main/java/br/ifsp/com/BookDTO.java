package br.ifsp.com;

public class BookDTO {
    private String title;
    private String author;
    private String publisher;
    private Float price;
    private RatingDTO rating;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public Float getPrice() {
        return price;
    }

    public RatingDTO getRating() {
        return rating;
    }
}
