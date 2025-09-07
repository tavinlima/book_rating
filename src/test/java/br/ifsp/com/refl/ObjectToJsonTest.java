package br.ifsp.com.refl;

import br.ifsp.com.Book;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ObjectToJsonTest {
    private final ObjectMapper om = new ObjectMapper();

    @Test
    void shouldConvertObjToJson() throws Exception {
        //Arrange
        Book book = new Book(3,"Amêndoas","Won-pyung Sohn","Rocco", 40.82f,10140);
        ObjectToJson converter = new ObjectToJson();

        //Act
        String json = converter.transform(book);

        //Assert
        JsonNode node = om.readTree(json);
        Assertions.assertEquals("Amêndoas", node.get("title").asText());
        Assertions.assertEquals("Won-pyung Sohn", node.get("author").asText());
        Assertions.assertEquals("Rocco", node.get("publisher").asText());
        Assertions.assertEquals(40.82f, node.get("price").floatValue());
        //Assertions.assertEquals(40.28f, node.get("price").floatValue());

        Assertions.assertEquals(10140, node.get("stock").asInt());
    }

    @Test
    void shouldHandleNullFields() throws Exception {
        //Arrange
        Book bookSimple = new Book("Noites Brancas", "Fiódor Dostoiévski");
        ObjectToJson converter = new ObjectToJson();

        //Act
        String json = converter.transform(bookSimple);

        //Assert
        JsonNode node = om.readTree(json);
        Assertions.assertEquals("Noites Brancas", node.get("title").asText());
        Assertions.assertTrue(node.get("publisher").isNull());

            //Se o campo não existir
        Assertions.assertNull(node.get("movieName"));
    }
}
