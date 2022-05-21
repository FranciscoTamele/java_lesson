package httpclient;

import enviroment.LoadEnviroment;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientLesson {

    public static void main(final String[] args) throws IOException, URISyntaxException {
//        sendGetRequest();
        sendPostRequestJson();
    }

    /**
     * Metodo que vai executar a requisicao GET
     */
    private static void sendGetRequest() throws IOException, URISyntaxException {

        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {

            HttpGet httpGet = new HttpGet(LoadEnviroment.enviromentVariables().get("URLMULTIAPI").concat("/api/mozta/vendas"));

            URI uri = new URIBuilder(httpGet.getUri())
                    .addParameter("provincia", "1")
                    .addParameter("categoria", "1")
                    .addParameter("subcategoria","9")
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

    public static void sendPostRequestJson() throws IOException, URISyntaxException {

        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {

            HttpPost httpPost = new HttpPost(LoadEnviroment.enviromentVariables().get("URLMOZTA").concat("/api/v1/produto"));

            List<NameValuePair> values=new ArrayList<>();
            values.add(new BasicNameValuePair("nome","Bolsa Bolsa"));
            values.add(new BasicNameValuePair("descricao","Bolsa descricao"));
            values.add(new BasicNameValuePair("preco","1000"));
            values.add(new BasicNameValuePair("idlojacategsub","85"));

            UrlEncodedFormEntity forms=new UrlEncodedFormEntity(values);

            System.out.println(forms.getContent());

            httpPost.setEntity(forms);

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
            final String responseBody = httpclient.execute(httpPost, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        }

    }

}
