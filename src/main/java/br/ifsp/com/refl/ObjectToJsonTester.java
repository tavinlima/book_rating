package br.ifsp.com.refl;

import br.ifsp.com.Book;

public class ObjectToJsonTester {
    public static void main(String[] args) {
        Book book = new Book(2,"Fahrenheit 451","Ray Bradbury","Biblioteca Azul", 72.65f,451);
        ObjectToJson objectToJson = new ObjectToJson();

        System.out.println(objectToJson.transform(book));
    }
}
