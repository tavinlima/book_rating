package br.ifsp.com.refl;

import br.ifsp.com.Book;
import br.ifsp.com.Rating;

public class ObjectToJsonTester {
    public static void main(String[] args) {
        Rating rating = new Rating(5.0d, "Gustavo", "Livro bom demais!! Recomendo.");
        Book book = new Book(2,"Fahrenheit 451","Ray Bradbury",rating,"Biblioteca Azul", 72.65f,451);
        ObjectToJson objectToJson = new ObjectToJson();

        System.out.println(objectToJson.transform(book));
    }
}
