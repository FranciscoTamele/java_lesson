package collections;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

class Truque implements Comparable<Truque>{

    private String nome;
    private String data;
    private Date date;

    public Truque(String data) {
        this.data = data;
        buildDate();
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
    public String toString() {
        return "Truque{" +
                ", date=" + date +
                '}';
    }

    @Override
    public int compareTo(Truque truque) {
        return truque.getDate().compareTo(this.date);
    }
}


public class ComparableLesson{

    public static void main(String[] args) {

        Truque truque=new Truque("29-07-1996");
        Truque truque1=new Truque("04-04-2001");
        Truque truque2=new Truque("16-01-1980");

        List<Truque> list=new ArrayList<>();

        list.add(truque);
        list.add(truque1);
        list.add(truque2);

        Collections.sort(list);

        System.out.println(list);


    }
}
