package interfaces;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

class RemoveCd implements Predicate<Aluno>{

    @Override
    public boolean test(Aluno aluno) {
        return aluno.getNota1()<10;
    }

}

public class PredicateLS {

    public static void main(String[] args) {

        List<Aluno> alunoList = Resources.alunoList();

        /**
         * Predicate serve para remover objectos de uma lista caso se satisfaca uma determinada condicao seja satisfeita
         */

        /**
         * Os predicate abaixo removem todos alunos com nota1<10
         */

        // Instanciando um objecto Predicate
        Predicate<Aluno> removeObj =new Predicate<Aluno>() {
            @Override
            public boolean test(Aluno aluno) {
                return aluno.getNota1()<10;
            }
        };

        // Utilizando classe externa
        RemoveCd removeCd = new RemoveCd();

        Resources.printList("Antes da remocao", alunoList);

        alunoList.removeIf(removeCd);

        Resources.printList("Depois da remocao (nota1<10)", alunoList);

    }

}
