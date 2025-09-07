package br.ifsp.com.refl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;

public class Transformator {
    private static final Set<Class<?>> SIMPLE_TYPES = Set.of(
            String.class,
            Boolean.class,
            Character.class,
            Byte.class,
            Short.class,
            Integer.class,
            Long.class,
            Float.class,
            Double.class
    );

    public <I, O> O transform(I input) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //Recupera a classe recebida como argumento
        Class<?> source = input.getClass();

        //Busca por uma classe com o nome da source + sufixo DTO
        Class<?> target = Class.forName(source.getName() + "DTO");

        //Localiza o método construtor da classe de destino e cria uma nova instância
        O targetClass = (O) target.getDeclaredConstructor().newInstance();

        //tipo Field -> Faz parte da API Reflection e representa os atributos da classe
        //Retorna um array com todos os campos declarados da classe de entrada
        Field[] sourceFields = source.getDeclaredFields();

        //Retorna um array com todos os campos declarados da classe de destino

        Field[] targetFields = target.getDeclaredFields();

        for(Field sourceField : sourceFields) {
            for(Field targetField : targetFields) {
                if(validate(sourceField, targetField)){
                    try {
                        Object sourceValue = sourceField.get(input);

                        if(sourceValue == null) {
                            targetField.set(targetClass, null);
                            continue;
                        }

                        if (isSimple(sourceField.getType()))
                            targetField.set(targetClass, sourceValue);
                        else {
                            Object nested = transform(sourceValue);
                            targetField.set(targetClass, nested);
                        }
                    } catch (IllegalAccessException | NoSuchMethodException |
                             InvocationTargetException | InstantiationException e) {
                        System.out.println("Erro ao realizar mapeamento: " + e.getMessage());
                        e.printStackTrace();
                    } catch (ClassNotFoundException c) {
                        System.out.println("Não foi encontrada uma DTO relacionada: " + c.getMessage());
                    }
                }
            }
        }

//        Arrays.stream(sourceFields).forEach(sourceField ->
//                Arrays.stream(targetFields).forEach(targetField -> {
//                    if(validate(sourceField, targetField)){
//                        try {
//                            Object sourceValue = sourceField.get(input);
//
//                            if(sourceValue == null) {
//                                targetField.set(targetClass, null);
//                                continue;
//                            }
//
//                            if (isSimple(sourceField.getType()))
//                                targetField.set(targetClass, sourceValue);
//                            else {
//                                Object nested = transform(sourceValue);
//                                targetField.set(targetClass, nested);
//                            }
//                        } catch (IllegalAccessException | ClassNotFoundException | NoSuchMethodException |
//                                 InvocationTargetException | InstantiationException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                }));

        //Retorna a instância criada da classe de destino
        return targetClass;
    }

    public boolean validate(Field sourceField, Field targetField) {

        boolean sameName = sourceField.getName().equals(targetField.getName());
        boolean sameType = sourceField.getType().equals(targetField.getType());

        // Pega o nome simples da classe (sem pacote)
        String sourceTypeName = sourceField.getType().getSimpleName();   // ex.: Endereco
        String targetTypeName = targetField.getType().getSimpleName();   // ex.: EnderecoDTO

        // Verifica se o target é o mesmo nome + "DTO"
        boolean findDto = targetTypeName.equals(sourceTypeName + "DTO");

        if (findDto || (sameName && sameType)) {
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
            return true;
        }
        return false;
    }


    // Função auxiliar para decidir o que é "simples"
    private boolean isSimple(Class<?> type) {
        return type.isPrimitive()
                || SIMPLE_TYPES.contains(type)
                || Number.class.isAssignableFrom(type)
                || type.isEnum();
    }
}
