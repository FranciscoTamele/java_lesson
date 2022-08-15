package heroku_test;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class Client {

    private static int controller=0;

    /**
     * Metodo metodo responsavel por executar a requisicao GET
     */
    public void sendGetRequest() throws IOException, URISyntaxException {

        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {

            HttpGet httpGet = new HttpGet("https://moztastore.herokuapp.com/api/v1/categorias/1");

            // Create a custom response handler
            final HttpClientResponseHandler<String> responseHandler = new HttpClientResponseHandler<String>() {

                @Override
                public String handleResponse(final ClassicHttpResponse response) throws IOException {

                    final int status = response.getCode();
                    if (status >= HttpStatus.SC_SUCCESS && status < HttpStatus.SC_REDIRECTION) {
                        controller++;
                        try {
                            return EntityUtils.toString(response.getEntity());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                    return null;
                }

            };
            final String responseBody = httpclient.execute(httpGet, responseHandler);
            System.out.println(controller+" --------- "+responseBody);
        }

    }


}
