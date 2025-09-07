package br.ifsp.com.refl;

import br.ifsp.com.Book;
import br.ifsp.com.BookDTO;

import br.ifsp.com.Publisher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class TransformatorTest {
    Book book = new Book(1,"Morro dos Ventos Uivantes","Emily Brontë","Editora Globo", 50.99f,100);
    Publisher publisher = new Publisher("Editora Seguinte", "45192875000170");

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

    @Test
    public void shouldNotTransform() {
        Assertions.assertThrows(ClassNotFoundException.class, () -> {
            Transformator transformator = new Transformator();
            transformator.transform(publisher);
        });
    }

    @Test
    public void shouldTransformWhenSomeFieldIsNull() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Book bookSimple = new Book("Swimming In The Dark","Tomasz Jedrowski");

        Transformator transformator = new Transformator();
        BookDTO BookDTOSimple = transformator.transform(bookSimple);

        Assertions.assertEquals(bookSimple.getTitle(), BookDTOSimple.getTitle());
        Assertions.assertNull(BookDTOSimple.getPublisher());
    }
}