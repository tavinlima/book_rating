package br.ifsp.com.refl;

import br.ifsp.com.Book;
import br.ifsp.com.BookDTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class TransformatorTest {
    Book book = new Book(1,"Morro dos Ventos Uivantes","Emily Brontë","Editora Globo", 50.99f,100);

    @Test
    public void shouldTransform() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Transformator transformator = new Transformator();
        BookDTO bookDTO = transformator.transform(book);

        Assertions.assertInstanceOf(BookDTO.class, bookDTO);
        Assertions.assertEquals(book.getTitle(), bookDTO.getTitle());
        Assertions.assertEquals(book.getAuthor(), bookDTO.getAuthor());
        Assertions.assertEquals(book.getPublisher(), bookDTO.getPublisher());
        Assertions.assertEquals(book.getPrice(), bookDTO.getPrice());
    }

}