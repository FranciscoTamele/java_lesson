package resources_b;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourcesLesson {

    /**
     * Regras para criar arquivos para gravar mensagens do sistema
     * nome_lingua_pais.properties
     *
     * Ex: Arquivo para depositar mensagens da lingua ingles americano : messages_en_US.properties
     *
     * Para comentar nos arquivos .properties usa-se '#' ou '!'
     *
     * Heranca
     * Como fazer para aquelas mensagens que nao mudam independe da lingua?
     *
     * Como a criacao dos arquivos .properties seguem o padrao nome_lingua_pais.properties
     * se criarmos um arquivo sem definir a lingua nem pais os outros arquivos que possuem
     * o mesmo nome base herdarao as mensagens.
     * Ex:
     * Arquivo 1: messages.properties
     * Arquivo 2: messages_en_US.properties
     * Arquivo 3: messages_pt_BR.properties
     *
     * Os arquivos 2 e 3 herdarao as mensagens que estao no arquivo 1, pois possuem o nome base igual e
     * ainda mais porque no arquivo 1 nao foi definido a lingua e o pais.
     *
     */

    public static void main(String[] args) {
        ResourceBundle rs = ResourceBundle.getBundle("messages",new Locale("en","US"));
        System.out.println(rs.getString("saudar"));
        System.out.println(rs.getString("video"));
    }

}
