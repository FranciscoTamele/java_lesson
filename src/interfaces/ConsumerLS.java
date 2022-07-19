package interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import static interfaces.Aluno.*;

class AvaliarResultado implements Consumer<Aluno> {

    @Override
    public void accept(Aluno aluno) {
        aluno.setMedia((aluno.getNota1()+aluno.getNota2())/2);

        if(aluno.getMedia()<9.5){
            aluno.setResultado(EXCLUIDO);
        }else if(aluno.getMedia()>=13.5){
            aluno.setResultado(DISPENSADO);
        }else{
            aluno.setResultado(ADMITIDO);
        }
    }
}

class ComsumerLS {

    public static void main(String[] args) {

        /**
         * A classe consumer permite-nos realizar uma determinada accao sobre todos os objectos de uma Lista, no objecto List
         * possui um metodo List.forEach(*) que recebe um objecto Consumer.
         *
         * Esse objecto pode-se criar de uma classe externa ou apenas instanciar um objecto directamente como no exemplo abaixo.
         *
         */

        Consumer<Aluno> mediaR = new Consumer<Aluno>() {
            @Override
            public void accept(Aluno aluno) {

                aluno.setMedia((aluno.getNota1()+aluno.getNota2())/2);

                if(aluno.getMedia()<9.5){
                    aluno.setResultado(EXCLUIDO);
                }else if(aluno.getMedia()>=13.5){
                    aluno.setResultado(DISPENSADO);
                }else{
                    aluno.setResultado(ADMITIDO);
                }

            }
        };

        List<Aluno> alunoList = Resources.alunoList();

        Resources.printList("Antes do forEach", alunoList);

        alunoList.forEach(mediaR);

        Resources.printList("Depois do forEach", alunoList);

    }

}
