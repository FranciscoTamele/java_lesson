package enviroment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class LoadEnviroment {

    private static StringTokenizer tokenizer;

    public static Map<String,String> enviromentVariables() throws IOException {

        Map<String,String> variables=new HashMap<>();

        /**
         * Stream funcionam com listas podemos utilizar para armazenar dados, porem streams tem metodos que facilitam
         * a manipulacao dos dados. Exemplo: filtros
         */

        Path path = Paths.get(".env");
        Stream<String> stringStream = Files.lines(path);

        stringStream.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                tokenizer=new StringTokenizer(s,"=");
                while (tokenizer.hasMoreTokens()){
                    variables.put(tokenizer.nextToken(),tokenizer.nextToken());
                }
            }
        });

        return variables;
    }

}
