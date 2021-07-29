package watson.gateway.client;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

//https://howtodoinjava.com/spring-boot2/resttemplate/spring-restful-client-resttemplate-example/#post-example
//https://howtodoinjava.com/spring-boot2/resttemplate/resttemplate-post-json-example/
@PropertySource("classpath:application.properties")
public class APIClient {
    public static void main(String[] args) throws IOException {

        String url = "http://localhost:9002/hello";

        final RequestCallback requestCallback = request -> {
            request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            String initialString = " {\"name\":\"Test\", \"job\" : \"dev\"}";
            InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
                IOUtils.copy(inputStream, request.getBody());
        };
        final RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setBufferRequestBody(false);
        restTemplate.setRequestFactory(requestFactory);
        ClientHttpResponse response = restTemplate.execute(url, HttpMethod.POST, requestCallback,
                new ResponseFromHeadersExtractor());

        System.out.println(response);

    }

    private static class ResponseFromHeadersExtractor implements ResponseExtractor<ClientHttpResponse> {

        @Override
        public ClientHttpResponse extractData(ClientHttpResponse response) {
            System.out.println("StringFromHeadersExtractor - response headers: " + response.getHeaders());
            return response;
        }
    }

    public static void main2(String[] args) throws IOException {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String uri = "http://localhost:9002/hello";
            User user = new User();
            user.setName("Testing");
            user.setJob("dev");
            Map<String, String> vars = new HashMap<String, String>();
            vars.put("user", "");
//            User returns = restTemplate.postForObject(uri, user, User.class, vars);
//            restTemplate.postForObject(uri, user, Void.class, vars);
            restTemplate.postForObject(uri, user, Void.class);
            System.out.println("Done");
//            System.out.println(returns);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
