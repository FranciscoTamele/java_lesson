package fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

class Pessoa{

    private String nome;
    private int idade;
    private List<Pessoa> lista;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public List<Pessoa> getLista() {
        return lista;
    }

    public void setLista(List<Pessoa> lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", lista=" + lista +
                '}';
    }
}

public class FastJSONLesson {

    /**
     * fastjson e uma biblioteca que auxilia-nos a criar objectos e array json
     * abaixo constam alguns tutotias
     */

    public static void main(String[] args) {
        mapToJsonFormat();
//        jsonToObjectClass();
    }

    private static void mapToJsonFormat(){

        Map<String,Object> map=new HashMap<>();
        map.put("nome","Java8");
        map.put("idade",String.valueOf(20));
        map.put("lista",new ArrayList<>());

        String json = JSON.toJSONString(map);
        System.out.println(json);

    }

    private static void jsonToObjectClass(){

        String stringJsonObject = "{\"idade\":\"20\",\"nome\":\"C++\"}";
        String stringJsonArray = "[{\"idade\":\"20\",\"nome\":\"Java8\"},{\"idade\":\"15\",\"nome\":\"PHP7.34\"}]";
        String stringjsonobject_attr_array = "{\"lista\":[],\"idade\":\"20\",\"nome\":\"Java8\"}";

        // Converte string json em Object json
        JSONObject jsonObject= JSON.parseObject(stringJsonObject);
//        System.out.println(jsonObject.get("nome"));

        JSONArray jsonArray= JSON.parseArray(stringJsonArray);
        for(int i=0;i<jsonArray.size();i++){
//            System.out.println(jsonArray.get(i).toString());
        }

        // Converte string json em uma object class NB: Os atributos nao definidos ficam como null
        Pessoa pessoa =(Pessoa) JSON.parseObject(stringJsonObject,Pessoa.class);
//        System.out.println(pessoa);

        // Converte string json em uma Lista
        List<Pessoa> pessoaList =(List<Pessoa>) JSON.parseArray(stringJsonArray,Pessoa.class);
        for(Pessoa pessoa1 : pessoaList){
//            System.out.println(pessoa1.getNome());
        }

        // Converte string json em um Pessoa Object NB: Os atributos nao definidos ficam como null
        Pessoa pessoa1 = JSON.parseObject(stringjsonobject_attr_array, Pessoa.class);
//        System.out.println(pessoa1);

        // Converte string json em um Map Object
        Map<String,Object> map = JSON.parseObject(stringjsonobject_attr_array, Map.class);
//        System.out.println(map.get("lista"));

    }

}
