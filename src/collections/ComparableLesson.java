package collections;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Truque implements Comparable<Truque>{

    private String nome;
    private String data;
    private Date date;

    public Truque(String data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
    public void buildDate(){
        try {
            this.date=format.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Truque truque) {
        return truque.getDate().compareTo(this.date);
    }
}


public class ComparableLesson{

    public static void main(String[] args) {

    }
}
