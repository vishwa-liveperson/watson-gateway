package watson.gateway.util.client;

import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//https://howtodoinjava.com/spring-boot2/resttemplate/spring-restful-client-resttemplate-example/#post-example
//https://howtodoinjava.com/spring-boot2/resttemplate/resttemplate-post-json-example/
@PropertySource("classpath:application.properties")
public class APIClientExchange {
    public static void main(String[] args) throws IOException {

        String url = "http://localhost:9002/hello";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("key", "keyValue");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        User user = new User();
        user.setName("Testing");
        user.setJob("dev");

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

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
