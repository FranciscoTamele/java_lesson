package nio;


import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

class BuscarArquivos extends SimpleFileVisitor<Path>{

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {

        if(path.getFileName().toString().endsWith(".htm") && basicFileAttributes.size()==0){
            System.out.println(path.getParent()+"\\"+path.getFileName());
            Files.delete(Paths.get(path.getParent().toString()+"\\"+path.getFileName()));
        }

//        if(basicFileAttributes.size()!=0){
//            System.out.println(path.getParent()+"\\"+path.getFileName());
//            System.out.println(basicFileAttributes.size());
//        }

        return FileVisitResult.CONTINUE;
    }
}

public class SimpleFileVisitorLesson {

    public static void main(String[] args) {

        /**
         * Na classe Files temos o metodo que Files.walkFileTree que recebe um Path e um objecto que tenha o metodo visitFile e essa instancia e
         * do SimpleFileVisitor
         * Ao passar por aquela instrucao, o metodo visitFile e chamado automacticamente
         */

        Path pathLesson = Paths.get("pastas");

        Path pathW3S = Paths.get("C:\\wamp64\\www\\websites\\w3c\\w3schools_offline_2022");

        try {

            Files.walkFileTree(pathW3S,new BuscarArquivos());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
