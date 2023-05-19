package csv;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Indica onde essa anotação Campo irá ser usada
@Target(ElementType.FIELD)
// @Target( { ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE(Classes ou interfaces...)} )

// Indica a política de retenção da anotação, ou seja, quanto tempo essa informação deve ficar retida para algum programa
// que deseja ler informações de alguma classe ou de algum membro
// Por exemplo, se colocarmos RetentionPolicy.SOURCE, essa anotação pode ser lida pelo código fonte, mas depois da compilação
// ela deixa de existir
@Retention(RetentionPolicy.RUNTIME)

// Tipo especial de interface que permite criar anotações customizadas
// não é uma interface, é uma anotação
public @interface Campo {

    // Propriedades das anotações são chamados de métodos de anotação
    // O retorno deles também são limitados a tipos primitivos, String, Enums, alguma anotação ou um array desses tipos
    boolean maiusculo() default false; // Setamos o  default de false desse método de anotação

}
