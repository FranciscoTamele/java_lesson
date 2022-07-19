package interfaces;

import java.util.ArrayList;
import java.util.List;

public class Resources {

    public static List<Aluno> alunoList(){
        List<Aluno> alunoList = new ArrayList<>();

        Aluno aluno1 = new Aluno(9,(float)7.5);
        Aluno aluno2 = new Aluno(17,15);
        Aluno aluno3 = new Aluno(7,14);
        Aluno aluno4 = new Aluno(18,19);

        alunoList.add(aluno1);
        alunoList.add(aluno2);
        alunoList.add(aluno3);
        alunoList.add(aluno4);

        return alunoList;
    }

    public static void printList(String title, List<Aluno> alunoList){

        System.out.println("******************************* "+title+" ***********************************");

        for(Aluno aluno : alunoList){
            System.out.println(aluno);
        }

    }

}
