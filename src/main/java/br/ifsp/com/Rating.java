package br.ifsp.com;

public class Rating {
    private Integer id;
    private Double rating;
    private String Author;
    private String comment;

    public Rating(Double rating, String Author, String comment) {
        this.rating = rating;
        this.Author = Author;
        this.comment = comment;
    }

    public Rating() {
        this.rating = 0.0;
        this.Author = "";
        this.comment = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        this.Author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
