package string;

import com.weicoder.common.C;
import javafx.application.Platform;
import javafx.scene.control.TextField;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

public class StringMethodsLesson {

    public static void main(String[] args) throws ParseException {
//        splitMehod();
//        tokinezerMehod();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                TextField textField=new TextField();
                for(String css : textField.getStyleClass()){
                    System.out.println(css);
                }
            }
        });



//        String timestamp="2022-05-27 01:20:29";
//        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = dateFormat.parse(timestamp);
//
//        SimpleDateFormat dateFormat1=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        System.out.println(dateFormat1.format(date));

    }

    private static void splitMehod(){
        String value="Maputo|loja#1@gmail.com[#]Gaza|gaza@gmail.com[#]Inhambane|inhamb#ane@gmail.com";

        String localizacao[]=value.split("[#]");
        for(String local : localizacao){
            System.out.println(local);
        }
    }

    private static void tokinezerMehod(){
        String value="Maputo|loja#1@gmail.com[#]Gaza|gaza@gmail.com[#]Inhambane|inhamb#ane@gmail.com";

        StringTokenizer stringTokenizer=new StringTokenizer(value,"[#]",false);

        while(stringTokenizer.hasMoreTokens()){
            System.out.println(stringTokenizer.nextToken());
        }

    }

}
