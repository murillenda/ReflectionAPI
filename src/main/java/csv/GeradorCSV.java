package csv;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GeradorCSV {

    private GeradorCSV(){}

    // Como precisamos ler a estrutura de uma determinada classe, passamos um Class<T>
    public static <T> void imprimir(Class<T> clazz, List<T> objetos) {
        Field[] propriedades = clazz.getDeclaredFields(); // Pegamos as propriedades da classe

        System.out.println(Arrays.stream(propriedades)
                .filter(field -> field.isAnnotationPresent(Campo.class)) // Pergunta se tem ou não a anotação campo
                .map(Field::getName) // Filtramos pelos nomes dos campos
                .collect(Collectors.joining(";")));

        objetos.forEach(GeradorCSV::imprimir);
    }

    private static void imprimir(Object objt) {
        Field[] propriedades = objt.getClass().getDeclaredFields();
        List<String> valores = new ArrayList<>();

        try {
            for (Field propriedade : propriedades) {
                if (propriedade.isAnnotationPresent(Campo.class)) {
                    // Pega a anotação Campo do propriedade
                    Campo anotacaoCampo = propriedade.getAnnotation(Campo.class);

                    propriedade.setAccessible(true); // Torna o acesso possível mesmo sendo private
                    Object resultado = propriedade.get(objt); // Pegamos o objeto

                    String resultadoString = resultado == null ? "" : resultado.toString(); // Pegamos o value da propriedade
                    // Setamos para uppercase os valores das propriedades que tem a anotação com maiusculo true
                    valores.add(anotacaoCampo.maiusculo() ? resultadoString.toUpperCase() : resultadoString);
                }
            }

            // mesma coisa que valores.stream.collect(Collection.joining(;));
            System.out.println(String.join(";", valores));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}