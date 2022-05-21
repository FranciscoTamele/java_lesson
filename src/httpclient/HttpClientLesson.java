package httpclient;

import enviroment.LoadEnviroment;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HttpClientLesson {

    public static void main(final String[] args) throws IOException, URISyntaxException {
//        sendGetRequest();
//        sendPostRequestJson();
        sendPostRequestMultipartFormData();
//        sendPutRequestMultipartFormData();
//        sendDeleteRequest();
    }

    /**
     * Metodo metodo responsavel por executar a requisicao GET
     */
    private static void sendGetRequest() throws IOException, URISyntaxException {

        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {

            HttpGet httpGet = new HttpGet(LoadEnviroment.enviromentVariables().get("URLMULTIAPI").concat("/api/mozta/vendas"));

            URI uri = new URIBuilder(httpGet.getUri())
                    .addParameter("provincia", "1")
                    .addParameter("categoria", "1")
                    .addParameter("subcategoria", "9")
                    .build();

            httpGet.setUri(uri);

            System.out.println("Executing request " + httpGet.getMethod() + " " + httpGet.getUri());

            // Create a custom response handler
            final HttpClientResponseHandler<String> responseHandler = new HttpClientResponseHandler<String>() {

                @Override
                public String handleResponse(final ClassicHttpResponse response) throws IOException {

                    final int status = response.getCode();
                    if (status >= HttpStatus.SC_SUCCESS && status < HttpStatus.SC_REDIRECTION) {
                        final HttpEntity entity = response.getEntity();
                        try {
                            return entity != null ? EntityUtils.toString(entity) : null;
                        } catch (final ParseException ex) {
                            throw new ClientProtocolException(ex);
                        }
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }

                }

            };
            final String responseBody = httpclient.execute(httpGet, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        }

    }

    /**
     * Metodo responsavel por executar a requisicao POST como corpo objecto JSON
     */
    private static void sendPostRequestJson() throws IOException {

        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {

            HttpPost httpPost = new HttpPost(LoadEnviroment.enviromentVariables().get("URLMOZTA").concat("/api/v1/produto"));
            httpPost.setHeader("Accept", "*/*");

            List<NameValuePair> values = new ArrayList<>();
            values.add(new BasicNameValuePair("nome", "Bolsa Bolsa"));
            values.add(new BasicNameValuePair("descricao", "Bolsa descrição"));
            values.add(new BasicNameValuePair("preco", "1000"));
            values.add(new BasicNameValuePair("idlojacategsub", "85"));

            // E necessario definir charset pois retornaria erro, ao inserir corpo de caracteres com acentuacao.
            UrlEncodedFormEntity forms = new UrlEncodedFormEntity(values, Charset.forName("UTF-8"));

            httpPost.setEntity(forms);

            // Create a custom response handler
            final HttpClientResponseHandler<String> responseHandler = new HttpClientResponseHandler<String>() {

                @Override
                public String handleResponse(final ClassicHttpResponse response) throws IOException {

                    final int status = response.getCode();
                    if (status >= HttpStatus.SC_SUCCESS && status < HttpStatus.SC_REDIRECTION) {
                        final HttpEntity entity = response.getEntity();
                        try {
                            return entity != null ? EntityUtils.toString(entity, StandardCharsets.UTF_8) : null;
                        } catch (final ParseException ex) {
                            throw new ClientProtocolException(ex);
                        }
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }

                }

            };

            final String responseBody = httpclient.execute(httpPost, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);

        }

    }

    /**
     * Metodo responsavel por executar a requisicao POST multipart/form-data
     */
    private static void sendPostRequestMultipartFormData() throws IOException {

        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {

            HttpPost httpPost = new HttpPost(LoadEnviroment.enviromentVariables().get("URLMULTIAPI").concat("/api/mozta/vendas"));
            httpPost.setHeader("Accept", "*/*");

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("nome", "Minha Venda Post",ContentType.create("text","utf8"));
            builder.addTextBody("preco", "3000");
            builder.addTextBody("descricao", "Descrição",ContentType.create("text","utf8"));
            builder.addTextBody("localizacao", "Localização",ContentType.create("text","utf8"));
            builder.addTextBody("provincia", "1");
            builder.addTextBody("categoria", "1");
            builder.addTextBody("subcategoria", "9");
            builder.addTextBody("usuario_id", "1");
            builder.addTextBody("usuario_nome", "Usuario Teste",ContentType.create("text","utf8"));
            builder.addTextBody("usuario_telefone", "847777777");
            builder.addBinaryBody("file", new File("pastas/resources/banner.jpg"), ContentType.APPLICATION_OCTET_STREAM, "banner.jpg");
            HttpEntity multipart = builder.build();
            httpPost.setEntity(multipart);

            // Create a custom response handler
            final HttpClientResponseHandler<String> responseHandler = new HttpClientResponseHandler<String>() {

                @Override
                public String handleResponse(final ClassicHttpResponse response) throws IOException {

                    final int status = response.getCode();
                    if (status >= HttpStatus.SC_SUCCESS && status < HttpStatus.SC_REDIRECTION) {
                        final HttpEntity entity = response.getEntity();
                        try {
                            return entity != null ? EntityUtils.toString(entity, StandardCharsets.UTF_8) : null;
                        } catch (final ParseException ex) {
                            throw new ClientProtocolException(ex);
                        }
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }

                }

            };

            final String responseBody = httpclient.execute(httpPost, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);

        }

    }

    /**
     * Metodo responsavel por executar a requisicao PUT
     * O corpo da requisicao segue a mesma logica como em POST para json ou multipart/form-data apenas o metodo sera diferente
     */
    private static void sendPutRequestMultipartFormData() throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut("URL");

        /**'
         * Nessa requisicao pode ser necessario adicionar parametros gets, o como adicionar esta no metodo sendGetRequest(),
         * e segue a mesma logica da requisicao
         */

    }

    /**
     * Metodo responsavel em executar a requisicao DELETE
     */
    private static void sendDeleteRequest() {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete("URL");

        /**'
         * Nessa requisicao pode ser necessario adicionar parametros gets, o como adicionar esta no metodo sendGetRequest(),
         * e segue a mesma logica da requisicao
         */


    }


}
