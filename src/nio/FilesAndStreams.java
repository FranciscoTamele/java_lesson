package nio;

import java.io.*;

public class FilesAndStreams {

    public static void main(String[] args) {


        // Criar novo ficheiro e metodos principais
//        initLessonFile();

        // managePaths and Files
        createDirectories();

    }

    private static void initLessonFile(){

        File file =new File("config.mozta");
        try {

            // Criar ficheiro
            file.createNewFile();

            // Deletar ficheiro
//            file.delete();

            // Retorna o caminho absoluto do arquivo
            System.out.println(file.getAbsolutePath());

            // Retorna resultado se o arquivo existe ou nao
            System.out.println(file.exists());

            System.out.println(file.renameTo(new File("config.txt")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createDirectories(){

        File dirConfig = new File("C:\\Users\\Tamele\\Documents\\MozTa");
        File fileConfig = new File(dirConfig,"config.mozta");

        try {

            if(!dirConfig.exists()){

                // Retorna um boolean informando-se criou o directorio ou nao
                // Caso o directorio ja exista, retorna false pois nao criara ou novo, mas carregara o existente
                dirConfig.mkdirs();

            }else if(!fileConfig.exists()){

                // Retorna um boolean informando se criou ou nao o arquivo
                // Caso ja exista um arquivo com mesmo nome, retorna false porque nao criara um novo arquivo mas carregara o existente
                fileConfig.createNewFile();
            }

            FileOutputStream fileOutputStream =new FileOutputStream(fileConfig);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

            outputStream.writeObject(new Integer(300));
            outputStream.flush();
            outputStream.close();

            fileConfig.setWritable(false);

            FileInputStream fileInputStream = new FileInputStream(fileConfig);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            try {
                System.out.println(objectInputStream.readObject());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }


    }

}
