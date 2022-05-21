package nio;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryStreamLesson {

    public static void main(String[] args) {

        Path path = Paths.get("pastas\\nio");

        try {
            /**
             * A classe DirectoryStream percorre apenas o directorio actual, nao entra nas subpastas !!!
             * Por exemplo dentro da pasta1 tem um arquivo txt mas nao sera mostrado, por nao percorre subdirectorios
             */

            DirectoryStream<Path> stream = Files.newDirectoryStream(path);

            for(Path path1 : stream){
                System.out.println(path1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
