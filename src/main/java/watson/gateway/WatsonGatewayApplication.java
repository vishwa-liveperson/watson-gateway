package watson.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import watson.gateway.domain.Credentials;

@SpringBootApplication
public class WatsonGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatsonGatewayApplication.class, args);
	}


	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public Credentials getCredentials(){
		return new Credentials();
	}
}
