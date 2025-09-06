package br.ifsp.com;

import br.ifsp.com.refl.Transformator;

import java.lang.reflect.InvocationTargetException;

public class BookService {
    public BookDTO list() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Book book = new BookRepository().list();

        BookDTO bookDTO = new Transformator().transform(book);
        return bookDTO;
    }
}
