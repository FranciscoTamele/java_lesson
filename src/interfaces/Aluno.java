package interfaces;

public class Aluno {

    public static final String EXCLUIDO="Excluido";
    public static final String ADMITIDO="Admitido";
    public static final String DISPENSADO="Dispensado";

    private float nota1;
    private float nota2;
    private float media;
    private String resultado;

    public Aluno() {
    }

    public Aluno(float nota1, float nota2) {
        this.nota1 = nota1;
        this.nota2 = nota2;
    }

    public float getNota1() {
        return nota1;
    }

    public void setNota1(float nota1) {
        this.nota1 = nota1;
    }

    public float getNota2() {
        return nota2;
    }

    public void setNota2(float nota2) {
        this.nota2 = nota2;
    }

    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nota1=" + nota1 +
                ", nota2=" + nota2 +
                ", media=" + media +
                ", resultado='" + resultado + '\'' +
                '}';
    }
}
