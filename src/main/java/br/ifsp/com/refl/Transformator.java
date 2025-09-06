package br.ifsp.com.refl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Transformator {

    public <I,O> O transform(I input) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //Recupera a classe recebida como argumento
        Class<?> source = input.getClass();

        //Busca por uma classe com o nome da source + sufixo DTO
        Class<?> target = Class.forName(source + "DTO") ;

        //Localiza o método construtor da classe de destino e cria uma nova instância
        O targetClass = (O) target.getDeclaredConstructor().newInstance();

        //tipo Field -> Faz parte da API Reflection e representa os atributos da classe
        //Retorna um array com todos os campos declarados da classe de entrada
        Field[] sourceFields = source.getDeclaredFields();

        //Retorna um array com todos os campos declarados da classe de destino

        Field[] targetFields = target.getDeclaredFields();

        Arrays.stream(sourceFields).forEach(sourceField ->
                Arrays.stream(targetFields).forEach(targetField -> {
                    validate(sourceField, targetField);
                    try {
                        targetField.set(targetClass, sourceField.get(input));
                    }
                    catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }));


        //Retorna a instância criada da classe de destino
        return targetClass;
    }

    public void validate(Field sourceField, Field targetField) {
        if(sourceField.getName().equals(targetField.getName())
            && sourceField.getType().equals(targetField.getType())) {
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
        }
    }
}
